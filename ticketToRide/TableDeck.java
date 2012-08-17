import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.IOException;
import java.awt.Color;
/**
 * ArrayList of TrainCards that are flipped over for the player 
 * to choose from.
 * 
 * @author Stephanie Del Belso, Devin Hurley, Bryan Leicht, 
 * Serena Moore, Mike Paff
 * @version Final Version
 */
public class TableDeck
{
    protected ArrayList<TrainCard> tableDeck;
    protected TrainCardDeck tcDeck;
    /**
     * Constructor for objects of class TableDeck
     */
    public TableDeck() throws IOException
    {
        tableDeck = new ArrayList<TrainCard>();
        tcDeck = new TrainCardDeck();
    }
    
    /**
     * Adds a trainCard to tableDeck
     * 
     * @param TrainCard card, int i
     * @return void
     */
    public void addToDeck(TrainCard card, int i)
    {
        tableDeck.add(i, card);
    }
    
    /**
     * Returns the TrainCard at position clicked.
     * 
     * @param int n
     * @return TrainCard
     */
    public TrainCard getCard(int n){
        return tableDeck.get(n);
    }

    /**
     * Removes TrainCard from tcDeck.
     * 
     * @param trainCard card
     * @return void
     */
    public void discard(TrainCard card){
        tcDeck.discard(card);
        
    }
    
    /**
     * Removes TrainCard from tableDeck(cards flipped over on board)
     * 
     * @param int n
     * @return void
     */
    public void remove(int n){
        tableDeck.remove(n);
    }
    
    /**
     * Clears the last TrainCards from tableDeck and repaints
     * the new tableDeck.
     * 
     * @param Graphics g
     * @return void
     */
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect( 600, 5, 800, 75);
        for(int i = 0; i < tableDeck.size(); i++)
        {
            BufferedImage image = tableDeck.get(i).getImage();
            g.drawImage(image, 110* (i+6), 10, null);
        }
    }
}
