import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * Creates TrainCard objects.
 * 
 * @author Stephanie Del Belso, Devin Hurley, 
 * Bryan Leicht, Serena Moore, Mike Paff
 * @version Final Version
 */
public class TrainCard
{
    protected BufferedImage image;
    protected String color;
    protected TrainCardImages cardCols;
    
    /**
     * Constructor for objects of class TrainCard
     */
    public TrainCard(String color) throws IOException
    {
        cardCols = new TrainCardImages();
        image = cardCols.getImage(color);
        this.color = color;
    }
    
    /**
     * Gets the color of the train card.
     * 
     * @param
     * @return String
     */
    public String getColor()
    {
        return this.color;
    }
    
    /**
     * Gets the image of the train card.
     * 
     * @param
     * @return BufferedImage
     */
    public BufferedImage getImage()
    {
        return this.image;
    }

}
