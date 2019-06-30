package ARK.Illuminati.cards;

public class IlluminatiCard extends Card {
    private int income;
    private String ability;
    private int power;
    private int tpower;
    private Mode mode = Mode.ATTACK;

    public IlluminatiCard(String name, String type,String ability, int i, int p, int tp){
        super(name, type);
        this.ability= ability;
        this.income = i;
        this.power= p;
        this.tpower= tp;

    }
    public  void action(Card cardA){}


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

    public void giveAwayMoney(){

    }

    public int gettPower() { return tpower; }

    public void settPower(int tPower) {
        this.tpower = tPower;
    }


    public void setIncome(int income) {
        this.income = income;
    }

    public int getIncome(){ return income;}

    public void setMode(Mode mode){ this.mode = mode;}

    public Mode getMode(){ return mode;}

}
