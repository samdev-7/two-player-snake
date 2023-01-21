package snake.types;

import java.awt.Color;

/**
 * A collection of colors used in the game.
 *
 * @author samliu
 */
public class GameColors {
    public static final Color BG = new Color(0, 0, 1);
    public static final Color BORDER = new Color(34, 32, 52);
    public static final Color GREEN = new Color(106, 189, 48);
    public static final Color BLUE = new Color(28, 126, 213);
    public static final Color RED = new Color(231, 20, 38);
    public static final Color YELLOW = new Color(208, 164, 1);
    public static final Color PURPLE = new Color(146, 38, 204);

    public static Color getColorFromType(int type) {
        switch (type) {
            case TileTypes.GREEN_SNAKE -> {
                return GREEN;
            }
            case TileTypes.BLUE_SNAKE -> {
                return BLUE;
            }
            case TileTypes.RED_SNAKE -> {
                return RED;
            }
            case TileTypes.YELLOW_SNAKE -> {
                return YELLOW;
            }
            case TileTypes.PURPLE_SNAKE -> {
                return PURPLE;
            }
            default -> {
                return null;
            }
        }
    }
}
