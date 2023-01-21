package engine;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * Base class for a tile-based game.
 *
 * @author samliu
 */
public abstract class TileGame extends TileGamePanel implements KeyListener, MouseListener {

    private final int TILE_WIDTH;
    private final int TILE_HEIGHT;
    private int[][] map;
    private ImageTile[][] tiles;
    private final HashMap<Integer, ImageIcon> TILE_IMAGES;

    /**
     * Creates a new tile-based game with a empty map.
     *
     * @param WIDTH       The width, in tiles, of the entire map
     * @param HEIGHT      The height, in tiles, of the entire map
     * @param TILE_WIDTH  The width, in pixels, of each tile
     * @param TILE_HEIGHT The height, in pixels, of each tile
     * @param TILE_IMAGES A HashMap of tile images, where the key is the tile ID and the value is the image
     */
    public TileGame(int WIDTH, int HEIGHT, int TILE_WIDTH, int TILE_HEIGHT, HashMap<Integer, ImageIcon> TILE_IMAGES) {
        super(WIDTH * TILE_WIDTH, HEIGHT * TILE_HEIGHT);

        this.TILE_WIDTH = TILE_WIDTH;
        this.TILE_HEIGHT = TILE_HEIGHT;
        this.map = new int[WIDTH][HEIGHT];
        this.tiles = new ImageTile[WIDTH][HEIGHT];
        this.TILE_IMAGES = TILE_IMAGES;

        init();
    }

    /**
     * Creates a new tile-based game with a preexisting map.
     *
     * @param map         The map to use. HAS TO BE A RECTANGLE
     * @param TILE_WIDTH  The width, in pixels, of each tile
     * @param TILE_HEIGHT The height, in pixels, of each tile
     * @param TILE_IMAGES A HashMap of tile images, where the key is the tile ID and the value is the image
     */
    public TileGame(int[][] map, int TILE_WIDTH, int TILE_HEIGHT, HashMap<Integer, ImageIcon> TILE_IMAGES) {
        super(map.length * TILE_WIDTH, map[0].length * TILE_HEIGHT);

        this.TILE_WIDTH = TILE_WIDTH;
        this.TILE_HEIGHT = TILE_HEIGHT;
        this.map = map;
        this.tiles = new ImageTile[map.length][map[0].length];
        this.TILE_IMAGES = TILE_IMAGES;

        init();
    }

    /**
     * Initializes the game. So I do not have to write it for each constructor.
     */
    private void init() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                map[x][y] = 0; // Default to empty
                tiles[x][y] = new ImageTile(TILE_IMAGES.get(map[x][y]));
                tiles[x][y].setBounds(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT); // Places it in a grid
                this.add(tiles[x][y]);
            }
        }

        this.addKeyListener(this); // https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
        this.addMouseListener(this); // https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html
    }

    /**
     * Sets the tile at the specified coordinates.
     *
     * @param x    The x coordinate of the tile
     * @param y    The y coordinate of the tile
     * @param tile The tile type to set
     */
    public void setTile(int x, int y, int tile) {
        map[x][y] = tile;
        tiles[x][y].setImage(TILE_IMAGES.get(tile));
    }

    /**
     * Get the ID of the tile at the specified coordinates.
     *
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     * @return The tile's type
     */
    public int getTile(int x, int y) {
        return map[x][y];
    }

    /**
     * Removes a tile. (Resets it)
     *
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     */
    public void removeTile(int x, int y) {
        setTile(x, y, 0);
    }

    /**
     * Removes all tiles. (Resets all tiles)
     */
    public void clearTiles() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                removeTile(x, y);
            }
        }
    }

    protected void refresh() {
        repaint();
        revalidate();
    }

    /**
     * Runs when the user presses a key on their keyboard.
     *
     * @param key The character of the key that was pressed
     */
    protected abstract void onInput(char key);

    @Override
    public void keyPressed(KeyEvent e) {
        onInput(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    } // Empty as not needed.

    @Override
    public void keyReleased(KeyEvent e) {
    } // Empty as not needed.

    /**
     * Runs when the user clicks a tile.
     *
     * @param x The x coordinate, in tiles
     * @param y The y coordinate, in tiles
     */
    protected abstract void onClick(int x, int y);

    @Override
    public void mousePressed(MouseEvent e) {
        onClick(e.getX() / TILE_WIDTH, e.getY() / TILE_HEIGHT);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    } // Empty as not needed.

    @Override
    public void mouseReleased(MouseEvent e) {
    } // Empty as not needed.

    @Override
    public void mouseEntered(MouseEvent e) {
    } // Empty as not needed.

    @Override
    public void mouseExited(MouseEvent e) {
    } // Empty as not needed.

}
