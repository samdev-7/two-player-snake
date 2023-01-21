package snake;

/**
 * An apple in the game.
 *
 * @author samliu
 */
public class Apple {

    private int[] coordinate = new int[2]; // The apple's location.
    public final int LENGTH_INCREASE; // The length the snake will increase by when it eats this apple.

    /**
     * Creates a new apple.
     *
     * @param x The x coordinate of the apple
     * @param y The y coordinate of the apple
     */
    public Apple(int x, int y) {
        this(x, y, 1);
    }

    /**
     * Creates a new apple.
     *
     * @param x               The x coordinate of the apple
     * @param y               The y coordinate of the apple
     * @param LENGTH_INCREASE The length the snake will increase by when it eats this apple
     */
    public Apple(int x, int y, int LENGTH_INCREASE) {
        this.coordinate[0] = x;
        this.coordinate[1] = y;
        this.LENGTH_INCREASE = LENGTH_INCREASE;
    }

    /**
     * Gets the coordinates of the apple.
     *
     * @return The coordinates of the apple. {x, y}
     */
    public int[] getCoordinate() {
        return coordinate;
    }

}
