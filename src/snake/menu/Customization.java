package snake.menu;

import snake.fileio.Configuration;
import snake.types.GameColors;
import snake.types.TileTypes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;

/**
 * Menu to customize the color of the snakes.
 * 
 * @author samliu
 */
public class Customization extends JPanel implements ActionListener {

    private JFrame mainFrame;
    private Image bgImage;
    private JLabel title, p1Title, p2Title;
    private JButton p1Left, p1Right, p2Left, p2Right;
    private HoverButton back;
    private final int[] TYPE_CYCLE = {TileTypes.RED_SNAKE, TileTypes.YELLOW_SNAKE, TileTypes.GREEN_SNAKE, TileTypes.BLUE_SNAKE, TileTypes.PURPLE_SNAKE};

    /**
     * Creates a new customization menu.
     *
     * @param mainFrame The main JFrame of the game
     */
    public Customization(JFrame mainFrame){
        this.setPreferredSize(new Dimension(512, 544));
        this.setLayout(null);

        this.setBackground(GameColors.BG);

        this.mainFrame = mainFrame;

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

        title = new JLabel("Customization");
        title.setBounds(0, 64, 512, 64);
        title.setFont(font.deriveFont(28f));
        title.setForeground(GameColors.BLUE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        add(title);

        p1Title = new JLabel("Player 1");
        p1Title.setBounds(64, 160, 160, 32);
        p1Title.setFont(font.deriveFont(18f));
        p1Title.setForeground(GameColors.getColorFromType(Configuration.getP1Type()));
        p1Title.setHorizontalAlignment(SwingConstants.CENTER);
        p1Title.setVerticalAlignment(SwingConstants.CENTER);
        add(p1Title);

        p2Title = new JLabel("Player 2");
        p2Title.setBounds(288, 160, 160, 32);
        p2Title.setFont(font.deriveFont(18f));
        p2Title.setForeground(GameColors.getColorFromType(Configuration.getP2Type()));
        p2Title.setHorizontalAlignment(SwingConstants.CENTER);
        p2Title.setVerticalAlignment(SwingConstants.CENTER);
        add(p2Title);

        p1Left = new JButton();
        p1Left.setBounds(64, 288, 32, 32);
        p1Left.setIcon(TileTypes.TILE_IMAGES.get(TileTypes.ARROW_LEFT));
        p1Left.setBorder(BorderFactory.createEmptyBorder());
        p1Left.addActionListener(this);
        add(p1Left);

        p1Right = new JButton();
        p1Right.setBounds(192, 288, 32, 32);
        p1Right.setIcon(TileTypes.TILE_IMAGES.get(TileTypes.ARROW_RIGHT));
        p1Right.setBorder(BorderFactory.createEmptyBorder());
        p1Right.addActionListener(this);
        add(p1Right);

        p2Left = new JButton();
        p2Left.setBounds(288, 288, 32, 32);
        p2Left.setIcon(TileTypes.TILE_IMAGES.get(TileTypes.ARROW_LEFT));
        p2Left.setBorder(BorderFactory.createEmptyBorder());
        p2Left.addActionListener(this);
        add(p2Left);

        p2Right = new JButton();
        p2Right.setBounds(416, 288, 32, 32);
        p2Right.setIcon(TileTypes.TILE_IMAGES.get(TileTypes.ARROW_RIGHT));
        p2Right.setBorder(BorderFactory.createEmptyBorder());
        p2Right.addActionListener(this);
        add(p2Right);

        back = new HoverButton("BACK", GameColors.BORDER);
        back.setBounds(192, 416, 128, 64);
        back.setFont(font.deriveFont(16f));
        back.setForeground(GameColors.GREEN);
        back.setBackground(GameColors.BG);
        back.setBorder(BorderFactory.createLineBorder(GameColors.BORDER, 2));
        back.addActionListener(this);
        add(back);
    }

    // To draw the background image
    // https://stackoverflow.com/questions/1466240
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, this);

        int p1Type = Configuration.getP1Type();
        int p2Type = Configuration.getP2Type();

        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.HEAD_N).paintIcon(this, g, 128, 224);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_NS).paintIcon(this, g, 128, 256);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_NS).paintIcon(this, g, 128, 288);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.BODY_NS).paintIcon(this, g, 128, 320);
        TileTypes.TILE_IMAGES.get(p1Type + TileTypes.TAIL_S).paintIcon(this, g, 128, 352);

        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.HEAD_N).paintIcon(this, g, 352, 224);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_NS).paintIcon(this, g, 352, 256);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_NS).paintIcon(this, g, 352, 288);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.BODY_NS).paintIcon(this, g, 352, 320);
        TileTypes.TILE_IMAGES.get(p2Type + TileTypes.TAIL_S).paintIcon(this, g, 352, 352);

    }

    /**
     * Get the index of a type in the cycle.
     *
     * @param type the type
     */
    private int indexOfType(int type) {
        for (int i = 0; i < TYPE_CYCLE.length; i++) {
            if (TYPE_CYCLE[i] == type) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Makes a index wrap.
     * Example with length 5: -1 -> 5, 6 -> 1
     *
     * @param index the index
     * @return the wrapped index
     */
    private int indexToWrap(int index, int length) {
        if (index >= length) {
            return index % length;
        } else if (index < 0) {
            return length + (index % length);
        } else {
            return index;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int p1Type = Configuration.getP1Type();
        int p2Type = Configuration.getP2Type();
        int p1Index = indexOfType(p1Type);
        int p2Index = indexOfType(p2Type);

        // Cycle the snake colors
        if (e.getSource() == p1Left) {
            p1Index = indexToWrap(p1Index - 1, TYPE_CYCLE.length);
            if (p1Index == p2Index) {
                p1Index = indexToWrap(p1Index - 1, TYPE_CYCLE.length);
            }
        } else if (e.getSource() == p1Right) {
            p1Index = indexToWrap(p1Index + 1, TYPE_CYCLE.length);
            if (p1Index == p2Index) {
                p1Index = indexToWrap(p1Index + 1, TYPE_CYCLE.length);
            }
        } else if (e.getSource() == p2Left) {
            p2Index = indexToWrap(p2Index - 1, TYPE_CYCLE.length);
            if (p2Index == p1Index) {
                p2Index = indexToWrap(p2Index - 1, TYPE_CYCLE.length);
            }
        } else if (e.getSource() == p2Right) {
            p2Index = indexToWrap(p2Index + 1, TYPE_CYCLE.length);
            if (p2Index == p1Index) {
                p2Index = indexToWrap(p2Index + 1, TYPE_CYCLE.length);
            }
        } else if (e.getSource() == back) {
            // Go back to the main menu
            mainFrame.add(new Menu(mainFrame));
            mainFrame.remove(this);
            mainFrame.pack();
            return;
        } else {
            return;
        }

        // Update the configuration
        Configuration.setP1Type(TYPE_CYCLE[p1Index]);
        Configuration.setP2Type(TYPE_CYCLE[p2Index]);
        p1Title.setForeground(GameColors.getColorFromType(Configuration.getP1Type()));
        p2Title.setForeground(GameColors.getColorFromType(Configuration.getP2Type()));

        repaint();
        revalidate();
    }
}
