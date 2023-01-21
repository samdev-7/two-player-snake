package snake;

import snake.types.Direction;
import snake.types.GameColors;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a snake in the game.
 *
 * @author samliu
 */
public class Snake {

    private Direction heading; // The direction the snake is heading
    private Direction nextHeading; // The direction the snake will head next
    private ArrayList<Integer[]> coordinates = new ArrayList<>(1); // A list of the coordinates of the snake's body
    private ArrayList<Integer[]> previousCoordinates = coordinates; // A list of the coordinates of the snake's body from the previous frame
    private int needsIncreasing = 2; // The number of tiles the snake needs to increase by
    private boolean isAlive = true; // Whether the snake is alive or not
    private final int TYPE; // The type (color) of snake this is
    private int aliveSince = 0; // The time, in seconds, when the snake wss born
    private int aliveFor = -1;

    public Snake(int startX, int startY, Direction heading, int TYPE) {
        coordinates.add(new Integer[]{startX, startY});

        this.heading = heading;
        this.nextHeading = heading;
        this.TYPE = TYPE;

        // Make the snake start off with a length of two for aesthetics
        switch (heading) {
            case NORTH -> coordinates.add(new Integer[]{startX, startY + 1});
            case EAST -> coordinates.add(new Integer[]{startX - 1, startY});
            case SOUTH -> coordinates.add(new Integer[]{startX, startY - 1});
            case WEST -> coordinates.add(new Integer[]{startX + 1, startY});
        }
    }

    /**
     * Gets when the snake was born.
     *
     * @return the time, in seconds, when the snake was born
     */
    public int getAliveSince() {
        return aliveSince;
    }

    /**
     * Sets when the snake was born.
     *
     * @param aliveSince the time, in seconds, when the snake was born
     */
    public void setAliveSince(int aliveSince) {
        this.aliveSince = aliveSince;
    }

    /**
     * Gets how long the snake is.
     *
     * @return the length of the snake, in tiles
     */
    public int getLength() {
        return coordinates.size();
    }

    /**
     * Gets if the snake is alive or not.
     *
     * @return whether the snake is alive or not
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Un-alive the snake.
     */
    public void kill() {
        isAlive = false;
        aliveFor = ((int) System.currentTimeMillis() / 1000) - aliveSince;
    }

    /**
     * Get how long the snake has been alive for.
     *
     * @return the time, in seconds, that the snake has been alive for
     */
    public int getAliveFor() {
        if (isAlive) {
            return ((int) System.currentTimeMillis() / 1000) - aliveSince;
        } else {
            return aliveFor;
        }
    }

    /**
     * Get the list of coordinates of the snake's body.
     *
     * @return the list of coordinates of the snake's body
     */
    public ArrayList<Integer[]> getCoordinates() {
        return coordinates;
    }

    /**
     * Get the list of coordinates of the snake's body from the previous tick.
     *
     * @return the list of coordinates of the snake's body from the previous tick
     */
    public ArrayList<Integer[]> getPreviousCoordinates() {
        return previousCoordinates;
    }

    /**
     * Get the type of this snake.
     *
     * @return the type of this snake
     */
    public int getTYPE() {
        return TYPE;
    }

    /**
     * Increase the snake's length.
     *
     * @param len the number of tiles to increase the snake's length by
     */
    public void increaseLength(int len) {
        needsIncreasing += len;
    }

    /**
     * Increase the snake's length by one.
     */
    public void increaseLength() {
        increaseLength(1);
    }

    /**
     * Get the direction the snake is heading.
     *
     * @param heading the direction the snake is heading
     */
    public void setHeading(Direction heading) {
        // Using next heading to avoid changing the heading while the snake is moving
        switch (this.heading) {
            case NORTH -> {
                // The snake cannot turn into itself
                if (heading != Direction.SOUTH) {
                    this.nextHeading = heading;
                }
            }
            case EAST -> {
                // The snake cannot turn into itself
                if (heading != Direction.WEST) {
                    this.nextHeading = heading;
                }
            }
            case SOUTH -> {
                // The snake cannot turn into itself
                if (heading != Direction.NORTH) {
                    this.nextHeading = heading;
                }
            }
            case WEST -> {
                // The snake cannot turn into itself
                if (heading != Direction.EAST) {
                    this.nextHeading = heading;
                }
            }
        }
    }

    /**
     * Get the direction the snake is heading.
     *
     * @return the direction the snake is heading
     */
    public Direction getHeading() {
        return heading;
    }

    /**
     * Move the snake forward.
     */
    public void move() {
        // No need to move it if it's dead
        if (!isAlive) {
            return;
        }

        // Updates the heading
        heading = nextHeading;

        // Save the coordinates of the snake's body from the previous tick
        previousCoordinates = new ArrayList<>(coordinates);

        // Add a new head
        switch (heading) {
            case NORTH -> coordinates.add(0, new Integer[]{coordinates.get(0)[0], coordinates.get(0)[1] - 1});
            case EAST -> coordinates.add(0, new Integer[]{coordinates.get(0)[0] + 1, coordinates.get(0)[1]});
            case SOUTH -> coordinates.add(0, new Integer[]{coordinates.get(0)[0], coordinates.get(0)[1] + 1});
            case WEST -> coordinates.add(0, new Integer[]{coordinates.get(0)[0] - 1, coordinates.get(0)[1]});
        }

        // Remove the rail if the snake doesn't need to increase
        if (needsIncreasing <= 0) {
            coordinates.remove(coordinates.size() - 1);
        } else {
            needsIncreasing--;
        }
    }

    /**
     * Moves the snake back to its location in the previous tick.
     */
    public void moveBack() {
        coordinates = new ArrayList<>(previousCoordinates);
    }

    /**
     * Get the color of the snake.
     *
     * @return the color of the snake
     */
    public Color getColor() {
        if (isAlive) {
            return GameColors.getColorFromType(TYPE);
        } else {
            return Color.GRAY; // Dead snakes are gray
        }
    }
}
