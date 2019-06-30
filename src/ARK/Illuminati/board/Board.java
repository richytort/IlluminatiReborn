package ARK.Illuminati.board;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.board.player.UncontrolledArea;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.board.player.Deck;
import ARK.Illuminati.board.player.Field;
import ARK.Illuminati.exceptions.UnexpectedFormatException;

import java.io.IOException;
import java.util.UnknownFormatConversionException;

public class Board {
    private Player activePlayer;
    private Player opponentPlayer;
    private Player winner;
    private int dice1;
    private UncontrolledArea uncontrolled;
    Deck deck;

    private int dice2;
    private int total;

    public Board(){

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
        uncontrolled = new UncontrolledArea();
        //p1.addIlluminatiCard();
        //p2.addIlluminatiCard();
        //deck.shuffle();
        //uncontrolled.add4CardsToUncontrolled();
        p1.addIlluminatiCard();
        p2.addIlluminatiCard();
        p1.addNCardsToHand(1);
        p2.addNCardsToHand(1);
        uncontrolled.add4CardsToUncontrolled();
        whoStarts(p1, p2);
        activePlayer.addCardToHand();
    }

    public void nextPlayer(){
        Player temp = activePlayer;
        activePlayer = opponentPlayer;
        opponentPlayer = temp;
        activePlayer.getIncome();
        activePlayer.addCardToHand();
    }
    public static void main(String [] args)throws IOException, UnexpectedFormatException {
        Board board = new Board();
        Deck deck=new Deck();
        Player p1 = new Player("Kathya");
        Player p2 = new Player("ulu");
        board.whoStarts(p1,p2);
        board.startGame(p1,p2);

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
    public UncontrolledArea uncontrolledRIGHT(){
        return uncontrolled;
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

    public void setWinner(Player winner) {
        if (isGameOver())
            return;
        this.winner = winner;
    }

}
