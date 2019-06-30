package ARK.Illuminati.board.player;

import java.io.IOException;
import java.util.ArrayList;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.board.player.Field;
import ARK.Illuminati.cards.Location;
import ARK.Illuminati.exceptions.UnexpectedFormatException;

public class UncontrolledArea {
    private ArrayList<Card> uncontrolled;
    private Field field;
    private int location;

    public UncontrolledArea()throws IOException, UnexpectedFormatException {
        uncontrolled = new ArrayList<>();
        this.field = new Field();
    }
    public Card getCard(Card i) {
        return i;
    }
    public void add4CardsToUncontrolled(){

        this.field.add4CardsToUncontrolled();
    }
    //check it!!!
    public void addCartToHand(){
        this.field.addCardToHand();
    }

    public void printUn(){
        for(Card e: uncontrolled){
            System.out.println(e + " ");
        }
    }


}
