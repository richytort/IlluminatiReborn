package ARK.Illuminati.board.player;


import ARK.Illuminati.cards.*;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.exceptions.UnexpectedFormatException;
import ARK.Illuminati.board.Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Player implements Contender {
    //instance variables
    private final String name;
    private int income;
    private int totalIncome;
    private int result;
    private Field field;
    private Board board;
    private Deck deck;
    private boolean addedGroupThisTurn;
    private ArrayList<Card> hand;

    /**
     * Initializes the player
     * @param name name of the player
     * @throws IOException - error exception
     * @throws UnexpectedFormatException -format exception thrown
     */
    public Player(String name)  throws IOException, UnexpectedFormatException {
        this.name = name;
        this.field = new Field();
        hand = new ArrayList<>();
        this.deck = new Deck();
    }


    //need to set it facing up
    @Override
    /**
     * setter for the group
     */
    public boolean setGroup(Card group) {
        if (Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer()){
            return false;
        }
        boolean groupAdded = this.field.addGroupToField(group, Mode.ATTACK, false);


        return groupAdded;
    }

    /**
     * setter for illuminati card
     * @param illuminati - illuminati card set
     * @return boolean value true/false for setting of illuminati card
     */
    public boolean setIlluminati(IlluminatiCard illuminati){

        if (Card.getBoard().isGameOver())
            return false;

        if(this != Card.getBoard().getActivePlayer())
            return false;

        boolean illuminatiAdded = this.field.addIlluminatiToField( illuminati,Mode.ATTACK);

        if( !illuminatiAdded)
            return false;

        addedGroupThisTurn = true ;

        return true;
    }

    /**
     * sets the group down
     * @param group group card
     * @return boolean value for group set down
     */
    public boolean setGroupDown(Card group) {
        if (Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer()){
            return false;
        }
        boolean groupAdded = this.field.addGroupToField(group, Mode.ATTACK, true);

        return groupAdded;
    }


    @Override
    /**
     * boolean for special card set to field
     */
    public boolean setSpecial(SpecialCard special) {
        if (Card.getBoard().isGameOver())
            return false;

        if (this != Card.getBoard().getActivePlayer()) {
            return false;
        }
        boolean specialAdded = this.field.addSpecialToField( special , null , true );
        return specialAdded;
    }


//    /**
//     * Places the special card face down
//     * @param special the special card to be placed face down
//     * @return boolean true/false
//     */
//    public boolean setSpecialFaceDown(SpecialCard special){
//            if (Card.getBoard().isGameOver())
//                return false;
//
//            if (this != Card.getBoard().getActivePlayer()) {
//                return false;
//            }
//            boolean specialAdded = this.field.addSpecialToField( special , null , true );
//            return specialAdded;
//    }



    @Override
    //finish when they finish field.
    /**
     * Activates the special card from the player
     */
    public boolean activateSpecial(SpecialCard special, GroupCard group) {

        if (Card.getBoard().isGameOver())
            return false;

        if (this != Card.getBoard().getActivePlayer())
            return false;

        boolean specialActivated;

        if(this.field.getSpecialArea().contains(special))
            specialActivated = this.field.activeSpecial(special,group);
        else
            specialActivated = this.field.addSpecialToField(special, group, false);

        return specialActivated;
    }


    /**
     * Declares an attack on the opponents group with the intention to control
     * @param activeGroup - the IlluminatiCard
     * @param opponentGroup - the opponent group attempted to be controlled via attacking
     * @return opponent group Card that was attacked
     */
    public boolean declareAttackToControlI(IlluminatiCard activeGroup, GroupCard opponentGroup) {
       if(Card.getBoard().isGameOver())
             return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
        boolean CardAttacked = this.field.declareAttackToControlI(activeGroup,opponentGroup);

        return CardAttacked;

    }

    /**
     * Declares attacks on opponent group to control
     * @param activeGroup - the current group attempting to control the other
     * @param opponentGroup - the group at risk of being attacked and controlled
     * @return boolean value true/false
     */
    public boolean declareAttackToControlG(GroupCard activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
        boolean CardAttacked = this.field.declareAttackToControlG(activeGroup,opponentGroup);

        return CardAttacked;
    }


    /**
     * Declares attacks on opponent group to neutralize
     * @param activeGroup:
     * @param opponentGroup:
     * @return the card that was Attacked
     */
    public boolean declareAttackToNeutralizeI(IlluminatiCard activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
        boolean CardAttacked = this.field.declareAttackToNeutralizeI(activeGroup,opponentGroup);

        return CardAttacked;
    }

    /**
     * Declares an attack to neutralize the opponent group
     * @param activeGroup: the first group carrying out the attack declaration
     * @param opponentGroup: The opponent group that is being declared to be attacked
     * @return Card Attacked
     */
    public boolean declareAttackToNeutralizeG(GroupCard activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
        boolean CardAttacked = this.field.declareAttackToNeutralizeG(activeGroup,opponentGroup);

        return CardAttacked;
    }


    /**
     * Declaring an attack on the opponent group with intent to destroy
     * @param activeGroup: type Illuminati, attacks the opposing group
     * @param opponentGroup: the opponent group that is being attacked
     * @return boolean value CardAttacked
     */
    public boolean declareAttackToDestroyI(IlluminatiCard activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
        boolean CardAttacked = this.field.declareAttackToDestroyI(activeGroup,opponentGroup);

        return CardAttacked;

    }

    /**
     * Attack to destroy the opponent group
     */
    public boolean declareAttackToDestroyG(GroupCard activeGroup, GroupCard opponentGroup) {
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
        boolean CardAttacked = this.field.declareAttackToDestroyG(activeGroup,opponentGroup);

        return CardAttacked;

    }


    @Override
    /**
     * adds card to structure
     */
    public void addCardToStructure() {
        //this.field.addCardToStructure();
    }


    //get from field
    @Override
    /**
     * ends action on the field
     */
    public void endAction() {

        if (Card.getBoard().isGameOver())
            return;

        if (this != Card.getBoard().getActivePlayer())
            return;

        this.getField().endPhase();

    }

    //get from field
    @Override
    /**
     * boolean for ending the turn of a player
     */
    public boolean endTurn() {
        if (Card.getBoard().isGameOver())
            return false;
        if (this != Card.getBoard().getActivePlayer())
            return false;

        addedGroupThisTurn = false;
        this.getField().endTurn();

        return true;

    }

    /**
     * prints players hand
     */
    public void printHand(){
        for(Card e: hand){
            System.out.println(e+ " ");
        }
    }
    //fixed later

    /**
     * switches card mode
     * @param cards group cards
     * @return boolean value
     */
    public boolean switchCardModeG(GroupCard cards){
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
        boolean CardSwitched = this.field.switchCardModeG(cards);
        return CardSwitched;
    }

    public boolean switchCardModeI(IlluminatiCard illu){
        if(Card.getBoard().isGameOver())
            return false;
        if(this != Card.getBoard().getActivePlayer())
            return false;
        boolean CardSwitched = this.field.switchCardModeI(illu);
        return CardSwitched;
    }

    /////////////Seems we can adjust the rotation on this part/////////////////////////////////////////////////////////
    @Override
    /**
     * boolean value returned for switched group position
     */
    public boolean switchGroupPosition( GroupCard group ){
//        if (Card.getBoard().isGameOver())
//            return false;
//
//        if (this != Card.getBoard().getActivePlayer())
//            return false;
//        boolean groupRotated = this.field.switchGroupPosition(group);

        return false; //return groupRotated ;

    }

//    public void addCardToHand(){
//        this.field.addCardToHand();
//    }

//    public void addNCardsToHand(int n){
//        this.field.addNCardsToHand(n);
//    }
//
//    public void addIlluminatiCard(){
//        Card temp = Board.deck.drawOneCard();
//        hand.add(temp);
//          //temp.setLocation(Location.HAND);
//
// //       this.field.addIlluminatiCard();
//    }
//
//    public void addCard(Card k){
//        getHand().add(k);
//     //   board.getDeck().drawOneCard();
//      //  this.field.addCardToHand();
//       // hand.add(k);
//    }

    //DO I NEED IT IN FIELD
    //implement this actions

    /**
     * give away a special cards
     * @param special
     */
    public void giveAwaySpecialCard(SpecialCard special){
        Player p1 = Card.getBoard().getActivePlayer();
        if(this ==Card.getBoard().getActivePlayer()){

        }

    }

    //DO I NEED IT IN FIELD
    //implement this action

    /**
     * use special cards
     */
    public void useSpecialCard(){ }

    //DO I NEED THEM IN FIELD

    /**
     * moving a group
     * @param moveGroup group to be moved
     * @param newLocation int value for new location
     */
    public void moveAGroup(int moveGroup, int newLocation ){
        Collections.swap(Card.getBoard().getActivePlayer().getHand(),moveGroup,newLocation);
    }

    //DO I NEED IT IN

    /**
     * passing in turn
     */
    public void passing(){
        this.getField().endTurn();
        this.setIncome(getTotalIncome() + 5);
    }

    //DO I NEED IT IN FIELD

    /**
     * dropping  a grouo
     * @param i group card to be dropped
     */
    public void dropAgroup(GroupCard i){
       Player p = Card.getBoard().getActivePlayer();
        p.getField().removeGroupToUncontrolled(i);
    }


    //DO I NEED IT IN FIELD
    //need to implement it

    /**
     * aiding an attack
     */
    public void aidAnAttack(){ }

    //DO I NEED IT IN FIELD
    public void giveAgroupAway(GroupCard target){
        Player p1 = Card.getBoard().getActivePlayer();
        p1.getField().removeGroupToHand(target);

    }

    /**
     * getting the income
     * @return int income
     */
    public int getIncome(){
        for(Card e: hand){
            if(getCard(e).getType().equalsIgnoreCase("illuminati") || getCard(e).getType().equalsIgnoreCase("other group")){
                income = e.getIncome() + e.getIncome();
            }
        }return income;

    }

    /**
     * getting total income
     * @return total income
     */
    public int getTotalIncome(){ //use it to check for winner
        for(int i = 0; i < hand.size();i++){
            totalIncome+=hand.get(i).getIncome();
        }
        return totalIncome;
    }


    /**
     * get field of the game
     * @return field
     */
    public Field getField(){ return field;}

    /**
     * getter for card
     * @param i card
     * @return card
     */
    public Card getCard(Card i) { return i;
    }

    /**
     * getter for name
     * @return name
     */
    public String getName() { return name; }

    /**
     * getter for hand
     * @return hand
     */
    public ArrayList<Card> getHand(){ return hand; }

    /**
     * setter for result
     * @param result of the game
     */
    public void setResult(int result){ this.result = result; }

    /**
     * getter for result
     * @return game result
     */
    public int getResult(){ return result; }

    /**
     * setter for income
     * @param income of the player
     */
    public void setIncome(int income){ this.income = income; }

}



