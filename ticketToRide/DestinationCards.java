import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Sets the destination cards ands adds them to 
 *      the deck of destination cards
 * 
 * @author (Stephane Del Belso, Devin Hurley,
 * Bryan Leicht, Serena Moore, Mike Paff)
 * @version (04/30/2012)
 */
public class DestinationCards {
protected BufferedImage destDeck;
protected ArrayList<String> dCards;
protected static final String TAMPERE_TALLINN
 = "Tampere to Tallinn - 3 Points";
protected static final String TORNIO_IMATRA
     = "Tornio to Imatra - 6 Points";
protected static final String OSLO_HELSINKI
     = "Oslo to Helsinki - 8 Points";
protected static final String OSLO_MOIRANA
     = "Oslo to Moirana - 10 Points";
protected static final String OSLO_STOCKHOLM
     = "Oslo to Stockholm - 4 Points";
protected static final String KOBENHAVN_OULU
     = "Kobenhavn to Oulu - 14 Points";
protected static final String KOBENHAVN_MURMANSK
     = "Kobenhavn to Murmansk - 24 Points";
protected static final String OSLO_HONNINGSVAG
     = "Oslo to Honningsvag - 21 Points";
protected static final String STOCKHOLM_TROMSO
     = "Stockholm to Tromso - 17 Points";
protected static final String BERGEN_NARVIK
     = "Bergen to Narvik - 16 Points";
protected static final String BERGEN_TORNIO
     = "Bergen to Tornio - 17 Points";
protected static final String KOBENHAVN_NARVIK
     = "Kobenhavn to Narvik - 18 Points";
protected static final String STAVANGER_ROVANIEMI
     = "Stavanger to Rovaniemi - 18 Points";
protected static final String STOCKHOLM_KAJAANI
     = "Stockholm to Kajaani - 10 Points";
protected static final String STOCKHOLM_BERGEN
     = "Stockholm to Bergen - 8 Points";
protected static final String TURKU_TRONDHEIM
     = "Turku to Trondheim - 10 Points";
protected static final String TAMPERE_KRISTIANSAND
     = "Tampere to Kristiansand - 10 Points";
protected static final String GOTEBORG_OULU
     = "Goteborg to Oulu - 12 Points";
protected static final String KRISTIANSAND_MOIRANA
     = "Kristiansand to Moirana - 12 Points";
protected static final String NARVIK_MURMANSK
     = "Narvik to Murmansk - 12 Points";
protected static final String ARHUS_LILLEHAMMER
     = "Arhus to Lillehammer - 6 Points";
protected static final String TROMSO_VAASA
     = "Tromso to Vaasa - 11 Points";
protected static final String HELSINKI_OSTERSUND
     = "Helsinki to Ostersund - 8 Points";
protected static final String OSLO_VAASA
     = "Oslo to Vaasa - 9 Points";
protected static final String BERGEN_KOBENHAVN
     = "Bergen to Kobenhavn - 8 Points";
protected static final String HELSINKI_KOBENHAVN
     = "Helsinki to Kobenhavn - 10 Points";
protected static final String OREBRO_KUOPIO
     = "Orebro to Kuopio - 10 Points";
protected static final String GOTEBORG_ANDALSNES
     = "Goteborg to Andalsnes - 6 Points";
protected static final String HELSINKI_KIRUNA
     = "Helsinki to Kiruna - 10 Points";
protected static final String HELSINKI_KIRKENES
     = "Helsinki to Kirkenes - 15 Points";
protected static final String OSLO_KOBENHAVN
     = "Oslo to Kobenhavn - 4 Points";
protected static final String ALBORG_UMEA
     = "Alborg to Umea - 11 Points";
protected static final String BERGEN_TRONDHEIM
     = "Bergen to Trondheim - 7 Points";
protected static final String ALBORG_NORRKOPING
     = "Alborg to Norrkoping - 5 Points";
protected static final String STOCKHOLM_KOBENHAVN
     = "Stockholm to Kobenhavn - 6 Points";
protected static final String SUNDSVALL_LAHTI
     = "Sundsvall to Lahti - 6 Points";
protected static final String HELSINKI_LIEKSA
     = "Helsinki to Lieksa - 5 Points";
protected static final String TAMPERE_BODEN
     = "Tampere to Boden - 6 Points";
protected static final String STAVANGER_KARLSKRONA
     = "Stavanger to Karlskrona - 8 Points";
protected static final String OSLO_STAVANGER
     = "Oslo to Stavanger - 4 Points";
protected static final String NORRKOPPING_BODEN
     = "Norrkoping to Boden - 11 Points";
protected static final String STOCKHOLM_UMEA
     = "Stockholm to Umea - 7 Points";
protected static final String NARVIK_TALLINN
     = "Narvik to Tallinn - 13 Points";
protected static final String GOTEBORG_TURKU
     = "Goteborg to Turku - 7 Points";
protected static final String STOCKHOLM_IMATRA
     = "Stockholm to Imatra - 7 Points";
protected static final String HELSINKI_BERGEN
     = "Helsinki to Bergen - 12 Points";

    /**
     * Constructor for objects of class DestinationCards
     */
    public DestinationCards() throws IOException {
        destDeck = ImageIO.read(
            new File("destinationcards.png"));
        dCards = new ArrayList<String>();
        dCards.add(TAMPERE_TALLINN);
        dCards.add(TORNIO_IMATRA);
        dCards.add(OSLO_HELSINKI);
        dCards.add(OSLO_MOIRANA);
        dCards.add(OSLO_STOCKHOLM);
        dCards.add(KOBENHAVN_OULU);
        dCards.add(KOBENHAVN_MURMANSK);
        dCards.add(OSLO_HONNINGSVAG);
        dCards.add(STOCKHOLM_TROMSO);
        dCards.add(BERGEN_NARVIK);
        dCards.add(BERGEN_TORNIO);
        dCards.add(KOBENHAVN_NARVIK);
        dCards.add(STAVANGER_ROVANIEMI);
        dCards.add(STOCKHOLM_KAJAANI);
        dCards.add(STOCKHOLM_BERGEN);
        dCards.add(TURKU_TRONDHEIM);
        dCards.add(TAMPERE_KRISTIANSAND);
        dCards.add(GOTEBORG_OULU);
        dCards.add(KRISTIANSAND_MOIRANA);
        dCards.add(NARVIK_MURMANSK);
        dCards.add(ARHUS_LILLEHAMMER);
        dCards.add(TROMSO_VAASA);
        dCards.add(HELSINKI_OSTERSUND);
        dCards.add(OSLO_VAASA);
        dCards.add(BERGEN_KOBENHAVN);
        dCards.add(HELSINKI_KOBENHAVN);
        dCards.add(OREBRO_KUOPIO);
        dCards.add(GOTEBORG_ANDALSNES);
        dCards.add(HELSINKI_KIRUNA);
        dCards.add(HELSINKI_KIRKENES);
        dCards.add(OSLO_KOBENHAVN);
        dCards.add(ALBORG_UMEA);
        dCards.add(BERGEN_TRONDHEIM);
        dCards.add(ALBORG_NORRKOPING);
        dCards.add(STOCKHOLM_KOBENHAVN);
        dCards.add(SUNDSVALL_LAHTI);
        dCards.add(HELSINKI_LIEKSA);
        dCards.add(TAMPERE_BODEN);
        dCards.add(STAVANGER_KARLSKRONA);
        dCards.add(OSLO_STAVANGER);
        dCards.add(NORRKOPPING_BODEN);
        dCards.add(STOCKHOLM_UMEA);
        dCards.add(NARVIK_TALLINN);
        dCards.add(GOTEBORG_TURKU);
        dCards.add(STOCKHOLM_IMATRA);
        dCards.add(HELSINKI_BERGEN);

        Collections.shuffle(dCards);
    }

    /**
     * Accessor method to get the destination cards dealt
     * 
     * @param int n
     * @return String destination card at that position
     */
    public String getDCard(int n) {
        return dCards.get(n);
    }

    /**
     * Mutator method to remove a destination card 
     * from the deck (arraylist) of
     * destination cards
     * 
     * @param
     * @return void
     */
    public void removeDCard() {
        dCards.remove(0);
    }

    /**
     * Paint method for class DestinationCards
     * 
     * @param Graphics
     *            g
     * @return void
     */
    public void paintComponent(Graphics g) {
        g.drawImage(destDeck, 900, 150, null);
    }
}
