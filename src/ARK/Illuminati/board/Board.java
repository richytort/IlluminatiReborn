package ARK.Illuminati.board;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.board.player.UncontrolledArea;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.board.player.Deck;
import ARK.Illuminati.board.player.Field;
import ARK.Illuminati.cards.Location;
import ARK.Illuminati.exceptions.UnexpectedFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UnknownFormatConversionException;

public class Board {
    private Player activePlayer;
    private Player opponentPlayer;
    private Player winner;
    private Field field;
    private int dice1;

    public static ArrayList<Card> cardArea1 ;
    public static ArrayList<Card> cardArea2 ;

    public static ArrayList<Card> uncontrolled;

    /**
     * getter for uncontrolled cards
     * @return the uncontrolled cards
     */
    public static ArrayList<Card> getUncontrolled() {
        return uncontrolled;
    }

    /**
     * sets the uncontrolled data structure of cards
     * @param uncontrolled arraylist of cards
     */
    public static void setUncontrolled(ArrayList<Card> uncontrolled) {
        Board.uncontrolled = uncontrolled;
    }

    /**
     * Deck for the deck of cards
     */
    public static Deck deck;

    private int dice2;  //int for dice
    private int total;  //total of the dice rolls

    /**
     * Board throws exceptions
     * @throws IOException - error exception
     * @throws UnexpectedFormatException- format exception thrown
     */
    public Board() throws IOException, UnexpectedFormatException {
        Card.setBoard(this);
    }

    /**
     * Sets who starts the game
     * @param p1 - player one
     * @param p2 - second player
     */
    public void whoStarts(Player p1, Player p2) {
        cardArea1 = new ArrayList<Card>() ;
        cardArea2 = new ArrayList<Card>() ;
        int FirstP = rollDice();
        int SecondP = rollDice();
        if (FirstP > SecondP) {
            System.out.println("Player 1 goes first");
            activePlayer = p1;
            opponentPlayer = p2;
        } else {
            System.out.println("Player 2 goes first");
            activePlayer = p2;
            opponentPlayer = p1;
        }
    }

    /**
     * Starst the game with the players
     * @param p1 first player in the game
     * @param p2 second player in the game
     * @throws IOException error exception thrown
     * @throws UnexpectedFormatException format exception thrown
     */
    public void startGame(Player p1, Player p2) throws IOException, UnexpectedFormatException {
        deck = new Deck();
        uncontrolled = new ArrayList<Card>();
        //illuminati
        p1.getField().addNCardsToHand();
        p2.getField().addNCardsToHand();
        for (int e = deck.size() - 1; e >= 0; e--) {
            if (Board.deck.getDeck().get(e).getType().equalsIgnoreCase("Illuminati")) {
                Board.deck.getDeck().remove(e);
            }
        }
        Board.deck.shuffle();
        //uncontrolled
        for (int i = 0; i < 4; i++) {
            p1.getField().addUncontrolled();
        }
        whoStarts(p1, p2);
        //during game
        //for(int e=0; e<10;e++) {
            activePlayer.getField().addOthers();
        //}
    }


    /**
     * Next player in the game is set
      **/
    public void nextPlayer(){
        Player temp = activePlayer;
        activePlayer = opponentPlayer;
        opponentPlayer = temp;
        activePlayer.getIncome();
        activePlayer.getField().addOthers();
   }

    /**
     * returns true/false for game over
     * @return boolean value
     */
    public boolean isGameOver(){
        if(winner != null){
            return true;
        }return false;
    }

    /**
     * rolls the dice of the game
     * @return total dice value
     */
    public int rollDice() {
        dice1 = (int) (Math.random() * 6);
        dice2 = (int) (Math.random() * 6);
        total = dice1 * dice2;
        if(total == 0){
            rollDice();
        }
        return total;
    }


    /**
     * @return the active player in the game
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * sets active player in the game
     * @param activePlayer this is the active player
     */
    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    /**
     * Gets the opponent player
     * @return the opponent player
     */
    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    /**
     * Sets the opponent player
     * @param opponentPlayer the other player against the player
     */
    public void setOpponentPlayer(Player opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    /**
     * getter for the winner
     * @return the winner of the game
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * gets the deck
     * @return deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * deck seter
     * @param deck to be set
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * setter for the winner of game
     * @param winner - the winner of the game
     */
    public void setWinner(Player winner) {
        if (isGameOver())
            return;
        this.winner = winner;
    }

}
