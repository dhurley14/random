import java.util.*;
import java.io.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

/**
 * Player allows us to construct and keep track 
 * of players for the game TicketToRide
 * 
 * @author (Stephanie Del Belso, Devin Hurley,
 * Bryan Leicht, Serena Moore, Mike Paff)
 * @version (final version)
 */
public class Player {
    protected int score;
    protected int numTrains;
    protected boolean turn;
    protected boolean firstTurn;
    protected ArrayList<TrainCard> playerTrainHand;
    protected ArrayList<String> playerDestCards;
    protected String name;
    protected String color;
    protected ArrayList<String> routes;
    protected int numRed;
    protected int numBlue;
    protected int numBlack;
    protected int numGreen;
    protected int numPink;
    protected int numOrange;
    protected int numYellow;
    protected int numWhite;
    protected int numLoco;
    protected int totalCards;
    protected String city1, city2;
    protected int value;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name, String color) {
        score = 0;
        numTrains = 40;
        playerTrainHand = new ArrayList<TrainCard>();
        playerDestCards = new ArrayList<String>();
        routes = new ArrayList<String>();
        this.color = color;
        this.name = name;
        turn = false;
        firstTurn = true;
        numRed = 0;
        numBlue = 0;
        numBlack = 0;
        numGreen = 0;
        numPink = 0;
        numOrange = 0;
        numYellow = 0;
        numWhite = 0;
        numLoco = 0;
        totalCards = 0;
    }

    /**
     * Accessor method
     * 
     * @param
     * @return String color
     */
    public String getColor() {
        return color;
    }

    /**
     * Adds a train card to a player's hand, 
     * constructed as an arraylist of cards that player owns
     * 
     * @param TrainCard card
     * @return void
     * 
     */
    public void addToHand(TrainCard card) {
        playerTrainHand.add(card);
        if (card.getColor().equals("redCard")) {
            numRed++;
        }
        if (card.getColor().equals("blueCard")) {
            numBlue++;
        }
        if (card.getColor().equals("greenCard")) {
            numGreen++;
        }
        if (card.getColor().equals("orangeCard")) {
            numOrange++;
        }
        if (card.getColor().equals("yellowCard")) {
            numYellow++;
        }
        if (card.getColor().equals("pinkCard")) {
            numPink++;
        }
        if (card.getColor().equals("whiteCard")) {
            numWhite++;
        }
        if (card.getColor().equals("blackCard")) {
            numBlack++;
        }
        if (card.getColor().equals("locomotiveCard")) {
            numLoco++;
        }
    }

    /**
     * removes the specified TrainCard from the players hand.
     * 
     * @param TrainCard
     *            card
     * @return void
     */
    public void removeFromHand(TrainCard card) {
        playerTrainHand.remove(card);
        if (card.getColor().equals("redCard")) {
            numRed--;
        }
        if (card.getColor().equals("blueCard")) {
            numBlue--;
        }
        if (card.getColor().equals("greenCard")) {
            numGreen--;
        }
        if (card.getColor().equals("orangeCard")) {
            numOrange--;
        }
        if (card.getColor().equals("yellowCard")) {
            numYellow--;
        }
        if (card.getColor().equals("pinkCard")) {
            numPink--;
        }
        if (card.getColor().equals("whiteCard")) {
            numWhite--;
        }
        if (card.getColor().equals("blackCard")) {
            numBlack--;
        }
        if (card.getColor().equals("locomotiveCard")) {
            numLoco--;
        }
    }

    /**
     * Adds a destination card to the players arrayList.
     * 
     * @param String
     *            dCard
     * @return void
     */
    public void addDestCard(String dCard) {
        playerDestCards.add(dCard);
    }

    /**
     * Adds a route to the routes the player captured
     * 
     * @param String
     *            route
     * @return void
     */
    public void addRoute(String route) {
        routes.add(route);
    }

    /**
     * Method to add to the score of the player.
     * 
     * @param int n
     * @return void
     * 
     */
    public void addToScore(int n) {
        score += n;
    }

    /**
     * Method to decrement the score of the player
     * 
     * @param int n
     * @return void
     * 
     */
    public void decScore(int n) {
        score = score - n;
    }

    /**
     * Accessor method for the score of the player
     * 
     * @param
     * @return int score
     * 
     */
    public int getScore() {
        return score;
    }

    /**
     * Mutator method to decriment the number of 
     * trains the player has
     * 
     * @param int n
     * @return void
     * 
     */
    public void decrimentTrains(int n) {
        numTrains = numTrains - n;
    }

    /**
     * Accessor method to get the number of 
     * trains left for the player
     * 
     * @param
     * @return int numTrains
     * 
     */
    public int getNumTrains() {
        return numTrains;
    }

    /**
     * Used to compute the final score of each player
     * 
     * @param
     * @return int totalCards
     * 
     */
    public int getTotalCards() {
        totalCards = numRed + numBlue + numGreen + numYellow
        + numBlack + numOrange + numWhite + numPink + numLoco;
        return totalCards;
    }

    /**
     * Accessor method that is used to get the 
     * number of trains the player has
     * 
     * @param String
     *            color
     * @return int number of train cards of that spec. color
     * 
     */
    public int getTrains(String color) {
        if (color.equals("Red")) {
            return numRed;
        }
        if (color.equals("Blue")) {
            return numBlue;
        }
        if (color.equals("Green")) {
            return numGreen;
        }
        if (color.equals("Orange")) {
            return numOrange;
        }
        if (color.equals("Yellow")) {
            return numYellow;
        }
        if (color.equals("Black")) {
            return numBlack;
        }
        if (color.equals("Purple")) {
            return numPink;
        }
        if (color.equals("White")) {
            return numWhite;
        }
        if (color.equals("Locomotive")) {
            return numLoco;
        }

        return -1;
    }

    /**
     * Sets the turn of the player
     * 
     * @param boolean t turn either true or false
     * @return void
     * 
     */
    public void setTurn(boolean t) {
        turn = t;
    }

    /**
     * Accessor method to see if it is the
     * players turn to go on the board
     * 
     * @param
     * @return boolean turn
     * 
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * Mutator method to set the first 
     * turn of the player
     * 
     * @param boolean f
     * @return void
     * 
     */
    public void setFirstTurn(boolean f) {
        firstTurn = f;
    }

    /**
     * Accessor method to tell us if 
     * there is a players first turn.
     * 
     * @param
     * @return boolean firstTurn
     * 
     */
    public boolean isFirstTurn() {
        return firstTurn;
    }

    /**
     * checks to see what destination cards
     * are contacting each other
     * 
     * @param String
     *            city
     * @return boolean
     * 
     */
    public boolean destinationCardCheck(String city) {
        // use recursion

        return false;
    }

    /**
     * Supposed to calculate longest path
     * 
     * @param
     * @return int longest of the paths
     * 
     * 
     */
//     public int longestPath() {
//         return -1;
//     }

    /**
     * Method to set the destination cities
     * and their corresponding point values
     * 
     * @param String
     *            destCard
     * @return void
     * 
     * 
     */
    public void setDestinationCities(String destCard) {
        city1 = "";
        city2 = "";
        value = 0;
        if (destCard.equals(DestinationCards.TAMPERE_TALLINN)) {
            city1 = "Tampere";
            city2 = "Tallinn";
            value = 3;
        }
        if (destCard.equals(DestinationCards.TORNIO_IMATRA)) {
            city1 = "Tornio";
            city2 = "Imatra";
            value = 6;
        }
        if (destCard.equals(DestinationCards.OSLO_HELSINKI)) {
            city1 = "Oslo";
            city2 = "Helsinki";
            value = 8;
        }
        if (destCard.equals(DestinationCards.OSLO_MOIRANA)) {
            city1 = "Oslo";
            city2 = "Moirnana";
            value = 10;
        }
        if (destCard.equals(DestinationCards.OSLO_STOCKHOLM)) {
            city1 = "Oslo";
            city2 = "Stockholm";
            value = 4;
        }
        if (destCard.equals(DestinationCards.KOBENHAVN_OULU)) {
            city1 = "Kobenhavn";
            city2 = "Oulu";
            value = 14;
        }
        if (destCard.equals(DestinationCards.KOBENHAVN_MURMANSK)) {
            city1 = "Kobenhavn";
            city2 = "Murmansk";
            value = 24;
        }
        if (destCard.equals(DestinationCards.OSLO_HONNINGSVAG)) {
            city1 = "Oslo";
            city2 = "Honningsvag";
            value = 21;
        }
        if (destCard.equals(DestinationCards.STOCKHOLM_TROMSO)) {
            city1 = "Stockholm";
            city2 = "Tromso";
            value = 17;
        }
        if (destCard.equals(DestinationCards.BERGEN_NARVIK)) {
            city1 = "Bergen";
            city2 = "Narvik";
            value = 16;
        }
        if (destCard.equals(DestinationCards.BERGEN_TORNIO)) {
            city1 = "Bergen";
            city2 = "Tornio";
            value = 17;
        }
        if (destCard.equals(DestinationCards.KOBENHAVN_NARVIK)) {
            city1 = "Kobenhavn";
            city2 = "Narvik";
            value = 18;
        }
        if (destCard.equals(DestinationCards.KOBENHAVN_NARVIK)) {
            city1 = "Kobenhavn";
            city2 = "Narvik";
            value = 18;
        }
        if (destCard.equals(DestinationCards.STAVANGER_ROVANIEMI)) {
            city1 = "Stavanger";
            city2 = "Rovaniemi";
            value = 18;
        }
        if (destCard.equals(DestinationCards.STOCKHOLM_KAJAANI)) {
            city1 = "Stockholm";
            city2 = "Kajaani";
            value = 10;
        }
        if (destCard.equals(DestinationCards.STOCKHOLM_BERGEN)) {
            city1 = "Stockholm";
            city2 = "Bergen";
            value = 8;
        }
        if (destCard.equals(DestinationCards.TURKU_TRONDHEIM)) {
            city1 = "Turku";
            city2 = "Trondheim";
            value = 10;
        }
        if (destCard.equals(DestinationCards.TAMPERE_KRISTIANSAND)) {
            city1 = "Tampere";
            city2 = "Kristiansand";
            value = 10;
        }
        if (destCard.equals(DestinationCards.GOTEBORG_OULU)) {
            city1 = "Goteborg";
            city2 = "Oulu";
            value = 12;
        }
        if (destCard.equals(DestinationCards.KRISTIANSAND_MOIRANA)) {
            city1 = "Kristiansand";
            city2 = "Moirana";
            value = 12;
        }
        if (destCard.equals(DestinationCards.NARVIK_MURMANSK)) {
            city1 = "Narvik";
            city2 = "Murmansk";
            value = 12;
        }
        if (destCard.equals(DestinationCards.ARHUS_LILLEHAMMER)) {
            city1 = "Arhus";
            city2 = "Lillehammer";
            value = 6;
        }
        if (destCard.equals(DestinationCards.TROMSO_VAASA)) {
            city1 = "Tromso";
            city2 = "Vaasa";
            value = 11;
        }
        if (destCard.equals(DestinationCards.HELSINKI_OSTERSUND)) {
            city1 = "Helsinki";
            city2 = "Ostersund";
            value = 8;
        }
        if (destCard.equals(DestinationCards.OSLO_VAASA)) {
            city1 = "Oslo";
            city2 = "Vaasa";
            value = 9;
        }
        if (destCard.equals(DestinationCards.BERGEN_KOBENHAVN)) {
            city1 = "Bergen";
            city2 = "Kobenhavn";
            value = 8;
        }
        if (destCard.equals(DestinationCards.HELSINKI_KOBENHAVN)) {
            city1 = "Helsinki";
            city2 = "Kobenhavn";
            value = 10;
        }
        if (destCard.equals(DestinationCards.OREBRO_KUOPIO)) {
            city1 = "Orebro";
            city2 = "Kuopio";
            value = 10;
        }
        if (destCard.equals(DestinationCards.GOTEBORG_ANDALSNES)) {
            city1 = "Goteborg";
            city2 = "Andalsnes";
            value = 6;
        }
        if (destCard.equals(DestinationCards.HELSINKI_KIRUNA)) {
            city1 = "Helsinki";
            city2 = "Kiruna";
            value = 10;
        }
        if (destCard.equals(DestinationCards.HELSINKI_KIRKENES)) {
            city1 = "Helsinki";
            city2 = "Kirkenes";
            value = 15;
        }
        if (destCard.equals(DestinationCards.OSLO_KOBENHAVN)) {
            city1 = "Oslo";
            city2 = "Kobenhavn";
            value = 4;
        }
        if (destCard.equals(DestinationCards.ALBORG_UMEA)) {
            city1 = "Alborg";
            city2 = "Umea";
            value = 11;
        }
        if (destCard.equals(DestinationCards.BERGEN_TRONDHEIM)) {
            city1 = "Bergen";
            city2 = "Trondheim";
            value = 7;
        }
        if (destCard.equals(DestinationCards.ALBORG_NORRKOPING)) {
            city1 = "Alborg";
            city2 = "Norrkoping";
            value = 5;
        }
        if (destCard.equals(DestinationCards.STOCKHOLM_KOBENHAVN)) {
            city1 = "Stockholm";
            city2 = "Kobenhavn";
            value = 6;
        }
        if (destCard.equals(DestinationCards.SUNDSVALL_LAHTI)) {
            city1 = "Sundsvall";
            city2 = "Lahti";
            value = 6;
        }
        if (destCard.equals(DestinationCards.HELSINKI_LIEKSA)) {
            city1 = "Helsinki";
            city2 = "Lieksa";
            value = 5;
        }
        if (destCard.equals(DestinationCards.TAMPERE_BODEN)) {
            city1 = "Tampere";
            city2 = "Boden";
            value = 6;
        }
        if (destCard.equals(DestinationCards.STAVANGER_KARLSKRONA)) {
            city1 = "Stavanger";
            city2 = "Karlskrona";
            value = 8;
        }
        if (destCard.equals(DestinationCards.OSLO_STAVANGER)) {
            city1 = "Oslo";
            city2 = "Stavanger";
            value = 4;
        }
        if (destCard.equals(DestinationCards.NORRKOPPING_BODEN)) {
            city1 = "Norrkopping";
            city2 = "Boden";
            value = 11;
        }
        if (destCard.equals(DestinationCards.STOCKHOLM_UMEA)) {
            city1 = "Stockholm";
            city2 = "Umea";
            value = 7;
        }
        if (destCard.equals(DestinationCards.NARVIK_TALLINN)) {
            city1 = "Narvik";
            city2 = "Tallinn";
            value = 13;
        }
        if (destCard.equals(DestinationCards.GOTEBORG_TURKU)) {
            city1 = "Goteborg";
            city2 = "Turku";
            value = 7;
        }
        if (destCard.equals(DestinationCards.STOCKHOLM_IMATRA)) {
            city1 = "Stockholm";
            city2 = "Imatra";
            value = 7;
        }
        if (destCard.equals(DestinationCards.HELSINKI_BERGEN)) {
            city1 = "Helsinki";
            city2 = "Bergen";
            value = 12;
        }
    }

    /**
     * Paint method for the Player class
     * 
     * @param Graphics
     *            g
     * @return void
     */
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(500, 385, 140, 20);
        g.fillRect(500, 500, 800, 800);

        g.setFont(new Font("serif", Font.ROMAN_BASELINE, 12));
        g.setColor(Color.black);

        if (playerDestCards.size() <= 10) {
            for (int i = 1; i <= playerDestCards.size(); i++) {
                g.drawString(playerDestCards.
                    get(i - 1), 535, 515 + (i * 15));
            }
        } else if (playerDestCards.size() > 10) {
            for (int i = 1; i <= 10; i++) {
                g.drawString(playerDestCards.
                    get(i - 1), 535, 515 + (i * 15));
            }
            for (int i = 11; i <= playerDestCards.size(); i++) {
                g.drawString(playerDestCards.get(i - 1), 750,
                        515 + ((i - 10) * 15));
            }
        }

        g.setFont(new Font("serif", Font.ROMAN_BASELINE, 32));
        g.drawString("[" + numRed + "]", 520, 345);
        g.drawString("[" + numBlue + "]", 630, 345);
        g.drawString("[" + numBlack + "]", 740, 345);
        g.drawString("[" + numGreen + "]", 850, 345);
        g.drawString("[" + numPink + "]", 960, 345);
        g.drawString("[" + numOrange + "]", 1070, 345);
        g.drawString("[" + numYellow + "]", 680, 410);
        g.drawString("[" + numWhite + "]", 790, 410);
        g.drawString("[" + numLoco + "]", 900, 410);

        g.setColor(Color.white);
        g.fillRect(525, 475, 650, 25);

        if (color.equals("Blue")) {
            g.setColor(Color.blue);
        }

        if (color.equals("Green")) {
            g.setColor(Color.green);
        }

        if (color.equals("Red")) {
            g.setColor(Color.red);
        }

        for (int i = 1; i <= numTrains; i++) {
            g.fillRect(525 + (i * 15), 475, 5, 25);
        }
        g.setFont(new Font("serif", Font.ROMAN_BASELINE, 16));
    }
}
