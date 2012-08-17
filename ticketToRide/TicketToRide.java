import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.regex.*;

/**
 * Class TicketToRide - Applet to run the game
 * TicketToRide
 * 
 * @author (Stephanie Del Belso, Devin Hurley,
 * Bryan Leicht, Serena Moore, Mike Paff)
 * @version (final version)
 */
public class TicketToRide extends JApplet
implements MouseListener {
    protected int numPlayers; 
    protected Board boardGame;
    protected TrainCardDeck trainCards;
    protected TableDeck tableDeck;
    protected DestinationCards destCards;
    protected Globetrotter globetrot;
    protected Player playerOne;
    protected Player playerTwo;
    protected Player playerThree;

    protected String name1, name2, name3;

    protected int score1, score2, score3,
    boardScore1, boardScore2, boardScore3;
    protected int destCardNum;

    protected Rectangle Kirkenes;
    protected Rectangle Honningsvag;
    protected Rectangle Karlskrona;
    protected Rectangle Arhus;
    protected Rectangle Kobenhavn;
    protected Rectangle Goteborg;
    protected Rectangle Alborg;
    protected Rectangle Kristiansand;
    protected Rectangle Stavanger;
    protected Rectangle Bergen;
    protected Rectangle Oslo;
    protected Rectangle Orebro;
    protected Rectangle Lillehammer;
    protected Rectangle Andalsnes;
    protected Rectangle Stockholm;
    protected Rectangle Norrkoping;
    protected Rectangle Tallinn;
    protected Rectangle Helsinki;
    protected Rectangle Turku;
    protected Rectangle Sundsvall;
    protected Rectangle Ostersund;
    protected Rectangle Trondheim;
    protected Rectangle Tampere;
    protected Rectangle Lahti;
    protected Rectangle Imatra;
    protected Rectangle Vaasa;
    protected Rectangle Umea;
    protected Rectangle Kuopio;
    protected Rectangle Lieksa;
    protected Rectangle Kajaani;
    protected Rectangle Murmansk;
    protected Rectangle Oulu;
    protected Rectangle Tornio;
    protected Rectangle Boden;
    protected Rectangle Moirana;
    protected Rectangle Narvik;
    protected Rectangle Kiruna;
    protected Rectangle Rovaniemi;
    protected Rectangle Tromso;

    protected Rectangle trainCard1;
    protected Rectangle trainCard2;
    protected Rectangle trainCard3;
    protected Rectangle trainCard4;
    protected Rectangle trainCard5;
    protected Rectangle trainDeck;
    protected Rectangle dDeck;

    private TrainCardImages red;
    private TrainCardImages blue;
    private TrainCardImages green;
    private TrainCardImages black;
    private TrainCardImages pink;
    private TrainCardImages orange;
    private TrainCardImages yellow;
    private TrainCardImages white;
    private TrainCardImages loco;
    private String input;
    private int click;

    boolean lastRoundCalled;

    int playCount;
    int boardClick;
    protected String city1, city2;

    /**
     * Called by the browser or applet viewer to
     * inform this JApplet that it has
     * been loaded into the system. It 
     * is always called before the first time
     * that the start method is called.
     */
    public void init() {
        // this is a workaround for a security 
        //conflict with some browsers
        // including some versions of Netscape & 
        //Internet Explorer which do
        // not allow access to the AWT system event 
        //queue which JApplets do
        // on startup to check access. May not be 
        //necessary with your browser.

        JRootPane rootPane = this.getRootPane();
        rootPane.
        putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);

        Routes.routeChecker();
        click = 0;
        score1 = 0;
        score2 = 0;
        score3 = 0;

        boardClick = 0;
        playCount = 1000;
        destCardNum = 0;

        lastRoundCalled = false;

        enterPlayers();
        try {

            boardGame = new Board();
            trainCards = new TrainCardDeck();
            tableDeck = new TableDeck();
            destCards = new DestinationCards();
            globetrot = new Globetrotter();
            red = new TrainCardImages();
            blue = new TrainCardImages();
            black = new TrainCardImages();
            green = new TrainCardImages();
            pink = new TrainCardImages();
            orange = new TrainCardImages();
            yellow = new TrainCardImages();
            white = new TrainCardImages();
            loco = new TrainCardImages();

        } catch (IOException ex) {
            Class error = ex.getClass();
            String errname = error.getName();
            String message = ex.getMessage();
            JOptionPane.
            showMessageDialog(null, "Error: " + message, errname,
                JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        // provide any initialisation necessary for your JApplet

        if (numPlayers == 2) {
            for (int i = 0; i < 4; i++) {
                playerOne.addToHand(trainCards.draw());
                playerTwo.addToHand(trainCards.draw());
            }
        }
        if (numPlayers == 3) {
            for (int i = 0; i < 4; i++) {
                playerOne.addToHand(trainCards.draw());
                playerTwo.addToHand(trainCards.draw());
                playerThree.addToHand(trainCards.draw());
            }
        }

        for (int i = 0; i < 5; i++) {
            tableDeck.addToDeck(trainCards.draw(), i);
        }

        playerOne.setTurn(true);
        setCities();
        setUpCards();
        addMouseListener(this);
        repaint();
        // playerOne.addDestCard(destCards.getDCard(0));
        // playerOne.addDestCard(destCards.getDCard(0));
        // playerOne.addDestCard(destCards.getDCard(0));
        // firstRound();
    }

    /**
     * Creates a JOptionPane to select the number of 
     * players in the game
     * 
     * @param
     * @return void
     */
    public void enterPlayers() {
        JOptionPane.
        showMessageDialog(null, "Ticket To Ride: Nordic Countries",
            "Ticket To Ride: Nordic Countries", 
            JOptionPane.PLAIN_MESSAGE,
            null);
        Object[] playerOptions = { 2, 3 };
        int numPlayersInput = JOptionPane.showOptionDialog(null,
                "How many players?", "Players", 1,
                JOptionPane.QUESTION_MESSAGE, null, playerOptions,
                playerOptions[0]);
        if (numPlayersInput == 0) {
            numPlayers = 2;
            enterNames();
        }
        if (numPlayersInput == 1) {
            numPlayers = 3;
            enterNames();
        }
    }

    /**
     * JOptionPane with input to get the names of the players
     * 
     * @param
     * @return void
     * 
     */
    public void enterNames() {
        if (numPlayers == 2) {
            name1 = JOptionPane.showInputDialog(null,
                "Please enter Player 1's name");
            if (name1 == null || name1.equals("")) {
                name1 = "Player 1";
            }
            name2 = JOptionPane.showInputDialog(null,
                "Please enter Player 2's name");
            if (name2 == null || name2.equals("")) {
                name2 = "Player 2";
            }
            playerOne = new Player(name1, "Blue");
            playerTwo = new Player(name2, "Green");
        }
        if (numPlayers == 3) {
            name1 = JOptionPane.showInputDialog(null,
                "Please enter Player 1's name");
            if (name1 == null || name1.equals("")) {
                name1 = "Player 1";
            }
            name2 = JOptionPane.showInputDialog(null,
                "Please enter Player 2's name");
            if (name2 == null || name2.equals("")) {
                name2 = "Player 2";
            }
            name3 = JOptionPane.showInputDialog(null,
                "Please enter Player 3's name");
            if (name3 == null || name3.equals("")) {
                name3 = "Player 3";
            }
            playerOne = new Player(name1, "Blue");
            playerTwo = new Player(name2, "Green");
            playerThree = new Player(name3, "Red");
        }
    }

    /**
     * Called by nextPlayer() to see if lastRound was 
     * set to true or the current player has two or less trains left
     * 
     * @param
     * @return void
     * 
     */
    public void gamePlay() {
        if (lastRoundCalled) {
            return;
        }
        if (currentPlayer().getNumTrains() <= 2) {
            lastRound();
        }
        repaint();

    }

    /**
     * called by init() This adds rectangles behind
     * the cities so that the program know's where the user has clicked.
     * 
     * @param
     * @return void
     */
    public void setCities() {

        Honningsvag = new Rectangle(205, 20, 15, 15);
        Kirkenes = new Rectangle(255, 50, 15, 15);
        Karlskrona = new Rectangle(290, 587, 15, 15);
        Arhus = new Rectangle(190, 620, 15, 15);
        Kobenhavn = new Rectangle(227, 614, 20, 20);
        Goteborg = new Rectangle(201, 549, 17, 17);
        Alborg = new Rectangle(170, 590, 15, 15);
        Kristiansand = new Rectangle(128, 554, 15, 15);
        Stavanger = new Rectangle(68, 551, 15, 15);
        Bergen = new Rectangle(41, 492, 20, 20);
        Oslo = new Rectangle(150, 495, 22, 22);
        Orebro = new Rectangle(215, 475, 16, 16);
        Lillehammer = new Rectangle(124, 444, 15, 15);
        Andalsnes = new Rectangle(79, 400, 15, 15);
        Stockholm = new Rectangle(275, 450, 22, 22);
        Norrkoping = new Rectangle(274, 499, 16, 16);
        Tallinn = new Rectangle(378, 420, 15, 15);
        Helsinki = new Rectangle(365, 377, 19, 19);
        Turku = new Rectangle(327, 380, 15, 15);
        Sundsvall = new Rectangle(217, 355, 15, 15);
        Ostersund = new Rectangle(163, 345, 15, 15);
        Trondheim = new Rectangle(108, 367, 17, 17);
        Tampere = new Rectangle(327, 340, 17, 17);
        Lahti = new Rectangle(367, 340, 15, 15);
        Imatra = new Rectangle(411, 302, 15, 15);
        Vaasa = new Rectangle(272, 298, 15, 15);
        Umea = new Rectangle(243, 275, 15, 15);
        Kuopio = new Rectangle(372, 257, 15, 15);
        Lieksa = new Rectangle(380, 223, 15, 15);
        Kajaani = new Rectangle(348, 205, 15, 15);
        Murmansk = new Rectangle(322, 35, 15, 15);
        Oulu = new Rectangle(288, 215, 15, 15);
        Tornio = new Rectangle(263, 188, 15, 15);
        Boden = new Rectangle(228, 192, 15, 15);
        Moirana = new Rectangle(110, 233, 15, 15);
        Narvik = new Rectangle(122, 145, 15, 15);
        Kiruna = new Rectangle(155, 155, 15, 15);
        Rovaniemi = new Rectangle(267, 152, 15, 15);
        Tromso = new Rectangle(137, 92, 15, 15);
    }

    /**
     * Called by init(). this creats rectangles behind 
     * the train cards that are drawn at game time.
     * 
     * 
     */
    public void setUpCards() {
        trainCard1 = new Rectangle(655, 10, 100, 65);
        trainCard2 = new Rectangle(765, 10, 100, 65);
        trainCard3 = new Rectangle(875, 10, 100, 65);
        trainCard4 = new Rectangle(985, 10, 100, 65);
        trainCard5 = new Rectangle(1095, 10, 100, 65);
        trainDeck = new Rectangle(500, 10, 100, 125);
        dDeck = new Rectangle(900, 150, 140, 100);
    }

    /**
     * Acquires the player who's turn it currently is.
     * 
     * @param
     * @return Player
     */
    public Player currentPlayer() {
        if (playerOne.isTurn() == true) {
            return playerOne;
        }
        if (playerTwo.isTurn() == true) {
            return playerTwo;
        }
        if (playerThree.isTurn() == true) {
            return playerThree;
        }
        return null;
    }

    /**
     * Sets the currentplayer to whoever's turn it is
     * 
     * 
     */
    public void nextPlayer() {
        repaint();
        playCount--;
        if (playCount == 0) {
            JOptionPane.showMessageDialog(null,
                "The game is over! Time to tally up the scores!");
            finalScore();
        }
        click = 0;
        boardClick = 0;
        city1 = "";
        city2 = "";
        gamePlay();
        trainCards.reshuffle();
        if (numPlayers == 2) {
            if (playerOne.isTurn() == true) {
                playerOne.setTurn(false);
                playerTwo.setTurn(true);
                if (playerTwo.isFirstTurn() == true) {
                    firstRound();
                }
            } else if (playerTwo.isTurn() == true) {
                playerTwo.setTurn(false);
                playerOne.setTurn(true);
            }
        }

        if (numPlayers == 3) {
            if (playerOne.isTurn() == true) {
                playerOne.setTurn(false);
                playerTwo.setTurn(true);
                if (playerTwo.isFirstTurn() == true) {
                    firstRound();
                }
            } else if (playerTwo.isTurn() == true) {
                playerTwo.setTurn(false);
                playerThree.setTurn(true);
                if (playerThree.isFirstTurn() == true) {
                    firstRound();
                }
            } else if (playerThree.isTurn() == true) {
                playerThree.setTurn(false);
                playerOne.setTurn(true);
            }
        }
        repaint();
    }

    /**
     * Called by init(). Begins the game by dealing out 
     * cards to players, in the order of their turns, 
     * beginning with playerOne.
     * 
     * 
     */
    public void firstRound() {
        String message1 =
            "You have been dealt the following " + 
            "Destination Tickets: \n\n "
            + destCards.getDCard(0)
            + "\n"
            + destCards.getDCard(1)
            + "\n"
            + destCards.getDCard(2)
            + "\n"
            + destCards.getDCard(3)
            + "\n"
            + destCards.getDCard(4);
        JOptionPane.showMessageDialog(null, message1);

        String message = 
            "You must keep 2 cards, but you can keep all of them. \n"
            + "Would you like to keep: \n" 
            + destCards.getDCard(0) + " ?";
        int choice = JOptionPane.showConfirmDialog(null, message);
        if (choice == JOptionPane.NO_OPTION) {
            destCards.removeDCard();
        } else if (choice == JOptionPane.YES_OPTION) {
            currentPlayer().addDestCard(destCards.getDCard(0));
            destCardNum++;
            destCards.removeDCard();

        }

        message = 
        "You must keep 2 cards, but you can keep all of them. \n"
        + "Would you like to keep: \n" + destCards.getDCard(0)
        + " ?";
        choice = JOptionPane.showConfirmDialog(null, message);
        if (choice == JOptionPane.NO_OPTION) {
            destCards.removeDCard();
        } else if (choice == JOptionPane.YES_OPTION) {
            currentPlayer().addDestCard(destCards.getDCard(0));
            destCardNum++;
            destCards.removeDCard();

        }

        message = 
        "You must keep 2 cards, but you can keep all of them. \n"
        + "Would you like to keep: \n"
        + destCards.getDCard(0) + " ?";
        choice = JOptionPane.showConfirmDialog(null, message);
        if (choice == JOptionPane.NO_OPTION) {
            destCards.removeDCard();
        } else if (choice == JOptionPane.YES_OPTION) {
            currentPlayer().addDestCard(destCards.getDCard(0));
            destCardNum++;
            destCards.removeDCard();

        }

        if (destCardNum == 0) {
            String keep1 = destCards.getDCard(0);
            currentPlayer().addDestCard(destCards.getDCard(0));
            destCards.removeDCard();
            String keep2 = destCards.getDCard(0);
            currentPlayer().addDestCard(destCards.getDCard(0));
            destCards.removeDCard();

            message = 
            "You must now keep these 2 cards\n" + keep1 + " and "
            + keep2;
            JOptionPane.showMessageDialog(null, message);

            destCardNum = 0;

            currentPlayer().setFirstTurn(false);

            repaint();
            return;
        } else {
            message = 
            "You must keep 2 cards, but you can keep all of them. \n"
            + "Would you like to keep: \n" + destCards.getDCard(0)
            + " ?";
            choice = JOptionPane.showConfirmDialog(null, message);
            if (choice == JOptionPane.NO_OPTION) {
                destCards.removeDCard();
            } else if (choice == JOptionPane.YES_OPTION) {
                currentPlayer().addDestCard(destCards.getDCard(0));
                destCardNum++;
                destCards.removeDCard();

            }
        }

        if (destCardNum == 1) {
            String keep1 = destCards.getDCard(0);
            currentPlayer().addDestCard(destCards.getDCard(0));
            destCards.removeDCard();

            message = "You must now keep this card\n" + keep1;
            JOptionPane.showMessageDialog(null, message);

            destCardNum = 0;

            currentPlayer().setFirstTurn(false);

            repaint();
            return;
        } else {
            message = 
            "You must keep 2 cards, but you can keep all of them. \n"
            + "Would you like to keep: \n" + destCards.getDCard(0)
            + " ?";
            choice = JOptionPane.showConfirmDialog(null, message);
            if (choice == JOptionPane.NO_OPTION) {
                destCards.removeDCard();
            } else if (choice == JOptionPane.YES_OPTION) {
                currentPlayer().addDestCard(destCards.getDCard(0));
                destCards.removeDCard();

            }
        }
        destCardNum = 0;

        currentPlayer().setFirstTurn(false);

        repaint();
    }

    /**
     * Called by gamePlay(). sets playCount so that 
     * each player has one turn left.
     * 
     */
    public void lastRound() {
        JOptionPane.showMessageDialog(null, "Last Round");
        if (numPlayers == 2) {
            playCount = 2;
        }
        if (numPlayers == 3) {
            playCount = 3;
        }
        lastRoundCalled = true;
    }

    /**
     * Computes the final score. Called by nextPlayer().
     * Determines the winner and the removes the
     * mouse listener so a player cannot continue to click
     * and affect the gameboard.
     * 
     * 
     */
    public void finalScore() {

        if (numPlayers == 3) {
//             int longestPath1 = playerOne.longestPath();
//             int longestPath2 = playerTwo.longestPath();
//             int longestPath3 = playerThree.longestPath();
//             if (longestPath1 > longestPath2 &&
//             longestPath1 > longestPath3) {
//                 JOptionPane.showMessageDialog(null,
//                     "The globetrotter award goes to " + name1);
//                 playerOne.score = playerOne.score + 10;
//             }
//             if (longestPath2 > longestPath1 &&
//             longestPath2 > longestPath3) {
//                 JOptionPane.showMessageDialog(null,
//                     "The globetrotter award goes to " + name2);
//                 playerTwo.score = playerTwo.score + 10;
//             }
//             if (longestPath3 > longestPath1 &&
//             longestPath3 > longestPath2) {
//                 JOptionPane.showMessageDialog(null,
//                     "The globetrotter award goes to " + name3);
//                 playerThree.score = playerThree.score + 10;
//             }

            for (int i = 0; i < playerOne.playerDestCards.size(); i++) {
                playerOne
                .setDestinationCities(playerOne.playerDestCards.get(i));
                boolean completed = playerOne.destinationCardCheck("");
                if (completed) {
                    playerOne.score = playerOne.score + playerOne.value;
                } else {
                    playerOne.score = playerOne.score - playerOne.value;
                }
            }
            JOptionPane.
            showMessageDialog(null, name1 + "'s final score is: "
                + playerOne.score);

            for (int i = 0; i < playerTwo.playerDestCards.size(); i++) {
                playerTwo
                .setDestinationCities(playerTwo.playerDestCards.get(i));
                boolean completed = playerTwo.destinationCardCheck("");
                if (completed) {
                    playerTwo.score = playerTwo.score + playerTwo.value;
                } else {
                    playerTwo.score = playerTwo.score - playerTwo.value;
                }
            }
            JOptionPane.
            showMessageDialog(null, name2 + "'s final score is: "
                + playerTwo.score);

            if (numPlayers == 3) {
                for 
                (int i = 0; i < playerThree.playerDestCards.size(); i++) {
                    playerThree
                    .setDestinationCities(playerThree.playerDestCards
                        .get(i));
                    boolean completed = 
                        playerThree.destinationCardCheck("");
                    if (completed) {
                        playerThree.score = playerThree.score
                        + playerThree.value;
                    } else {
                        playerThree.score = playerThree.score
                        - playerThree.value;
                    }
                }
                JOptionPane.showMessageDialog(null, name3
                    + "'s final score is: " + playerThree.score);
            }

            if (numPlayers == 3) {
                if (playerOne.score > playerTwo.score
                && playerOne.score > playerThree.score) {
                    JOptionPane.showMessageDialog(null, "The Winner is "
                        + name1 + "!!!");
                }
                if (playerTwo.score > playerOne.score
                && playerTwo.score > playerThree.score) {
                    JOptionPane.showMessageDialog(null, "The Winner is "
                        + name2 + "!!!");
                }
                if (playerThree.score > playerOne.score
                && playerThree.score > playerTwo.score) {
                    JOptionPane.showMessageDialog(null, "The Winner is "
                        + name3 + "!!!");
                }
            }
        } else { // if numplayers == 2
//             int longestPath1 = playerOne.longestPath();
//             int longestPath2 = playerTwo.longestPath();
// 
//             if (longestPath1 > longestPath2) {
//                 JOptionPane.showMessageDialog(null,
//                     "The globetrotter award goes to " + name1);
//                 playerOne.score = playerOne.score + 10;
//             }
//             if (longestPath2 > longestPath1) {
//                 JOptionPane.showMessageDialog(null,
//                     "The globetrotter award goes to " + name2);
//                 playerTwo.score = playerTwo.score + 10;
//             }

            for (int i = 0; i < playerOne.playerDestCards.size(); i++) {
                playerOne
                .setDestinationCities(playerOne.playerDestCards.get(i));
                boolean completed = playerOne.destinationCardCheck("");
                if (completed) {
                    playerOne.score = playerOne.score + playerOne.value;
                } else {
                    playerOne.score = playerOne.score - playerOne.value;
                }
            }
            JOptionPane.
            showMessageDialog(null, name1 + "'s final score is: "
                + playerOne.score);

            for (int i = 0; i < playerTwo.playerDestCards.size(); i++) {
                playerTwo
                .setDestinationCities(playerTwo.playerDestCards.get(i));
                boolean completed = playerTwo.destinationCardCheck("");
                if (completed) {
                    playerTwo.score = playerTwo.score + playerTwo.value;
                } else {
                    playerTwo.score = playerTwo.score - playerTwo.value;
                }
            }
            JOptionPane.
            showMessageDialog(null, name2 + "'s final score is: "
                + playerTwo.score);

            if (playerOne.score > playerTwo.score) {
                JOptionPane.
                showMessageDialog(null, "The Winner is " + name1
                    + "!!!");

            } else {
                JOptionPane.
                showMessageDialog(null, "The Winner is " + name2
                    + "!!!");

            }

        }

        removeMouseListener(this);

    }

    /**
     * Paint method for applet.
     * 
     * @param g
     *            the Graphics object for this applet
     */
    public void paint(Graphics g) {
        // simple text displayed on applet

        boardGame.paintComponent(g);
        trainCards.paintComponent(g);
        tableDeck.paintComponent(g);
        destCards.paintComponent(g);
        globetrot.paintComponent(g);

        g.setColor(Color.green);
        boardScore2 = score2 % 100;
        if (boardScore2 >= 0 && boardScore2 <= 20) {
            g.fillOval((20 * boardScore2) + 20, 4, 10, 10);
        }
        if (boardScore2 > 20 && boardScore2 <= 27) {
            g.fillOval(430, (21 * (boardScore2 - 20)) + 4, 10, 10);
        }
        if (boardScore2 > 27 && boardScore2 <= 35) {
            g.fillOval(435, (21 * (boardScore2 - 20)) + 4, 10, 10);
        }
        if (boardScore2 > 35 && boardScore2 <= 42) {
            g.fillOval(440, (21 * (boardScore2 - 20)) + 5, 10, 10);
        }
        if (boardScore2 > 42 && boardScore2 <= 50) {
            g.fillOval(441, (21 * (boardScore2 - 20)) + 5, 10, 10);
        }
        if (boardScore2 > 50 && boardScore2 <= 70) {
            g.fillOval(436 - (21 * (boardScore2 - 50)), 646, 10, 10);
        }
        if (boardScore2 > 70 && boardScore2 <= 80) {
            g.fillOval(13, 641 - (22 * (boardScore2 - 70)), 10, 10);
        }
        if (boardScore2 > 80 && boardScore2 <= 89) {
            g.fillOval(13, 641 - (22 * (boardScore2 - 70)) + 5, 10, 10);
        }
        if (boardScore2 > 89 && boardScore2 <= 94) {
            g.fillOval(13, 641 - (21 * (boardScore2 - 70)) - 8, 10, 10);
        }
        if (boardScore2 > 94 && boardScore2 <= 99) {
            g.fillOval(16, 641 - (21 * (boardScore2 - 70)) - 3, 10, 10);
        }

        if (numPlayers == 3) {
            g.setColor(Color.red);
            boardScore3 = score3 % 100;
            if (boardScore3 >= 0 && boardScore3 <= 20) {
                g.fillOval((20 * boardScore3) + 20, 12, 10, 10);
            }
            if (boardScore3 > 20 && boardScore3 <= 27) {
                g.fillOval(433, (21 * (boardScore3 - 20)) + 4, 10, 10);
            }
            if (boardScore3 > 27 && boardScore3 <= 35) {
                g.fillOval(438, (21 * (boardScore3 - 20)) + 4, 10, 10);
            }
            if (boardScore3 > 35 && boardScore3 <= 42) {
                g.fillOval(443, (21 * (boardScore3 - 20)) + 5, 10, 10);
            }
            if (boardScore3 > 42 && boardScore3 <= 50) {
                g.fillOval(444, (21 * (boardScore3 - 20)) + 5, 10, 10);
            }
            if (boardScore3 > 50 && boardScore3 <= 70) {
                g.fillOval(436 - (21 * (boardScore3 - 50)), 636, 10, 10);
            }
            if (boardScore3 > 70 && boardScore3 <= 80) {
                g.fillOval(4, 641 - (22 * (boardScore3 - 70)), 10, 10);
            }
            if (boardScore3 > 80 && boardScore3 <= 89) {
                g.fillOval(4, 641 - (22 * (boardScore3 - 70)) + 5, 10, 10);
            }
            if (boardScore3 > 89 && boardScore3 <= 94) {
                g.fillOval(4, 641 - (21 * (boardScore3 - 70)) - 8, 10, 10);
            }
            if (boardScore3 > 94 && boardScore3 <= 99) {
                g.fillOval(7, 641 - (21 * (boardScore3 - 70)) - 3, 10, 10);
            }
        }

        g.setColor(Color.blue);
        boardScore1 = score1 % 100;
        if (boardScore1 >= 0 && boardScore1 <= 20) {
            g.fillOval((20 * boardScore1) + 20, 8, 10, 10);
        }
        if (boardScore1 > 20 && boardScore1 <= 27) {
            g.fillOval(425, (21 * (boardScore1 - 20)) + 4, 10, 10);
        }
        if (boardScore1 > 27 && boardScore1 <= 35) {
            g.fillOval(430, (21 * (boardScore1 - 20)) + 4, 10, 10);
        }
        if (boardScore1 > 35 && boardScore1 <= 42) {
            g.fillOval(435, (21 * (boardScore1 - 20)) + 5, 10, 10);
        }
        if (boardScore1 > 42 && boardScore1 <= 50) {
            g.fillOval(436, (21 * (boardScore1 - 20)) + 5, 10, 10);
        }
        if (boardScore1 > 50 && boardScore1 <= 70) {
            g.fillOval(436 - (21 * (boardScore1 - 50)), 641, 10, 10);
        }
        if (boardScore1 > 70 && boardScore1 <= 80) {
            g.fillOval(8, 641 - (22 * (boardScore1 - 70)), 10, 10);
        }
        if (boardScore1 > 80 && boardScore1 <= 89) {
            g.fillOval(8, 641 - (22 * (boardScore1 - 70)) + 5, 10, 10);
        }
        if (boardScore1 > 89 && boardScore1 <= 94) {
            g.fillOval(8, 641 - (21 * (boardScore1 - 70)) - 8, 10, 10);
        }
        if (boardScore1 > 94 && boardScore1 <= 99) {
            g.fillOval(11, 641 - (21 * (boardScore1 - 70)) - 3, 10, 10);
        }

        BufferedImage tcard1 = red.getImage("redCard");
        BufferedImage tcard2 = blue.getImage("blueCard");
        BufferedImage tcard3 = black.getImage("blackCard");
        BufferedImage tcard4 = green.getImage("greenCard");
        BufferedImage tcard5 = pink.getImage("pinkCard");
        BufferedImage tcard6 = orange.getImage("orangeCard");
        BufferedImage tcard7 = yellow.getImage("yellowCard");
        BufferedImage tcard8 = white.getImage("whiteCard");
        BufferedImage tcard9 = loco.getImage("locomotiveCard");

        g.drawImage(tcard1, 490, 310, null);
        g.drawImage(tcard2, 600, 310, null);
        g.drawImage(tcard3, 710, 310, null);
        g.drawImage(tcard4, 820, 310, null);
        g.drawImage(tcard5, 930, 310, null);
        g.drawImage(tcard6, 1040, 310, null);
        g.drawImage(tcard7, 650, 375, null);
        g.drawImage(tcard8, 760, 375, null);
        g.drawImage(tcard9, 870, 375, null);

        g.setColor(Color.black);
        g.fillRect(500, 300, 650, 5);

        for (int i = 0; i < playerOne.routes.size(); i++) {
            g.setColor(Color.blue);

            if (playerOne.routes.get(i).
            contains("Murmansk Lieksa Gray 9")) {
                g.drawLine(328, 40, 390, 100);
                g.drawLine(390, 100, 405, 200);
                g.drawLine(405, 200, 385, 228);
            }
            if (playerOne.routes.get(i).contains(
                "Kirkenes Murmansk White 3 Ferry 1")) {
                g.drawLine(327, 37, 280, 30);
                g.drawLine(280, 30, 260, 55);
            }
            if (playerOne.routes.get(i).contains(
                "Kirkenes Honningsvag Green 2 Ferry 1")) {
                g.drawLine(260, 55, 210, 25);
            }
            if (playerOne.routes.get(i).contains(
                "Honningsvag Tromso Purple 4 Ferry 2")) {
                g.drawLine(210, 25, 142, 97);
            }
            if (playerOne.routes.get(i).contains(
                "Tromso Narvik Yellow 3 Ferry 1")) {
                g.drawLine(142, 97, 112, 120);
                g.drawLine(112, 120, 127, 150);
            }
            if (playerOne.routes.get(i)
            .contains("Narvik Kiruna White 1 Tunnel")) {
                g.drawLine(137, 163, 155, 167);
            }
            if (playerOne.routes.get(i).contains(
                "Narvik Kiruna Purple 1 Tunnel")) {
                g.drawLine(132, 150, 155, 155);
            }
            if (playerOne.routes.get(i).contains(
                "Narvik Moirana Orange 4 Ferry 2")) {
                g.drawLine(127, 150, 95, 188);
                g.drawLine(95, 188, 115, 238);
            }
            if (playerOne.routes.get(i).
            contains("Kiruna Boden Black 3")) {
                g.drawLine(170, 163, 195, 185);
                g.drawLine(195, 185, 228, 192);
            }
            if (playerOne.routes.get(i).
            contains("Kiruna Boden Orange 3")) {
                g.drawLine(163, 170, 200, 200);
                g.drawLine(200, 200, 228, 200);
            }
            if (playerOne.routes.get(i).
            contains("Kirkenes Rovaniemi Blue 5")) {
                g.drawLine(260, 55, 305, 105);
                g.drawLine(305, 105, 272, 157);
            }
            if (playerOne.routes.get(i).
            contains("Rovaniemi Tornio Red 1")) {
                g.drawLine(270, 163, 266, 192);
            }
            if (playerOne.routes.get(i).
            contains("Boden Tornio Green 1")) {
                g.drawLine(233, 201, 267, 193);
            }
            if (playerOne.routes.get(i).
            contains("Rovaniemi Oulu Orange 2")) {
                g.drawLine(276, 158, 296, 223);
            }
            if (playerOne.routes.get(i).
            contains("Tornio Oulu White 1")) {
                g.drawLine(269, 195, 293, 220);
            }
            if (playerOne.routes.get(i).
            contains("Oulu Kajaani Yellow 2")) {
                g.drawLine(293, 220, 353, 210);
            }
            if (playerOne.routes.get(i).
            contains("Kajaani Lieksa Blue 1")) {
                g.drawLine(353, 210, 388, 230);
            }
            if (playerOne.routes.get(i).
            contains("Lieksa Kuopio Black 1")) {
                g.drawLine(388, 230, 378, 260);
            }
            if (playerOne.routes.get(i).
            contains("Kajaani Kuopio Green 2")) {
                g.drawLine(353, 210, 349, 240);
                g.drawLine(349, 240, 377, 263);
            }
            if (playerOne.routes.get(i).
            contains("Oulu Kuopio Gray 3")) {
                g.drawLine(293, 220, 362, 263);
                g.drawLine(362, 263, 377, 263);
            }
            if (playerOne.routes.get(i).
            contains("Oulu Vaasa Black 3")) {
                g.drawLine(295, 220, 279, 303);
            }
            if (playerOne.routes.get(i).
            contains("Vaasa Umea Gray 1 Ferry 1")) {
                g.drawLine(279, 303, 248, 280);
            }
            if (playerOne.routes.get(i).contains(
                "Vaasa Sundsvall Blue 3 Ferry 1")) {
                g.drawLine(279, 303, 280, 345);
                g.drawLine(280, 345, 222, 360);
            }
            if (playerOne.routes.get(i).
            contains("Vaasa Kuopio Gray 4")) {
                g.drawLine(279, 305, 377, 265);
            }
            if (playerOne.routes.get(i).
            contains("Vaasa Tampere Purple 2")) {
                g.drawLine(279, 305, 332, 345);
            }
            if (playerOne.routes.get(i).
            contains("Kuopio Imatra Purple 2")) {
                g.drawLine(377, 262, 409, 275);
                g.drawLine(409, 275, 416, 307);
            }
            if (playerOne.routes.get(i).
            contains("Kuopio Lahti White 3")) {
                g.drawLine(377, 262, 372, 345);
            }
            if (playerOne.routes.get(i).contains(
                "Moirana Trondheim Green 5 Tunnel")) {
                g.drawLine(118, 238, 116, 372);
            }
            if (playerOne.routes.get(i).contains(
                "Moirana Trondheim Red 6 Ferry 2")) {
                g.drawLine(115, 238, 79, 275);
                g.drawLine(79, 275, 79, 330);
                g.drawLine(79, 330, 113, 372);
            }
            if (playerOne.routes.get(i).contains(
                "Trondheim Ostersund Black 2 Tunnel")) {
                g.drawLine(116, 375, 172, 353);
            }
            if (playerOne.routes.get(i).contains(
                "Trondheim Lillehammer Orange 3 Tunnel")) {
                g.drawLine(113, 372, 145, 410);
                g.drawLine(145, 410, 129, 449);
            }
            if (playerOne.routes.get(i).contains(
                "Trondheim Andalsnes White 2 Ferry 1")) {
                g.drawLine(113, 372, 84, 372);
                g.drawLine(84, 372, 84, 405);
            }
            if (playerOne.routes.get(i).contains(
                "Lillehammer Andalsnes Yellow 2 Tunnel")) {
                g.drawLine(84, 405, 129, 449);
            }
            if (playerOne.routes.get(i).contains(
                "Bergen Andalsnes Black 5 Ferry 2")) {
                g.drawLine(84, 405, 54, 405);
                g.drawLine(54, 405, 30, 460);
                g.drawLine(30, 460, 46, 497);
            }
            if (playerOne.routes.get(i).contains(
                "Bergen Stavanger Purple 2 Ferry 1")) {
                g.drawLine(52, 502, 52, 532);
                g.drawLine(52, 532, 73, 556);
            }
            if (playerOne.routes.get(i).
            contains("Bergen Oslo Blue 4 Tunnel")) {
                g.drawLine(46, 497, 100, 473);
                g.drawLine(100, 473, 155, 500);
            }
            if (playerOne.routes.get(i).
            contains("Bergen Oslo Red 4 Tunnel")) {
                g.drawLine(46, 507, 100, 483);
                g.drawLine(100, 483, 155, 510);
            }
            if (playerOne.routes.get(i).
            contains("Lahti Imatra Yellow 2")) {
                g.drawLine(372, 345, 416, 307);
            }
            if (playerOne.routes.get(i).
            contains("Lahti Helsinki Black 1")) {
                g.drawLine(372, 345, 372, 382);
            }
            if (playerOne.routes.get(i).
            contains("Lahti Tampere Blue 1")) {
                g.drawLine(372, 345, 337, 345);
            }
            if (playerOne.routes.get(i).
            contains("Helsinki Tampere Orange 1")) {
                g.drawLine(370, 382, 333, 347);
            }
            if (playerOne.routes.get(i).
            contains("Helsinki Turku White 1")) {
                g.drawLine(375, 387, 335, 387);
            }
            if (playerOne.routes.get(i).
            contains("Helsinki Imatra Red 3")) {
                g.drawLine(375, 387, 400, 365);
                g.drawLine(400, 365, 420, 310);
            }
            if (playerOne.routes.get(i).
            contains("Helsinki Tallinn Purple 2")) {
                g.drawLine(375, 387, 405, 410);
                g.drawLine(405, 410, 390, 425);
            }
            if (playerOne.routes.get(i).contains(
                "Helsinki Stockholm Yellow 4 Ferry 1")) {
                g.drawLine(283, 458, 375, 387);
            }
            if (playerOne.routes.get(i).contains(
                "Helsinki Stockholm Gray 4 Ferry 2")) {
                g.drawLine(288, 463, 380, 392);
            }
            if (playerOne.routes.get(i).
            contains("Tampere Turku Red 1")) {
                g.drawLine(334, 387, 335, 348);
            }
            if (playerOne.routes.get(i).contains(
                "Tallinn Stockholm Green 4 Ferry 2")) {
                g.drawLine(390, 425, 345, 460);
                g.drawLine(345, 460, 320, 466);
                g.drawLine(320, 466, 283, 466);
            }
            if (playerOne.routes.get(i).
            contains("Boden Umea Red 3")) {
                g.drawLine(237, 200, 250, 280);
            }
            if (playerOne.routes.get(i).
            contains("Boden Umea White 3")) {
                g.drawLine(230, 200, 242, 280);
            }
            if (playerOne.routes.get(i).
            contains("Umea Sundsvall Yellow 3")) {
                g.drawLine(250, 280, 230, 360);
            }
            if (playerOne.routes.get(i).
            contains("Umea Sundsvall Purple 3")) {                
                g.drawLine(242, 280, 222, 360);
            }
            if (playerOne.routes.get(i).
            contains("Sundsvall Stockholm Gray 4 Left")) {
                g.drawLine(222, 360, 283, 466);
            }
            if (playerOne.routes.get(i).
            contains("Sundsvall Stockholm Gray 4 Right")) {
                g.drawLine(230, 360, 290, 466);
            }
            if (playerOne.routes.get(i).
            contains("Sundsvall Ostersund Green 2")) {
                g.drawLine(222, 360, 195, 374);
                g.drawLine(195, 374, 170, 350);
            }
            if (playerOne.routes.get(i).
            contains("Sundsvall Orebro Orange 4")) {
                g.drawLine(222, 360, 220, 482);
            }
            if (playerOne.routes.get(i).
            contains("Stockholm Norrkoping Red 1")) {
                g.drawLine(290, 466, 285, 505);
            }
            if (playerOne.routes.get(i).contains(
                "Stockholm Norrkoping Orange 1")) {
                g.drawLine(283, 466, 280, 505);
            }
            if (playerOne.routes.get(i).
            contains("Stockholm Orebro Purple 2")) {
                g.drawLine(283, 457, 220, 478);
            }
            if (playerOne.routes.get(i).
            contains("Stockholm Orebro Black 2")) {
                g.drawLine(290, 466, 220, 486);
            }
            if (playerOne.routes.get(i).contains(
                "Stockholm Turku Blue 3 Ferry 1")) {
                g.drawLine(290, 466, 295, 426);
                g.drawLine(295, 426, 308, 405);
                g.drawLine(308, 405, 335, 387);
            }
            if (playerOne.routes.get(i).
            contains("Oslo Orebro Yellow 2")) {
                g.drawLine(220, 478, 160, 498);
            }
            if (playerOne.routes.get(i).
            contains("Oslo Orebro Green 2")) {
                g.drawLine(220, 486, 160, 506);
            }
            if (playerOne.routes.get(i).
            contains("Orebro Norrkoping Gray 2")) {
                g.drawLine(220, 486, 245, 505);
                g.drawLine(245, 505, 280, 505);
            }
            if (playerOne.routes.get(i).
            contains("Orebro Goteborg Blue 2")) {
                g.drawLine(220, 486, 210, 560);
            }
            if (playerOne.routes.get(i).
            contains("Oslo Goteborg Orange 2")) {
                g.drawLine(160, 506, 210, 560);
            }
            if (playerOne.routes.get(i).
            contains("Oslo Alborg White 3 Ferry 1")) {
                g.drawLine(160, 506, 175, 595);
            }
            if (playerOne.routes.get(i).
            contains("Norrkoping Goteborg Gray 3")) {
                g.drawLine(280, 505, 210, 560);
            }
            if (playerOne.routes.get(i).contains(
                "Norrkoping Karlskrona Yellow 3")) {
                g.drawLine(285, 505, 298, 598);
            }
            if (playerOne.routes.get(i).contains(
                "Norrkoping Karlskrona White 3")) {
                g.drawLine(280, 505, 293, 593);
            }
            if (playerOne.routes.get(i).contains(
                "Karlskrona Kobenhavn Green 2 Ferry 1")) {
                g.drawLine(293, 593, 235, 618);
            }
            if (playerOne.routes.get(i).contains(
                "Karlskrona Kobenhavn Blue 2 Ferry 1")) {
                g.drawLine(298, 598, 235, 627);
            }
            if (playerOne.routes.get(i).contains(
                "Arhus Kobenhavn Gray 1 Ferry 1")) {
                g.drawLine(235, 627, 195, 627);
            }
            if (playerOne.routes.get(i).
            contains("Arhus Alborg Purple 1")) {
                g.drawLine(195, 627, 175, 595);
            }
            if (playerOne.routes.get(i).contains(
                "Goteborg Kobenhavn Black 2 Ferry 1")) {
                g.drawLine(210, 560, 235, 618);
            }
            if (playerOne.routes.get(i).contains(
                "Goteborg Alborg Gray 2 Ferry 1")) {
                g.drawLine(210, 560, 205, 595);
                g.drawLine(205, 595, 175, 595);
            }
            if (playerOne.routes.get(i).contains(
                "Kristiansand Alborg Red 2 Ferry 1")) {
                g.drawLine(175, 595, 150, 590);
                g.drawLine(150, 590, 137, 560);
            }
            if (playerOne.routes.get(i).contains(
                "Kristiansand Stavanger Green 2 Tunnel")) {
                g.drawLine(137, 560, 75, 558);
            }
            if (playerOne.routes.get(i).contains(
                "Kristiansand Stavanger Orange 3 Ferry 1")) {
                g.drawLine(137, 560, 120, 580);
                g.drawLine(120, 580, 85, 580);
                g.drawLine(85, 580, 75, 558);
            }
            if (playerOne.routes.get(i).
            contains("Kristiansand Oslo Black 2")) {
                g.drawLine(137, 560, 137, 530);
                g.drawLine(137, 530, 160, 506);
            }
            if (playerOne.routes.get(i).contains(
                "Oslo Lillehammer Purple 2 Tunnel")) {
                g.drawLine(160, 506, 155, 475);
                g.drawLine(155, 475, 132, 450);
            }
        }
        for (int i = 0; i < playerTwo.routes.size(); i++) {
            g.setColor(Color.green);

            if (playerTwo.routes.get(i).
            contains("Murmansk Lieksa Gray 9")) {
                g.drawLine(328, 40, 390, 100);
                g.drawLine(390, 100, 405, 200);
                g.drawLine(405, 200, 385, 228);
            }
            if (playerTwo.routes.get(i).contains(
                "Kirkenes Murmansk White 3 Ferry 1")) {
                g.drawLine(327, 37, 280, 30);
                g.drawLine(280, 30, 260, 55);
            }
            if (playerTwo.routes.get(i).contains(
                "Kirkenes Honningsvag Green 2 Ferry 1")) {
                g.drawLine(260, 55, 210, 25);
            }
            if (playerTwo.routes.get(i).contains(
                "Honningsvag Tromso Purple 4 Ferry 2")) {
                g.drawLine(210, 25, 142, 97);
            }
            if (playerTwo.routes.get(i).contains(
                "Tromso Narvik Yellow 3 Ferry 1")) {
                g.drawLine(142, 97, 112, 120);
                g.drawLine(112, 120, 127, 150);
            }
            if (playerTwo.routes.get(i)
            .contains("Narvik Kiruna White 1 Tunnel")) {
                g.drawLine(137, 163, 155, 167);
            }
            if (playerTwo.routes.get(i).contains(
                "Narvik Kiruna Purple 1 Tunnel")) {
                g.drawLine(132, 150, 155, 155);
            }
            if (playerTwo.routes.get(i).contains(
                "Narvik Moirana Orange 4 Ferry 2")) {
                g.drawLine(127, 150, 95, 188);
                g.drawLine(95, 188, 115, 238);
            }
            if (playerTwo.routes.get(i).
            contains("Kiruna Boden Black 3")) {
                g.drawLine(170, 163, 195, 185);
                g.drawLine(195, 185, 228, 192);
            }
            if (playerTwo.routes.get(i).
            contains("Kiruna Boden Orange 3")) {
                g.drawLine(163, 170, 200, 200);
                g.drawLine(200, 200, 228, 200);
            }
            if (playerTwo.routes.get(i).
            contains("Kirkenes Rovaniemi Blue 5")) {
                g.drawLine(260, 55, 305, 105);
                g.drawLine(305, 105, 272, 157);
            }
            if (playerTwo.routes.get(i).
            contains("Rovaniemi Tornio Red 1")) {
                g.drawLine(270, 163, 266, 192);
            }
            if (playerTwo.routes.get(i).
            contains("Boden Tornio Green 1")) {
                g.drawLine(233, 201, 267, 193);
            }
            if (playerTwo.routes.get(i).
            contains("Rovaniemi Oulu Orange 2")) {
                g.drawLine(276, 158, 296, 223);
            }
            if (playerTwo.routes.get(i).
            contains("Tornio Oulu White 1")) {
                g.drawLine(269, 195, 293, 220);
            }
            if (playerTwo.routes.get(i).
            contains("Oulu Kajaani Yellow 2")) {
                g.drawLine(293, 220, 353, 210);
            }
            if (playerTwo.routes.get(i).
            contains("Kajaani Lieksa Blue 1")) {
                g.drawLine(353, 210, 388, 230);
            }
            if (playerTwo.routes.get(i).
            contains("Lieksa Kuopio Black 1")) {
                g.drawLine(388, 230, 378, 260);
            }
            if (playerTwo.routes.get(i).
            contains("Kajaani Kuopio Green 2")) {
                g.drawLine(353, 210, 349, 240);
                g.drawLine(349, 240, 377, 263);
            }
            if (playerTwo.routes.get(i).
            contains("Oulu Kuopio Gray 3")) {
                g.drawLine(293, 220, 362, 263);
                g.drawLine(362, 263, 377, 263);
            }
            if (playerTwo.routes.get(i).
            contains("Oulu Vaasa Black 3")) {
                g.drawLine(295, 220, 279, 303);
            }
            if (playerTwo.routes.get(i).
            contains("Vaasa Umea Gray 1 Ferry 1")) {
                g.drawLine(279, 303, 248, 280);
            }
            if (playerTwo.routes.get(i).contains(
                "Vaasa Sundsvall Blue 3 Ferry 1")) {
                g.drawLine(279, 303, 280, 345);
                g.drawLine(280, 345, 222, 360);
            }
            if (playerTwo.routes.get(i).
            contains("Vaasa Kuopio Gray 4")) {
                g.drawLine(279, 305, 377, 265);
            }
            if (playerTwo.routes.get(i).
            contains("Vaasa Tampere Purple 2")) {
                g.drawLine(279, 305, 332, 345);
            }
            if (playerTwo.routes.get(i).
            contains("Kuopio Imatra Purple 2")) {
                g.drawLine(377, 262, 409, 275);
                g.drawLine(409, 275, 416, 307);
            }
            if (playerTwo.routes.get(i).
            contains("Kuopio Lahti White 3")) {
                g.drawLine(377, 262, 372, 345);
            }
            if (playerTwo.routes.get(i).contains(
                "Moirana Trondheim Green 5 Tunnel")) {
                g.drawLine(118, 238, 116, 372);
            }
            if (playerTwo.routes.get(i).contains(
                "Moirana Trondheim Red 6 Ferry 2")) {
                g.drawLine(118, 238, 116, 372);
                g.drawLine(115, 238, 79, 275);
                g.drawLine(79, 275, 79, 330);
                g.drawLine(79, 330, 113, 372);
            }
            if (playerTwo.routes.get(i).contains(
                "Trondheim Ostersund Black 2 Tunnel")) {
                g.drawLine(116, 375, 172, 353);
            }
            if (playerTwo.routes.get(i).contains(
                "Trondheim Lillehammer Orange 3 Tunnel")) {
                g.drawLine(113, 372, 145, 410);
                g.drawLine(145, 410, 129, 449);
            }
            if (playerTwo.routes.get(i).contains(
                "Trondheim Andalsnes White 2 Ferry 1")) {
                g.drawLine(113, 372, 84, 372);
                g.drawLine(84, 372, 84, 405);
            }
            if (playerTwo.routes.get(i).contains(
                "Lillehammer Andalsnes Yellow 2 Tunnel")) {
                g.drawLine(84, 405, 129, 449);
            }
            if (playerTwo.routes.get(i).contains(
                "Bergen Andalsnes Black 5 Ferry 2")) {
                g.drawLine(84, 405, 54, 405);
                g.drawLine(54, 405, 30, 460);
                g.drawLine(30, 460, 46, 497);
            }
            if (playerTwo.routes.get(i).contains(
                "Bergen Stavanger Purple 2 Ferry 1")) {
                g.drawLine(52, 502, 52, 532);
                g.drawLine(52, 532, 73, 556);
            }
            if (playerTwo.routes.get(i).
            contains("Bergen Oslo Blue 4 Tunnel")) {
                g.drawLine(46, 497, 100, 473);
                g.drawLine(100, 473, 155, 500);
            }
            if (playerTwo.routes.get(i).
            contains("Bergen Oslo Red 4 Tunnel")) {
                g.drawLine(46, 507, 100, 483);
                g.drawLine(100, 483, 155, 510);
            }
            if (playerTwo.routes.get(i).
            contains("Lahti Imatra Yellow 2")) {
                g.drawLine(372, 345, 416, 307);
            }
            if (playerTwo.routes.get(i).
            contains("Lahti Helsinki Black 1")) {
                g.drawLine(372, 345, 372, 382);
            }
            if (playerTwo.routes.get(i).
            contains("Lahti Tampere Blue 1")) {
                g.drawLine(372, 345, 337, 345);
            }
            if (playerTwo.routes.get(i).
            contains("Helsinki Tampere Orange 1")) {
                g.drawLine(370, 382, 333, 347);
            }
            if (playerTwo.routes.get(i).
            contains("Helsinki Turku White 1")) {
                g.drawLine(375, 387, 335, 387);
            }
            if (playerTwo.routes.get(i).
            contains("Helsinki Imatra Red 3")) {
                g.drawLine(375, 387, 400, 365);
                g.drawLine(400, 365, 420, 310);
            }
            if (playerTwo.routes.get(i).
            contains("Helsinki Tallinn Purple 2")) {
                g.drawLine(375, 387, 405, 410);
                g.drawLine(405, 410, 390, 425);
            }
            if (playerTwo.routes.get(i).contains(
                "Helsinki Stockholm Yellow 4 Ferry 1")) {
                g.drawLine(283, 458, 375, 387);
            }
            if (playerTwo.routes.get(i).contains(
                "Helsinki Stockholm Gray 4 Ferry 2")) {
                g.drawLine(288, 463, 380, 392);
            }
            if (playerTwo.routes.get(i).
            contains("Tampere Turku Red 1")) {
                g.drawLine(334, 387, 335, 348);
            }
            if (playerTwo.routes.get(i).contains(
                "Tallinn Stockholm Green 4 Ferry 2")) {
                g.drawLine(390, 425, 345, 460);
                g.drawLine(345, 460, 320, 466);
                g.drawLine(320, 466, 283, 466);
            }
            if (playerTwo.routes.get(i).
            contains("Boden Umea Red 3")) {                
                g.drawLine(237, 200, 250, 280);
            }
            if (playerTwo.routes.get(i).
            contains("Boden Umea White 3")) {
                g.drawLine(230, 200, 242, 280);
            }
            if (playerTwo.routes.get(i).
            contains("Umea Sundsvall Yellow 3")) {         
                g.drawLine(250, 280, 230, 360);
            }
            if (playerTwo.routes.get(i).
            contains("Umea Sundsvall Purple 3")) {
                g.drawLine(242, 280, 222, 360);                
            }
            if (playerTwo.routes.get(i).
            contains("Sundsvall Stockholm Gray 4 Left")) {
                g.drawLine(222, 360, 283, 466);
            }
            if (playerTwo.routes.get(i).
            contains("Sundsvall Stockholm Gray 4 Right")) {
                g.drawLine(230, 360, 290, 466);
            }
            if (playerTwo.routes.get(i).
            contains("Sundsvall Ostersund Green 2")) {
                g.drawLine(222, 360, 195, 374);
                g.drawLine(195, 374, 170, 350);
            }
            if (playerTwo.routes.get(i).
            contains("Sundsvall Orebro Orange 4")) {
                g.drawLine(222, 360, 220, 482);
            }
            if (playerTwo.routes.get(i).
            contains("Stockholm Norrkoping Red 1")) {
                g.drawLine(290, 466, 285, 505);
            }
            if (playerTwo.routes.get(i).contains(
                "Stockholm Norrkoping Orange 1")) {
                g.drawLine(283, 466, 280, 505);
            }
            if (playerTwo.routes.get(i).
            contains("Stockholm Orebro Purple 2")) {
                g.drawLine(283, 457, 220, 478);
            }
            if (playerTwo.routes.get(i).
            contains("Stockholm Orebro Black 2")) {
                g.drawLine(290, 466, 220, 486);
            }
            if (playerTwo.routes.get(i).contains(
                "Stockholm Turku Blue 3 Ferry 1")) {
                g.drawLine(290, 466, 295, 426);
                g.drawLine(295, 426, 308, 405);
                g.drawLine(308, 405, 335, 387);
            }
            if (playerTwo.routes.get(i).
            contains("Oslo Orebro Yellow 2")) {
                g.drawLine(220, 478, 160, 498);
            }
            if (playerTwo.routes.get(i).
            contains("Oslo Orebro Green 2")) {
                g.drawLine(220, 486, 160, 506);
            }
            if (playerTwo.routes.get(i).
            contains("Orebro Norrkoping Gray 2")) {
                g.drawLine(220, 486, 245, 505);
                g.drawLine(245, 505, 280, 505);
            }
            if (playerTwo.routes.get(i).
            contains("Orebro Goteborg Blue 2")) {
                g.drawLine(220, 486, 210, 560);
            }
            if (playerTwo.routes.get(i).
            contains("Oslo Goteborg Orange 2")) {
                g.drawLine(160, 506, 210, 560);
            }
            if (playerTwo.routes.get(i).
            contains("Oslo Alborg White 3 Ferry 1")) {
                g.drawLine(160, 506, 175, 595);
            }
            if (playerTwo.routes.get(i).
            contains("Norrkoping Goteborg Gray 3")) {
                g.drawLine(280, 505, 210, 560);
            }
            if (playerTwo.routes.get(i).contains(
                "Norrkoping Karlskrona Yellow 3")) {
                g.drawLine(285, 505, 298, 598);
            }
            if (playerTwo.routes.get(i).contains(
                "Norrkoping Karlskrona White 3")) {
                g.drawLine(280, 505, 293, 593);
            }
            if (playerTwo.routes.get(i).contains(
                "Karlskrona Kobenhavn Green 2 Ferry 1")) {
                g.drawLine(293, 593, 235, 618);
            }
            if (playerTwo.routes.get(i).contains(
                "Karlskrona Kobenhavn Blue 2 Ferry 1")) {
                g.drawLine(298, 598, 235, 627);
            }
            if (playerTwo.routes.get(i).contains(
                "Arhus Kobenhavn Gray 1 Ferry 1")) {
                g.drawLine(235, 627, 195, 627);
            }
            if (playerTwo.routes.get(i).
            contains("Arhus Alborg Purple 1")) {
                g.drawLine(195, 627, 175, 595);
            }
            if (playerTwo.routes.get(i).contains(
                "Goteborg Kobenhavn Black 2 Ferry 1")) {
                g.drawLine(210, 560, 235, 618);
            }
            if (playerTwo.routes.get(i).contains(
                "Goteborg Alborg Gray 2 Ferry 1")) {
                g.drawLine(210, 560, 205, 595);
                g.drawLine(205, 595, 175, 595);
            }
            if (playerTwo.routes.get(i).contains(
                "Kristiansand Alborg Red 2 Ferry 1")) {
                g.drawLine(175, 595, 150, 590);
                g.drawLine(150, 590, 137, 560);
            }
            if (playerTwo.routes.get(i).contains(
                "Kristiansand Stavanger Green 2 Tunnel")) {
                g.drawLine(137, 560, 75, 558);
            }
            if (playerTwo.routes.get(i).contains(
                "Kristiansand Stavanger Orange 3 Ferry 1")) {
                g.drawLine(137, 560, 120, 580);
                g.drawLine(120, 580, 85, 580);
                g.drawLine(85, 580, 75, 558);
            }
            if (playerTwo.routes.get(i).
            contains("Kristiansand Oslo Black 2")) {
                g.drawLine(137, 560, 137, 530);
                g.drawLine(137, 530, 160, 506);
            }
            if (playerTwo.routes.get(i).contains(
                "Oslo Lillehammer Purple 2 Tunnel")) {
                g.drawLine(160, 506, 155, 475);
                g.drawLine(155, 475, 132, 450);
            }
        }
        if (numPlayers == 3) {
            for (int i = 0; i < playerThree.routes.size(); i++) {
                g.setColor(Color.red);

                if (playerThree.routes.get(i)
                .contains("Murmansk Lieksa Gray 9")) {
                    g.drawLine(328, 40, 390, 100);
                    g.drawLine(390, 100, 405, 200);
                    g.drawLine(405, 200, 385, 228);
                }
                if (playerThree.routes.get(i).contains(
                    "Kirkenes Murmansk White 3 Ferry 1")) {
                    g.drawLine(327, 37, 280, 30);
                    g.drawLine(280, 30, 260, 55);
                }
                if (playerThree.routes.get(i).contains(
                    "Kirkenes Honningsvag Green 2 Ferry 1")) {
                    g.drawLine(260, 55, 210, 25);
                }
                if (playerThree.routes.get(i).contains(
                    "Honningsvag Tromso Purple 4 Ferry 2")) {
                    g.drawLine(210, 25, 142, 97);
                }
                if (playerThree.routes.get(i).contains(
                    "Tromso Narvik Yellow 3 Ferry 1")) {
                    g.drawLine(142, 97, 112, 120);
                    g.drawLine(112, 120, 127, 150);
                }
                if (playerThree.routes.get(i).contains(
                    "Narvik Kiruna White 1 Tunnel")) {
                    g.drawLine(137, 163, 155, 167);
                }
                if (playerThree.routes.get(i).contains(
                    "Narvik Kiruna Purple 1 Tunnel")) {
                    g.drawLine(132, 150, 155, 155);
                }
                if (playerThree.routes.get(i).contains(
                    "Narvik Moirana Orange 4 Ferry 2")) {
                    g.drawLine(127, 150, 95, 188);
                    g.drawLine(95, 188, 115, 238);
                }
                if (playerThree.routes.get(i).
                contains("Kiruna Boden Black 3")) {
                    g.drawLine(170, 163, 195, 185);
                    g.drawLine(195, 185, 228, 192);
                }
                if (playerThree.routes.get(i).
                contains("Kiruna Boden Orange 3")) {
                    g.drawLine(163, 170, 200, 200);
                    g.drawLine(200, 200, 228, 200);
                }
                if (playerThree.routes.get(i).contains(
                    "Kirkenes Rovaniemi Blue 5")) {
                    g.drawLine(260, 55, 305, 105);
                    g.drawLine(305, 105, 272, 157);
                }
                if (playerThree.routes.get(i)
                .contains("Rovaniemi Tornio Red 1")) {
                    g.drawLine(270, 163, 266, 192);
                }
                if (playerThree.routes.get(i).
                contains("Boden Tornio Green 1")) {
                    g.drawLine(233, 201, 267, 193);
                }
                if (playerThree.routes.get(i).contains(
                    "Rovaniemi Oulu Orange 2")) {
                    g.drawLine(276, 158, 296, 223);
                }
                if (playerThree.routes.get(i).
                contains("Tornio Oulu White 1")) {
                    g.drawLine(269, 195, 293, 220);
                }
                if (playerThree.routes.get(i).
                contains("Oulu Kajaani Yellow 2")) {
                    g.drawLine(293, 220, 353, 210);
                }
                if (playerThree.routes.get(i).
                contains("Kajaani Lieksa Blue 1")) {
                    g.drawLine(353, 210, 388, 230);
                }
                if (playerThree.routes.get(i).
                contains("Lieksa Kuopio Black 1")) {
                    g.drawLine(388, 230, 378, 260);
                }
                if (playerThree.routes.get(i)
                .contains("Kajaani Kuopio Green 2")) {
                    g.drawLine(353, 210, 349, 240);
                    g.drawLine(349, 240, 377, 263);
                }
                if (playerThree.routes.get(i).
                contains("Oulu Kuopio Gray 3")) {
                    g.drawLine(293, 220, 362, 263);
                    g.drawLine(362, 263, 377, 263);
                }
                if (playerThree.routes.get(i).
                contains("Oulu Vaasa Black 3")) {
                    g.drawLine(295, 220, 279, 303);
                }
                if (playerThree.routes.get(i).contains(
                    "Vaasa Umea Gray 1 Ferry 1")) {
                    g.drawLine(279, 303, 248, 280);
                }
                if (playerThree.routes.get(i).contains(
                    "Vaasa Sundsvall Blue 3 Ferry 1")) {
                    g.drawLine(279, 303, 280, 345);
                    g.drawLine(280, 345, 222, 360);
                }
                if (playerThree.routes.get(i).
                contains("Vaasa Kuopio Gray 4")) {
                    g.drawLine(279, 305, 377, 265);
                }
                if (playerThree.routes.get(i)
                .contains("Vaasa Tampere Purple 2")) {
                    g.drawLine(279, 305, 332, 345);
                }
                if (playerThree.routes.get(i)
                .contains("Kuopio Imatra Purple 2")) {
                    g.drawLine(377, 262, 409, 275);
                    g.drawLine(409, 275, 416, 307);
                }
                if (playerThree.routes.get(i).
                contains("Kuopio Lahti White 3")) {
                    g.drawLine(377, 262, 372, 345);
                }
                if (playerThree.routes.get(i).contains(
                    "Moirana Trondheim Green 5 Tunnel")) {
                    g.drawLine(118, 238, 116, 372);
                }
                if (playerThree.routes.get(i).contains(
                    "Moirana Trondheim Red 6 Ferry 2")) {
                    g.drawLine(115, 238, 79, 275);
                    g.drawLine(79, 275, 79, 330);
                    g.drawLine(79, 330, 113, 372);
                }
                if (playerThree.routes.get(i).contains(
                    "Trondheim Ostersund Black 2 Tunnel")) {
                    g.drawLine(116, 375, 172, 353);
                }
                if (playerThree.routes.get(i).contains(
                    "Trondheim Lillehammer Orange 3 Tunnel")) {
                    g.drawLine(113, 372, 145, 410);
                    g.drawLine(145, 410, 129, 449);
                }
                if (playerThree.routes.get(i).contains(
                    "Trondheim Andalsnes White 2 Ferry 1")) {
                    g.drawLine(113, 372, 84, 372);
                    g.drawLine(84, 372, 84, 405);
                }
                if (playerThree.routes.get(i).contains(
                    "Lillehammer Andalsnes Yellow 2 Tunnel")) {
                    g.drawLine(84, 405, 129, 449);
                }
                if (playerThree.routes.get(i).contains(
                    "Bergen Andalsnes Black 5 Ferry 2")) {
                    g.drawLine(84, 405, 54, 405);
                    g.drawLine(54, 405, 30, 460);
                    g.drawLine(30, 460, 46, 497);
                }
                if (playerThree.routes.get(i).contains(
                    "Bergen Stavanger Purple 2 Ferry 1")) {
                    g.drawLine(52, 502, 52, 532);
                    g.drawLine(52, 532, 73, 556);
                }
                if (playerThree.routes.get(i).contains(
                    "Bergen Oslo Blue 4 Tunnel")) {
                    g.drawLine(46, 497, 100, 473);
                    g.drawLine(100, 473, 155, 500);
                }
                if (playerThree.routes.get(i).contains(
                    "Bergen Oslo Red 4 Tunnel")) {
                    g.drawLine(46, 507, 100, 483);
                    g.drawLine(100, 483, 155, 510);
                }
                if (playerThree.routes.get(i).
                contains("Lahti Imatra Yellow 2")) {
                    g.drawLine(372, 345, 416, 307);
                }
                if (playerThree.routes.get(i)
                .contains("Lahti Helsinki Black 1")) {
                    g.drawLine(372, 345, 372, 382);
                }
                if (playerThree.routes.get(i).
                contains("Lahti Tampere Blue 1")) {
                    g.drawLine(372, 345, 337, 345);
                }
                if (playerThree.routes.get(i).contains(
                    "Helsinki Tampere Orange 1")) {
                    g.drawLine(370, 382, 333, 347);
                }
                if (playerThree.routes.get(i)
                .contains("Helsinki Turku White 1")) {
                    g.drawLine(375, 387, 335, 387);
                }
                if (playerThree.routes.get(i).
                contains("Helsinki Imatra Red 3")) {
                    g.drawLine(375, 387, 400, 365);
                    g.drawLine(400, 365, 420, 310);
                }
                if (playerThree.routes.get(i).contains(
                    "Helsinki Tallinn Purple 2")) {
                    g.drawLine(375, 387, 405, 410);
                    g.drawLine(405, 410, 390, 425);
                }
                if (playerThree.routes.get(i).contains(
                    "Helsinki Stockholm Yellow 4 Ferry 1")) {
                    g.drawLine(283, 458, 375, 387);
                }
                if (playerThree.routes.get(i).contains(
                    "Helsinki Stockholm Gray 4 Ferry 2")) {
                    g.drawLine(288, 463, 380, 392);
                }
                if (playerThree.routes.get(i).
                contains("Tampere Turku Red 1")) {
                    g.drawLine(334, 387, 335, 348);
                }
                if (playerThree.routes.get(i).contains(
                    "Tallinn Stockholm Green 4 Ferry 2")) {
                    g.drawLine(390, 425, 345, 460);
                    g.drawLine(345, 460, 320, 466);
                    g.drawLine(320, 466, 283, 466);
                }
                if (playerThree.routes.get(i).
                contains("Boden Umea Red 3")) {
                    g.drawLine(237, 200, 250, 280);
                }
                if (playerThree.routes.get(i).
                contains("Boden Umea White 3")) {                    
                    g.drawLine(230, 200, 242, 280);
                }
                if (playerThree.routes.get(i).contains(
                    "Umea Sundsvall Yellow 3")) {
                    g.drawLine(250, 280, 230, 360);
                }
                if (playerThree.routes.get(i).contains(
                    "Umea Sundsvall Purple 3")) {
                    g.drawLine(242, 280, 222, 360);
                }
                if (playerThree.routes.get(i).contains(
                    "Sundsvall Stockholm Gray 4 Left")) {
                    g.drawLine(222, 360, 283, 466);
                }
                if (playerThree.routes.get(i).contains(
                    "Sundsvall Stockholm Gray 4 Right")) {
                    g.drawLine(230, 360, 290, 466);
                }
                if (playerThree.routes.get(i).contains(
                    "Sundsvall Ostersund Green 2")) {
                    g.drawLine(222, 360, 195, 374);
                    g.drawLine(195, 374, 170, 350);
                }
                if (playerThree.routes.get(i).contains(
                    "Sundsvall Orebro Orange 4")) {
                    g.drawLine(222, 360, 220, 482);
                }
                if (playerThree.routes.get(i).contains(
                    "Stockholm Norrkoping Red 1")) {
                    g.drawLine(290, 466, 285, 505);
                }
                if (playerThree.routes.get(i).contains(
                    "Stockholm Norrkoping Orange 1")) {
                    g.drawLine(283, 466, 280, 505);
                }
                if (playerThree.routes.get(i).contains(
                    "Stockholm Orebro Purple 2")) {
                    g.drawLine(283, 457, 220, 478);
                }
                if (playerThree.routes.get(i).contains(
                    "Stockholm Orebro Black 2")) {
                    g.drawLine(290, 466, 220, 486);
                }
                if (playerThree.routes.get(i).contains(
                    "Stockholm Turku Blue 3 Ferry 1")) {
                    g.drawLine(290, 466, 295, 426);
                    g.drawLine(295, 426, 308, 405);
                    g.drawLine(308, 405, 335, 387);
                }
                if (playerThree.routes.get(i).
                contains("Oslo Orebro Yellow 2")) {
                    g.drawLine(220, 478, 160, 498);
                }
                if (playerThree.routes.get(i).
                contains("Oslo Orebro Green 2")) {
                    g.drawLine(220, 486, 160, 506);
                }
                if (playerThree.routes.get(i).contains(
                    "Orebro Norrkoping Gray 2")) {
                    g.drawLine(220, 486, 245, 505);
                    g.drawLine(245, 505, 280, 505);
                }
                if (playerThree.routes.get(i)
                .contains("Orebro Goteborg Blue 2")) {
                    g.drawLine(220, 486, 210, 560);
                }
                if (playerThree.routes.get(i)
                .contains("Oslo Goteborg Orange 2")) {
                    g.drawLine(160, 506, 210, 560);
                }
                if (playerThree.routes.get(i).contains(
                    "Oslo Alborg White 3 Ferry 1")) {
                    g.drawLine(160, 506, 175, 595);
                }
                if (playerThree.routes.get(i).contains(
                    "Norrkoping Goteborg Gray 3")) {
                    g.drawLine(280, 505, 210, 560);
                }
                if (playerThree.routes.get(i).contains(
                    "Norrkoping Karlskrona Yellow 3")) {
                    g.drawLine(285, 505, 298, 598);
                }
                if (playerThree.routes.get(i).contains(
                    "Norrkoping Karlskrona White 3")) {
                    g.drawLine(280, 505, 293, 593);
                }
                if (playerThree.routes.get(i).contains(
                    "Karlskrona Kobenhavn Green 2 Ferry 1")) {
                    g.drawLine(293, 593, 235, 618);
                }
                if (playerThree.routes.get(i).contains(
                    "Karlskrona Kobenhavn Blue 2 Ferry 1")) {
                    g.drawLine(298, 598, 235, 627);
                }
                if (playerThree.routes.get(i).contains(
                    "Arhus Kobenhavn Gray 1 Ferry 1")) {
                    g.drawLine(235, 627, 195, 627);
                }
                if (playerThree.routes.get(i).
                contains("Arhus Alborg Purple 1")) {
                    g.drawLine(195, 627, 175, 595);
                }
                if (playerThree.routes.get(i).contains(
                    "Goteborg Kobenhavn Black 2 Ferry 1")) {
                    g.drawLine(210, 560, 235, 618);
                }
                if (playerThree.routes.get(i).contains(
                    "Goteborg Alborg Gray 2 Ferry 1")) {
                    g.drawLine(210, 560, 205, 595);
                    g.drawLine(205, 595, 175, 595);
                }
                if (playerThree.routes.get(i).contains(
                    "Kristiansand Alborg Red 2 Ferry 1")) {
                    g.drawLine(175, 595, 150, 590);
                    g.drawLine(150, 590, 137, 560);
                }
                if (playerThree.routes.get(i).contains(
                    "Kristiansand Stavanger Green 2 Tunnel")) {
                    g.drawLine(137, 560, 75, 558);
                }
                if (playerThree.routes.get(i).contains(
                    "Kristiansand Stavanger Orange 3 Ferry 1")) {
                    g.drawLine(137, 560, 120, 580);
                    g.drawLine(120, 580, 85, 580);
                    g.drawLine(85, 580, 75, 558);
                }
                if (playerThree.routes.get(i).contains(
                    "Kristiansand Oslo Black 2")) {
                    g.drawLine(137, 560, 137, 530);
                    g.drawLine(137, 530, 160, 506);
                }
                if (playerThree.routes.get(i).contains(
                    "Oslo Lillehammer Purple 2 Tunnel")) {
                    g.drawLine(160, 506, 155, 475);
                    g.drawLine(155, 475, 132, 450);
                }
            }
        }

        if (playerOne.isTurn() == true) {
            playerOne.paintComponent(g);
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.ROMAN_BASELINE, 20));
            g.drawString(name1 + "'s turn", 500, 400);
            g.setFont(new Font("serif", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.blue);
            g.drawString(name1 + "'s Score is: " + score1, 950, 550);
            g.setColor(Color.green);
            g.drawString(name2 + "'s Score is: " + score2, 950, 575);
        }
        if (playerTwo.isTurn() == true) {
            playerTwo.paintComponent(g);
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.ROMAN_BASELINE, 20));
            g.drawString(name2 + "'s turn", 500, 400);
            g.setFont(new Font("serif", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.blue);
            g.drawString(name1 + "'s Score is: " + score1, 950, 550);
            g.setColor(Color.green);
            g.drawString(name2 + "'s Score is: " + score2, 950, 575);
        }
        if (playerThree.isTurn() == true) {
            playerThree.paintComponent(g);
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.ROMAN_BASELINE, 20));
            g.drawString(name3 + "'s turn", 500, 400);
            g.setFont(new Font("serif", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.blue);
            g.drawString(name1 + "'s Score is: " + score1, 950, 550);
            g.setColor(Color.green);
            g.drawString(name2 + "'s Score is: " + score2, 950, 575);
        }

        if (numPlayers == 3) {
            g.setColor(Color.red);
            g.drawString(name3 + "'s Score is: " + score3, 950, 600);
        }

        g.setColor(Color.black);

    }

    public void mouseClicked(MouseEvent e) {
        if (click != 1 && click != 2 &&
        e.getButton() == 1 && boardClick == 0) {
            if (Karlskrona.contains(e.getPoint())) {
                city1 = "Karlskrona";
                boardClick++;
                return;
            }
            if (Arhus.contains(e.getPoint())) {
                city1 = "Arhus";
                boardClick++;
                return;
            }
            if (Kobenhavn.contains(e.getPoint())) {
                city1 = "Kobenhavn";
                boardClick++;
                return;
            }
            if (Goteborg.contains(e.getPoint())) {
                city1 = "Goteborg";
                boardClick++;
                return;
            }
            if (Alborg.contains(e.getPoint())) {
                city1 = "Alborg";
                boardClick++;
                return;
            }
            if (Kristiansand.contains(e.getPoint())) {
                city1 = "Kristiansand";
                boardClick++;
                return;
            }
            if (Stavanger.contains(e.getPoint())) {
                city1 = "Stavanger";
                boardClick++;
                return;
            }
            if (Bergen.contains(e.getPoint())) {
                city1 = "Bergen";
                boardClick++;
                return;
            }
            if (Oslo.contains(e.getPoint())) {
                city1 = "Oslo";
                boardClick++;
                return;
            }
            if (Orebro.contains(e.getPoint())) {
                city1 = "Orebro";
                boardClick++;
                return;
            }
            if (Lillehammer.contains(e.getPoint())) {
                city1 = "Lillehammer";
                boardClick++;
                return;
            }
            if (Andalsnes.contains(e.getPoint())) {
                city1 = "Andalsnes";
                boardClick++;
                return;
            }
            if (Stockholm.contains(e.getPoint())) {
                city1 = "Stockholm";
                boardClick++;
                return;
            }
            if (Norrkoping.contains(e.getPoint())) {
                city1 = "Norrkoping";
                boardClick++;
                return;
            }
            if (Tallinn.contains(e.getPoint())) {
                city1 = "Tallinn";
                boardClick++;
                return;
            }
            if (Helsinki.contains(e.getPoint())) {
                city1 = "Helsinki";
                boardClick++;
                return;
            }
            if (Turku.contains(e.getPoint())) {
                city1 = "Turku";
                boardClick++;
                return;
            }
            if (Sundsvall.contains(e.getPoint())) {
                city1 = "Sundsvall";
                boardClick++;
                return;
            }
            if (Ostersund.contains(e.getPoint())) {
                city1 = "Ostersund";
                boardClick++;
                return;
            }
            if (Trondheim.contains(e.getPoint())) {
                city1 = "Trondheim";
                boardClick++;
                return;
            }
            if (Tampere.contains(e.getPoint())) {
                city1 = "Tampere";
                boardClick++;
                return;
            }
            if (Lahti.contains(e.getPoint())) {
                city1 = "Lahti";
                boardClick++;
                return;
            }
            if (Imatra.contains(e.getPoint())) {
                city1 = "Imatra";
                boardClick++;
                return;
            }
            if (Vaasa.contains(e.getPoint())) {
                city1 = "Vaasa";
                boardClick++;
                return;
            }
            if (Umea.contains(e.getPoint())) {
                city1 = "Umea";
                boardClick++;
                return;
            }
            if (Kuopio.contains(e.getPoint())) {
                city1 = "Kuopio";
                boardClick++;
                return;
            }
            if (Lieksa.contains(e.getPoint())) {
                city1 = "Lieksa";
                boardClick++;
                return;
            }
            if (Kajaani.contains(e.getPoint())) {
                city1 = "Kajaani";
                boardClick++;
                return;
            }
            if (Murmansk.contains(e.getPoint())) {
                city1 = "Murmansk";
                boardClick++;
                return;
            }
            if (Oulu.contains(e.getPoint())) {
                city1 = "Oulu";
                boardClick++;
                return;
            }
            if (Tornio.contains(e.getPoint())) {
                city1 = "Tornio";
                boardClick++;
                return;
            }
            if (Boden.contains(e.getPoint())) {
                city1 = "Boden";
                boardClick++;
                return;
            }
            if (Moirana.contains(e.getPoint())) {
                city1 = "Moirana";
                boardClick++;
                return;
            }
            if (Narvik.contains(e.getPoint())) {
                city1 = "Narvik";
                boardClick++;
                return;
            }
            if (Kiruna.contains(e.getPoint())) {
                city1 = "Kiruna";
                boardClick++;
                return;
            }
            if (Rovaniemi.contains(e.getPoint())) {
                city1 = "Rovaniemi";
                boardClick++;
                return;
            }
            if (Kirkenes.contains(e.getPoint())) {
                city1 = "Kirkenes";
                boardClick++;
                return;
            }
            if (Tromso.contains(e.getPoint())) {
                city1 = "Tromso";
                boardClick++;
                return;
            }
            if (Honningsvag.contains(e.getPoint())) {
                city1 = "Honningsvag";
                boardClick++;
                return;
            }
        }

        if (click != 1 && click != 2 &&
        e.getButton() == 1 && boardClick == 1) {
            if (Karlskrona.contains(e.getPoint())) {
                city2 = "Karlskrona";
                boardClick++;
            }
            if (Arhus.contains(e.getPoint())) {
                city2 = "Arhus";
                boardClick++;
            }
            if (Kobenhavn.contains(e.getPoint())) {
                city2 = "Kobenhavn";
                boardClick++;
            }
            if (Goteborg.contains(e.getPoint())) {
                city2 = "Goteborg";
                boardClick++;
            }
            if (Alborg.contains(e.getPoint())) {
                city2 = "Alborg";
                boardClick++;
            }
            if (Kristiansand.contains(e.getPoint())) {
                city2 = "Kristiansand";
                boardClick++;
            }
            if (Stavanger.contains(e.getPoint())) {
                city2 = "Stavanger";
                boardClick++;
            }
            if (Bergen.contains(e.getPoint())) {
                city2 = "Bergen";
                boardClick++;
            }
            if (Oslo.contains(e.getPoint())) {
                city2 = "Oslo";
                boardClick++;
            }
            if (Orebro.contains(e.getPoint())) {
                city2 = "Orebro";
                boardClick++;
            }
            if (Lillehammer.contains(e.getPoint())) {
                city2 = "Lillehammer";
                boardClick++;
            }
            if (Andalsnes.contains(e.getPoint())) {
                city2 = "Andalsnes";
                boardClick++;
            }
            if (Stockholm.contains(e.getPoint())) {
                city2 = "Stockholm";
                boardClick++;
            }
            if (Norrkoping.contains(e.getPoint())) {
                city2 = "Norrkoping";
                boardClick++;
            }
            if (Tallinn.contains(e.getPoint())) {
                city2 = "Tallinn";
                boardClick++;
            }
            if (Helsinki.contains(e.getPoint())) {
                city2 = "Helsinki";
                boardClick++;
            }
            if (Turku.contains(e.getPoint())) {
                city2 = "Turku";
                boardClick++;
            }
            if (Sundsvall.contains(e.getPoint())) {
                city2 = "Sundsvall";
                boardClick++;
            }
            if (Ostersund.contains(e.getPoint())) {
                city2 = "Ostersund";
                boardClick++;
            }
            if (Trondheim.contains(e.getPoint())) {
                city2 = "Trondheim";
                boardClick++;
            }
            if (Tampere.contains(e.getPoint())) {
                city2 = "Tampere";
                boardClick++;
            }
            if (Lahti.contains(e.getPoint())) {
                city2 = "Lahti";
                boardClick++;
            }
            if (Imatra.contains(e.getPoint())) {
                city2 = "Imatra";
                boardClick++;
            }
            if (Vaasa.contains(e.getPoint())) {
                city2 = "Vaasa";
                boardClick++;
            }
            if (Umea.contains(e.getPoint())) {
                city2 = "Umea";
                boardClick++;
            }
            if (Kuopio.contains(e.getPoint())) {
                city2 = "Kuopio";
                boardClick++;
            }
            if (Lieksa.contains(e.getPoint())) {
                city2 = "Lieksa";
                boardClick++;
            }
            if (Kajaani.contains(e.getPoint())) {
                city2 = "Kajaani";
                boardClick++;
            }
            if (Murmansk.contains(e.getPoint())) {
                city2 = "Murmansk";
                boardClick++;
            }
            if (Oulu.contains(e.getPoint())) {
                city2 = "Oulu";
                boardClick++;
            }
            if (Tornio.contains(e.getPoint())) {
                city2 = "Tornio";
                boardClick++;
            }
            if (Boden.contains(e.getPoint())) {
                city2 = "Boden";
                boardClick++;
            }
            if (Moirana.contains(e.getPoint())) {
                city2 = "Moirana";
                boardClick++;
            }
            if (Narvik.contains(e.getPoint())) {
                city2 = "Narvik";
                boardClick++;
            }
            if (Kiruna.contains(e.getPoint())) {
                city2 = "Kiruna";
                boardClick++;
            }
            if (Rovaniemi.contains(e.getPoint())) {
                city2 = "Rovaniemi";
                boardClick++;
            }
            if (Kirkenes.contains(e.getPoint())) {
                city2 = "Kirkenes";
                boardClick++;
            }
            if (Tromso.contains(e.getPoint())) {
                city2 = "Tromso";
                boardClick++;
            }
            if (Honningsvag.contains(e.getPoint())) {
                city2 = "Honningsvag";
                boardClick++;
            }
        }

        if (boardClick == 2) {
            Routes.edgeChecker(city1);
            Routes.tempChecker(city2);
            if (Routes.doubleRoutes.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "There is no route available between " + city1
                    + " and " + city2);
            } else if (Routes.doubleRoutes.size() == 1) {
                for 
                (int i = 0; i < currentPlayer().routes.size(); i++) {
                    if (currentPlayer().routes.get(i).contains(city1)
                    && currentPlayer().routes.get(i).contains(city2)) {
                        JOptionPane
                        .showMessageDialog(null,
                            "You cannot take both routes in a double route!");
                        return;
                    }
                }
                takeRoute(Routes.doubleRoutes.get(0));
            } else if (Routes.doubleRoutes.size() > 1) {
                Object[] choices = { Routes.doubleRoutes.get(0),
                        Routes.doubleRoutes.get(1) };

                Object choice = JOptionPane
                    .showInputDialog(null, "Choose Route", "Choose Route",
                        JOptionPane.QUESTION_MESSAGE, null, choices,
                        choices[0]);
                if (choice == null) {
                    boardClick = 0;
                    city1 = "";
                    city2 = "";
                    Routes.temp.clear();
                    Routes.doubleRoutes.clear();
                    return;
                }
                String pathChoice = choice.toString();
                takeRoute(pathChoice);
            }

            Routes.temp.clear();
            Routes.doubleRoutes.clear();

            boardClick = 0;
            city1 = "";
            city2 = "";
        }

        if (e.getButton() == 3) {
            if (Karlskrona.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Karlskrona");
            }
            if (Arhus.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Arhus");
            }
            if (Kobenhavn.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Kobenhavn");
            }
            if (Goteborg.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Goteborg");
            }
            if (Alborg.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Alborg");
            }
            if (Kristiansand.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Kristiansand");
            }
            if (Stavanger.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Stavanger");
            }
            if (Bergen.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Bergen");
            }
            if (Oslo.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Oslo");
            }
            if (Orebro.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Orebro");
            }
            if (Lillehammer.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Lillehammer");
            }
            if (Andalsnes.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Andalsnes");
            }
            if (Stockholm.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Stockholm");
            }
            if (Norrkoping.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Norrkoping");
            }
            if (Tallinn.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Tallinn");
            }
            if (Helsinki.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Helsinki");
            }
            if (Turku.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Turku");
            }
            if (Sundsvall.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Sundsvall");
            }
            if (Ostersund.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Ostersund");
            }
            if (Trondheim.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Trondheim");
            }
            if (Tampere.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Tampere");
            }
            if (Lahti.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Lahti");
            }
            if (Imatra.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Imatra");
            }
            if (Vaasa.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Vaasa");
            }
            if (Umea.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Umea");
            }
            if (Kuopio.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Kuopio");
            }
            if (Lieksa.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Lieksa");
            }
            if (Kajaani.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Kajaani");
            }
            if (Murmansk.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Murmansk");
            }
            if (Oulu.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Oulu");
            }
            if (Tornio.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Tornio");
            }
            if (Boden.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Boden");
            }
            if (Moirana.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Moirana");
            }
            if (Narvik.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Narvik");
            }
            if (Kiruna.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Kiruna");
            }
            if (Rovaniemi.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Rovaniemi");
            }
            if (Kirkenes.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Kirkenes");
            }
            if (Tromso.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Tromso");
            }
            if (Honningsvag.contains(e.getPoint())) {
                JOptionPane.showMessageDialog(null, "Honningsvag");
            }
        }

        if (dDeck.contains(e.getPoint())) {
            click++;
            if (click == 1) {
                String message =
                    "You have drawn 3 Destination Tickets: \n\n"
                    + destCards.getDCard(0) + "\n" + destCards.getDCard(1)
                    + "\n" + destCards.getDCard(2) + "\n";
                JOptionPane.showMessageDialog(null, message);
                message =
                "You must keep 1 card, but you can keep all of them. \n"
                + "Would you like to keep: \n\n"
                + destCards.getDCard(0) + " ?";
                int choice = JOptionPane.showConfirmDialog(null, message);
                if (choice == JOptionPane.NO_OPTION) {
                    destCards.removeDCard();
                } else if (choice == JOptionPane.YES_OPTION) {
                    currentPlayer().addDestCard(destCards.getDCard(0));
                    destCardNum++;
                    destCards.removeDCard();
                }
                message = 
                "You must keep 1 card, but you can keep all of them. \n"
                + "Would you like to keep: \n\n"
                + destCards.getDCard(0) + " ?";
                choice = JOptionPane.showConfirmDialog(null, message);
                if (choice == JOptionPane.NO_OPTION) {
                    destCards.removeDCard();
                } else if (choice == JOptionPane.YES_OPTION) {
                    currentPlayer().addDestCard(destCards.getDCard(0));
                    destCardNum++;
                    destCards.removeDCard();
                }
                if (destCardNum == 0) {
                    String keep1 = destCards.getDCard(0);
                    currentPlayer().addDestCard(destCards.getDCard(0));
                    destCards.removeDCard();

                    message = "You must now keep this card\n" + keep1;
                    JOptionPane.showMessageDialog(null, message);
                    destCardNum = 0;

                    JOptionPane.showMessageDialog
                    (null, "Next player please!");
                    nextPlayer();
                    return;

                }
                message =
                "You must keep 1 card, but you can keep all of them. \n"
                + "Would you like to keep: \n\n"
                + destCards.getDCard(0) + " ?";
                choice = JOptionPane.showConfirmDialog(null, message);
                if (choice == JOptionPane.NO_OPTION) {
                    destCards.removeDCard();
                } else if (choice == JOptionPane.YES_OPTION) {
                    currentPlayer().addDestCard(destCards.getDCard(0));
                    destCards.removeDCard();
                }

                destCardNum = 0;

                JOptionPane.showMessageDialog
                (null, "Next player please!");
                nextPlayer();

            } else if (click > 1) {
                JOptionPane.showMessageDialog(null,
                    "You can't do that this turn!");
                click--;
            }
        }

        if (trainDeck.contains(e.getPoint())) {
            click++;
            if (click == 1) {
                currentPlayer().addToHand(trainCards.draw());
                repaint();
            } else if (click == 2) {
                currentPlayer().addToHand(trainCards.draw());
                JOptionPane.showMessageDialog
                (null, "Next player please!");
                nextPlayer();
            }

        }

        if (trainCard1.contains(e.getPoint())) {
            click++;
            if (click == 1) {
                currentPlayer().addToHand(tableDeck.getCard(0));
                tableDeck.remove(0);
                tableDeck.addToDeck(trainCards.draw(), 0);
                repaint();
            } else if (click == 2) {
                currentPlayer().addToHand(tableDeck.getCard(0));
                tableDeck.remove(0);
                tableDeck.addToDeck(trainCards.draw(), 0);
                JOptionPane.showMessageDialog
                (null, "Next player please!");
                nextPlayer();
            }
        }

        if (trainCard2.contains(e.getPoint())) {
            click++;
            if (click == 1) {
                currentPlayer().addToHand(tableDeck.getCard(1));
                tableDeck.remove(1);
                tableDeck.addToDeck(trainCards.draw(), 1);
                repaint();
            } else if (click == 2) {
                currentPlayer().addToHand(tableDeck.getCard(1));
                tableDeck.remove(1);
                tableDeck.addToDeck(trainCards.draw(), 1);
                JOptionPane.showMessageDialog
                (null, "Next player please!");
                nextPlayer();
            }
        }

        if (trainCard3.contains(e.getPoint())) {
            click++;
            if (click == 1) {
                currentPlayer().addToHand(tableDeck.getCard(2));
                tableDeck.remove(2);
                tableDeck.addToDeck(trainCards.draw(), 2);
                repaint();
            } else if (click == 2) {
                currentPlayer().addToHand(tableDeck.getCard(2));
                tableDeck.remove(2);
                tableDeck.addToDeck(trainCards.draw(), 2);
                JOptionPane.showMessageDialog
                (null, "Next player please!");
                nextPlayer();
            }
        }

        if (trainCard4.contains(e.getPoint())) {
            click++;
            if (click == 1) {
                currentPlayer().addToHand(tableDeck.getCard(3));
                tableDeck.remove(3);
                tableDeck.addToDeck(trainCards.draw(), 3);

                repaint();
            } else if (click == 2) {
                currentPlayer().addToHand(tableDeck.getCard(3));
                tableDeck.remove(3);
                tableDeck.addToDeck(trainCards.draw(), 3);
                JOptionPane.showMessageDialog
                (null, "Next player please!");
                nextPlayer();
            }
        }

        if (trainCard5.contains(e.getPoint())) {
            click++;
            if (click == 1) {
                currentPlayer().addToHand(tableDeck.getCard(4));
                tableDeck.remove(4);
                tableDeck.addToDeck(trainCards.draw(), 4);
                repaint();
            } else if (click == 2) {
                currentPlayer().addToHand(tableDeck.getCard(4));
                tableDeck.remove(4);
                tableDeck.addToDeck(trainCards.draw(), 4);
                JOptionPane.showMessageDialog
                (null, "Next player please!");
                nextPlayer();
            }
        }
    }

    /**
     * Method developed to handle the special 
     * cases of when a double route is
     * selected.
     * 
     * 
     * @param String
     *            route
     * @return void
     */
    public void takeRoute(String route) {

        boolean found = false;
        int k = 0;
        TrainCard card = null;

        String routeColor = "";
        int routeTrains = 0;
        if (route.contains("Red")) {
            routeColor = "Red";
        }
        if (route.contains("Blue")) {
            routeColor = "Blue";
        }
        if (route.contains("Green")) {
            routeColor = "Green";
        }
        if (route.contains("Yellow")) {
            routeColor = "Yellow";
        }
        if (route.contains("Purple")) {
            routeColor = "Purple";
        }
        if (route.contains("White")) {
            routeColor = "White";
        }
        if (route.contains("Gray")) {
            routeColor = "Gray";
        }
        if (route.contains("Black")) {
            routeColor = "Black";
        }
        if (route.contains("Orange")) {
            routeColor = "Orange";
        }

        if (route.contains("1") && !route.contains("Ferry")) {
            routeTrains = 1;
        }
        if (route.contains("2") && !route.contains("Ferry")) {
            routeTrains = 2;
        }
        if (route.contains("3") && !route.contains("Ferry")) {
            routeTrains = 3;
        }
        if (route.contains("4") && !route.contains("Ferry")) {
            routeTrains = 4;
        }
        if (route.contains("5") && !route.contains("Ferry")) {
            routeTrains = 5;
        }
        if (route.contains("6") && !route.contains("Ferry")) {
            routeTrains = 6;
        }
        if (route.contains("9") && !route.contains("Ferry")) {
            routeTrains = 9;
        }

        if (currentPlayer().getNumTrains() < routeTrains) {
            JOptionPane.showMessageDialog(null,
                "You do not have enough trains left");
            return;
        }

        int trainCost = routeTrains;
        boolean satisfied = false;
        if (route.contains("Murmansk Lieksa")) {
            int remainingTrains = 9;

            Object[] choices =
                { "Red", "Blue", "Green", "Yellow", "Orange",
                    "Black", "Purple", "White" };
            Object choice = JOptionPane.showInputDialog(null,
                    "Choose the Primary Color to Use", "Choose Color",
                    JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
            String primaryColor = choice.toString();
            remainingTrains = remainingTrains
            - currentPlayer().getTrains(primaryColor);
            remainingTrains = remainingTrains * 4;
            if (remainingTrains == 0) {
                currentPlayer().routes.add(route);
                currentPlayer().decrimentTrains(routeTrains);
                if (routeColor.equals("Red")) {
                    while (!found) {
                        if (trainCards.possibleCards.get(k).getColor()
                        .equals("redCard")) {
                            card = trainCards.possibleCards.get(k);
                            found = true;
                        }
                        k++;
                    }
                    for (int i = 0; i < trainCost; i++) {
                        currentPlayer().removeFromHand(card);
                        trainCards.discardDeck.add(card);
                    }
                }
                if (routeColor.equals("Blue")) {
                    while (!found) {
                        if (trainCards.possibleCards.get(k).getColor()
                        .equals("blueCard")) {
                            card = trainCards.possibleCards.get(k);
                            found = true;
                        }
                        k++;
                    }
                    for (int i = 0; i < trainCost; i++) {
                        currentPlayer().removeFromHand(card);
                        trainCards.discardDeck.add(card);
                    }
                }
                if (routeColor.equals("Green")) {
                    while (!found) {
                        if (trainCards.possibleCards.get(k).getColor()
                        .equals("greenCard")) {
                            card = trainCards.possibleCards.get(k);
                            found = true;
                        }
                        k++;
                    }
                    for (int i = 0; i < trainCost; i++) {
                        currentPlayer().removeFromHand(card);
                        trainCards.discardDeck.add(card);
                    }
                }
                if (routeColor.equals("Yellow")) {
                    while (!found) {
                        if (trainCards.possibleCards.get(k).getColor()
                        .equals("yellowCard")) {
                            card = trainCards.possibleCards.get(k);
                            found = true;
                        }
                        k++;
                    }
                    for (int i = 0; i < trainCost; i++) {
                        currentPlayer().removeFromHand(card);
                        trainCards.discardDeck.add(card);
                    }
                }
                if (routeColor.equals("Orange")) {
                    while (!found) {
                        if (trainCards.possibleCards.get(k).getColor()
                        .equals("orangeCard")) {
                            card = trainCards.possibleCards.get(k);
                            found = true;
                        }
                        k++;
                    }
                    for (int i = 0; i < trainCost; i++) {
                        currentPlayer().removeFromHand(card);
                        trainCards.discardDeck.add(card);
                    }
                }
                if (routeColor.equals("Black")) {
                    while (!found) {
                        if(trainCards.possibleCards.get(k).getColor()
                        .equals("blackCard")) {
                            card = trainCards.possibleCards.get(k);
                            found = true;
                        }
                        k++;
                    }
                    for (int i = 0; i < trainCost; i++) {
                        currentPlayer().removeFromHand(card);
                        trainCards.discardDeck.add(card);
                    }
                }
                if (routeColor.equals("White")) {
                    while (!found) {
                        if (trainCards.possibleCards.get(k).getColor()
                        .equals("whiteCard")) {
                            card = trainCards.possibleCards.get(k);
                            found = true;
                        }
                        k++;
                    }
                    for (int i = 0; i < trainCost; i++) {
                        currentPlayer().removeFromHand(card);
                        trainCards.discardDeck.add(card);
                    }
                }
                if (routeColor.equals("Purple")) {
                    while (!found) {
                        if (trainCards.possibleCards.get(k).getColor()
                        .equals("pinkCard")) {
                            card = trainCards.possibleCards.get(k);
                            found = true;
                        }
                        k++;
                    }
                    for (int i = 0; i < trainCost; i++) {
                        currentPlayer().removeFromHand(card);
                        trainCards.discardDeck.add(card);
                    }
                }

                Routes.possibleRoutes.remove(route);

                if (routeTrains == 1) {
                    currentPlayer().addToScore(1);
                }
                if (routeTrains == 2) {
                    currentPlayer().addToScore(2);
                }
                if (routeTrains == 3) {
                    currentPlayer().addToScore(4);
                }
                if (routeTrains == 4) {
                    currentPlayer().addToScore(7);
                }
                if (routeTrains == 5) {
                    currentPlayer().addToScore(10);
                }
                if (routeTrains == 6) {
                    currentPlayer().addToScore(15);
                }
                if (routeTrains == 9) {
                    currentPlayer().addToScore(27);
                }

                if (currentPlayer().equals(playerOne)) {
                    score1 = currentPlayer().getScore();
                }
                if (currentPlayer().equals(playerTwo)) {
                    score2 = currentPlayer().getScore();
                }
                if (currentPlayer().equals(playerThree)) {
                    score3 = currentPlayer().getScore();
                }
                JOptionPane.showMessageDialog
                (null, "Next player please!");
                nextPlayer();
                repaint();
                return;
            } else {
                int leftover = currentPlayer().getTotalCards()
                    - currentPlayer().getTrains(primaryColor);
                JOptionPane.showMessageDialog(null, "You still owe "
                    + remainingTrains + " cards");
                if (remainingTrains > leftover) {
                    JOptionPane.showMessageDialog(null,
                        "You cannot afford that route");
                    return;
                }
                if (remainingTrains < leftover) {
                    String message = "You can afford to take the route. \n"
                        + "Would you like to take it?";
                    int choice2 = 
                        JOptionPane.showConfirmDialog(null, message);
                    if (choice2 == JOptionPane.NO_OPTION) {
                        return;
                    } else if (choice2 == JOptionPane.CANCEL_OPTION) {
                        return;
                    } else if (choice2 == JOptionPane.YES_OPTION) {
                        if (primaryColor.equals("Red")) {
                            while (!found) {
                                if (trainCards.
                                possibleCards.get(k).getColor()
                                .equals("redCard")) {
                                    card = trainCards.possibleCards.get(k);
                                    found = true;
                                }
                                k++;
                            }
                            for 
                            (int i = 0; i < currentPlayer().numRed; i++) {
                                currentPlayer().removeFromHand(card);
                                trainCards.discardDeck.add(card);
                            }
                        }
                        if (primaryColor.equals("Blue")) {
                            while (!found) {
                                if 
                                (trainCards.possibleCards.get(k).
                                getColor()
                                .equals("blueCard")) {
                                    card = trainCards.
                                    possibleCards.get(k);
                                    found = true;
                                }
                                k++;
                            }
                            for 
                            (int i = 0; i < currentPlayer().numBlue; i++){
                                currentPlayer().removeFromHand(card);
                                trainCards.discardDeck.add(card);
                            }
                        }
                        if (primaryColor.equals("Green")) {
                            while (!found) {
                                if 
                                (trainCards.possibleCards.get(k).getColor()
                                .equals("greenCard")) {
                                    card =
                                    trainCards.possibleCards.get(k);
                                    found = true;
                                }
                                k++;
                            }
                            for 
                            (int i = 0; i < currentPlayer().numGreen; i++){
                                currentPlayer().removeFromHand(card);
                                trainCards.discardDeck.add(card);
                            }
                        }
                        if (primaryColor.equals("Yellow")) {
                            while (!found) {
                                if 
                                (trainCards.possibleCards.get(k).getColor()
                                .equals("yellowCard")) {
                                    card =
                                    trainCards.possibleCards.get(k);
                                    found = true;
                                }
                                k++;
                            }
                            for 
                            (int i = 0; i < currentPlayer().numYellow; i++) {
                                currentPlayer().removeFromHand(card);
                                trainCards.discardDeck.add(card);
                            }
                        }
                        if (primaryColor.equals("Orange")) {
                            while (!found) {
                                if 
                                (trainCards.possibleCards.get(k).getColor()
                                .equals("orangeCard")) {
                                    card =
                                    trainCards.possibleCards.get(k);
                                    found = true;
                                }
                                k++;
                            }
                            for 
                            (int i = 0; i < currentPlayer().numOrange; i++) {
                                currentPlayer().removeFromHand(card);
                                trainCards.discardDeck.add(card);
                            }
                        }
                        if (primaryColor.equals("Black")) {
                            while (!found) {
                                if 
                                (trainCards.possibleCards.get(k).getColor()
                                .equals("blackCard")) {
                                    card =
                                    trainCards.possibleCards.get(k);
                                    found = true;
                                }
                                k++;
                            }
                            for 
                            (int i = 0; i < currentPlayer().numBlack; i++){
                                currentPlayer().removeFromHand(card);
                                trainCards.discardDeck.add(card);
                            }
                        }
                        if (primaryColor.equals("Purple")) {
                            while (!found) {
                                if (trainCards.possibleCards.get(k).
                                getColor()
                                .equals("pinkCard")) {
                                    card = 
                                    trainCards.possibleCards.get(k);
                                    found = true;
                                }
                                k++;
                            }
                            for 
                            (int i = 0; i < currentPlayer().numPink; i++){
                                currentPlayer().removeFromHand(card);
                                trainCards.discardDeck.add(card);
                            }
                        }
                        if (primaryColor.equals("White")) {
                            while (!found) {
                                if 
                                (trainCards.possibleCards.get(k).getColor()
                                .equals("whiteCard")) {
                                    card = trainCards.possibleCards.get(k);
                                    found = true;
                                }
                                k++;
                            }
                            for 
                            (int i = 0; i < currentPlayer().numWhite; i++){
                                currentPlayer().removeFromHand(card);
                                trainCards.discardDeck.add(card);
                            }
                        }

                        remainingTrains = remainingTrains
                        - currentPlayer().numRed;
                        while (!satisfied) {
                            if (remainingTrains < 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("redCard")) {
                                        card = trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < remainingTrains; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains == 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("redCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numRed; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains > 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("redCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < currentPlayer().numRed; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                            }
                            remainingTrains = remainingTrains
                            - currentPlayer().numBlue;
                            if (remainingTrains < 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("blueCard")) {
                                        card =
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < remainingTrains; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains == 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("blueCard")) {
                                        card =
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < currentPlayer().numBlue; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains > 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("blueCard")) {
                                        card =
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < currentPlayer().numBlue; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                            }
                            remainingTrains = remainingTrains
                            - currentPlayer().numBlack;
                            if (remainingTrains < 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("blackCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < remainingTrains; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains == 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("blackCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < currentPlayer().numBlack; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains > 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("blackCard")) {
                                        card =
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < currentPlayer().numBlack; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                            }
                            remainingTrains = remainingTrains
                            - currentPlayer().numGreen;
                            if (remainingTrains < 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("greenCard")) {
                                        card =
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for (int i = 0; i < remainingTrains; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains == 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("greenCard")) {
                                        card =
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numGreen; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains > 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("greenCard")) {
                                        card =
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numGreen; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                            }
                            remainingTrains = remainingTrains
                            - currentPlayer().numPink;
                            if (remainingTrains < 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("pinkCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for (int i = 0; i < remainingTrains; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains == 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("pinkCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < currentPlayer().numPink; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains > 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("pinkCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numPink; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                            }
                            remainingTrains = remainingTrains
                            - currentPlayer().numOrange;
                            if (remainingTrains < 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("orangeCard")) {
                                        card =
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for (int i = 0; i < remainingTrains; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains == 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("orangeCard")) {
                                        card =
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numOrange; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains > 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("orangeCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numOrange; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                            }
                            remainingTrains = remainingTrains
                            - currentPlayer().numYellow;
                            if (remainingTrains < 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("yellowCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < remainingTrains; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains == 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("yellowCard")) {
                                        card = trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numYellow; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains > 0 && !satisfied) {
                                while (!found) {
                                    if 
                                    (trainCards.possibleCards.get(k)
                                    .getColor().equals("yellowCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numYellow; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                            }
                            remainingTrains = remainingTrains
                            - currentPlayer().numWhite;
                            if (remainingTrains < 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("whiteCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for (int i = 0; i < remainingTrains; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains == 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("whiteCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numWhite; i++){
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains > 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor().equals("whiteCard")) {
                                        card = 
                                        trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < currentPlayer().numWhite; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                            }
                            remainingTrains = remainingTrains
                            - currentPlayer().numLoco;
                            if (remainingTrains < 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor()
                                    .equals("locomotiveCard")) {
                                        card = trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for
                                (int i = 0; i < remainingTrains; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                            if (remainingTrains == 0 && !satisfied) {
                                while (!found) {
                                    if (trainCards.possibleCards.get(k)
                                    .getColor()
                                    .equals("locomotiveCard")) {
                                        card = trainCards.possibleCards.get(k);
                                        found = true;
                                    }
                                    k++;
                                }
                                for 
                                (int i = 0; i < currentPlayer().numLoco; i++) {
                                    currentPlayer().removeFromHand(card);
                                    trainCards.discardDeck.add(card);
                                }
                                satisfied = true;
                            }
                        }
                    }
                }
            }
        }

        if (satisfied) {
            currentPlayer().routes.add(route);
            currentPlayer().decrimentTrains(routeTrains);

            Routes.possibleRoutes.remove(route);

            if (routeTrains == 1) {
                currentPlayer().addToScore(1);
            }
            if (routeTrains == 2) {
                currentPlayer().addToScore(2);
            }
            if (routeTrains == 3) {
                currentPlayer().addToScore(4);
            }
            if (routeTrains == 4) {
                currentPlayer().addToScore(7);
            }
            if (routeTrains == 5) {
                currentPlayer().addToScore(10);
            }
            if (routeTrains == 6) {
                currentPlayer().addToScore(15);
            }
            if (routeTrains == 9) {
                currentPlayer().addToScore(27);
            }

            if (currentPlayer().equals(playerOne)) {
                score1 = currentPlayer().getScore();
            }
            if (currentPlayer().equals(playerTwo)) {
                score2 = currentPlayer().getScore();
            }
            if (currentPlayer().equals(playerThree)) {
                score3 = currentPlayer().getScore();
            }
            JOptionPane.showMessageDialog(null, "Next player please!");
            nextPlayer();
            repaint();
            return;
        }

        if (route.contains("Tunnel")) {
            TrainCard card1 = trainCards.draw();
            TrainCard card2 = trainCards.draw();
            TrainCard card3 = trainCards.draw();

            int count = 0;
            String tempColor = "";
            if (routeColor.equals("Red")) {
                tempColor = "redCard";
            }
            if (routeColor.equals("Blue")) {
                tempColor = "blueCard";
            }
            if (routeColor.equals("Green")) {
                tempColor = "greenCard";
            }
            if (routeColor.equals("Yellow")) {
                tempColor = "yellowCard";
            }
            if (routeColor.equals("Orange")) {
                tempColor = "orangeCard";
            }
            if (routeColor.equals("White")) {
                tempColor = "whiteCard";
            }
            if (routeColor.equals("Purple")) {
                tempColor = "pinkCard";
            }
            if (routeColor.equals("Black")) {
                tempColor = "blackCard";
            }
            if (card1.getColor().equals(tempColor)
            || card1.getColor().equals("locomotiveCard")) {
                count++;
            }
            if (card2.getColor().equals(tempColor)
            || card2.getColor().equals("locomotiveCard")) {
                count++;
            }
            if (card3.getColor().equals(tempColor)
            || card3.getColor().equals("locomotiveCard")) {
                count++;
            }
            trainCards.discard(card1);
            trainCards.discard(card2);
            trainCards.discard(card3);
            JOptionPane.showMessageDialog(null, "You must pay " + count
                + " extra for this route");

            if (count > 0) {
                trainCost = trainCost + count;
            }

            if (trainCost > currentPlayer().getTrains(routeColor)) {
                int difference = trainCost
                    - currentPlayer().getTrains(routeColor);
                if (difference > currentPlayer().getTrains("Locomotive")) {
                    JOptionPane
                    .showMessageDialog(null,
                        "You cannot afford that route, you may try again next turn");
                    JOptionPane.
                    showMessageDialog(null, "Next player please!");
                    nextPlayer();
                    repaint();
                    return;
                } else {
                    if (routeColor.equals("Red")) {
                        currentPlayer().numRed = 
                        currentPlayer().numRed
                        + difference;
                    }
                    if (routeColor.equals("Blue")) {
                        currentPlayer().numBlue = 
                        currentPlayer().numBlue
                        + difference;
                    }
                    if (routeColor.equals("Green")) {
                        currentPlayer().numGreen = 
                        currentPlayer().numGreen
                        + difference;
                    }
                    if (routeColor.equals("Yellow")) {
                        currentPlayer().numYellow = 
                        currentPlayer().numYellow
                        + difference;
                    }
                    if (routeColor.equals("Orange")) {
                        currentPlayer().numOrange = 
                        currentPlayer().numOrange
                        + difference;
                    }
                    if (routeColor.equals("Black")) {
                        currentPlayer().numBlack = 
                        currentPlayer().numBlack
                        + difference;
                    }
                    if (routeColor.equals("White")) {
                        currentPlayer().numWhite =
                        currentPlayer().numWhite
                        + difference;
                    }
                    if (routeColor.equals("Purple")) {
                        currentPlayer().numPink = 
                        currentPlayer().numPink
                        + difference;
                    }
                    currentPlayer().numLoco = currentPlayer().numLoco
                    - difference;
                }
            }
        }

        if (routeColor.equals("Gray")) {
            Object[] choices = 
                { "Red", "Blue", "Green", "Yellow", "Orange",
                    "Black", "Purple", "White" };
            Object choice = JOptionPane.
                showInputDialog(null, "Choose Color",

                    "Choose Color", JOptionPane.QUESTION_MESSAGE, null,
                    choices, choices[0]);
            routeColor = choice.toString();
        }

        if (route.contains("Ferry")) {
            int first = 0;
            int second = 0;

            if (route.contains("6")) {
                first = 6;
                second = 2;
            } else if (route.contains("4") && route.contains("1")) {
                first = 4;
                second = 1;
            } else if (route.contains("4") && route.contains("2")) {
                first = 4;
                second = 2;
            } else if (route.contains("3")) {
                first = 3;
                second = 1;
            } else if (route.contains("2")) {
                first = 2;
                second = 1;
            } else {
                first = 1;
                second = 1;
            }

            int colorNum = first - second;
            if (colorNum > currentPlayer().getTrains(routeColor)) {
                JOptionPane.showMessageDialog(null,
                    "You cannot afford that route");
                return;
            } else {
                if (second > currentPlayer().getTrains("Locomotive")) {
                    JOptionPane.showMessageDialog(null,
                        "You cannot afford that route");
                    return;
                } else {
                    currentPlayer().routes.add(route);
                    currentPlayer().decrimentTrains(first);
                    if (routeColor.equals("Red")) {
                        while (!found) {
                            if (trainCards.
                            possibleCards.get(k).getColor()
                            .equals("redCard")) {
                                card = trainCards.possibleCards.get(k);
                                found = true;
                            }
                            k++;
                        }
                        for (int i = 0; i < colorNum; i++) {
                            currentPlayer().removeFromHand(card);
                            trainCards.discardDeck.add(card);
                        }
                    }
                    if (routeColor.equals("Blue")) {
                        while (!found) {
                            if (trainCards.
                            possibleCards.get(k).getColor()
                            .equals("blueCard")) {
                                card = trainCards.possibleCards.get(k);
                                found = true;
                            }
                            k++;
                        }
                        for (int i = 0; i < colorNum; i++) {
                            currentPlayer().removeFromHand(card);
                            trainCards.discardDeck.add(card);
                        }
                    }
                    if (routeColor.equals("Green")) {
                        while (!found) {
                            if (trainCards.
                            possibleCards.get(k).getColor()
                            .equals("greenCard")) {
                                card = trainCards.possibleCards.get(k);
                                found = true;
                            }
                            k++;
                        }
                        for (int i = 0; i < colorNum; i++) {
                            currentPlayer().removeFromHand(card);
                            trainCards.discardDeck.add(card);
                        }
                    }
                    if (routeColor.equals("Yellow")) {
                        while (!found) {
                            if (trainCards.
                            possibleCards.get(k).getColor()
                            .equals("yellowCard")) {
                                card = trainCards.possibleCards.get(k);
                                found = true;
                            }
                            k++;
                        }
                        for (int i = 0; i < colorNum; i++) {
                            currentPlayer().removeFromHand(card);
                            trainCards.discardDeck.add(card);
                        }
                    }
                    if (routeColor.equals("Orange")) {
                        while (!found) {
                            if (trainCards.
                            possibleCards.get(k).getColor()
                            .equals("orangeCard")) {
                                card = trainCards.possibleCards.get(k);
                                found = true;
                            }
                            k++;
                        }
                        for (int i = 0; i < colorNum; i++) {
                            currentPlayer().removeFromHand(card);
                            trainCards.discardDeck.add(card);
                        }
                    }
                    if (routeColor.equals("Black")) {
                        while (!found) {
                            if (trainCards.
                            possibleCards.get(k).getColor()
                            .equals("blackCard")) {
                                card = trainCards.possibleCards.get(k);
                                found = true;
                            }
                            k++;
                        }
                        for (int i = 0; i < colorNum; i++) {
                            currentPlayer().removeFromHand(card);
                            trainCards.discardDeck.add(card);
                        }
                    }
                    if (routeColor.equals("White")) {
                        while (!found) {
                            if (trainCards.
                            possibleCards.get(k).getColor()
                            .equals("whiteCard")) {
                                card = trainCards.possibleCards.get(k);
                                found = true;
                            }
                            k++;
                        }
                        for (int i = 0; i < colorNum; i++) {
                            currentPlayer().removeFromHand(card);
                            trainCards.discardDeck.add(card);
                        }
                    }
                    if (routeColor.equals("Purple")) {
                        while (!found) {
                            if (trainCards.
                            possibleCards.get(k).getColor()
                            .equals("pinkCard")) {
                                card = trainCards.possibleCards.get(k);
                                found = true;
                            }
                            k++;
                        }
                        for (int i = 0; i < colorNum; i++) {
                            currentPlayer().removeFromHand(card);
                            trainCards.discardDeck.add(card);
                        }
                    }
                    found = false;
                    while (!found) {
                        if (trainCards.possibleCards.get(k).getColor()
                        .equals("locomotiveCard")) {
                            card = trainCards.possibleCards.get(k);
                            found = true;
                        }
                        k++;
                    }
                    for (int i = 0; i < second; i++) {
                        currentPlayer().removeFromHand(card);
                        trainCards.discardDeck.add(card);
                    }

                    if (numPlayers == 2 && 
                    Routes.doubleRoutes.size() > 1) {
                        Routes.possibleRoutes
                        .remove(Routes.doubleRoutes.get(0));
                        Routes.possibleRoutes
                        .remove(Routes.doubleRoutes.get(1));
                    } else {
                        Routes.possibleRoutes.remove(route);
                    }
                    if (first == 1) {
                        currentPlayer().addToScore(1);
                    }
                    if (first == 2) {
                        currentPlayer().addToScore(2);
                    }
                    if (first == 3) {
                        currentPlayer().addToScore(4);
                    }
                    if (first == 4) {
                        currentPlayer().addToScore(7);
                    }
                    if (first == 5) {
                        currentPlayer().addToScore(10);
                    }
                    if (first == 6) {
                        currentPlayer().addToScore(15);
                    }

                    if (currentPlayer().equals(playerOne)) {
                        score1 = currentPlayer().getScore();
                    }
                    if (currentPlayer().equals(playerTwo)) {
                        score2 = currentPlayer().getScore();
                    }
                    if (currentPlayer().equals(playerThree)) {
                        score3 = currentPlayer().getScore();
                    }
                    JOptionPane.
                    showMessageDialog(null, "Next player please!");
                    nextPlayer();
                    repaint();
                    return;
                }
            }
        }

        if (currentPlayer().getTrains(routeColor) >= trainCost) {
            currentPlayer().routes.add(route);
            currentPlayer().decrimentTrains(routeTrains);

            if (routeColor.equals("Red")) {
                while (!found) {
                    if (trainCards.possibleCards.get(k).getColor()
                    .equals("redCard")) {
                        card = trainCards.possibleCards.get(k);
                        found = true;
                    }
                    k++;
                }
                for (int i = 0; i < trainCost; i++) {
                    currentPlayer().removeFromHand(card);
                    trainCards.discardDeck.add(card);
                }
            }
            if (routeColor.equals("Blue")) {
                while (!found) {
                    if (trainCards.possibleCards.get(k).getColor()
                    .equals("blueCard")) {
                        card = trainCards.possibleCards.get(k);
                        found = true;
                    }
                    k++;
                }
                for (int i = 0; i < trainCost; i++) {
                    currentPlayer().removeFromHand(card);
                    trainCards.discardDeck.add(card);
                }
            }
            if (routeColor.equals("Green")) {
                while (!found) {
                    if (trainCards.possibleCards.get(k).getColor()
                    .equals("greenCard")) {
                        card = trainCards.possibleCards.get(k);
                        found = true;
                    }
                    k++;
                }
                for (int i = 0; i < trainCost; i++) {
                    currentPlayer().removeFromHand(card);
                    trainCards.discardDeck.add(card);
                }
            }
            if (routeColor.equals("Yellow")) {
                while (!found) {
                    if (trainCards.possibleCards.get(k).getColor()
                    .equals("yellowCard")) {
                        card = trainCards.possibleCards.get(k);
                        found = true;
                    }
                    k++;
                }
                for (int i = 0; i < trainCost; i++) {
                    currentPlayer().removeFromHand(card);
                    trainCards.discardDeck.add(card);
                }
            }
            if (routeColor.equals("Orange")) {
                while (!found) {
                    if (trainCards.possibleCards.get(k).getColor()
                    .equals("orangeCard")) {
                        card = trainCards.possibleCards.get(k);
                        found = true;
                    }
                    k++;
                }
                for (int i = 0; i < trainCost; i++) {
                    currentPlayer().removeFromHand(card);
                    trainCards.discardDeck.add(card);
                }
            }
            if (routeColor.equals("Black")) {
                while (!found) {
                    if (trainCards.possibleCards.get(k).getColor()
                    .equals("blackCard")) {
                        card = trainCards.possibleCards.get(k);
                        found = true;
                    }
                    k++;
                }
                for (int i = 0; i < trainCost; i++) {
                    currentPlayer().removeFromHand(card);
                    trainCards.discardDeck.add(card);
                }
            }
            if (routeColor.equals("White")) {
                while (!found) {
                    if (trainCards.possibleCards.get(k).getColor()
                    .equals("whiteCard")) {
                        card = trainCards.possibleCards.get(k);
                        found = true;
                    }
                    k++;
                }
                for (int i = 0; i < trainCost; i++) {
                    currentPlayer().removeFromHand(card);
                    trainCards.discardDeck.add(card);
                }
            }
            if (routeColor.equals("Purple")) {
                while (!found) {
                    if (trainCards.possibleCards.get(k).getColor()
                    .equals("pinkCard")) {
                        card = trainCards.possibleCards.get(k);
                        found = true;
                    }
                    k++;
                }
                for (int i = 0; i < trainCost; i++) {
                    currentPlayer().removeFromHand(card);
                    trainCards.discardDeck.add(card);
                }
            }

            if (numPlayers == 2 && Routes.doubleRoutes.size() > 1) {
                Routes.possibleRoutes.
                remove(Routes.doubleRoutes.get(0));
                Routes.possibleRoutes.
                remove(Routes.doubleRoutes.get(1));
            } else {
                Routes.possibleRoutes.remove(route);
            }

            if (routeTrains == 1) {
                currentPlayer().addToScore(1);
            }
            if (routeTrains == 2) {
                currentPlayer().addToScore(2);
            }
            if (routeTrains == 3) {
                currentPlayer().addToScore(4);
            }
            if (routeTrains == 4) {
                currentPlayer().addToScore(7);
            }
            if (routeTrains == 5) {
                currentPlayer().addToScore(10);
            }
            if (routeTrains == 6) {
                currentPlayer().addToScore(15);
            }
            if (routeTrains == 9) {
                currentPlayer().addToScore(27);
            }

            if (currentPlayer().equals(playerOne)) {
                score1 = currentPlayer().getScore();
            }
            if (currentPlayer().equals(playerTwo)) {
                score2 = currentPlayer().getScore();
            }
            if (currentPlayer().equals(playerThree)) {
                score3 = currentPlayer().getScore();
            }
            JOptionPane.showMessageDialog(null, "Next player please!");
            nextPlayer();
            repaint();
        } else {
            JOptionPane.
            showMessageDialog(null, "You cannot afford that route");
        }

    }

    public void mouseEntered(MouseEvent e) {
        if (e.getX() >= 0 && e.getY() >= 0
        && currentPlayer().isFirstTurn() == true) {
            firstRound();
        }
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Called by the browser or applet viewer to 
     * inform this JApplet that it
     * should start its execution. It is called 
     * after the init method and each
     * time the JApplet is revisited in a Web page.
     */
    public void start() {
        // provide any code requred to run each time
        // web page is visited
    }

    /**
     * Called by the browser or applet viewer to 
     * inform this JApplet that it
     * should stop its execution. It is called 
     * when the Web page that contains
     * this JApplet has been replaced by another 
     * page, and also just before the
     * JApplet is to be destroyed.
     */
    public void stop() {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed
    }

    /**
     * Called by the browser or applet viewer to 
     * inform this JApplet that it is
     * being reclaimed and that it should destroy 
     * any resources that it has
     * allocated. The stop method will always be 
     * called before destroy.
     */
    public void destroy() {
        // provide code to be run when JApplet is about to be destroyed.
    }

    // public void myMethod(int numberOfColors, int lengthOfPath){
    // if(currentPlayer().numberOfColors >= lengthOfPath){
    // //Routes.remove
    // }
    //
    // }

    /**
     * Returns information about this applet. 
     * An applet should override this
     * method to return a String containing 
     * information about the author,
     * version, and copyright of the JApplet.
     * 
     * @return a String representation of information 
     * about this JApplet
     */
    public String getAppletInfo() {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple " + 
        "applet example description. ";
    }

    /**
     * Returns parameter information about this JApplet. 
     * Returns information
     * about the parameters than are understood by this 
     * JApplet. An applet
     * should override this method to return an array of 
     * Strings describing
     * these parameters. Each element of the array should 
     * be a set of three
     * Strings containing the name, the type, and a description.
     * 
     * @return a String[] representation of parameter 
     * information about this
     *         JApplet
     */
    public String[][] getParameterInfo() {
        // provide parameter information about the applet
        String paramInfo[][] = {
                { "firstParameter", "1-10", "description of first parameter" },
                { "status", "boolean", "description of second parameter" },
                { "images", "url", "description of third parameter" } };
        return paramInfo;
    }
}
