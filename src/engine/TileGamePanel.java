package engine;

import javax.swing.*;
import java.awt.*;

/**
 * A basic JPanel for the game.
 *
 * @author samliu
 */
public class TileGamePanel extends JPanel {
    public final int WIDTH, HEIGHT;

    public TileGamePanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        this.setFocusable(true); // Allows keyboard input

        this.setVisible(true);

        this.WIDTH = width;
        this.HEIGHT = height;

    }

}
