package snake.menu;

import snake.Game;
import snake.fileio.Configuration;
import snake.types.GameColors;
import snake.types.TileTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

/**
 * The main menu of the game.
 *
 * @author samliu
 */
public class Menu extends JPanel implements ActionListener {

    private Image bgImage;
    private JButton startButton,customizationButton;
    private JFrame mainFrame;

    /**
     * Creates a new main menu.
     * 
     * @param mainFrame The main JFrame of the game
     */
    public Menu(JFrame mainFrame) {
        this.mainFrame = mainFrame;

        this.setLayout(null);
        this.setPreferredSize(new Dimension(512, 544));

        // Loading the custom font
        // https://stackoverflow.com/questions/22081586
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/assets/PressStart2P-Regular.ttf")));
            font = font.deriveFont(14f);
        } catch (FontFormatException | IOException | NullPointerException e) {
            System.err.println("Error loading custom font.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Loading the background image
        // https://stackoverflow.com/questions/1466240
        try {
            bgImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/menus/empty.png"))).getImage();
        } catch (NullPointerException e) {
            System.err.println("Error loading background image.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        JLabel heading = new JLabel("Two Player");
        heading.setFont(font);
        heading.setForeground(GameColors.YELLOW);
        heading.setBounds(96, 72, 256, 32);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setVerticalAlignment(SwingConstants.CENTER);
        this.add(heading);

        JLabel title = new JLabel("Snake");
        title.setFont(font.deriveFont(46f));
        title.setForeground(GameColors.GREEN);
        title.setBounds(0, 96, 512, 64);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        this.add(title);

        JLabel subtitle = new JLabel("By Sam Liu");
        subtitle.setFont(font);
        subtitle.setForeground(GameColors.BLUE);
        subtitle.setBounds(160, 152, 256, 32);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setVerticalAlignment(SwingConstants.CENTER);
        this.add(subtitle);

        startButton = new HoverButton("START", GameColors.BG, GameColors.BORDER);
        startButton.setFont(font.deriveFont(16f));
        startButton.setForeground(GameColors.RED);
        startButton.setBackground(GameColors.BG);
        startButton.setBorder(BorderFactory.createLineBorder(GameColors.BORDER, 2));// https://www.tutorialspoint.com/how-to-change-button-border-in-java-swing
        startButton.setBounds(128, 320, 256, 64);
        startButton.addActionListener(this);
        this.add(startButton);

        customizationButton = new HoverButton("CUSTOMIZATION", GameColors.BG, GameColors.BORDER);
        customizationButton.setFont(font.deriveFont(16f));
        customizationButton.setForeground(GameColors.BLUE);
        customizationButton.setBackground(GameColors.BG);
        customizationButton.setBorder(BorderFactory.createLineBorder(GameColors.BORDER, 2));// https://www.tutorialspoint.com/how-to-change-button-border-in-java-swing
        customizationButton.setBounds(128, 416, 256, 64);
        customizationButton.addActionListener(this);
        this.add(customizationButton);
    }

    // To draw the background image
    // https://stackoverflow.com/questions/1466240
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, this);
        } else {
            g.setColor(Color.BLACK);
        }

        int p1Type = Configuration.getP1Type();
        int p2Type = Configuration.getP2Type();

        // Paint the player 1 snake
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.HEAD_N).paintIcon(this, g, 32, 160);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_NS).paintIcon(this, g, 32, 192);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_NE).paintIcon(this, g, 32, 224);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_EW).paintIcon(this, g, 64, 224);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_SW).paintIcon(this, g, 96, 224);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_WN).paintIcon(this, g, 96, 256);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_ES).paintIcon(this, g, 64, 256);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_NS).paintIcon(this, g, 64, 288);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.TAIL_S).paintIcon(this, g, 64, 320);

        // Paint the player 2 snake
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.HEAD_W).paintIcon(this, g, 384, 224);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_SW).paintIcon(this, g, 416, 224);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_NS).paintIcon(this, g, 416, 256);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_NE).paintIcon(this, g, 416, 288);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_SW).paintIcon(this, g, 448, 288);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_NS).paintIcon(this, g, 448, 320);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_WN).paintIcon(this, g, 448, 352);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_NE).paintIcon(this, g, 416, 352);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.TAIL_N).paintIcon(this, g, 416, 320);

        // Paint the apple
        TileTypes.TILE_IMAGES.get(TileTypes.APPLE).paintIcon(this, g, 320, 192);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Start the game
            Game game = new Game(mainFrame);
            mainFrame.remove(this);
            mainFrame.add(game);
            mainFrame.pack();
            game.start();
        } else if (e.getSource() == customizationButton) {
            // Open the customization menu
            Customization customization = new Customization(mainFrame);
            mainFrame.remove(this);
            mainFrame.add(customization);
            mainFrame.pack();
        }
    }
}
