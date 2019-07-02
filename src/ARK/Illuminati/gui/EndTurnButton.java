package ARK.Illuminati.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates an endturn button for the game
 */
public class EndTurnButton extends JButton {
    /**
     * End turn button created with settings
     * @param string string for button
     */
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
