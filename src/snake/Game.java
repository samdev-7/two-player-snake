package snake;

import snake.menu.GameOver;
import snake.menu.Scoreboard;

import javax.swing.*;
import snake.fileio.Configuration;
import snake.types.TileTypes;

/**
 * The main class for the game
 *
 * @author samliu
 */
public class Game extends JPanel {

    Scoreboard score;
    SnakeBoard snakeBoard;
    JFrame mainFrame;

    /**
     * Creates a new game.
     *
     * @param mainFrame The main JFrame of the game
     */
    public Game(JFrame mainFrame) {
        // To stack elements vertically
        // https://stackoverflow.com/questions/4495094
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.mainFrame = mainFrame;

        score = new Scoreboard();
        score.setVisible(true);
        this.add(score);
        
        snakeBoard = new SnakeBoard(16, 16, 135, 1, score, Configuration.getP1Type(), Configuration.getP2Type(), this);
        snakeBoard.setVisible(true);
        this.add(snakeBoard);
        
    }

    /**
     * Starts the game.
     */
    public void start() {
        snakeBoard.draw();
        snakeBoard.countdown();
    }

    /**
     * Ends the game.
     *
     * @param p1Score The score of the first player
     * @param p2Score The score of the second player
     * @param p1Time  The time, in seconds of the first player
     * @param p2Time  The time, in seconds of the second player
     */
    public void gameOver(int p1Score, int p2Score, int p1Time, int p2Time) {
        // Go to the game over screen
        this.remove(snakeBoard);
        this.remove(score);
        mainFrame.remove(this);
        JPanel gameOverPanel = new GameOver(p1Score, p2Score, p1Time, p2Time, mainFrame);
        gameOverPanel.setVisible(true);
        mainFrame.add(gameOverPanel);
        mainFrame.pack();
    }
}
