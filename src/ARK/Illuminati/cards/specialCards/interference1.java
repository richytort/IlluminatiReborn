package ARK.Illuminati.cards.specialCards;

import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;

public class interference1 extends SpecialCard{
    String ability;

    public interference1(String name, String type, String ability){
        super(name, type);
        this.ability = ability;
    }
    @Override
    public void action(GroupCard card) {

    }
    public void setAbility(String ability){
        this.ability = ability;
    }
    public String getAbility(){
        return ability;
    }

}
