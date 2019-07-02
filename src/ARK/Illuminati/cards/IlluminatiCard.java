package ARK.Illuminati.cards;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.board.Board;
import ARK.Illuminati.board.player.Field;
import ARK.Illuminati.board.player.UncontrolledArea;

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

    /**
     * Illuminati Card Constructor
     * @param name of the Illuminati card
     * @param type of the Illuminati card
     * @param ability of the Illuminati card
     * @param i income of the Illuminati card
     * @param p power of the Illuminati card
     * @param tp transfer power of the Illuminati card
     */
    public IlluminatiCard(String name, String type,String ability, int i, int p, int tp){
        super(name, type);
        this.ability= ability;
        this.income = i;
        this.power= p;
        this.tpower= tp;

    }

    /**
     * gets the transferMoney of the group
     */
    public int transferMoney(GroupCard groupTransfer, int incomeTransfer){
        setIncome(this.getIncome()-incomeTransfer);
        groupTransfer.setIncome(incomeTransfer);
        return groupTransfer.getIncome();
    }


    /**
     * give money away action
     * @param amount to be given away
     */
    public void giveAwayMoney( int amount){
        Player p1 = getBoard().getActivePlayer();
        Player p2 = getBoard().getOpponentPlayer();
        int totalIncome = p1.getTotalIncome() - amount;
        int newIncome = p2.getTotalIncome() + amount;

    }

    /**
     * attack to control action
     * @param target the target to be attacked
     */
    public void attackToControl(GroupCard target){
        Player activePl = getBoard().getActivePlayer();
        Player opponentPl = getBoard().getOpponentPlayer();
        //UncontrolledArea uncontrolled = getBoard().uncontrolledRIGHT();
        if(target.getMode() == Mode.ATTACK){
            target.switchMode();
        }
        int opponentResistance = 0;
        if(target.getLocation().equals("UNCONTROLLED")) {
            //opponentResistance = uncontrolled.getCard(target).getResistance();

        }else if(target.getLocation().equals("HAND")){
            opponentResistance = opponentPl.getCard(target).getResistance();
        }
        int activePower = this.getPower();
        int totalSubtraction = activePower - opponentResistance;
        int diceNumber = boardd.rollDice();
        System.out.println(diceNumber);
        //what does it really do??
          this.setAttacked(true);
        if(diceNumber >= totalSubtraction){
            //opponentPl.getField().removeGroupToHand(target);
            activePl.setResult(10);
            income = activePl.getIncome() + target.getIncome()/2;
            targetIncome = target.getIncome() - target.getIncome();
        }else if(diceNumber == 11 || diceNumber == 12){
            activePl.setResult(0);
        }else{
            activePl.setResult(0);
        }
    }

    /**
     * attack to neutralize action
     * @param target target group to be attacked for intent to neutralize
     */
    public void attackToNeutralize(GroupCard target) {
        Player active = getBoard().getActivePlayer();
        Player opponent = getBoard().getOpponentPlayer();
        if (target.getMode() == Mode.ATTACK) {
            target.setMode(Mode.DEFENSE);
        }
        int thisPower = this.getPower();
        int otherResistance = target.getResistance();
        int total = thisPower - otherResistance;
        this.setAttacked(true);
        int diceNumber = boardd.rollDice();
        System.out.println(diceNumber);
        if (diceNumber >= total) {
            ///COMMENTED OUT FOR NOW SINCE I GOT AN ERROR
            //opponent.getField().removeGroupToUncontrolled(target);
            active.setIncome(active.getIncome() + 6);
            target.setIncome(target.getIncome() - target.getIncome());
            active.setResult(10);
        } else if (diceNumber == 11 || diceNumber == 12) {
            active.setIncome(0);
        }
    }

    /**
     * attack to destroy action
     * @param target to be attacked for the purpose of being destroyed
     */
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
            System.out.println("Sorry this is an automatic lost");

        }else{
            System.out.println("Maybe next time");
        }

    }
//
//      public int giveAwAyMoney(IlluminatiCard groupTransfer, int incomeTransfer){
//        income = this.getIncome()- incomeTransfer;
//        targetIncome = groupTransfer.getIncome()+ incomeTransfer;
//        return targetIncome;
//
//    }

    /**
     * switches the mode of the illuminati card
     */
    public void switchMode(){
        if(mode == Mode.ATTACK){
            mode = Mode.DEFENSE;
            setHidden(true);
        }else{
            mode = Mode.ATTACK;
            setHidden(true);
        }
    }


    public String getAbility() {
        return ability;
    }

    /**
     * sets the ability of the illuminati card
     * @param ability of the illuminati card
     */
    public void setAbility(String ability) {
        this.ability = ability;
    }

    /**
     * gets the power of the illuminati card
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * sets the power of the illuminati card
     * @param power set
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * gets the transferable power
     * @return the transferable power
     */
    public int gettPower() { return tpower; }

    /**
     * setter for transferable power
     * @param tPower transferable power
     */
    public void settPower(int tPower) {
        this.tpower = tPower;
    }

    /**
     * sets the income
     * @param income setted
     */
    public void setIncome(int income) {
        this.income = income;
    }

    /**
     * getter for the income
     * @return the income
     */
    public int getIncome(){ return income;}

    /**
     * setter for hidden
     * @param hidden boolean value
     */
    public void setHidden(boolean hidden){ super.setHidden(hidden); }

    /**
     * setter for mode
     * @param mode of the card
     */
    public void setMode(Mode mode){ this.mode = mode; }

    /**
     * getter for mode of Illuminati card
     * @return mode
     */
    public Mode getMode(){ return mode; }

    /**
     * setter for attacked
     * @param attacked boolean value true or false
     */
    public void setAttacked(boolean attacked){this.attacked = attacked;}

    /**
     * test if switched
     * @return boolean value
     */
    public boolean isSwitchedMode(){ return switchedMode;}

    /**
     * setter fpr switched mode
     * @param switchedMode for the setting
     */
    public void setSwitchedMode(boolean switchedMode){
        this.switchedMode = switchedMode;
    }


}
