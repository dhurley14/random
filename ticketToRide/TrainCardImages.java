
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * Reads in train card images and adds them to images arrayList.
 * 
 * @author Stephanie Del Belso, Devin Hurley, 
 * Bryan Leicht, Serena Moore, Mike Paff
 * @version Final Version
 */
public class TrainCardImages
{
    protected ArrayList<BufferedImage> images;
    protected BufferedImage redCard;
    protected BufferedImage blueCard;
    protected BufferedImage blackCard;
    protected BufferedImage greenCard;
    protected BufferedImage pinkCard;
    protected BufferedImage orangeCard;
    protected BufferedImage yellowCard;
    protected BufferedImage whiteCard;
    protected BufferedImage locomotiveCard;
    

    /**
     * Constructor for objects of class TrainCards
     * 
     */
    public TrainCardImages() throws IOException
    {
        images = new ArrayList<BufferedImage>();
        redCard = ImageIO.read(new File ("redticket.png"));
        blueCard = ImageIO.read(new File ("blueticket.png"));
        blackCard = ImageIO.read(new File ("blackticket.png"));
        greenCard = ImageIO.read(new File ("greenticket.png"));
        pinkCard = ImageIO.read(new File ("pinkticket.png"));
        orangeCard = ImageIO.read(new File ("orangeticket.png"));
        yellowCard = ImageIO.read(new File ("yellowticket.png"));
        whiteCard = ImageIO.read(new File ("whiteticket.png"));
        locomotiveCard = ImageIO.read(new File ("locoticket.png"));
        images.add(redCard);
        images.add(blueCard);
        images.add(blackCard);
        images.add(greenCard);
        images.add(pinkCard);
        images.add(orangeCard);
        images.add(yellowCard);
        images.add(whiteCard);
        images.add(locomotiveCard);
        
    }
    /**
     * Gets the image from images arrayList.
     * 
     * @param String color
     * @return BufferedImage
     */
    public BufferedImage getImage(String color)
    {
        if(color.equals("redCard"))
        {
            return images.get(0);
        }
        else if(color.equals("blueCard"))
        {
            return images.get(1);
        }
        else if(color.equals("blackCard"))
        {
            return images.get(2);
        }
        else if(color.equals("greenCard"))
        {
            return images.get(3);
        }
        else  if(color.equals("pinkCard"))
        {
            return images.get(4);
        }
        else if(color.equals("orangeCard"))
        {
            return images.get(5);
        }
        else if(color.equals("yellowCard"))
        {
            return images.get(6);
        }
        else if(color.equals("whiteCard"))
        {
            return images.get(7);
        }
        else if(color.equals("locomotiveCard"))
        {
            return images.get(8);
        }
        return null;
       
    }
        

}
