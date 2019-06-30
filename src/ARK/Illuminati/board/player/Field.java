package ARK.Illuminati.board.player;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import ARK.Illuminati.cards.*;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.exceptions.UnexpectedFormatException;
import ARK.Illuminati.exceptions.WrongActionException;

public class Field {
    private Phase phase = Phase.MAIN1;
    private final Deck deck;
    private ArrayList<GroupCard> groupsArea;
    private ArrayList<SpecialCard> specialArea;
    private  ArrayList<Card> uncontrolledArea;

    public Field()throws IOException, UnexpectedFormatException {
        groupsArea = new ArrayList<GroupCard>();
        specialArea = new ArrayList<SpecialCard>();
        uncontrolledArea = new ArrayList<Card> ();
        deck = new Deck();
    }

    public void addCardToUncontrolledArea(){

    }

    public void addNToUncontrolledArea(){
        for(int i = 0 ; i < n ; i++)
            addCardToUncontrolledArea();
    }
    //MAYBE FIND A PLACE TO PLACE GROUPS WON FROM UNCONTROLLED AREA

    /*
    public boolean addGroupToStructure(Card group, Mode m, boolean isHidden) {

        if (!(uncontrolledArea.contains(group) && group.getLocation() == Location.UNCONTROLLED))
            return false;

        //if (hand.size() >= 5)
        //    throw new NoSpaceException();



        hand.remove(group);
        group.setHidden(isHidden);
        group.setLocation(Location.FIELD);
        hand.add(group);
        return true;

    }


    public void removeCard(Card e){
        hand.remove(e);
        uncontrolledGroups.add(e);
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
