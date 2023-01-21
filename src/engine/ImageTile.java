package engine;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 * A class that represents a tile in the game, displayed with an image.
 *
 * @author samliu
 */
public class ImageTile extends JLabel {

    private ImageIcon image;

    /**
     * Creates a new ImageTile with the given image.
     *
     * @param image the image to display
     */
    public ImageTile(ImageIcon image) {
        super();
        this.image = image;
    }

    /**
     * Sets the image of this ImageTile.
     *
     * @param image the image to display
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    /**
     * Gets the image of this ImageTile.
     *
     * @return the image of this ImageTile
     */
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public void paintComponent(Graphics g) {
        image.paintIcon(this, g, 0, 0); // https://stackoverflow.com/questions/10084787
    }

}
