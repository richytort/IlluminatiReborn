package ARK.Illuminati.gui;

import javax.swing.*;
import java.awt.*;

public class EndTurnButton extends JButton {

    public EndTurnButton(String string){
        super(string);
        this.setPreferredSize(new Dimension(214, 53));
        ImageIcon ic = new ImageIcon("EndTurn.png");
        this.setIcon(ic);
        this.setOpaque(false);
        this.repaint();
        this.revalidate();
    }
}
