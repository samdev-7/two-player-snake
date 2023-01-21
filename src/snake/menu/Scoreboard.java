package snake.menu;

import snake.types.GameColors;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * A scoreboard that displays the scores.
 *
 * @author samliu
 */

public class Scoreboard extends JPanel {

    private int p1Score = 0;
    private int p2Score = 0;
    private String infoText;

    private JLabel p1Label, p2Label, infoLabel;

    /**
     * Creates a new scoreboard.
     */
    public Scoreboard() {
        // To left/right align components
        // https://stackoverflow.com/questions/12096548
        this.setLayout(new BorderLayout(0, 100));

        this.setPreferredSize(new Dimension(512, 32));

        // To add padding
        // https://stackoverflow.com/question/5328405
        this.setBorder(new EmptyBorder(2, 16, 0, 16));

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

        this.setBackground(GameColors.BORDER);

        p1Label = new JLabel("P1: " + p1Score);
        p1Label.setVisible(true);
        p1Label.setFont(font);
        this.add(p1Label, BorderLayout.WEST);

        p2Label = new JLabel("P2: " + p2Score);
        p2Label.setVisible(true);
        p2Label.setFont(font);
        this.add(p2Label, BorderLayout.EAST);

        infoText = "Starting in 5...";
        infoLabel = new JLabel(infoText);
        infoLabel.setVisible(true);
        infoLabel.setFont(font);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(infoLabel, BorderLayout.CENTER);
    }

    /**
     * Updates the scores.
     *
     * @param p1Score The score of player 1
     * @param p2Score The score of player 2
     * @param p1Color The color to display player 1's score in
     * @param p2Color The color to display player 2's score in
     */
    public void setScores(int p1Score, int p2Score, Color p1Color, Color p2Color) {
        this.p1Score = p1Score;
        this.p2Score = p2Score;

        p1Label.setText("P1: " + this.p1Score);
        p2Label.setText("P2: " + this.p2Score);

        p1Label.setForeground(p1Color);
        p2Label.setForeground(p2Color);
    }

    /**
     * Updates the info text.
     *
     * @param infoText The new info text
     * @param color    The color to display the info text in
     */
    public void setInfoText(String infoText, Color color) {
        this.infoText = infoText;

        infoLabel.setText(this.infoText);
        infoLabel.setForeground(color);
    }

}
