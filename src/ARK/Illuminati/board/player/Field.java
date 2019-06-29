package ARK.Illuminati.board.player;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.IlluminatiCard;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.exceptions.UnexpectedFormatException;

public class Field {
    private final Deck deck;
    private ArrayList<Card> hand;
    private ArrayList<IlluminatiCard> illuminatiArea;
    private ArrayList<SpecialCard> specialArea;
    private ArrayList<GroupCard> GroupArea;
    private  ArrayList<Card> uncontrolledGroups;

    public Field()throws IOException, UnexpectedFormatException {
        hand = new ArrayList<Card>();
        uncontrolledGroups = new ArrayList<Card>();
        deck = new Deck();

    }
}
