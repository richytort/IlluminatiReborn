package ARK.Illuminati.gui;

import javax.swing.*;
import java.awt.*;

public class NextActionButton extends JButton {

    public NextActionButton(String string ){
        super(string);
        this.setPreferredSize(new Dimension(214, 53)) ;
        ImageIcon ic = new ImageIcon("NextAction.png");
        this.setIcon(ic);
        this.setOpaque(false);
    }


}
