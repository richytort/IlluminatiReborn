package ARK.Illuminati.board.player;

import java.io.IOException;
import java.util.ArrayList;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.board.player.Field;
import ARK.Illuminati.exceptions.UnexpectedFormatException;

public class UncontrolledArea {
    private ArrayList<Card> uncontrolled;
    private Field field;

    public UncontrolledArea()throws IOException, UnexpectedFormatException {
        uncontrolled = new ArrayList<>();
        this.field = new Field();
    }

    public void add4CardsToUncontrolled(){

        this.field.add4CardsToUncontrolled();
    }
    //check it!!!
    public void addCartToHand(){
        this.field.addCardToHand();
    }
}
