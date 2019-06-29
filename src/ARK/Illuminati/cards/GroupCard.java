package ARK.Illuminati.cards;
import ARK.Illuminati.board.Board;
import ARK.Illuminati.board.player.Player;

import javax.print.attribute.standard.MediaSize;

public class GroupCard extends Card {
    private int power;
    private String ability;
    private int tpower;
    private Mode mode = Mode.ATTACK;
    private int income;
    private boolean attacked;
    private Board boardd;
    private boolean switchedMode;
    private int resistance;
    private String alignment;


    public GroupCard(String name, String type, String ability,String alignment, int power, int tpower, int resistance, int income){
        super(name,type);
        this.ability = ability;
        this.power = power;
        this.tpower = tpower;
        this.income = income;
        this.resistance = resistance;
        this.alignment = alignment;
    }

    public void attackToControl(GroupCard target){
        Player active = getBoard().getActivePlayer();
        Player opponent = getBoard().getOpponentPlayer();
        int thisPower = this.getPower();
        int otherResistance = target.getResistance();
        int totalSubtraction = thisPower-otherResistance;
        int diceNumber = boardd.rollDice();
        System.out.println(diceNumber);
        if(diceNumber >= totalSubtraction){
            opponent.getField().removeGroupToHand(target);
        }else if(diceNumber == 11 || diceNumber == 12){
            System.out.println("Sorry automatic lost");

        }else{
            System.out.println("Maybe next time");
        }

    }

    public void attackToNeutralize(){

    }
    public void attacktoDestroy(){

    }
    public void transferMoney(){

    }
    public void moveAGroup(){

    }
    public void giveAgroupAway(){

    }
    public void dropAgroup(){

    }


    public void aidAnAttack(){

    }

    public void giveAwaySpecialCard(){

    }

    public void giveAwayMoney(){

    }

    public void useSpecialCard(){

    }

    public void passing(){

    }


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

    public String getAlignment(){
        return alignment;
    }
    public void setAlignment(String alignment){
        this.alignment = alignment;
    }
    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getPower() {
        return power;
    }

    public void settPower(int tPower) {
        this.tpower = tPower;
    }


   public int getResistance(){
        return resistance;
   }
   public void setResistance(int resistance){
        this.resistance = resistance;
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
