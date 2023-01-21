package snake.menu;

import snake.fileio.Configuration;
import snake.types.GameColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

/**
 * A game over screen.
 *
 * @author samliu
 */
public class GameOver extends JPanel implements ActionListener {

    private JFrame mainFrame;
    private JLabel title, winner, p1Title, p2Title, p1Score, p2Score, p1Time, p2Time;
    private JButton restartButton;
    private Image bgImage;

    /**
     * Creates a new game over screen.
     *
     * @param p1Score   The score of the first player
     * @param p2Score   The score of the second player
     * @param p1Time    The time, in seconds of the first player
     * @param p2Time    The time, in seconds of the second player
     * @param mainFrame The main JFrame of the game
     */
    public GameOver(int p1Score, int p2Score, int p1Time, int p2Time, JFrame mainFrame) {
        this.setPreferredSize(new Dimension(512, 544));
        this.setLayout(null);

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

        title = new JLabel("GAME OVER");
        title.setBounds(0, 96, 512, 64);
        title.setFont(font.deriveFont(32f));
        title.setForeground(GameColors.RED);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        add(title);

        Color p1Color = GameColors.getColorFromType(Configuration.getP1Type());
        Color p2Color = GameColors.getColorFromType(Configuration.getP2Type());

        // Determining the winner
        if (p1Score > p2Score) {
            winner = new JLabel("Player 1 Wins!");
            winner.setForeground(p1Color);
        } else if (p2Score > p1Score) {
            winner = new JLabel("Player 2 Wins!");
            winner.setForeground(p2Color);
        } else {
            if (p1Time > p2Time) {
                winner = new JLabel("Player 1 Wins!");
                winner.setForeground(p1Color);
            } else if (p2Time > p1Time) {
                winner = new JLabel("Player 2 Wins!");
                winner.setForeground(p2Color);
            } else {
                winner = new JLabel("It's a tie!");
                winner.setForeground(Color.WHITE);
            }
        }
        winner.setBounds(0, 160, 512, 64);
        winner.setFont(font.deriveFont(24f));
        winner.setHorizontalAlignment(SwingConstants.CENTER);
        winner.setVerticalAlignment(SwingConstants.CENTER);
        add(winner);


        this.p1Title = new JLabel("Player 1");
        this.p1Title.setBounds(64, 256, 192, 32);
        this.p1Title.setFont(font.deriveFont(18f));
        this.p1Title.setForeground(p1Color);
        this.p1Title.setHorizontalAlignment(SwingConstants.CENTER);
        this.p1Title.setVerticalAlignment(SwingConstants.CENTER);
        add(this.p1Title);

        this.p1Score = new JLabel("Score: " + p1Score);
        this.p1Score.setBounds(64, 288, 192, 32);
        this.p1Score.setFont(font);
        this.p1Score.setForeground(p1Color);
        this.p1Score.setHorizontalAlignment(SwingConstants.CENTER);
        this.p1Score.setVerticalAlignment(SwingConstants.CENTER);
        add(this.p1Score);

        String parsedP1Time = (p1Time / 60 < 10 ? "0" + p1Time / 60 : p1Time / 60) + ":" + (p1Time % 60 < 10 ? "0" + p1Time % 60 : p1Time % 60);
        this.p1Time = new JLabel("Time: " + parsedP1Time);
        this.p1Time.setBounds(64, 320, 192, 32);
        this.p1Time.setFont(font);
        this.p1Time.setForeground(p1Color);
        this.p1Time.setHorizontalAlignment(SwingConstants.CENTER);
        this.p1Time.setVerticalAlignment(SwingConstants.CENTER);
        add(this.p1Time);

        this.p2Title = new JLabel("Player 2");
        this.p2Title.setBounds(256, 256, 192, 32);
        this.p2Title.setFont(font.deriveFont(18f));
        this.p2Title.setForeground(p2Color);
        this.p2Title.setHorizontalAlignment(SwingConstants.CENTER);
        this.p2Title.setVerticalAlignment(SwingConstants.CENTER);
        this.add(this.p2Title);

        this.p2Score = new JLabel("Score: " + p2Score);
        this.p2Score.setBounds(256, 288, 192, 32);
        this.p2Score.setFont(font);
        this.p2Score.setForeground(p2Color);
        this.p2Score.setHorizontalAlignment(SwingConstants.CENTER);
        this.p2Score.setVerticalAlignment(SwingConstants.CENTER);
        add(this.p2Score);

        String parsedP2Time = (p2Time / 60 < 10 ? "0" + p2Time / 60 : p2Time / 60) + ":" + ((p2Time % 60) < 10 ? "0" + p2Time % 60 : p2Time % 60);
        this.p2Time = new JLabel("Time: " + parsedP2Time);
        this.p2Time.setBounds(256, 320, 192, 32);
        this.p2Time.setFont(font);
        this.p2Time.setForeground(p2Color);
        this.p2Time.setHorizontalAlignment(SwingConstants.CENTER);
        this.p2Time.setVerticalAlignment(SwingConstants.CENTER);
        add(this.p2Time);

        restartButton = new HoverButton("RESTART", GameColors.BG, GameColors.BORDER);
        restartButton.setFont(font.deriveFont(16f));
        restartButton.setForeground(GameColors.YELLOW);
        restartButton.setBackground(GameColors.BG);
        restartButton.setBorder(BorderFactory.createLineBorder(GameColors.BORDER, 2));// https://www.tutorialspoint.com/how-to-change-button-border-in-java-swing
        restartButton.setBounds(160, 384, 192, 64);
        this.add(restartButton);

        restartButton.addActionListener(this);
    }

    // To draw the background image
    // https://stackoverflow.com/questions/1466240
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            // Go back to the main menu
            JPanel menu = new Menu(mainFrame);
            mainFrame.remove(this);
            mainFrame.add(menu);
            mainFrame.pack();
        }
    }
}


