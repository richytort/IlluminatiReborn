package GUI;

import javax.swing.*;
import java.awt.*;

public class EndTurnButtom extends JButton {
    public EndTurnButtom(String string){
        super(string);
        this.setPreferredSize((new Dimension(215,55)));
        ImageIcon ic = new ImageIcon("endturn.png");
        this.setIcon(ic);
        this.setOpaque(false);
        this.repaint();
        this.revalidate();

    }
}
