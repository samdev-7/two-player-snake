package snake;

import engine.TileGame;
import snake.menu.Scoreboard;
import snake.types.Direction;
import snake.types.GameColors;
import snake.types.TileTypes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

/**
 * The main game board for the Snake game.
 */
public class SnakeBoard extends TileGame {

    // Game pieces
    private Snake[] snakes = new Snake[2];
    private ArrayList<Apple> apples = new ArrayList<>();

    Random rand = new Random();

    private Timer tickTimer, countdownTimer, stopwatchTimer; // Timers for the game to loop
    private boolean running = false;

    private final int HEIGHT_TILE;
    private final int WIDTH_TILE;
    private final int MAX_APPLES; // Maximum number of apples on the board at once

    private Scoreboard scorePanel;
    private Game gamePanel;

    private int countdown = 5;
    private long startTime;

    /**
     * Creates a new SnakeBoard.
     *
     * @param HEIGHT_TILE The height of the board, in tiles.
     * @param WIDTH_TILE  The width of the board, in tiles.
     * @param SPEED       The speed of the game, in milliseconds.
     * @param MAX_APPLES  The maximum number of apples on the board at once.
     * @param scorePanel  The scoreboard associated with this board.
     * @param gamePanel   The game panel associated with this board.
     */
    public SnakeBoard(int HEIGHT_TILE, int WIDTH_TILE, int SPEED, int MAX_APPLES, Scoreboard scorePanel, int p1Type, int p2Type, Game gamePanel) {
        super(WIDTH_TILE, HEIGHT_TILE, 32, 32, TileTypes.TILE_IMAGES);

        // Add the snakes to the board
        this.snakes[0] = new Snake(WIDTH_TILE / 2 - 1, WIDTH_TILE / 2 - 1, Direction.SOUTH, p1Type);
        this.snakes[1] = new Snake(WIDTH_TILE / 2, WIDTH_TILE / 2 - 1, Direction.NORTH, p2Type);

        this.HEIGHT_TILE = HEIGHT_TILE;
        this.WIDTH_TILE = WIDTH_TILE;
        // The time, in milliseconds, between each tick of the game
        this.MAX_APPLES = MAX_APPLES;

        // Adds a timer to tick the game every SPEED milliseconds
        ActionListener tickTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        }; // https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
        tickTimer = new Timer(SPEED, tickTask);
        tickTimer.setInitialDelay(0);

        // Adds a timer to count down before the game starts
        ActionListener countdownTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countdown > 0) {
                    if (countdown == 1) {
                        scorePanel.setInfoText("Starting in " + countdown + "...", GameColors.YELLOW);
                    } else if (countdown == 2) {
                        scorePanel.setInfoText("Starting in " + countdown + "...", GameColors.RED);
                    } else {
                        scorePanel.setInfoText("Starting in " + countdown + "...", Color.WHITE);
                    }
                } else if (countdown == 0) {
                    scorePanel.setInfoText("GO!", GameColors.GREEN);
                    start();
                } else {
                    stopwatch();
                    countdownTimer.stop();
                }
                countdown--;
                draw();
            }
        };
        countdownTimer = new Timer(1000, countdownTask);
        countdownTimer.setInitialDelay(0);

        // Adds a timer to keep track of the game time
        ActionListener stopwatchTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = "";
                int seconds = (int) ((System.currentTimeMillis() - startTime) / 1000);
                int minutes = seconds / 60;
                seconds %= 60;
                if (minutes < 10) {
                    time += "0";
                }
                if (seconds < 10) {
                    time += minutes + ":0" + seconds;
                } else {
                    time += minutes + ":" + seconds;
                }
                scorePanel.setInfoText(time, Color.WHITE);
            }
        };
        stopwatchTimer = new Timer(1000, stopwatchTask);
        stopwatchTimer.setInitialDelay(0);

        // Initialize the scoreboard
        this.scorePanel = scorePanel;
        updateScores();

        this.gamePanel = gamePanel;
    }

    /**
     * Starts the countdown for the game.
     */
    public void countdown() {
        countdownTimer.start();
    }

    /**
     * Starts the stopwatch to keep track of the game time.
     */
    private void stopwatch() {
        stopwatchTimer.start();
    }

    /**
     * Main game loop.
     */
    private void tick() {
        if (!running) {
            return;
        }

        // Move the snakes
        for (Snake snake : snakes) {
            snake.move();
        }

        // Check for collisions
        checkCollisions();

        // Move the dead snakes back to prevent IndexOutOfBoundsExceptions
        for (Snake snake : snakes) {
            if (!snake.isAlive()) {
                snake.moveBack();
            }
        }

        // Check if apples have been eaten
        checkApples();

        // Check if the game is over
        if (!snakes[0].isAlive() && !snakes[1].isAlive()) {
            gameOver(snakes[0].getLength(), snakes[1].getLength(), snakes[0].getAliveFor(), snakes[1].getAliveFor());
        }

        // Adds apples to the board if needed
        if (apples.size() < MAX_APPLES) {
            addApple();
        }

        // Update the scoreboard
        updateScores();

        // Paints the board to the screen
        draw();
    }

    /**
     * Ends the game.
     *
     * @param p1Score The score of the first player
     * @param p2Score The score of the second player
     * @param p1Time  The time, in seconds of the first player
     * @param p2Time  The time, in seconds of the second player
     */
    private void gameOver(int p1Score, int p2Score, int p1Time, int p2Time) {
        running = false;

        draw(); // Paints the board to show the dead snakes

        // Stops the timers
        tickTimer.stop();
        stopwatchTimer.stop();

        scorePanel.setInfoText("Game Over!", GameColors.RED);

        // Add a 3-second delay before showing the game over screen
        ActionListener endTask = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.gameOver(p1Score, p2Score, p1Time, p2Time);
            }
        };
        Timer endTimer = new Timer(0, endTask);
        endTimer.setInitialDelay(3000);
        endTimer.setRepeats(false);
        endTimer.start();
    }

    /**
     * Updates the scoreboard's scores with current game scores.
     */
    private void updateScores() {
        scorePanel.setScores(snakes[0].getLength(), snakes[1].getLength(), snakes[0].getColor(), snakes[1].getColor());
    }

    /**
     * Paints the board to the screen.
     */
    public void draw() {
        clearTiles();
        for (Snake snake : snakes) {
            drawSnake(snake);
        }
        drawApples();
        refresh();
    }

    /**
     * Checks to see if snakes have collided.
     */
    private void checkCollisions() {
        checkLoop:
        for (Snake snake : snakes) {
            // No need to check dead snakes
            if (!snake.isAlive()) {
                continue;
            }

            // Check to see if the snake has hit the sides of the board
            if (snake.getCoordinates().get(0)[0] < 0 || snake.getCoordinates().get(0)[0] >= WIDTH_TILE || snake.getCoordinates().get(0)[1] < 0 || snake.getCoordinates().get(0)[1] >= HEIGHT_TILE) {
                snake.kill();
                continue;
            }

            // Check to see if the snake has hit itself
            for (int i = 1; i < snake.getCoordinates().size(); i++) {
                if (snake.getCoordinates().get(0)[0].equals(snake.getCoordinates().get(i)[0]) && snake.getCoordinates().get(0)[1].equals(snake.getCoordinates().get(i)[1])) {
                    snake.kill();
                    continue checkLoop;
                }
            }

            // Check to see if the snake has hit the other snake
            for (Snake otherSnake : snakes) {
                // Skip checking the snake with itself
                if (snake == otherSnake) {
                    continue;
                }

                for (Integer[] coordinate : otherSnake.getCoordinates()) {
                    if (snake.getCoordinates().get(0)[0].equals(coordinate[0]) && snake.getCoordinates().get(0)[1].equals(coordinate[1])) {
                        snake.kill();
                        continue checkLoop;
                    }
                }
            }
        }

    }

    /**
     * Checks to see if apples have been eaten.
     */
    private void checkApples() {
        for (int i = 0; i < apples.size(); i++) {
            Apple apple = apples.get(i);
            for (Snake snake : snakes) {
                Integer[] snakeCoordinate = snake.getCoordinates().get(0);
                int[] appleCoordinate = apple.getCoordinate();
                if (appleCoordinate[0] == snakeCoordinate[0] && appleCoordinate[1] == snakeCoordinate[1]) {
                    snake.increaseLength(apple.LENGTH_INCREASE); // Increase the snake's length
                    apples.remove(i); // Remove the apple from the board
                }
            }
        }
    }

    /**
     * Draws the snake to the screen.
     *
     * @param snake The snake to draw
     */
    private void drawSnake(Snake snake) {
        int SNAKE_TYPE = snake.getTYPE(); // Get the type (color) of the snake

        for (int i = 0; i < snake.getLength(); i++) {
            Integer[] coordinates = snake.getCoordinates().get(i);

            // Draw the head of the snake
            if (i == 0) {
                if (snake.isAlive()) {
                    // If the snake is alive, draw the head normally, whatever direction it is facing
                    switch (snake.getHeading()) {
                        case NORTH -> setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.HEAD_N);
                        case EAST -> setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.HEAD_E);
                        case SOUTH -> setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.HEAD_S);
                        case WEST -> setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.HEAD_W);
                    }
                } else {
                    // If the snake is dead, draw the head connecting to the body
                    Integer[] currentCoordinates = snake.getCoordinates().get(0);
                    Integer[] nextCoordinates = snake.getCoordinates().get(1); // The coordinates of the segment after the head

                    // Which direction is the head connected to the body?
                    boolean hasN = false;
                    boolean hasE = false;
                    boolean hasS = false;
                    boolean hasW = false;

                    // Check which direction the head is connected to the body
                    if (Objects.equals(currentCoordinates[0], nextCoordinates[0])) {
                        // The head is connected to the body vertically
                        if (currentCoordinates[1] > nextCoordinates[1]) {
                            hasN = true;
                        } else {
                            hasS = true;
                        }
                    } else {
                        // The head is connected to the body horizontally
                        if (currentCoordinates[0] > nextCoordinates[0]) {
                            hasW = true;
                        } else {
                            hasE = true;
                        }
                    }

                    // Draw the head
                    switch (snake.getHeading()) {
                        case NORTH -> {
                            if (hasE) {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_NE);
                            } else if (hasW) {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_NW);
                            } else {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_N);
                            }
                        }
                        case EAST -> {
                            if (hasN) {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_EN);
                            } else if (hasS) {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_ES);
                            } else {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_E);
                            }
                        }
                        case SOUTH -> {
                            if (hasE) {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_SE);
                            } else if (hasW) {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_SW);
                            } else {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_S);
                            }
                        }
                        case WEST -> {
                            if (hasN) {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_WN);
                            } else if (hasS) {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_WS);
                            } else {
                                setTile(coordinates[0], coordinates[1], SNAKE_TYPE + TileTypes.DEAD_W);
                            }
                        }
                    }
                }

            } else if (i == snake.getLength() - 1) {
                // Draw the tail of the snake
                Integer[] currentCoordinates = snake.getCoordinates().get(i);
                Integer[] previousCoordinates = snake.getCoordinates().get(i - 1); // The coordinates of the segment before the tail

                // Check which direction the tail is connected to the body
                if (Objects.equals(currentCoordinates[0], previousCoordinates[0])) {
                    // The tail is connected to the body vertically
                    if (currentCoordinates[1] > previousCoordinates[1]) {
                        setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.TAIL_S);
                    } else {
                        setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.TAIL_N);
                    }
                } else {
                    // The tail is connected to the body horizontally
                    if (currentCoordinates[0] > previousCoordinates[0]) {
                        setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.TAIL_E);
                    } else {
                        setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.TAIL_W);
                    }
                }
            } else {
                // Draw the body of the snake
                Integer[] currentCoordinates = snake.getCoordinates().get(i);
                Integer[] previousCoordinates = snake.getCoordinates().get(i - 1); // The coordinates of the segment before the current segment
                Integer[] nextCoordinates = snake.getCoordinates().get(i + 1); // The coordinates of the segment after the current segment

                // Check which directions the current segment is connected to
                boolean hasN = false;
                boolean hasE = false;
                boolean hasS = false;
                boolean hasW = false;

                // Check with the previous segment
                if (Objects.equals(currentCoordinates[0], previousCoordinates[0])) {
                    // The current segment is connected to the previous segment vertically
                    if (currentCoordinates[1] > previousCoordinates[1]) {
                        hasN = true;
                    } else {
                        hasS = true;
                    }
                } else {
                    // The current segment is connected to the previous segment horizontally
                    if (currentCoordinates[0] > previousCoordinates[0]) {
                        hasW = true;
                    } else {
                        hasE = true;
                    }
                }

                // Check with the next segment
                if (Objects.equals(currentCoordinates[0], nextCoordinates[0])) {
                    // The current segment is connected to the next segment vertically
                    if (currentCoordinates[1] > nextCoordinates[1]) {
                        hasN = true;
                    } else {
                        hasS = true;
                    }
                } else {
                    // The current segment is connected to the next segment horizontally
                    if (currentCoordinates[0] > nextCoordinates[0]) {
                        hasW = true;
                    } else {
                        hasE = true;
                    }
                }

                // Draw the body segment
                if (hasN && hasS) {
                    setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.BODY_NS);
                } else if (hasE && hasW) {
                    setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.BODY_EW);
                } else if (hasN && hasE) {
                    setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.BODY_NE);
                } else if (hasN && hasW) {
                    setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.BODY_WN);
                } else if (hasS && hasE) {
                    setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.BODY_ES);
                } else if (hasS && hasW) {
                    setTile(currentCoordinates[0], currentCoordinates[1], SNAKE_TYPE + TileTypes.BODY_SW);
                }

            }
        }
    }

    /**
     * Draws the apple onto the board.
     */
    private void drawApples() {
        for (Apple apple : apples) {
            int[] appleCoordinate = apple.getCoordinate();
            setTile(appleCoordinate[0], appleCoordinate[1], TileTypes.APPLE);
        }
    }

    /**
     * Checks if a tile is occupied by a object.
     *
     * @param x The x coordinate of the tile.
     * @param y The y coordinate of the tile.
     * @return is the tile occupied?
     */
    private boolean isOccupied(int x, int y) {
        // Check for snakes
        for (Snake snake : snakes) {
            ArrayList<Integer[]> snakeCoordinates = snake.getCoordinates();
            for (Integer[] snakeCoordinate : snakeCoordinates) {
                if (snakeCoordinate[0] == x && snakeCoordinate[1] == y) {
                    return true;
                }
            }
        }

        // Check for apples
        for (Apple apple : apples) {
            int[] appleCoordinate = apple.getCoordinate();
            if (appleCoordinate[0] == x && appleCoordinate[1] == y) {
                return true;
            }
        }

        return false;
    }

    /**
     * Add an apple to a random empty location on the board.
     */
    private void addApple() {
        // While the apple is not placed on an empty tile, keep trying
        while (true) {
            // Generate a random coordinate
            int x = rand.nextInt(WIDTH_TILE);
            int y = rand.nextInt(HEIGHT_TILE);

            // Check if the tile is empty
            if (isOccupied(x, y)) {
                continue;
            }

            // Add the apple
            Apple apple = new Apple(x, y);
            apples.add(apple);
            break;
        }
    }

    /**
     * Get an array of all the snakes.
     *
     * @return yhe array of all the snakes.
     */
    public Snake[] getSnakes() {
        return snakes;
    }

    /**
     * Start the game.
     * Do not call this method directly, use the start method in the Game class which will initialize the countdown to start the game.
     */
    private void start() {
        if (!running) {
            requestFocus(); // Makes sure KeyListener works
            tickTimer.start(); // Start the game loop
            running = true;
            startTime = System.currentTimeMillis(); // Saves the time when the game started
            for (Snake snake : snakes) {
                snake.setAliveSince((int) startTime / 1000); // Set the time when the snake was born
            }
        }
    }

    /**
     * Runs when the user presses a key on their keyboard.
     *
     * @param key The character of the key that was pressed
     */
    @Override
    protected void onInput(char key) {
        // No need to care about keyboard input if the game is not running
        if (!running) {
            return;
        }

        // Keyboard inputs for player 1
        if (snakes[0].isAlive()) {
            switch (key) {
                case 'w' -> snakes[0].setHeading(Direction.NORTH);
                case 'a' -> snakes[0].setHeading(Direction.WEST);
                case 's' -> snakes[0].setHeading(Direction.SOUTH);
                case 'd' -> snakes[0].setHeading(Direction.EAST);
            }
        }

        // Keyboard inputs for player 2
        if (snakes[1].isAlive()) {
            switch (key) {
                case 'i' -> snakes[1].setHeading(Direction.NORTH);
                case 'j' -> snakes[1].setHeading(Direction.WEST);
                case 'k' -> snakes[1].setHeading(Direction.SOUTH);
                case 'l' -> snakes[1].setHeading(Direction.EAST);
            }
        }
    }

    @Override
    protected void onClick(int x, int y) {
        // Nothing required here.
    }
}
