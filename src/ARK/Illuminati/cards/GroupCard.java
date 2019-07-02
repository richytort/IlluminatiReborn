package ARK.Illuminati.cards;
import ARK.Illuminati.board.Board;
import ARK.Illuminati.board.player.Field;
import ARK.Illuminati.board.player.Deck;
import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.board.player.UncontrolledArea;
import ARK.Illuminati.exceptions.UnexpectedFormatException;

import java.io.IOException;
import java.security.PublicKey;


public class GroupCard extends Card {
    private int power;
    private String ability;
    private int tpower;
    private Mode mode = Mode.ATTACK;
    private int income;
    private boolean attacked;
    private Field field;
    private Board boardd;
    private int location;
    private int result;
    private boolean switchedMode;
    private Player p;
    private int resistance;
    private int targetIncome;
    private String alignment;

    /**
     * Initializes values for the Groupcard constructor
     * @param name of group card
     * @param type of group card
     * @param ability of group card
     * @param alignment of group card
     * @param power of group card
     * @param tpower of group card
     * @param resistance of group card
     * @param income of group card
     */
    public GroupCard(String name, String type, String ability,String alignment, int power, int tpower, int resistance, int income){
        super(name,type);
        this.ability = ability;
        this.power = power;
        this.tpower = tpower;
        this.income = income;
        this.resistance = resistance;
        this.alignment = alignment;
        this.attacked = false;
        this.switchedMode = false;
    }

    /**
     * Attack to control action
     * @param target target group attacked to control
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
            opponentPl.getField().removeGroupToHand(target);
            activePl.setResult(10);
            activePl.setIncome( activePl.getIncome() + target.getIncome()/2);
            target.setIncome(target.getIncome() - target.getIncome());
        }else if(diceNumber == 11 || diceNumber == 12){
           activePl.setResult(0);
        }else{
           activePl.setResult(0);
       }
    }

    /**
     * Attack to neutralize action
     * @param target the target group to attack
     */
    public void attackToNeutralize(GroupCard target) {
        Player active = getBoard().getActivePlayer();
        Player opponent = getBoard().getOpponentPlayer();
        if (target.getMode() == Mode.ATTACK) {
            target.switchMode();
        }
        int thisPower = this.getPower();
        int otherResistance = target.getResistance();
        int total = thisPower - otherResistance;
        this.setAttacked(true);
        int diceNumber = boardd.rollDice();
        System.out.println(diceNumber);
        if (diceNumber >= total) {
            opponent.getField().removeGroupToUncontrolled(target);
            active.setIncome(active.getIncome() + 6);
            target.setIncome(target.getIncome() - target.getIncome());
            active.setResult(10);
        } else if (diceNumber == 11 || diceNumber == 12) {
            active.setResult(0);
        }
        else{
            active.setResult(0);
        }
    }

    /**
     * attack to destroy action
     * @param target the target group to attack
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
            opponent.getField().removeGroupToGraveyard(target);
            target.setIncome(0);
            active.setResult(10);
        }else if(diceNumber == 11 || diceNumber == 12){
            active.setResult(0);
        }else{
            active.setResult(0);
        }

    }

    /**
     * transfer money to a group
     * @param groupTransfer groupcard to transfer
     * @param incomeTransfer income amount
     * @return the amount transferred
     */
    public int transferMoney(GroupCard groupTransfer, int incomeTransfer){
        setIncome(this.getIncome()-incomeTransfer);
        groupTransfer.setIncome(groupTransfer.getIncome()+ incomeTransfer);
        return groupTransfer.getIncome();

    }

    /**
     * getter for ability
     * @return ability
     */
    public String getAbility() { return ability; }

    /**
     * getter for alignment
     * @return alignment
     */
    public String getAlignment(){ return alignment; }

    /**
     * setter for ability
     * @param ability string ability
     */
    public void setAbility(String ability) { this.ability = ability; }

    /**
     * getter for power of card
     * @return the power
     */
    public int getPower() { return power; }

    /**
     * getter for resistance
     * @return the resistance of card
     */
   public int getResistance(){ return resistance; }

    /**
     * setter for income amount of group card
     * @param income of the group
     */
    public void setIncome(int income) { this.income = income; }

    /**
     * gets the power of the group
     * @return power
     */
    public int gettPower() { return tpower; }

    /**
     *gets the income of the group
     * @return income
     */
    public int getIncome(){ return income;}

    /**
     * sets hidden group
     * @param hidden boolean value for hidden
     */
    public void setHidden(boolean hidden){ super.setHidden(hidden); }

  //  public void setMode(Mode mode){ this.mode = mode; }

    /**
     * gets the mode of the group
     * @return mode
     */
    public Mode getMode(){ return mode; }

    /**
     * sets attacked group
     * @param attacked boolean value true or false
     */
    public void setAttacked(boolean attacked){this.attacked = attacked;}

    /**
     * validates group is attacked
     * @return attacked group
     */
    public boolean isAttacked(){ return attacked; }

    /**
     * valiates switched mode
     * @return switchedMode
     */
    public boolean isSwitchedMode(){ return switchedMode;}

    /**
     *getter for the Switched Mode
     * @param switchedMode - boolean value for switched mode
     */
    public void setSwitchedMode(boolean switchedMode){ this.switchedMode = switchedMode; }
}
