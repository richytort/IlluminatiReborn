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

        if(this != Card.getBoard().getAvtivePlayer()){
            return false;
        }

        if(addedGroupThisTurn)
            throw new MultipleGroupAdditionException();

        boolean groupAdded = this.field.addMonsterToField(group,
                Mode.ATTACK, false);

        if (!groupAdded)
            return false;

        addedGroupThisTurn = true;

        return true ;
    }

    @Override
    public boolean setSpecial(SpecialCard special) {
        if (Card.getBoard().isGameOver() )
            return false;

        if(this != Card.getBoard().getActivePlayer())
            return false;

        boolean specialAdded = this.field.addSpecialToField( special , null , true );

        return specialAdded;
    }

    @Override
    public boolean activateSpecial(SpecialCard special, GroupCard group ) {

        if (Card.getBoard().isGameOver())
            return false;

        if(this != Card.getBoard().getActivePlayer() )
            return false;

        boolean specialActivated;

        if(this.field.getSpecialArea().contains(special))
            specialActivated = this.field.activateSetSpell(special,group);
        else
            specialActivated = this.field.addSpellToField(special, group, false);

        return specialActivated ;
    }

    @Override
    public boolean declareAttack(GroupCard group) {
        return false;
    }

    @Override
    public boolean declareAttack(GroupCard activeGroup, GroupCard opponentGroup) {
        return false;
    }

    @Override
    public void addCardToStructure() {

    }

    @Override
    public void addNCardsToStructure(int n) {

    }

    @Override
    public void endAction() {

    }

    @Override
    public boolean endTurn() {
        return false;
    }

    @Override
    public boolean switchGroupMode(GroupCard group) {
        return false;
    }
}



