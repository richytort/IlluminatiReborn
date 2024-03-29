package ARK.Illuminati.gui;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Special Panel creates the special panel for the player
 */
public class SpecialPanel extends JPanel {
    private ArrayList<JButton> specials;
    private Player p;

    /**
     * special panel constructor for player
     * @param p
     */
    public SpecialPanel(Player p) {
        super();
        setPreferredSize(new Dimension(500, 100));
        update(p);
    }

    /**
     * getter for specials
     * @return special
     */
    public ArrayList<JButton> getSpecials() {
        return specials;
    }

    /**
     * setter for specials
     * @param specials specials
     */
    public void setSpecials(ArrayList<JButton> specials) {
        this.specials = specials;
    }

    /**
     * updates the players settings
     * @param p player
     */
    public void update(Player p) {
        specials = new ArrayList<JButton>();
        ///////Sets the grid layout for the specials........
        this.setLayout(new GridLayout(1, 5));
        this.setOpaque(false);
        this.setVisible(true);

        /********************************FINISH WHEN FIELD IS DONE!!!!!!!!!!
        for (int i = 0; i < p.getField().getSpecialArea().size(); i++) {
            if ( p.getField().getSpellArea().get(i) != null) {
                SpecialButton specialButton = new SpecialButton();
                ImageIcon img = new ImageIcon("Back.png");
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(62, 91, java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                specialButton.setIcon(newIcon);
                specialButton.setSpecial(p.getField().getSpellArea().get(i));
                specialButton.setOpaque(false);
                specials.add(specialButton);
                this.add(specialButton);
            }

        }
        ************/
    }
}
