package ARK.Illuminati.cards.specialCards;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;

public abstract class SpecialCard extends Card{

    public SpecialCard(String name, String type){
        super(name,type);
    }

    public abstract void action(GroupCard card);

}
