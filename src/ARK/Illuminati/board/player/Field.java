package ARK.Illuminati.board.player;

import java.security.PublicKey;
import java.util.ArrayList;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.IlluminatiCard;
import ARK.Illuminati.cards.SpecialCard;
import ARK.Illuminati.cards.GroupCard;

public class Field {
    private final Deck deck;
    private ArrayList<Card> hand;
    private ArrayList<IlluminatiCard> illuminatiArea;
    private ArrayList<SpecialCard> specialArea;
    private ArrayList<GroupCard> GroupArea;
    private  ArrayList<Card> uncontrolledGroups;

    public Field(){
        hand = new ArrayList<Card>();
        uncontrolledGroups = new ArrayList<Card>();
        deck = new Deck();

    }
}
