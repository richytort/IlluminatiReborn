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


    public void attackToControl(GroupCard target){
        Player activePl = getBoard().getActivePlayer();
        Player opponentPl = getBoard().getOpponentPlayer();
        UncontrolledArea uncontrolled = getBoard().uncontrolledRIGHT();
        if(target.getMode() == Mode.ATTACK){
           // target.switchMode();
        }
        int opponentResistance = 0;
        if(target.getLocation().equals("UNCONTROLLED")) {
            opponentResistance = uncontrolled.getCard(target).getResistance();


        }else if(target.getLocation().equals("HAND")){
            opponentResistance = opponentPl.getCard(target).getResistance();
        }
        int activePower = this.getPower();
        int totalSubtraction = activePower - opponentResistance;
        int diceNumber = boardd.rollDice();
        System.out.println(diceNumber);
        //what does it really do??
      //  this.setAttacked(true);
       if(diceNumber >= totalSubtraction){
            opponentPl.getField().removeGroupToHand(target);
            activePl.setResult(10);
            income = activePl.getIncome() + target.getIncome()/2;
            targetIncome = target.getIncome() - target.getIncome();
        }else if(diceNumber == 11 || diceNumber == 12){
           activePl.setResult(0);
        }else{
           activePl.setResult(0);
       }
    }

//    public void attackToNeutralize(GroupCard target){
//        Player active = getBoard().getActivePlayer();
//        Player opponent = getBoard().getOpponentPlayer();
//        if(target.getMode() == Mode.ATTACK){
//            target.setMode(Mode.DEFENSE);
//        }
//        int thisPower = this.getPower();
//        int otherResistance = target.getResistance();
//        int totalSubtraction = thisPower-otherResistance;
//        this.setAttacked(true);
//        int diceNumber = boardd.rollDice();
//        System.out.println(diceNumber);
//        if(diceNumber >= totalSubtraction){
//          //  opponent.getField().removeGroupToUncontrolled(target);
//            income =active.getIncome() +6;
//            targetIncome = target.getIncome() - target.getIncome();
//        }else if(diceNumber == 11 || diceNumber == 12){
//            System.out.println("Sorry automatic lost");
//
//        }else{
//            System.out.println("Maybe next time");
//        }
//
//    }
//
//    public void attacktoDestroy(GroupCard target){
//        Player active = getBoard().getActivePlayer();
//        Player opponent = getBoard().getOpponentPlayer();
//        if(target.getMode() == Mode.ATTACK){
//            target.switchMode();
//        }
//        int thisPower = this.getPower();
//        int otherPower = target.getPower();
//        int totalSubtraction = thisPower - otherPower;
//        this.setAttacked(true);
//        int diceNumber = boardd.rollDice();
//        System.out.println(diceNumber);
//        if(diceNumber >= totalSubtraction){
//           // opponent.getField().removeToGraveYard(target);
//        }else if(diceNumber == 11 || diceNumber == 12){
//            System.out.println("Sorry automatic lost");
//
//        }else{
//            System.out.println("Maybe next time");
//        }
//
//    }

    public int transferMoney(GroupCard groupTransfer, int incomeTransfer){
        income = this.getIncome()- incomeTransfer;
        targetIncome = groupTransfer.getIncome()+ incomeTransfer;
        return targetIncome;

    }

    public void dropAgroup(GroupCard i){
        //field.removeCard(i);
    }


    public void aidAnAttack(){

    }
    public void giveAgroupAway(GroupCard target){
        Player p1 = getBoard().getActivePlayer();
        Player p2 = getBoard().getOpponentPlayer();
        //p1.getField().removeGroupToHand(target);

    }

//    public static void main(String [] args)throws IOException, UnexpectedFormatException {
//        Player p1 = new Player("Kathya");
//        Player p2 = new Player("ulyssez");
//        Deck deck = new Deck();
//
//    }




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
    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getPower() {
        return power;
    }


   public int getResistance(){
        return resistance;
   }

    public void setIncome(int income) {
        this.income = income;
    }

    public int gettPower() { return tpower; }


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
