package GUI;

import ARK.Illuminati.cards.IlluminatiCard;

import javax.swing.*;

public class IlluminatiButtom extends JButton {
    private IlluminatiCard illuminati;

    public IlluminatiCard getIlluminati(){
        return illuminati;
    }
    public IlluminatiButtom(){
        this.setVisible(true);
    }
    public IlluminatiButtom(IlluminatiCard illuminati){
        this.setVisible(true);
        this.setName(illuminati.getName());
    }
    public IlluminatiButtom(String name){
        super(name);
    }
}
