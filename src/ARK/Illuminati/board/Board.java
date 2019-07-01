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
    private int dice1;
    public static ArrayList<Card> uncontrolled;

    public static ArrayList<Card> getUncontrolled() {
        return uncontrolled;
    }

    public static void setUncontrolled(ArrayList<Card> uncontrolled) {
        Board.uncontrolled = uncontrolled;
    }

    public static Deck deck;



    private int dice2;
    private int total;

    public Board() throws IOException, UnexpectedFormatException{
        Card.setBoard(this);
    }
    public void whoStarts(Player p1, Player p2){
        int FirstP =rollDice();
        int SecondP = rollDice();
        if(FirstP > SecondP){
            activePlayer = p1;
            opponentPlayer= p2;
        }else{
            activePlayer = p2;
            opponentPlayer = p1;
        }
    }
    public void startGame(Player p1 , Player p2 ) throws IOException, UnexpectedFormatException{
        deck = new Deck();
        uncontrolled = new ArrayList<Card>();
        Card illu = deck.drawIlluminatiCard();
        p1.getHand().add(illu);
        deck.getDeck().remove(illu);
        Card illu2 = deck.drawIlluminatiCard();
        p2.getHand().add(illu2);
        deck.getDeck().remove(illu2);
        for(int e = deck.size() - 1; e >= 0; e--) {
            if (deck.getDeck().get(e).getType().equalsIgnoreCase("Illuminati")) {
                deck.getDeck().remove(e);
            }
        }
        deck.shuffle();
        for(int i = 0 ; i < 4 ; i++) {
            uncontrolled.add(deck.drawOneCardB());
            deck.getDeck().remove(i);
        }

        whoStarts(p1, p2);
        Card k = deck.drawOneCard();
        if(k.getType().equalsIgnoreCase("special card")){
            activePlayer.getHand().add(k);
        }
        else{
            uncontrolled.add(k);
        }deck.getDeck().remove(k);
    }


    public void nextPlayer(){
        Player temp = activePlayer;
        activePlayer = opponentPlayer;
        opponentPlayer = temp;
        activePlayer.getIncome();
        Card k = deck.drawOneCard();
        if(k.getType().equalsIgnoreCase("special card")){
            activePlayer.getHand().add(k);

        }
        else{
            uncontrolled.add(k);
        }deck.getDeck().remove(k);
    }

    public boolean isGameOver(){
        if(winner != null){
            return true;
        }return false;
    }

    public int rollDice() {
        dice1 = (int) (Math.random() * 6);
        dice2 = (int) (Math.random() * 6);
        total = dice1 * dice2;
        if(total == 0){
            rollDice();
        }
        return total;
    }
    public Player getActivePlayer() {
        return activePlayer;
    }


    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public void setOpponentPlayer(Player opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }


    public void setWinner(Player winner) {
        if (isGameOver())
            return;
        this.winner = winner;
    }

}
