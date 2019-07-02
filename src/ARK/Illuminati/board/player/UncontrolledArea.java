package ARK.Illuminati.board.player;

import java.io.IOException;
import java.util.ArrayList;

import ARK.Illuminati.board.Board;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.exceptions.UnexpectedFormatException;

public class UncontrolledArea {
    private ArrayList<GroupCard> uncontrolled;
    private Field field;

    /**
     * Uncontrolled area of the game
     * @throws IOException  exception thrown for IO errors
     * @throws UnexpectedFormatException format exceptions
     */
    public UncontrolledArea()throws IOException, UnexpectedFormatException {
        uncontrolled = new ArrayList<>();
        this.field = new Field();
    }

    /**
     * gettter for card
     * @param i card received
     * @return the card
     */
    public Card getCard(Card i) {

        return i;
    }
//    public void add4CardsToUncontrolled(){
//
//        this.field.add4CardsToUncontrolled();
//    }

    /**
     * getter for uncontrolled
     * @return uncontrolled
     */
    public  ArrayList<GroupCard> getUncontrolled() {
        return uncontrolled;
    }

    /**
     * setter for uncontrolled
     * @param uncontrolled the uncontrolled cards
     */
    public  void setUncontrolled(ArrayList<GroupCard> uncontrolled) {
        this.uncontrolled= uncontrolled;
    }


}
