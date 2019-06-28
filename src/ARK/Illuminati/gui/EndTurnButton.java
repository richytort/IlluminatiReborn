package ARK.Illuminati.gui;

import javax.swing.*;
import java.awt.*;

public class EndTurnButton extends JButton {

    public EndTurnButton(String string){
        super(string);
        this.setPreferredSize(new Dimension(214, 53));
        //////////////////CREATE ICON FOR 'ic'//////////////////////////////////////////
        ImageIcon ic = new ImageIcon(" ");
        this.setIcon(ic);
        this.setOpaque(false);
        this.repaint();
        this.revalidate();
    }
}
