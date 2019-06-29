package ARK.Illuminati.board.player;

import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.cards.Card;

public interface Contender {

    public boolean setGroup( GroupCard group );

    public boolean setSpecial( SpecialCard special) ;

    public Card getCard(Card i);

    public boolean activateSpecial( SpecialCard special, GroupCard group );

    public boolean declareAttack(GroupCard group);

    public boolean declareAttack(GroupCard activeGroup, GroupCard opponentGroup);

    public void addCardToStructure();

    public void addNCardsToStructure(int n );

    public void endAction();

    public boolean endTurn();

    public boolean switchGroupPosition( GroupCard group ) ;
}
