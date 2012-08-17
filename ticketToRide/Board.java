import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;

/**
 * Write a description of class Board here.
 * 
 * @author Stephane Del Belso, Devin Hurley,
 *      Bryan Leicht, Serena Moore, Mike Paff
 * @version 04/30/2012
 */
public class Board {
    protected BufferedImage boardImage;

    /**
     * Constructor for objects of class Board
     */
    public Board() throws IOException {
        boardImage = ImageIO.read(
            new File("gameboard.jpg"));

    }

    /**
     * Paint method for the Board class
     * 
     * @param Graphics
     *            g
     * @return void
     */
    public void paintComponent(Graphics g) {
        g.drawImage(boardImage, 0, 0, null);

    }

}
