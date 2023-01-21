package snake.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import snake.types.TileTypes;

/**
 *
 * @author samliu
 */
public class Configuration {
    
    private static final String PATH = "./configuration.txt"; 
    private static int p1Type, p2Type;
    
    private static boolean doesExist() {
        File f = new File(PATH);
        return f.exists();
    }
    
    public static void resetFile() {
        try {
            FileWriter fw = new FileWriter(PATH, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(TileTypes.GREEN_SNAKE);
            pw.println(TileTypes.BLUE_SNAKE);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void refresh(int attempt) {
        if (attempt > 3) {
            throw new RuntimeException("Erorr reading file after 3 attempts");
        }
        if (attempt > 1) {
            resetFile();
        }
        if (!doesExist()) {
            resetFile();
        }
        try {
            FileReader fr = new FileReader(PATH);
            BufferedReader br = new BufferedReader(fr);
            String p1 = br.readLine();
            String p2 = br.readLine();
            br.close();
            p1Type = Integer.valueOf(p1);
            p2Type = Integer.valueOf(p2);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            resetFile();
            refresh(attempt+1);
        }
    }
    
    public static void refresh() {
        refresh(1);
    }
    
    public static int getP1Type() {
        return p1Type;
    }
    
    public static int getP2Type() {
        return p2Type;
    }
    
    public static void setP1Type(int p1Type, int attempt) {
        if (attempt > 3) {
            throw new RuntimeException("Error writing to file after three attempts.");
        }
        if (attempt > 1) {
            resetFile();
        }
        if (!doesExist()) {
            resetFile();
        }
        try {
            FileWriter fw = new FileWriter(PATH, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(p1Type);
            pw.println(p2Type);
            pw.close();
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            setP1Type(p1Type, attempt+1);
        }
    }
    
    public static void setP1Type(int p1Type) {
        setP1Type(p1Type, 0);
    }
    
    public static void setP2Type(int p2Type, int attempt) {
        if (attempt > 3) {
            throw new RuntimeException("Error writing to file after three attempts.");
        }
        if (attempt > 1) {
            resetFile();
        }
        if (!doesExist()) {
            resetFile();
        }
        try {
            FileWriter fw = new FileWriter(PATH, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(p1Type);
            pw.println(p2Type);
            pw.close();
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            setP2Type(p2Type, attempt + 1);
        }
    }
    
    public static void setP2Type(int p2Type) {
        setP2Type(p2Type, 0);
    }
}
