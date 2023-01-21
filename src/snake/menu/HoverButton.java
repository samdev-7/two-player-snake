package snake.menu;

import javax.swing.*;
import java.awt.*;

/**
 * A JButton that changes color when hovered over.
 *
 * @author samliu
 */
class HoverButton extends JButton {

    private Color hover;

    /**
     * Creates a new HoverButton.
     *
     * @param text  The text of the button
     * @param bg    The background color of the button
     * @param hover The color of the button when hovered over
     */
    public HoverButton(String text, Color bg, Color hover) {
        this(text, hover);
        this.setBackground(bg);
    }

    /**
     * Creates a new HoverButton.
     *
     * @param text  The text of the button
     * @param hover The color of the button when hovered over
     */
    public HoverButton(String text, Color hover) {
        super(text);
        this.hover = hover;
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
    }

    /**
     * Get the color of the button when hovered over.
     *
     * @return The color of the button when hovered over
     */
    public Color getHoverColor() {
        return hover;
    }

    /**
     * Set the color of the button when hovered over.
     *
     * @param hover The color of the button when hovered over
     */
    public void setHoverColor(Color hover) {
        this.hover = hover;
    }

    // https://stackoverflow.com/questions/14627223
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed() || getModel().isRollover()) {
            g.setColor(hover);
        } else {
            g.setColor(this.getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

}