package ARK.Illuminati.board.player;

import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.SpecialCard;

public interface Contender {

    public boolean setGroup( GroupCard group );

    public boolean setSpecial( SpecialCard special) ;

    public boolean activateSpecial( SpecialCard special );

    public boolean declareAttack(GroupCard group);

    public boolean declareAttack(GroupCard activeGroup, GroupCard opponentGroup);

    public void addCardToStructure();

    public void addNCardsToStructure(int n );

    public void endAction();

    public boolean endTurn();

    public boolean switchGroupMode( GroupCard group ) ;
}
