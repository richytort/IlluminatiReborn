package ARK.Illuminati.cards.specialCards;

import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.Card;

public class deepAgent extends SpecialCard {
    String ability;

    public deepAgent(String name, String type, String ability){
        super(name, type);
        this.ability = ability;
    }
    public  void action(Card cardA){}


    public void setAbility(String ability){
        this.ability = ability;
    }
    public String getAbility(){
        return ability;
    }

}
