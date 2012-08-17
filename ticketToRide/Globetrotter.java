import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Constructs the globetrotter card 
 * image on the game board
 * 
 * @author (Stephane Del Belso, Devin Hurley,
 *      Bryan Leicht, Serena Moore, Mike Paff)
 * @version (4/30/2012)
 */
public class Globetrotter {
    protected BufferedImage globetrotter;

    /**
     * Constructor for objects of class 
     * Globetrotter
     */
    public Globetrotter() throws IOException {
        globetrotter = ImageIO.read(
            new File("globetrottercard.png"));
    }

    /**
     * paint method for Globetrotter class
     * 
     * @param Graphics
     *            g
     * @return void
     */
    public void paintComponent(Graphics g) {

        g.drawImage(globetrotter, 650, 130, null);

    }
}
