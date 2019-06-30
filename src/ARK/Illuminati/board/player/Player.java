package ARK.Illuminati.board.player;


import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.IlluminatiCard;
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
    private int result;
    private Field field;
    private boolean addedGroupThisTurn;
    private ArrayList<Card> hand;

    public Player(String name)  throws IOException, UnexpectedFormatException {
        this.name = name;
        this.field = new Field();
        hand = new ArrayList<>();
    }


    //need to set it facing up
    @Override
    public boolean setGroup(Card group) {

        if (Card.getBoard().isGameOver())
            return false;

        if(this != Card.getBoard().getActivePlayer()){
            return false;
        }
      //  if (addedGroupThisTurn)
            //do we need it? because we can have multiple cards there??
          // throw new MultipleGroupAdditionException();
       // boolean groupAdded = this.field.addGroupToField(group, Mode.ATTACK, false);
//            if (!groupAdded)
//              return false;
//            addedGroupThisTurn = true;
        return true;
    }


    @Override
    public boolean setSpecial(SpecialCard special) {
        if (Card.getBoard().isGameOver())
            return false;

        if (this != Card.getBoard().getActivePlayer()) {
            return false;
        }
        //boolean specialAdded = this.field.addSpecialToField( special , null , false );
        //return specialAdded;
        return true;
    }


 /*
    public boolean setSpecialFaceDown(SpecialCard special){
            if (Card.getBoard().isGameOver())
                return false;

            if (this != Card.getBoard().getActivePlayer()) {
                return false;
            }
            boolean specialAdded = this.field.addSpecialToField( special , null , true );
            return specialAdded;
    }

 */

    @Override
    //finish when they finish field.
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

  //  @Override
//    //finish when field
//    public boolean declareAttack(GroupCard group) {
//        if (Card.getBoard().isGameOver())
//            return false;
//
//        if (this != Card.getBoard().getActivePlayer())
//            return false;
//
//        //boolean groupAttacked = this.field.declareAttack(group, null);
//
//        //return groupAttacked;
//        return false;///////////delete when fixed.
//
//    }

    //do we really need it
  //  @Override
    /*
    public boolean declareAttack(GroupCard activeGroup, GroupCard opponentGroup) {

        if (Card.getBoard().isGameOver())
            return false;

        if (this != Card.getBoard().getActivePlayer())
            return false;

        boolean groupAttacked = this.field.declareAttack(activeGroup, opponentGroup);

        return groupAttacked ;

        return false; //delete when fixed.
    }
*/
//    public boolean declareAttackToControl(GroupCard group) {}
//    public boolean declareAttackToNeutralize(GroupCard group) {}
//
//    public boolean declareAttackToDestroy(GroupCard group) {}
//
//

    public boolean declareAttackToControlI(IlluminatiCard activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
//        boolean CardAttacked = this.field.declareAttackToControl(activeGroup,opponentGroup);
//        return cardAttacked;
        return false;
    }

    public boolean declareAttackToControlG(Card activeGroup, Card opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
//        boolean CardAttacked = this.field.declareAttackToControl(activeGroup,opponentGroup);
//
//        return cardAttacked;
        return false;
    }


    public boolean declareAttackToNeutralizeI(Card activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
//        boolean CardAttacked = this.field.declareAttackToNeutralize(activeGroup,opponentGroup);
//
//        return cardAttacked;
        return false;
    }
    public boolean declareAttackToNeutralizeG(Card activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
//        boolean CardAttacked = this.field.declareAttackToNeutralize(activeGroup,opponentGroup);
//
//        return cardAttacked;
        return false;
    }



    public boolean declareAttackToDestroyI(Card activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
//        boolean CardAttacked = this.field.declareAttackToDestroy(activeGroup,opponentGroup);
//
//        return cardAttacked;
        return false;

    }



    public boolean declareAttackToDestroyG(Card activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
//        boolean CardAttacked = this.field.declareAttackToDestroy(activeGroup,opponentGroup);
//
//        return cardAttacked;
        return false;

    }

    //fix
    public boolean dropAGroup(Card card){
        return false;
    }
    //fix
    public boolean giveawayGroup(Card card){
        return false;
    }

    @Override
    public void addCardToStructure() {
        //this.field.addCardToStructure();
    }


    //get from field
    @Override
    public void endAction() {

        if (Card.getBoard().isGameOver())
            return;

        if (this != Card.getBoard().getActivePlayer())
            return;

      //  this.getField().endAction();

    }

    //get from field
    @Override
    public boolean endTurn() {
        if (Card.getBoard().isGameOver())
            return false;
        if (this != Card.getBoard().getActivePlayer())
            return false;

        addedGroupThisTurn = false;
      //  this.getField().endTurn();

        return true;

    }
    public void printHand(){
        for(Card e: hand){
            System.out.println(e+ " ");
        }
    }
    //fixed later
    public boolean switchCardMode(Card cards){
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
       // boolean CardSwitched = this.file.switchCardMode(cards);
        //return CardSwitched;
        return false;
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

    public void addNCardsToHand(int n){
        this.field.addNCardsToHand(n);
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

    //should it be boolean
    //implement this actions
    public void giveAwaySpecialCard(){

    }
    //should it be boolean

    //implement this action
    public void useSpecialCard(){

    }
    public void moveAGroup(int moveGroup, int newLocation ){
        Collections.swap(hand,moveGroup,newLocation);
    }
    public void addCardToHand(Card e){
        hand.add(e);
    }

    //finish implementing
//    public void passing(){
//        this.getField().endTurn();
//        totalIncome = getTotalIncome() + 5;
//    }
    public void setResult(int result){
        this.result = result;
    }
    public int getResult(){
        return result;
    }
    public void setIncome(int income){
        this.income = income;
    }

}



