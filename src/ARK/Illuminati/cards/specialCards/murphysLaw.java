package ARK.Illuminati.cards.specialCards;

public class murphysLaw extends SpecialCard{
    String ability;

    public murphysLaw(String name, String type, String ability){
        super(name, type);
        this.ability = ability;
    }
    public void Action(){

    }
    public void setAbility(String ability){
        this.ability = ability;
    }
    public String getAbility(){
        return ability;
    }

}
