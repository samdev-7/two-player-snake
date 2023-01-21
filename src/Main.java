import snake.Game;
import snake.fileio.Configuration;
import snake.menu.*;
import snake.types.TileTypes;

import javax.swing.*;

/**
 * A snake game.
 *
 * @author samliu
 */
public class Main extends JFrame {

    /**
     * To start the entire game.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

    /**
     * Creates a new snake game.
     */
    public Main() {
        super("Two Player Snake - By Sam Liu"); // Set the title of the window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        // Adds a icon to the window
        this.setIconImage(TileTypes.TILE_IMAGES.get(TileTypes.GREEN_SNAKE + TileTypes.HEAD_N).getImage());

        Configuration.refresh();

        new Game(this); // "Pre-loading" the game to prevent it from lagging later. (Not sure why it works)

        // Create the menu
        JPanel menu = new Menu(this);
        //JPanel menu = new Customization(this);
        this.add(menu);

        // Makes sure the content pane is the right size
        // https://stackoverflow.com/questions/52573661
        this.pack();

        this.setVisible(true);

    }

}
