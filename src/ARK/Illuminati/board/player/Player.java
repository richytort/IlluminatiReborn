package ARK.Illuminati.board.player;


import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.Mode;
import ARK.Illuminati.cards.SpecialCard;

import java.util.ArrayList;

public class Player implements Contender{
    //instance variables
    private final String name;
    ////////////////////////FINISH FIELD CLASS////////////////
    //private Field field;
    private boolean addedGroupThisTurn;

    public Player(String name ){

        this.name = name ;
        ////////////////FINISH FIELD CLASS///////////////////////////
        //this.field = new Field();

    }


    @Override
    public boolean setGroup(GroupCard group) {
        if(Card.getBoard().isGameOver())
            return false;

        //if(this != Card.getBoard().getAvtivePlayer()){
        //    return false;
        //}

        if(addedGroupThisTurn)
            //throw new MultipleGroupAdditionException();

        //boolean groupAdded = this.field.addMonsterToField(group, Mode.ATTACK, false);

        //if (!groupAdded)
          //  return false;

        addedGroupThisTurn = true;

        return true ;
    }

    @Override
    public boolean setSpecial(SpecialCard special) {
        if (Card.getBoard().isGameOver() )
            return false;

        if(this != Card.getBoard().getActivePlayer())
            return false;

        //boolean specialAdded = this.field.addSpecialToField( special , null , true );

        //return specialAdded;
        return false ; ///////////////////delete when specialAdded is done
    }

    @Override
    public boolean activateSpecial(SpecialCard special, GroupCard group ) {

        if (Card.getBoard().isGameOver())
            return false;

        if(this != Card.getBoard().getActivePlayer() )
            return false;

        boolean specialActivated;

        //if(this.field.getSpecialArea().contains(special))
        //    specialActivated = this.field.activateSetSpell(special,group);
        //else
        //    specialActivated = this.field.addSpellToField(special, group, false);

        //return specialActivated ;
        return false; ///////////////////delete when specialactivated
    }

    @Override
    public boolean declareAttack(GroupCard group) {
        if(Card.getBoard().isGameOver())
            return false;

        if( this != Card.getBoard().getActivePlayer() )
            return false ;

        //boolean groupAttacked = this.field.declareAttack(group, null);

        //return groupAttacked;
        return false ;///////////delete when fixed.

    }

    @Override
    public boolean declareAttack(GroupCard activeGroup, GroupCard opponentGroup) {

        if(Card.getBoard().isGameOver())
            return false;

        if(this != Card.getBoard().getActivePlayer())
            return false ;

        //boolean groupAttacked = this.field.declareAttack(activeGroup, opponentGroup);

        //return groupAttacked ;

        return false ; //delete when fixed.
    }

    @Override
    public void addCardToStructure() {
        //this.field.addCardToStructure();
    }

    @Override
    public void addNCardsToStructure(int n) {
        //this.field.addNCardsToStructure(n);
    }

    @Override
    public void endAction() {

        if( Card.getBoard().isGameOver())
            return ;

        if( this != Card.getBoard().getActivePlayer())
            return ;

        //this.getField().endAction();

    }

    @Override
    public boolean endTurn() {
        if(Card.getBoard().isGameOver() )
            return false ;

        if(this != Card.getBoard().getActivePlayer() )
            return false;

        /////////////////////////////////////////May have to adjust this part for addedGropuThisTurn////////////////////
        addedGroupThisTurn = false ;
        //this.getField().endTurn();

        return true ;

    }

    /////////////Seems we can adjust the rotation on this part/////////////////////////////////////////////////////////
    @Override
    public boolean switchGroupPosition(GroupCard group) {

        if (Card.getBoard().isGameOver())
            return false ;

        if(this != Card.getBoard().getActivePlayer())
            return false ;

        //boolean groupRotated = this.field.switchGroupPosition(group);

        return false ; //return groupRotated ;

    }
}



