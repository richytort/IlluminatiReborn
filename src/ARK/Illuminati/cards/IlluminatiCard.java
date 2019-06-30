package ARK.Illuminati.cards;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.board.Board;
import ARK.Illuminati.board.player.Field;

public class IlluminatiCard extends Card {
    private int income;
    private String ability;
    private Board boardd;
    private Field field;
    private int power;
    private int tpower;
    private Mode mode = Mode.ATTACK;
    private Player player;
    private boolean attacked;
    private boolean switchedMode;
    private int targetIncome;

    public IlluminatiCard(String name, String type,String ability, int i, int p, int tp){
        super(name, type);
        this.ability= ability;
        this.income = i;
        this.power= p;
        this.tpower= tp;

    }
    public void attackToControl(GroupCard target){
        Player active = getBoard().getActivePlayer();
        Player opponent = getBoard().getOpponentPlayer();
        if(target.getMode() == Mode.ATTACK){
            target.switchMode();
        }
        int thisPower = this.getPower();
        int otherResistance = target.getResistance();
        //case where card is not owned by any group
        int totalSubtraction = thisPower-otherResistance;
        int diceNumber = boardd.rollDice();
        System.out.println(diceNumber);
        this.setAttacked(true);
        if(diceNumber >= totalSubtraction){
            //  opponent.getField().removeGroupToHand(target);
            income = active.getIncome() + target.getIncome()/2;
            targetIncome = target.getIncome() - target.getIncome();
        }else if(diceNumber == 11 || diceNumber == 12){
            System.out.println("Sorry automatic lost");

        }else{
            System.out.println("Maybe next time");
        }

    }

    public void attackToNeutralize(GroupCard target){
        Player active = getBoard().getActivePlayer();
        Player opponent = getBoard().getOpponentPlayer();
        if(target.getMode() == Mode.ATTACK){
            target.setMode(Mode.DEFENSE);
        }
        int thisPower = this.getPower();
        int otherResistance = target.getResistance();
        int totalSubtraction = thisPower-otherResistance;
        this.setAttacked(true);
        int diceNumber = boardd.rollDice();
        System.out.println(diceNumber);
        if(diceNumber >= totalSubtraction){
            //  opponent.getField().removeGroupToUncontrolled(target);
            income =active.getIncome() +6;
            targetIncome = target.getIncome() - target.getIncome();
        }else if(diceNumber == 11 || diceNumber == 12){
            System.out.println("Sorry automatic lost");

        }else{
            System.out.println("Maybe next time");
        }

    }

    public void attacktoDestroy(GroupCard target){
        Player active = getBoard().getActivePlayer();
        Player opponent = getBoard().getOpponentPlayer();
        if(target.getMode() == Mode.ATTACK){
            target.switchMode();
        }
        int thisPower = this.getPower();
        int otherPower = target.getPower();
        int totalSubtraction = thisPower - otherPower;
        this.setAttacked(true);
        int diceNumber = boardd.rollDice();
        System.out.println(diceNumber);
        if(diceNumber >= totalSubtraction){
            // opponent.getField().removeToGraveYard(target);
        }else if(diceNumber == 11 || diceNumber == 12){
            System.out.println("Sorry automatic lost");

        }else{
            System.out.println("Maybe next time");
        }

    }

    public int transferMoney(Card groupTransfer, int incomeTransfer){
        income = this.getIncome()- incomeTransfer;
        targetIncome = groupTransfer.getIncome()+ incomeTransfer;
        return targetIncome;

    }

    public void dropAgroup(GroupCard i){
        //field.removeCard(i);
    }


    public void aidAnAttack(){

    }

    public void giveAwayMoney( int amount){
        Player p1 = getBoard().getActivePlayer();
        Player p2 = getBoard().getOpponentPlayer();
        int totalIncome = p1.getTotalIncome() - amount;
        int newIncome = p2.getTotalIncome() + amount;

    }





    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int gettPower() { return tpower; }

    public void settPower(int tPower) {
        this.tpower = tPower;
    }


    public void setIncome(int income) {
        this.income = income;
    }

    public int getIncome(){ return income;}

    public void setHidden(boolean hidden){ super.setHidden(hidden); }

    public void setMode(Mode mode){ this.mode = mode; }

    public Mode getMode(){ return mode; }

    public void setAttacked(boolean attacked){this.attacked = attacked;}

    public boolean isSwitchedMode(){ return switchedMode;}

    public void setSwitchedMode(boolean switchedMode){
        this.switchedMode = switchedMode;
    }


}
