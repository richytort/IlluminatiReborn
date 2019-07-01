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

/**
 * Field class for the "board" of the game
 */
public class Field {
  //  private final Deck deck;
    private ArrayList<Card> hand;           //creating ArrayLists to be used- hand, cardarea, cards removed from game
    private ArrayList<Card> cardArea;
    private ArrayList<SpecialCard> specialArea;
    private Phase phase = Phase.ACTION1;
    private ArrayList<Card> uncontrolledGroups;     //uncontrolled groups
    private ArrayList<Card> graveYard;
    private Board board;
    Player p1;  //first player
    Player p2;   //second player
//

    /**
     * Initializing new arraylists for the game.
     * @throws IOException
     * @throws UnexpectedFormatException
     */
    public Field() throws IOException, UnexpectedFormatException {
        hand = new ArrayList<Card>();
        cardArea = new ArrayList<>();
        specialArea = new ArrayList<SpecialCard>();
        uncontrolledGroups = new ArrayList<Card>();
        board = new Board();
    //    deck = new Deck();
    }

    /**
     * This Method adds a Illuminati group to the board/field of the game.
     * @param group - group to be added from the database
     * @param m - the position the card is in
     * @param isHidden - the status of the group added to the field
     * @return
     */
    public boolean addGroupToField(Card group, Mode m, boolean isHidden) {
        if (!(hand.contains(group) && group.getLocation() == Location.HAND))
            return false;
        if(group.getType().equalsIgnoreCase("special card"))
            return false;

        hand.remove(group);
        group.setHidden(isHidden);
        group.setMode(m);
        group.setLocation(Location.FIELD);
        cardArea.add(group);
        return true;
    }

    /**
     * Removes group to the "graveyard" when it is targeted
     * @param target - the group card to be removed from the session
     */
    public void removeGroupToGraveyard(GroupCard target) {
        if (cardArea.contains(target)) {            //if the target is in field
            cardArea.remove(target);     //remove
            graveYard.add(target);  //target group is added to pile
            target.setLocation(Location.GRAVEYARD);
        }
    }

    /**
     * group selected is moved to the uncontrolled group- no longer in player possession
     * @param group -the group to moved to the uncontrolled area
     */
    public void removeGroupToUncontrolled(GroupCard group) {
        if (cardArea.contains(group)) {  //if group exists
            cardArea.remove(group);  //remove the group
            uncontrolledGroups.add(group);
            group.setLocation(Location.UNCONTROLLED);
        }
    }

    /**
     * The group is removed from the hand of the player
     * @param target - the group to be removed from the players hand
     */
    public void removeGroupToHand(GroupCard target) {
        if (p1.getHand().contains(target)) {
            p1.getHand().remove(target);
            p2.getHand().add(target);
        }
    }

    /**
     * Adds a special card to the field - to be used
     * @param special - the special card to be used ingame
     * @param group - the group the special is used upon
     * @param hidden - status of the card played
     * @return
     */
    public boolean addSpecialToField(SpecialCard special, GroupCard group, boolean hidden) {
        if (!hand.contains(special))   //if the special card doesnt exists in the hand
            return false;
//       if(cardArea.size() >=5)   //if there is no room in the card area
//            throw new WrongActionException();
//        if(phase == Phase.BATTLE)
//            throw W
        hand.remove(special);   //remove the special card
        specialArea.add(special);    //add the special card to the area
        special.setLocation(Location.FIELD);    //set the location of the special card to the field
        if (!hidden)
            return activeSpecial(special, group);
        return true;
    }

    /**
     * Determines whether the special card is currently in action
     * @param special - the special card assessed to be in play or not
     * @param group - the group the special card is acting upon
     * @return - boolean true or false regarding the status of the special card
     */
    public boolean activeSpecial(SpecialCard special, GroupCard group) {
        if (!specialArea.contains(special))
            return false;
        //if(phase)
        special.action(group);
        removeSpecialToGraveyard(special);
        return true;
    }

    /**
     * Tests the validity of whether the special card was removed from the field
     * @param special - the special card that was to be removed from game
     * @return - true or false regarding the removal of the special card
     */
    public boolean removeSpecialToGraveyard(SpecialCard special) {
        if (!specialArea.contains(special))
            return false; //return false
        specialArea.remove(special);            //removing the special card
        graveYard.add(special);             //special card is removed successfully
        special.setLocation(Location.GRAVEYARD);
        return true; //return true
    }

    /**
     * Declaring attack to control a group
     * @param g1 - the illuminaticard played to attack to control
     * @param g2 - the group to by controlled as a result of the attack
     * @return true or false regarding the control after attacking
     */
    public boolean declareAttackToControlI(IlluminatiCard g1, GroupCard g2) {   //parameters Illuminati Card and group
        if(g1.getMode() != Mode.ATTACK)      //if the Illuminati card is not played to attack
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
        if (temp.getType().equalsIgnoreCase("Special Card")) {
            hand.add(temp);
            temp.setLocation(Location.HAND);
        }else{
            uncontrolledGroups.add(temp);
            temp.setLocation(Location.UNCONTROLLED);
        }
    }

    public void addNCardsToHand(int n) {

        for (int i = 0; i < n; i++)
            hand.add( Board.deck.drawOneCard()) ;

    }


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