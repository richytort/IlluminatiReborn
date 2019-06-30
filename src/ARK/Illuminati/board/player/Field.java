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
        specialArea = new ArrayList<>();
        illuminatiArea = new ArrayList<>();
        deck = new Deck();
    }
    public void removeCard(Card e){
        hand.remove(e);
        uncontrolledGroups.add(e);
    }

    public boolean addGroupToField(Card group, Mode m, boolean isHidden) {

        if (!(hand.contains(group) && group.getLocation() == Location.HAND))
            return false;

        if (hand.size() >= 5)
            throw new NoSpaceException();

        if (Action == Action.Attack)
            throw new WrongActionException();

        hand.remove(group);
        group.setHidden(isHidden);
        group.setLocation(Location.FIELD);
        hand.add(group);
        return true;

    }






}
