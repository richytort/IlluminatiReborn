package ARK.Illuminati.board.player;


import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.exceptions.UnexpectedFormatException;
import ARK.Illuminati.cards.Mode;
import ARK.Illuminati.exceptions.MultipleGroupAdditionException;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player implements Contender {
    //instance variables
    private final String name;
    private int income;
    private int totalIncome;
    private Field field;
    private boolean addedGroupThisTurn;
    private ArrayList<Card> hand;

    public Player(String name)  throws IOException, UnexpectedFormatException {
        this.name = name;
        this.field = new Field();
        hand = new ArrayList<>();
    }


    @Override
    public boolean setGroup(GroupCard group) {
        if (Card.getBoard().isGameOver())
            return false;

        if(this != Card.getBoard().getActivePlayer()){
            return false;
        }
//
//        if (addedGroupThisTurn)
//            throw new MultipleGroupAdditionException();
//
//            boolean groupAdded = this.field.addGroupToField(group, Mode.ATTACK, false);
//
//            if (!groupAdded)
//              return false;
//
//            addedGroupThisTurn = true;

        return true;
    }


    @Override
    public boolean setSpecial(SpecialCard special) {
        if (Card.getBoard().isGameOver())
            return false;

        if (this != Card.getBoard().getActivePlayer())
            return false;

        //boolean specialAdded = this.field.addSpecialToField( special , null , true );

        //return specialAdded;
        return false; ///////////////////delete when specialAdded is done
    }

    @Override
    public boolean activateSpecial(SpecialCard special, GroupCard group) {

        if (Card.getBoard().isGameOver())
            return false;

        if (this != Card.getBoard().getActivePlayer())
            return false;

        boolean specialActivated;

        ///if(this.field.getSpecialArea().contains(special))
        //    specialActivated = this.field.activateSetSpell(special,group);
        //else
        //    specialActivated = this.field.addSpellToField(special, group, false);

        //return specialActivated ;
        return false; ///////////////////delete when specialactivated
    }

    @Override
    public boolean declareAttack(GroupCard group) {
        if (Card.getBoard().isGameOver())
            return false;

        if (this != Card.getBoard().getActivePlayer())
            return false;

        //boolean groupAttacked = this.field.declareAttack(group, null);

        //return groupAttacked;
        return false;///////////delete when fixed.

    }

    @Override
    public boolean declareAttack(GroupCard activeGroup, GroupCard opponentGroup) {

        if (Card.getBoard().isGameOver())
            return false;

        if (this != Card.getBoard().getActivePlayer())
            return false;

        //boolean groupAttacked = this.field.declareAttack(activeGroup, opponentGroup);

        //return groupAttacked ;

        return false; //delete when fixed.
    }

    @Override
    public void addCardToStructure() {
        //this.field.addCardToStructure();
    }


    @Override
    public void endAction() {

        if (Card.getBoard().isGameOver())
            return;

        if (this != Card.getBoard().getActivePlayer())
            return;

        this.getField().endAction();

    }

    @Override
    public boolean endTurn() {
        if (Card.getBoard().isGameOver())
            return false;
        if (this != Card.getBoard().getActivePlayer())
            return false;

        addedGroupThisTurn = false;
        this.getField().endTurn();

        return true;

    }
    public boolean switchCardMode(Card cards){
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
        boolean CardSwitched = this.file.switchCardMode(cards);
        return CardSwitched;
    }

    /////////////Seems we can adjust the rotation on this part/////////////////////////////////////////////////////////
    @Override
    public boolean switchGroupPosition(GroupCard group) {
        if (Card.getBoard().isGameOver())
            return false;

        if (this != Card.getBoard().getActivePlayer())
            return false;

        //boolean groupRotated = this.field.switchGroupPosition(group);

        return false; //return groupRotated ;

    }

    public void addCardToHand(){
        this.field.addCardToHand();
    }

    public void addIlluminatiCard(){
        this.field.addIlluminatiCard();
    }



   public int getIncome(){
        for(Card e: hand){
            if(getCard(e).getType().equalsIgnoreCase("illuminati") || getCard(e).getType().equalsIgnoreCase("other group")){
                income = e.getIncome() + e.getIncome();
            }
        }return income;

    }

    public int getTotalIncome(){ //use it to check for winner
        for(int i = 0; i < hand.size();i++){
            totalIncome+=hand.get(i).getIncome();
        }
        return totalIncome;
    }

    public Field getField(){ return field; }

    //check if neeeded
    public Card getCard(Card i) {
        return i;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public void giveAwaySpecialCard(){

    }

    public void useSpecialCard(){

    }
    public void moveAGroup(int moveGroup, int newLocation ){
        Collections.swap(hand,moveGroup,newLocation);
    }

    public void passing(){
        this.getField().endTurn();
        totalIncome = getTotalIncome() + 5;
    }

}



