package ARK.Illuminati.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class deals with the next action button
 */
public class NextActionButton extends JButton {

    /**
     * Next action button method
     * @param string specified string for the next action button
     */
    public NextActionButton(String string ){
        super(string);
        this.setPreferredSize(new Dimension(214, 53)) ;
        ImageIcon ic = new ImageIcon("NextAction.png");
        this.setIcon(ic);
        this.setOpaque(false);
    }


}
