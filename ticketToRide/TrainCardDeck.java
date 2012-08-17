import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;
/**
 * Creates a deck of train cards and shuffles the deck.
 * 
 * @author Stephanie Del Belso, Devin Hurley, 
 * Bryan Leicht, Serena Moore, Mike Paff
 * @version Final Version
 */
public class TrainCardDeck
{
    protected ArrayList<TrainCard> deck;
    protected ArrayList<TrainCard> discardDeck;
    protected ArrayList<TrainCard> possibleCards;
    protected BufferedImage deckCards;
    /**
     * Constructor for objects of class TrainCardDeck
     */
    public TrainCardDeck() throws IOException
    {
        deckCards = ImageIO.read(new File("deck.png"));
        discardDeck = new ArrayList<TrainCard>();
        deck = new ArrayList<TrainCard>();
        possibleCards = new ArrayList<TrainCard>();

        possibleCards.add(new TrainCard("redCard"));
        possibleCards.add(new TrainCard("blueCard"));
        possibleCards.add(new TrainCard("greenCard"));
        possibleCards.add(new TrainCard("orangeCard"));
        possibleCards.add(new TrainCard("yellowCard"));
        possibleCards.add(new TrainCard("pinkCard"));
        possibleCards.add(new TrainCard("whiteCard"));
        possibleCards.add(new TrainCard("blackCard"));
        possibleCards.add(new TrainCard("locomotiveCard"));
        for(int i = 0;  i < 12; i++)
        {
            deck.add(new TrainCard("redCard"));
            deck.add(new TrainCard("blueCard"));
            deck.add(new TrainCard("greenCard"));
            deck.add(new TrainCard("orangeCard"));
            deck.add(new TrainCard("yellowCard"));
            deck.add(new TrainCard("pinkCard"));
            deck.add(new TrainCard("whiteCard"));
            deck.add(new TrainCard("blackCard"));
        }
        for(int i = 0; i < 14; i++)
        {
            deck.add(new TrainCard("locomotiveCard"));
        }
        Collections.shuffle(deck);

    }

    /**
     * Takes a TrainCard from the top of the deck 
     * to add to the players
     * hand and then removes it from the deck.
     * 
     * @param
     * @return TrainCard
     */
    public TrainCard draw()
    {
        TrainCard card = deck.get(0);
        deck.remove(0);
        return card;
    }

    /**
     * Adds the train card to the dicardDeck to be reshuffled 
     * when trainCards deck is empty.
     * 
     * @param TrainCard card
     * @return void
     */
    public void discard(TrainCard card){
        discardDeck.add(card);
    }

    /**
     * When the discardDeck contains 40 or more cards,
     * cards in discardDeck are added back to 
     * trainCard deck and shuffled.
     * 
     * @param
     * @return void
     */
    public void reshuffle(){
        if(discardDeck.size() > 40){
            int size = discardDeck.size();
            for(int i = 0; i < size; i++){
                deck.add(discardDeck.get(0));
                discardDeck.remove(0);
            }
        }
        Collections.shuffle(deck);
    }

    /**
     * Draws deckCards image.
     * 
     * @param Graphics g
     * @return void
     */
    public void paintComponent(Graphics g)
    {

        g.drawImage(deckCards, 500, 10, null);

    }
}
