package ARK.Illuminati.cards;

public class GroupCard extends Card {
    private int power;
    private String ability;
    private int tpower;
    private int income;
    private int resistance;


    public GroupCard(String name, String type, String ability, int power, int tpower, int resistance, int income){
        super(name,type);
        this.ability = ability;
        this.power = power;
        this.tpower = tpower;
        this.income = income;
        this.resistance = resistance;
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

    public int gettPower()
    {
        return tpower;
    }

    public void settPower(int tpower) {
        this.tpower = tpower;
    }


    public void setIncome(int income) {

        this.income = income;
    }

}
