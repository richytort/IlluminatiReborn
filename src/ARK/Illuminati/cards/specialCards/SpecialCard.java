package ARK.Illuminati.cards.specialCards;

import ARK.Illuminati.cards.Card;

public class SpecialCard extends Card {
    private String ability;

    public SpecialCard(String name, String type, String ability){
        super(name, type);
        this.ability = ability;

    }
    /**
     * gets the special ability of the card
     * @return the ability
     */
    public String getAbility() {
        return ability;
    }

    /**
     * sets the special ability of the card
     * @param ability - special ability of the card
     */
    public void setAbility(String ability) {
        this.ability = ability;
    }

}
