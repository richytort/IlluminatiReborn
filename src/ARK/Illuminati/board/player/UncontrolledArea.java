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

    public UncontrolledArea()throws IOException, UnexpectedFormatException {
        uncontrolled = new ArrayList<>();
        this.field = new Field();
    }
    public Card getCard(Card i) {

        return i;
    }
//    public void add4CardsToUncontrolled(){
//
//        this.field.add4CardsToUncontrolled();
//    }
    public  ArrayList<GroupCard> getUncontrolled() {
        return uncontrolled;
    }

    public  void setUncontrolled(ArrayList<GroupCard> uncontrolled) {
        this.uncontrolled= uncontrolled;
    }


}
