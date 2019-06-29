package ARK.Illuminati.board;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.Card;

public class Board {
    private Player activePlayer;
    private Player opponentPlayer;
    private Player winner;
    private int dice1;
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
    public void startGame(Player p1 , Player p2 ){
        //We will need to figure out how to place cards on uncontrolled area instead
        //of adding cards to each player hand.
        //4 cards will be added to uncontrolled area.
        //p1.addNCardsToHand(5);
        //p2.addNCardsToHand(5);

        whoStarts(p1, p2);

        //activePlayer.addCardToHand();

    }

    public void nextPlayer(){
        Player temp = activePlayer;
        activePlayer = opponentPlayer;
        opponentPlayer = temp;
       //activePlayer.addCardToHand();
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

    public void setWinner(Player winner) {
        if (isGameOver())
            return;
        this.winner = winner;
    }


}
