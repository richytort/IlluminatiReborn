package ARK.Illuminati.board.player;

import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.cards.Card;

/**
 * Interface to perform actions within Illuminati game
 */
public interface Contender {

    /**
     * sets the group
     * @param group- Card of the type group
     * @return bool value
     */
    public boolean setGroup( Card group );

    /**
     * Setter for Special Card
     * @param special Card of the type special
     * @return bool value (T or F)
     */
    public boolean setSpecial( SpecialCard special) ;

    /**
     *Getter for card
     **/
    public Card getCard(Card i);

    /**
     * Setter for Face Down Cards
     * @param special - card of type special
     * @return bool value t or f
     */
   // public boolean setSpecialFaceDown(SpecialCard special);

    /**
     * Activates the special cards on a group
     * @param special- special card used
     * @param group- group special card is used one
     * @return bool value
     */
    public boolean activateSpecial( SpecialCard special, GroupCard group );

 //   public boolean declareAttack(GroupCard group);

//    public boolean declareAttack(GroupCard activeGroup, GroupCard opponentGroup);

    /**
     *Adds cards to Structure
     */
    public void addCardToStructure();

    /**
     * Ends player action
     */
    public void endAction();

    /**
     * Ends the turn of a player
     * @return bool value true or false
     */
    public boolean endTurn();

    /**
     * Switches the position of a greoup
     * @param group - the group to be positionally changed
     * @return bool value true of false
     */
    public boolean switchGroupPosition( GroupCard group ) ;
}
