package ARK.Illuminati.gui;

import ARK.Illuminati.cards.IlluminatiCard;

import javax.swing.*;

public class IlluminatiButton extends JButton {
    private IlluminatiCard illuminati;
    public IlluminatiCard getIlluminati() {
        return illuminati;
    }
    public void setIlluminati(IlluminatiCard monster) {
        this.illuminati = illuminati;
    }
    public IlluminatiButton() {
        this.setVisible(true);
    }
    public IlluminatiButton(IlluminatiCard illuminati){
        this.setVisible(true);
        this.setName(illuminati.getName());

    }
    public IlluminatiButton(String name){

        super(name);
    }
}
