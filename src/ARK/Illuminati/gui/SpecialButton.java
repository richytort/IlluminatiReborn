package ARK.Illuminati.gui;


import ARK.Illuminati.cards.specialCards.SpecialCard;

import javax.swing.*;

/**
 * This method deals with the Special Button and sets it up
 */
public class SpecialButton extends JButton {
    private SpecialCard special ;

    /**
     * The special button for special actions
     */
    public SpecialButton(){
        super();
        this.setVisible(true);
    }

    /**
     * Overloaded constructor for special card
     * @param special special cards
     */
    public SpecialButton(SpecialCard special){
        this.setVisible(true);
        this.setName(special.getName());
        this.setOpaque(false);
    }

    /**
     * getter for special cards
     * @return
     */
    public SpecialCard getSpecial(){
        return special ;
    }

    /**
     * setter for special cards
     * @param special the special card set
     */
    public void setSpecial(SpecialCard special){
        this.special = special ;
    }



}
