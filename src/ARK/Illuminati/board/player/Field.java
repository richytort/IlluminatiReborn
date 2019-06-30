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



    public boolean declareAttack(GroupCard one, GroupCard two) {

        if (Action != Action.BATTLE)
            throw new WrongActioneException();

        if (one.getMode() != Mode.ATTACK)
            throw new DefenseGroupAttackException();

        ArrayList<MonsterCard> oppMonstersArea = Card.getBoard().getOpponentPlayer().getField().monstersArea;

        if (m2 == null && oppMonstersArea.size() == 0)
            m1.action();
        else if (m2 != null && oppMonstersArea.contains(m2))
            m1.action(m2);
        else
            return false;

        if (Card.getBoard().getActivePlayer().getLifePoints() <= 0) {
            Card.getBoard().getActivePlayer().setLifePoints(0);
            Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
        }
        if (Card.getBoard().getOpponentPlayer().getLifePoints() <= 0) {
            Card.getBoard().getOpponentPlayer().setLifePoints(0);
            Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
        }

        return true;

    }
//
//    public void endPhase() {
//
//        switch (phase) {
//
//            case MAIN1:
//                setPhase(Phase.BATTLE);
//                break;
//
//            case BATTLE:
//                setPhase(Phase.MAIN2);
//                break;
//
//            case MAIN2:
//                endTurn();
//                break;
//
//        }
//
//    }
//
//    public void endTurn() {
//
//        phase = Phase.MAIN1;
//
//        for (MonsterCard m : monstersArea) {
//            m.setAttacked(false);
//            m.setSwitchedMode(false);
//        }
//
//        Card.getBoard().nextPlayer();
//
//    }
//










}
