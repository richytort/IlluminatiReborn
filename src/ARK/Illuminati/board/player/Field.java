package ARK.Illuminati.board.player;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import ARK.Illuminati.board.Board;
import ARK.Illuminati.cards.*;
import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.exceptions.*;
import ARK.Illuminati.gui.GUI;
import ARK.Illuminati.gui.GroupButton;

import static ARK.Illuminati.board.player.Phase.*;
import static ARK.Illuminati.cards.Location.STRUCTURE;

/**
 * Field class for the field of the game. Setters and Getters for the board of Illuminati.
 */
public class Field {
    private ArrayList<Card> hand;
    public static ArrayList<Card> cardArea;
    private ArrayList<SpecialCard> specialArea;
    private Phase phase = MAIN;
    private ArrayList<Card> uncontrolledGroups;
    private ArrayList<Card> graveYard;
   // private Board board;
   public static ArrayList<Card> cardArea1 ;
    public static ArrayList<Card> cardArea2 ;
    Player p1;
    Player p2;

    /**
     * Initializes arraylsits for the field
     * @throws IOException - IO errors cause this exception to be thrown
     * @throws UnexpectedFormatException - format issues result in this exception thrown
     */
    public Field() throws IOException, UnexpectedFormatException {
        hand = new ArrayList<Card>();
        cardArea = new ArrayList<>();
        cardArea1 = new ArrayList<Card>();
        cardArea2 = new ArrayList<Card>();
        specialArea = new ArrayList<SpecialCard>();
        uncontrolledGroups = new ArrayList<Card>();
       //
        // board = new Board();
    }

    //do we need one for Illuminati

    /**
     * Adds a group to the field (board)
     * @param group - group to be added
     * @param m - mode of the group
     * @param isHidden - boolean true or false for hidden
     * @return boolean value when group added to field
     */
    public boolean addGroupToField(Card group, Mode m, boolean isHidden) {
        if (!(hand.contains(group) && group.getLocation() == Location.HAND))
            return false;
        if(group.getType().equalsIgnoreCase("special card"))
            return false;
        if(cardArea.size() >=15)
            throw new NoGroupSpaceException();
        if(phase == phase.ACTION1 || phase==phase.ACTION2) {
            throw new WrongActionException();
        }
        hand.remove(group);
        group.setHidden(isHidden);
        group.setMode(m);
        group.setLocation(STRUCTURE);
        cardArea.add(group);
        return true;
    }

    /**
     * Adds Illuminati cards to field
     * @param illuminati - illuminati card added to the field
     * @return boolean value for Illuminati card on field
     */
    public boolean addIlluminatiToField(IlluminatiCard illuminati,Mode m  ){
        if(!(hand.contains(illuminati) && illuminati.getLocation() == Location.HAND))
            return false ;

        if( phase == ACTION1 || phase == ACTION2)
            throw new WrongActionException();

        hand.remove(illuminati);
        //illuminati.setHidden(isHidden); //MAYBE FIX LATER WHEN TIME IS GOOD
        illuminati.setLocation(STRUCTURE) ;
        illuminati.setMode(m);
        Player p = Card.getBoard().getActivePlayer();
        if(Card.getBoard().getActivePlayer().getName() == GUI.p1.getName())
            cardArea1.add(illuminati);
        else
            cardArea2.add(illuminati);
        return true;

    }

    /**
     * Removes group to the "terminated" graveyard pile
     * @param target the target groupcard to be removed
     */
    public void removeGroupToGraveyard(GroupCard target) {
        if (cardArea.contains(target)) {
            cardArea.remove(target);
            graveYard.add(target);
            target.setLocation(Location.GRAVEYARD);
        }
    }

    /**]
     * Removes group to uncontrolled
     * @param group to be removed
     */
    public void removeGroupToUncontrolled(GroupCard group) {
        if (cardArea.contains(group)) {
            cardArea.remove(group);
            uncontrolledGroups.add(group);
            group.setLocation(Location.UNCONTROLLED);
        }
    }

    /**
     * Removes groups to hand
     * @param target the group to be removed
     */
    public void removeGroupToHand(GroupCard target) {
        if (p1.getHand().contains(target)) {
            p1.getHand().remove(target);
            p2.getHand().add(target);
        }
    }

    /**
     * Adds special cards to the game board
     * @param special special card to be added
     * @param group group card to be added
     * @param hidden boolean value for hidden (T or F)
     * @return boolean value for special card to field
     */
    //what do we really need them for
    public boolean addSpecialToField(SpecialCard special, GroupCard group, boolean hidden) {
        if (!hand.contains(special))
            return false;
        if(cardArea.size() >=5)
            throw new NoSpecialSpaceException();
        if(phase == phase.ACTION1 || phase==phase.ACTION2) {
            throw new WrongActionException();
        }
        hand.remove(special);
        specialArea.add(special);
        special.setLocation(STRUCTURE);
        if (!hidden)
            return activeSpecial(special, group);
        return true;
    }

    /**
     * Boolean for if special card is activated
     * @param special special card to be active
     * @param group group to recieve effect of special card
     * @return boolean value if special is active
     */
    public boolean activeSpecial(SpecialCard special, GroupCard group) {
        if (!specialArea.contains(special))
            return false;
        if(phase==phase.ACTION1 || phase==phase.ACTION2){
            throw new WrongActionException();
        }
        special.action(group);
        removeSpecialToGraveyard(special);
        return true;
    }

    /**
     * Remove the special cards from the game
     * @param special special card to be removed
     * @return boolean value confirming action
     */
    public boolean removeSpecialToGraveyard(SpecialCard special) {
        if (!specialArea.contains(special))
            return false;
        specialArea.remove(special);
        graveYard.add(special);
        special.setLocation(Location.GRAVEYARD);
        return true;
    }

    /**
     * Declares attack to control with Illuminati type
     * @param g1 Illuminati card
     * @param g2 group card
     * @return boolean value true or false
     */
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
    /**This method declares attacks with intent to control group
     * @param g1 - the group card atttacking
     * @param g2- the second group card that is being attacked
     * @return boolean true or false if the attack to control occurs
     */
    public boolean declareAttackToControlG(GroupCard g1, GroupCard g2) {
        if(g1.getMode() != Mode.ATTACK)      //if group1 is not attacking
            throw new DefenseGroupAttackException();  //defenser exception appears

        ArrayList<Card> oppGroupArea = Card.getBoard().getOpponentPlayer().getField().cardArea;
        if (g2 != null && oppGroupArea.contains(g2)) //if group 2 exists and its in a grouparea
            g1.attackToControl(g2); // //attack to control occurs
        else
            return false; //false
        if (Card.getBoard().getActivePlayer().getResult() == 10) {
            Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
        }
        if (Card.getBoard().getActivePlayer().getResult() == 0) {
            Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
        }
        return true; //true is returned


    }

    /**
     * This method declares on a group with intent to neutralize
     * @param g1 Illuminati card played to attack g2
     * @param g2 the group being attacked by g1
     * @return true or false if the attack to neutralize was successful
     **/
    public boolean declareAttackToNeutralizeI(IlluminatiCard g1, GroupCard g2) {
        if(g1.getMode() != Mode.ATTACK) //IlluminatiCard is not played to attack
            throw new DefenseGroupAttackException();   //defense exception is thrown
//        if(g1.isAttacked())//what is it for??
//            throw new MultopleGroupAttackException();
        ArrayList<Card> oppGroupArea = Card.getBoard().getOpponentPlayer().getField().cardArea; //arraylist for the opponent group area
        if (g2 != null && oppGroupArea.contains(g2))
            g1.attackToControl(g2);   //attack to control occurs
        else
            return false; //false is returned

        if (Card.getBoard().getActivePlayer().getResult() == 10) {
            Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
        }
        if (Card.getBoard().getActivePlayer().getResult() == 0) {
            Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
        }
        return true;   //boolean true is returned


    }

    /**
     * This method declares attacks to neutralize another group
     * @param g1 - group to attack the other group
     * @param g2 - group to be attacked by g1
     * @return boolean for whether the attack is declared
     * */
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

    /**
     * This method declares attacks to destroy another group
     * @param g1 - the IlluminatiCard played
     * @param g2 - the group the attack is declared on
     * @return true or false if the attack is declared
     * */
    public boolean declareAttackToDestroyI(IlluminatiCard g1, GroupCard g2) {
        if(g1.getMode() != Mode.ATTACK)
            throw new DefenseGroupAttackException();
//        if(g1.isAttacked())//what is it for??
//            throw new MultopleGroupAttackException();
        ArrayList<Card> oppGroupArea = Card.getBoard().getOpponentPlayer().getField().cardArea;
        if (g2 != null && oppGroupArea.contains(g2))    //g2 isnt empty and the opponent group contains g2
            g1.attackToControl(g2);   //g1 attacks g2 to control
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

    /**
     * This method declares attacks with the intent to destroy another group
     * @param g1 - The first group that attacks g2
     * @param g2 - the group being attacked by the first group
     * @return boolean true or false if the declare occurred
     * */
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

    /**
     * Adds a card from the deck to the players hand
     */
//    public void addCardToHand() {
//        /*
//        if (deck.getDeck().size() == 0) {
//            if (this == Card.getBoard().getActivePlayer().getField())
//                Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
//            else
//                Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
//
//            return;
//        }
//
//         */
//        Card temp = Board.deck.drawOneCardB(2);
//        if(temp.getType().equalsIgnoreCase("other group")){
//            Board.uncontrolled.add(temp);
//            temp.setLocation(Location.UNCONTROLLED);
//        }else {
//            hand.add(temp);
//            temp.setLocation(Location.HAND);
//        }
//    }

    /**
     * This method adds cards to the players hand
     * @param : the number of cards added to players hand
     */
    public void addNCardsToHand( ){
            Card temp = Board.deck.getDeck().get(0);
            //Card temp = Board.deck.drawOneCardB(2);
            hand.add(temp);
            Board.deck.getDeck().remove(temp);
            temp.setLocation(Location.HAND);
    }

    /**
     * adds uncontrollled cards to the game
     */
    public void addUncontrolled(){
        Card temp = Board.deck.getDeck().get(0);
        if(temp.getType().equalsIgnoreCase("other group")){
            Board.uncontrolled.add(temp);
            Board.deck.getDeck().remove(temp);
            temp.setLocation(Location.UNCONTROLLED);
        }else if(temp.getType().equalsIgnoreCase("special card")){
            Board.deck.shuffle();
            addUncontrolled();
        }

    }

    /**
     * add other type cards
     */
    public void addOthers(){
        Card temp = Board.deck.getDeck().get(0);
        if (temp.getType().equalsIgnoreCase("other group")) {
            Board.uncontrolled.add(temp);
            Board.deck.getDeck().remove(temp);
            temp.setLocation(Location.UNCONTROLLED);
        } else if (temp.getType().equalsIgnoreCase("special card")) {
            hand.add(temp);
            Board.deck.getDeck().remove(temp);
            temp.setLocation(Location.HAND);
        }
    }


    /**
     * Phase of the players actions ends
     */
    public void endPhase(){
        switch (phase){
            case MAIN:
                setPhase(ACTION1);
                break;
            case ACTION1:
                setPhase(ACTION2);
                break;
            case ACTION2:
                endTurn();
                break;
        }
    }

    /**
     * Ends the turn of the player
     */
    public void endTurn(){
        phase = phase.MAIN;
        for(Card m: cardArea){
            m.setAttacked(false);
            m.setSwitchedMode(false);
        }
        Card.getBoard().nextPlayer();
    }

    /**
     * switches position of the cards
     * @param group type group card
     * @return boolean to verify switched mode
     */
    public boolean switchCardModeE(SpecialCard group){
        if(!specialArea.contains(group))
            return false;
        if(phase==phase.ACTION1 || phase==phase.ACTION2){
            throw new WrongActionException();
        }
        if(group.isSwitchedMode())
            return false;
        group.switchMode();
        group.setSwitchedMode(true);
        return true;

    }

    /**
     * switch card mode for group types
     * @param group type group cards
     * @return boolean value true/false
     */
    public boolean switchCardModeG(GroupCard group){
        if(!cardArea.contains(group))
            return false;
        if(phase==phase.ACTION1 || phase==phase.ACTION2){
            throw new WrongActionException();
        }

        if(group.isSwitchedMode())
            return false;
        group.switchMode();
        group.setSwitchedMode(true);
        return true;

    }

    /**
     * switch card mode for illuminati cards
     * @param group type group cards for game
     * @return boolean value
     */
    public boolean switchCardModeI(IlluminatiCard group){
        if(!cardArea.contains(group))
            return false;
        if(phase==phase.ACTION1 || phase==phase.ACTION2){
            throw new WrongActionException();
        }
        if(group.isSwitchedMode())
            return false;
        group.switchMode();
        group.setSwitchedMode(true);
        return true;

    }

    /**
     * getter fpr Illuminati card
     */
    public IlluminatiCard getIlluminati(IlluminatiCard illu){
        return illu;
    }


    /**
     * gets hand
     * @return hand
     */
    public ArrayList<Card> getHand() { return hand; }

    /**
     * getter for phase of game
     * @return phase
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * setter for phase of game
     * @param phase phase of the game
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    /**
     * gets the card in the game
     * @param e card
     * @return card e
     */
    public Card getCardL(Card e){
        return e;
    }

    /**
     * getter for group card
     * @param group card type group
     * @return group card
     */
    public GroupCard getGroup(GroupCard group){
        return group;
    }

    /**
     * gets card area
     * @return area
     */
    public ArrayList<Card> getCardArea(){return cardArea;}

    /**
     * getter for special area
     * @return special area
     */
    public ArrayList<SpecialCard> getSpecialArea(){ return specialArea;}

    /**
     * getter for uncontrolled groups
     * @return uncontrolled groups
     */
    public ArrayList<Card> getUncontrolledGroups(){ return uncontrolledGroups;}

    /**
     * getter for terminated
     * @return graveyard of terminated
     */
    public ArrayList<Card> getGraveYard(){ return graveYard;}




}