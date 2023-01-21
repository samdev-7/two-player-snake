package snake.types;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A collection of all the tiles used in the game.
 *
 * @author samliu
 */
public class TileTypes {

    // General tiles
    public static final int EMPTY = 0;
    public static final int APPLE = 1;

    // Menu tiles
    public static final int ARROW_LEFT = 2;
    public static final int ARROW_RIGHT = 3;

    // Formula for calculating the tile of snake segment: Snake tile + Segment tile

    // Snake tiles
    public static final int GREEN_SNAKE = 4;
    public static final int BLUE_SNAKE = 30;
    public static final int YELLOW_SNAKE = 56;
    public static final int RED_SNAKE = 82;
    public static final int PURPLE_SNAKE = 108;

    // Segment tiles
    public static final int HEAD_N = 0;
    public static final int HEAD_E = 1;
    public static final int HEAD_S = 2;
    public static final int HEAD_W = 3;

    public static final int BODY_NS = 4;
    public static final int BODY_EW = 5;
    public static final int BODY_NE = 6;
    public static final int BODY_ES = 7;
    public static final int BODY_SW = 8;
    public static final int BODY_WN = 9;

    public static final int TAIL_N = 10;
    public static final int TAIL_E = 11;
    public static final int TAIL_S = 12;
    public static final int TAIL_W = 13;

    public static final int DEAD_N = 14;
    public static final int DEAD_E = 15;
    public static final int DEAD_S = 16;
    public static final int DEAD_W = 17;

    public static final int DEAD_NE = 18;
    public static final int DEAD_NW = 19;
    public static final int DEAD_EN = 20;
    public static final int DEAD_ES = 21;
    public static final int DEAD_SE = 22;
    public static final int DEAD_SW = 23;
    public static final int DEAD_WS = 24;
    public static final int DEAD_WN = 25;

    // Putting them in a hashmap for easy access
    public static final HashMap<Integer, ImageIcon> TILE_IMAGES = new HashMap<>(Map.<Integer, ImageIcon>ofEntries(
            // All image assets are original by Sam Liu.
            // For resource paths: https://stackoverflow.com/questions/22520421/how-to-reference-images-once-theyre-packaged-into-a-jar

            // General tiles
            Map.entry(EMPTY, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/empty.png")))),
            Map.entry(APPLE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/apple.png")))),

            // Menu tiles
            Map.entry(ARROW_LEFT, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/menus/arrows/left.png")))),
            Map.entry(ARROW_RIGHT, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/menus/arrows/right.png")))),

            // Green snake tiles
            Map.entry(GREEN_SNAKE + HEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_head/n.png")))),
            Map.entry(GREEN_SNAKE + HEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_head/e.png")))),
            Map.entry(GREEN_SNAKE + HEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_head/s.png")))),
            Map.entry(GREEN_SNAKE + HEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_head/w.png")))),

            Map.entry(GREEN_SNAKE + BODY_NS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_body/ns.png")))),
            Map.entry(GREEN_SNAKE + BODY_EW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_body/ew.png")))),
            Map.entry(GREEN_SNAKE + BODY_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_body/ne.png")))),
            Map.entry(GREEN_SNAKE + BODY_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_body/es.png")))),
            Map.entry(GREEN_SNAKE + BODY_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_body/sw.png")))),
            Map.entry(GREEN_SNAKE + BODY_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_body/wn.png")))),

            Map.entry(GREEN_SNAKE + TAIL_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_tail/n.png")))),
            Map.entry(GREEN_SNAKE + TAIL_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_tail/e.png")))),
            Map.entry(GREEN_SNAKE + TAIL_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_tail/s.png")))),
            Map.entry(GREEN_SNAKE + TAIL_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_tail/w.png")))),

            Map.entry(GREEN_SNAKE + DEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/n.png")))),
            Map.entry(GREEN_SNAKE + DEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/e.png")))),
            Map.entry(GREEN_SNAKE + DEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/s.png")))),
            Map.entry(GREEN_SNAKE + DEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/w.png")))),

            Map.entry(GREEN_SNAKE + DEAD_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/ne.png")))),
            Map.entry(GREEN_SNAKE + DEAD_NW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/nw.png")))),
            Map.entry(GREEN_SNAKE + DEAD_EN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/en.png")))),
            Map.entry(GREEN_SNAKE + DEAD_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/es.png")))),
            Map.entry(GREEN_SNAKE + DEAD_SE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/se.png")))),
            Map.entry(GREEN_SNAKE + DEAD_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/sw.png")))),
            Map.entry(GREEN_SNAKE + DEAD_WS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/ws.png")))),
            Map.entry(GREEN_SNAKE + DEAD_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/green/snake_dead/wn.png")))),

            // Blue snake tiles
            Map.entry(BLUE_SNAKE + HEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_head/n.png")))),
            Map.entry(BLUE_SNAKE + HEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_head/e.png")))),
            Map.entry(BLUE_SNAKE + HEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_head/s.png")))),
            Map.entry(BLUE_SNAKE + HEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_head/w.png")))),

            Map.entry(BLUE_SNAKE + BODY_NS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_body/ns.png")))),
            Map.entry(BLUE_SNAKE + BODY_EW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_body/ew.png")))),
            Map.entry(BLUE_SNAKE + BODY_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_body/ne.png")))),
            Map.entry(BLUE_SNAKE + BODY_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_body/es.png")))),
            Map.entry(BLUE_SNAKE + BODY_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_body/sw.png")))),
            Map.entry(BLUE_SNAKE + BODY_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_body/wn.png")))),

            Map.entry(BLUE_SNAKE + TAIL_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_tail/n.png")))),
            Map.entry(BLUE_SNAKE + TAIL_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_tail/e.png")))),
            Map.entry(BLUE_SNAKE + TAIL_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_tail/s.png")))),
            Map.entry(BLUE_SNAKE + TAIL_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_tail/w.png")))),

            Map.entry(BLUE_SNAKE + DEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/n.png")))),
            Map.entry(BLUE_SNAKE + DEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/e.png")))),
            Map.entry(BLUE_SNAKE + DEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/s.png")))),
            Map.entry(BLUE_SNAKE + DEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/w.png")))),

            Map.entry(BLUE_SNAKE + DEAD_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/ne.png")))),
            Map.entry(BLUE_SNAKE + DEAD_NW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/nw.png")))),
            Map.entry(BLUE_SNAKE + DEAD_EN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/en.png")))),
            Map.entry(BLUE_SNAKE + DEAD_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/es.png")))),
            Map.entry(BLUE_SNAKE + DEAD_SE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/se.png")))),
            Map.entry(BLUE_SNAKE + DEAD_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/sw.png")))),
            Map.entry(BLUE_SNAKE + DEAD_WS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/ws.png")))),
            Map.entry(BLUE_SNAKE + DEAD_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/blue/snake_dead/wn.png")))),

            // Yellow snake tiles
            Map.entry(YELLOW_SNAKE + HEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_head/n.png")))),
            Map.entry(YELLOW_SNAKE + HEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_head/e.png")))),
            Map.entry(YELLOW_SNAKE + HEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_head/s.png")))),
            Map.entry(YELLOW_SNAKE + HEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_head/w.png")))),

            Map.entry(YELLOW_SNAKE + BODY_NS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_body/ns.png")))),
            Map.entry(YELLOW_SNAKE + BODY_EW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_body/ew.png")))),
            Map.entry(YELLOW_SNAKE + BODY_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_body/ne.png")))),
            Map.entry(YELLOW_SNAKE + BODY_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_body/es.png")))),
            Map.entry(YELLOW_SNAKE + BODY_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_body/sw.png")))),
            Map.entry(YELLOW_SNAKE + BODY_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_body/wn.png")))),

            Map.entry(YELLOW_SNAKE + TAIL_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_tail/n.png")))),
            Map.entry(YELLOW_SNAKE + TAIL_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_tail/e.png")))),
            Map.entry(YELLOW_SNAKE + TAIL_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_tail/s.png")))),
            Map.entry(YELLOW_SNAKE + TAIL_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_tail/w.png")))),

            Map.entry(YELLOW_SNAKE + DEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/n.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/e.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/s.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/w.png")))),

            Map.entry(YELLOW_SNAKE + DEAD_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/ne.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_NW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/nw.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_EN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/en.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/es.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_SE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/se.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/sw.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_WS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/ws.png")))),
            Map.entry(YELLOW_SNAKE + DEAD_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/yellow/snake_dead/wn.png")))),

            // Red snake tiles
            Map.entry(RED_SNAKE + HEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_head/n.png")))),
            Map.entry(RED_SNAKE + HEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_head/e.png")))),
            Map.entry(RED_SNAKE + HEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_head/s.png")))),
            Map.entry(RED_SNAKE + HEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_head/w.png")))),

            Map.entry(RED_SNAKE + BODY_NS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_body/ns.png")))),
            Map.entry(RED_SNAKE + BODY_EW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_body/ew.png")))),
            Map.entry(RED_SNAKE + BODY_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_body/ne.png")))),
            Map.entry(RED_SNAKE + BODY_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_body/es.png")))),
            Map.entry(RED_SNAKE + BODY_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_body/sw.png")))),
            Map.entry(RED_SNAKE + BODY_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_body/wn.png")))),

            Map.entry(RED_SNAKE + TAIL_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_tail/n.png")))),
            Map.entry(RED_SNAKE + TAIL_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_tail/e.png")))),
            Map.entry(RED_SNAKE + TAIL_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_tail/s.png")))),
            Map.entry(RED_SNAKE + TAIL_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_tail/w.png")))),

            Map.entry(RED_SNAKE + DEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/n.png")))),
            Map.entry(RED_SNAKE + DEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/e.png")))),
            Map.entry(RED_SNAKE + DEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/s.png")))),
            Map.entry(RED_SNAKE + DEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/w.png")))),

            Map.entry(RED_SNAKE + DEAD_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/ne.png")))),
            Map.entry(RED_SNAKE + DEAD_NW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/nw.png")))),
            Map.entry(RED_SNAKE + DEAD_EN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/en.png")))),
            Map.entry(RED_SNAKE + DEAD_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/es.png")))),
            Map.entry(RED_SNAKE + DEAD_SE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/se.png")))),
            Map.entry(RED_SNAKE + DEAD_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/sw.png")))),
            Map.entry(RED_SNAKE + DEAD_WS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/ws.png")))),
            Map.entry(RED_SNAKE + DEAD_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/red/snake_dead/wn.png")))),

            // Purple snake tiles
            Map.entry(PURPLE_SNAKE + HEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_head/n.png")))),
            Map.entry(PURPLE_SNAKE + HEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_head/e.png")))),
            Map.entry(PURPLE_SNAKE + HEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_head/s.png")))),
            Map.entry(PURPLE_SNAKE + HEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_head/w.png")))),

            Map.entry(PURPLE_SNAKE + BODY_NS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_body/ns.png")))),
            Map.entry(PURPLE_SNAKE + BODY_EW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_body/ew.png")))),
            Map.entry(PURPLE_SNAKE + BODY_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_body/ne.png")))),
            Map.entry(PURPLE_SNAKE + BODY_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_body/es.png")))),
            Map.entry(PURPLE_SNAKE + BODY_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_body/sw.png")))),
            Map.entry(PURPLE_SNAKE + BODY_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_body/wn.png")))),

            Map.entry(PURPLE_SNAKE + TAIL_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_tail/n.png")))),
            Map.entry(PURPLE_SNAKE + TAIL_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_tail/e.png")))),
            Map.entry(PURPLE_SNAKE + TAIL_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_tail/s.png")))),
            Map.entry(PURPLE_SNAKE + TAIL_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_tail/w.png")))),

            Map.entry(PURPLE_SNAKE + DEAD_N, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/n.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_E, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/e.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_S, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/s.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_W, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/w.png")))),

            Map.entry(PURPLE_SNAKE + DEAD_NE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/ne.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_NW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/nw.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_EN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/en.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_ES, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/es.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_SE, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/se.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_SW, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/sw.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_WS, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/ws.png")))),
            Map.entry(PURPLE_SNAKE + DEAD_WN, new ImageIcon(Objects.requireNonNull(TileTypes.class.getResource("/assets/snake/purple/snake_dead/wn.png"))))

    ));
}
