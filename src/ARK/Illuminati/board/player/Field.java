package ARK.Illuminati.board.player;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import ARK.Illuminati.board.Board;
import ARK.Illuminati.cards.*;
import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.exceptions.DefenseGroupAttackException;
import ARK.Illuminati.exceptions.NoGroupSpaceException;
import ARK.Illuminati.exceptions.UnexpectedFormatException;
import ARK.Illuminati.exceptions.WrongActionException;
import ARK.Illuminati.gui.GroupButton;

public class Field {
  //  private final Deck deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> cardArea;
    private ArrayList<SpecialCard> specialArea;
    private Phase phase = Phase.ACTION1;
    private ArrayList<Card> uncontrolledGroups;
    private ArrayList<Card> graveYard;
    private Board board;
    Player p1;
    Player p2;
//

    public Field() throws IOException, UnexpectedFormatException {
        hand = new ArrayList<Card>();
        cardArea = new ArrayList<>();
        specialArea = new ArrayList<SpecialCard>();
        uncontrolledGroups = new ArrayList<Card>();
        board = new Board();
    //    deck = new Deck();
    }

    //do we need one for Illuminati
    public boolean addGroupToField(Card group, Mode m, boolean isHidden) {
        if (!(hand.contains(group) && group.getLocation() == Location.HAND))
            return false;
        if(group.getType().equalsIgnoreCase("special card"))
            return false;

//        if(cardArea.size() >=5)
//            throw new NoGroupSpaceException();
//        if(phase == phase.BATTLE)
//            throw new Wron
        hand.remove(group);
        group.setHidden(isHidden);
        group.setMode(m);
        group.setLocation(Location.FIELD);
        cardArea.add(group);
        return true;
    }

    public void removeGroupToGraveyard(GroupCard target) {
        if (cardArea.contains(target)) {
            cardArea.remove(target);
            graveYard.add(target);
            target.setLocation(Location.GRAVEYARD);
        }
    }


    public void removeGroupToUncontrolled(GroupCard group) {
        if (cardArea.contains(group)) {
            cardArea.remove(group);
            uncontrolledGroups.add(group);
            group.setLocation(Location.UNCONTROLLED);
        }
    }


    public void removeGroupToHand(GroupCard target) {
        if (p1.getHand().contains(target)) {
            p1.getHand().remove(target);
            p2.getHand().add(target);
        }
    }


    //what do we really need them for
    public boolean addSpecialToField(SpecialCard special, GroupCard group, boolean hidden) {
        if (!hand.contains(special))
            return false;
//       if(cardArea.size() >=5)
//            throw new WrongActionException();
//        if(phase == Phase.BATTLE)
//            throw W
        hand.remove(special);
        specialArea.add(special);
        special.setLocation(Location.FIELD);
        if (!hidden)
            return activeSpecial(special, group);
        return true;
    }

    public boolean activeSpecial(SpecialCard special, GroupCard group) {
        if (!specialArea.contains(special))
            return false;
        //if(phase)
        special.action(group);
        removeSpecialToGraveyard(special);
        return true;
    }

    public boolean removeSpecialToGraveyard(SpecialCard special) {
        if (!specialArea.contains(special))
            return false;
        specialArea.remove(special);
        graveYard.add(special);
        special.setLocation(Location.GRAVEYARD);
        return true;
    }

    public boolean declareAttackToControlI(IlluminatiCard g1, GroupCard g2) {
        if(g1.getMode() != Mode.ATTACK)
            throw new DefenseGroupAttackException();
//        if(g1.isAttacked())//what is it for??
//            throw new MultopleGroupAttackException();
        ArrayList<Card> oppGroupArea = Card.getBoard().getOpponentPlayer().getField().cardArea;
        if (g2 != null && oppGroupArea.contains(g2))
            g1.attackToControl(g2);
        else
            return false;
        if (Card.getBoard().getActivePlayer().getResult() == 10) {
            Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
        }
        if (Card.getBoard().getActivePlayer().getResult() == 0) {
            Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
        }
        return true;

    }

    public boolean declareAttackToControlG(GroupCard g1, GroupCard g2) {
        if(g1.getMode() != Mode.ATTACK)
            throw new DefenseGroupAttackException();
//        if(g1.isAttacked())//what is it for??
//            throw new MultopleGroupAttackException();
        ArrayList<Card> oppGroupArea = Card.getBoard().getOpponentPlayer().getField().cardArea;
        if (g2 != null && oppGroupArea.contains(g2))
            g1.attackToControl(g2);
        else
            return false;
        if (Card.getBoard().getActivePlayer().getResult() == 10) {
            Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
        }
        if (Card.getBoard().getActivePlayer().getResult() == 0) {
            Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
        }
        return true;


    }

    public boolean declareAttackToNeutralizeI(IlluminatiCard g1, GroupCard g2) {
        if(g1.getMode() != Mode.ATTACK)
            throw new DefenseGroupAttackException();
//        if(g1.isAttacked())//what is it for??
//            throw new MultopleGroupAttackException();
        ArrayList<Card> oppGroupArea = Card.getBoard().getOpponentPlayer().getField().cardArea;
        if (g2 != null && oppGroupArea.contains(g2))
            g1.attackToControl(g2);
        else
            return false;
        if (Card.getBoard().getActivePlayer().getResult() == 10) {
            Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
        }
        if (Card.getBoard().getActivePlayer().getResult() == 0) {
            Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
        }
        return true;


    }

    public boolean declareAttackToNeutralizeG(GroupCard g1, GroupCard g2) {
        if(g1.getMode() != Mode.ATTACK)
            throw new DefenseGroupAttackException();
//        if(g1.isAttacked())//what is it for??
//            throw new MultopleGroupAttackException();
        ArrayList<Card> oppGroupArea = Card.getBoard().getOpponentPlayer().getField().cardArea;
        if (g2 != null && oppGroupArea.contains(g2))
            g1.attackToControl(g2);
        else
            return false;
        if (Card.getBoard().getActivePlayer().getResult() == 10) {
            Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
        }
        if (Card.getBoard().getActivePlayer().getResult() == 0) {
            Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
        }
        return true;


    }

    public boolean declareAttackToDestroyI(IlluminatiCard g1, GroupCard g2) {
        if(g1.getMode() != Mode.ATTACK)
            throw new DefenseGroupAttackException();
//        if(g1.isAttacked())//what is it for??
//            throw new MultopleGroupAttackException();
        ArrayList<Card> oppGroupArea = Card.getBoard().getOpponentPlayer().getField().cardArea;
        if (g2 != null && oppGroupArea.contains(g2))
            g1.attackToControl(g2);
        else
            return false;
        if (Card.getBoard().getActivePlayer().getResult() == 10) {
            Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
        }
        if (Card.getBoard().getActivePlayer().getResult() == 0) {
            Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
        }
        return true;


    }

    public boolean declareAttackToDestroyG(GroupCard g1, GroupCard g2) {
        if(g1.getMode() != Mode.ATTACK)
            throw new DefenseGroupAttackException();
//        if(g1.isAttacked())//what is it for??
//            throw new MultopleGroupAttackException();
        ArrayList<Card> oppGroupArea = Card.getBoard().getOpponentPlayer().getField().cardArea;
        if (g2 != null && oppGroupArea.contains(g2))
            g1.attackToControl(g2);
        else
            return false;
        if (Card.getBoard().getActivePlayer().getResult() == 10) {
            Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
        }
        if (Card.getBoard().getActivePlayer().getResult() == 0) {
            Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
        }
        return true;


    }


    public void addCardToHand() {
        /*
        if (deck.getDeck().size() == 0) {
            if (this == Card.getBoard().getActivePlayer().getField())
                Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
            else
                Card.getBoard().setWinner(Card.getBoard().getActivePlayer());

            return;
        }

         */
        Card temp = board.getDeck().drawOneCard();
        hand.add(temp);
        temp.setLocation(Location.HAND);


    }

    public void addNCardsToHand(int n) {

        for (int i = 0; i < n; i++)
            hand.add( Board.deck.drawOneCard()) ;

    }

    //    public void printUncontroled(){
//        for(Card e: uncontrolledGroups){
//            System.out.println(e+ " ");
//        }
//    }
    public void addIlluminatiCard() {
        Card temp = board.getDeck().drawIlluminatiCard();
        hand.add(temp);
        temp.setLocation(Location.HAND);
    }

    public void add4CardsToUncontrolled() {
        for (int i = 0; i < 4; i++) {
            Card temp = board.getDeck().drawOneCardB();
            uncontrolledGroups.add(temp);
            temp.setLocation(Location.UNCONTROLLED);
        }
    }


   public void printHand(){
       for(Card e: hand){
           System.out.println(e+ " ");
        }
    }

//    public static void main(String [] args) throws IOException,UnexpectedFormatException{
//        Field field = new Field();
//        Deck deck = new Deck();
//        field.addIlluminatiCard();
//        field.addCardToHand();
//        field.add4CardsToUncontrolled();
//        System.out.println("hand");
//        field.printHand();
//        System.out.println("uncontrolled");
//        field.printUncontroled();
//
//
    // }

    //   public boolean useSpecialCard(SpecialCard card, GroupCard group) {
//        if (!specialArea.contains(card))
//            return false;
////        if (Action == Action.BATTLE)
////            throw new WrongActionException();
//        card.action(group);
//        removeCard(card);
//        return true;
//    }
//

    public void endPhase(){
        switch (phase){
            case MAIN:
                setPhase(Phase.ACTION1);
                break;
            case ACTION1:
                setPhase(Phase.ACTION2);
                break;
            case ACTION2:
                endTurn();
        }
    }

    public void endTurn(){
        phase = Phase.MAIN;
        for(Card m: cardArea){
            m.setAttacked(false);
            m.setSwitchedMode(false);
        }
        Card.getBoard().nextPlayer();
    }

    public boolean switchCardModeE(SpecialCard group){
        if(!cardArea.contains(group))
            return false;
        //(if phase
        if(group.isSwitchedMode())
            return false;
        group.switchMode();
        group.setSwitchedMode(true);
        return true;

    }

    //DO WE REALLY NEED CARD DOES NOT TURN FACE DOWN
    public boolean switchCardModeG(GroupCard group){
        if(!cardArea.contains(group))
            return false;
        //(if phase
        if(group.isSwitchedMode())
            return false;
        group.switchMode();
        group.setSwitchedMode(true);
        return true;

    }
    public boolean switchCardModeI(IlluminatiCard group){
        if(!cardArea.contains(group))
            return false;
        //(if phase
        if(group.isSwitchedMode())
            return false;
        group.switchMode();
        group.setSwitchedMode(true);
        return true;

    }
    public IlluminatiCard getIlluminati(IlluminatiCard illu){
        return illu;
    }


    public ArrayList<Card> getHand() { return hand; }


    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Card getCardL(Card e){
        return e;
    }

    public GroupCard getGroup(GroupCard group){
        return group;
    }
    public ArrayList<Card> getCardArea(){return cardArea;}

    public ArrayList<SpecialCard> getSpecialArea(){ return specialArea;}

    public ArrayList<Card> getUncontrolledGroups(){ return uncontrolledGroups;}

    public ArrayList<Card> getGraveYard(){ return graveYard;}




}