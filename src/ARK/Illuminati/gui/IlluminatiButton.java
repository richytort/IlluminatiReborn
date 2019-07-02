package ARK.Illuminati.gui;

import ARK.Illuminati.cards.IlluminatiCard;
import javax.swing.JButton;

/**
 * This method sets the settings for the Illuminati Buttons
 */
public class IlluminatiButton extends JButton {

    private IlluminatiCard illuminati;

    /**
     * getter for Illuinati cards
     * @return Illuminati
     */
    public IlluminatiCard getIlluminati() {
        return illuminati;
    }

    /**
     * setter for Illuminati buttons
     * @param illuminati
     */
    public void setIlluminati(IlluminatiCard illuminati) {
        this.illuminati = illuminati;
    }

    /**
     * default constructor for Illuminati Button
     */
    public IlluminatiButton() {
        this.setVisible(true);
    }

    /**
     * Overloaded constructor Illuminati Button
     * @param illuminati  Illuminati Card
     */
    public IlluminatiButton(IlluminatiCard illuminati){
        this.setVisible(true);
        this.setName(illuminati.getName());
    }

    /**
     * Illuminati Button
     * @param name string name
     */
    public IlluminatiButton(String name){

        super(name);
    }
}
