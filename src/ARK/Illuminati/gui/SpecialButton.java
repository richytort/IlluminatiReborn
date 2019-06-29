package ARK.Illuminati.gui;


import ARK.Illuminati.cards.specialCards.SpecialCard;

import javax.swing.*;

public class SpecialButton extends JButton {
    private SpecialCard special ;

    public SpecialButton(){
        super();
        this.setVisible(true);
    }

    public SpecialButton(SpecialCard special){
        this.setVisible(true);
        this.setName(special.getName());
        this.setOpaque(false);
    }

    public SpecialCard getSpecial(){
        return special ;
    }

    public void setSpecial(SpecialCard sepcial){
        this.special = special ;
    }



}
