package ARK.Illuminati.board.player;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.IlluminatiCard;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.Location;
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
    public void addCardToHand(){
        if(deck.getDeck().size()==0){
            if (this == Card.getBoard().getActivePlayer().getField())
                Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
            else
                Card.getBoard().setWinner(Card.getBoard().getActivePlayer());

            return;
        }
        Card temp = deck.drawCards();
        if (temp.getType().equalsIgnoreCase("special card")) {
            hand.add(temp);
            temp.setLocation(Location.HAND);
        }else{
            uncontrolledGroups.add(temp);
            temp.setLocation(Location.UNCONTROLLED);
        }
    }

    public void addIlluminatiCard(){
        Card temp = deck.drawIlluminatiCard();
        hand.add(temp);
        temp.setLocation(Location.HAND);
    }

    public void add4CardsToUncontrolled(){
        for(int i = 0; i <4;i++){
            Card temp = deck.drawOneCardB();
            uncontrolledGroups.add(temp);
            temp.setLocation(Location.UNCONTROLLED);
        }
    }
/*
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


    public void removeCard(GroupCard group) {

        if (illuminatiArea.contains(group)) {

            illuminatiArea.remove(group);
            uncontrolledGroups.add(group);
            uncontrolledGroups.setLocation(Location.uncontrolledGroups);
        }

    }

    public boolean useSpecialCard(SpecialCard card, GroupCard group) {

        if (!specialArea.contains(card))
            return false;

        if (Action == Action.BATTLE)
            throw new WrongActionException();
        card.action(group);
        removeCard(card);

        return true;

    }





*/





}
