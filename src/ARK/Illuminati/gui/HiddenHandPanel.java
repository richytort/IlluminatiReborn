
package ARK.Illuminati.gui;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This methods deals wth hidden handbuttons for the players
 */
public class HiddenHandPanel extends JPanel {
    //instance variables
    private ArrayList<CardButton> handButtons;

    /**
     * HIdden Hand Panel constructor
     * @param p the player
     */
    public HiddenHandPanel( Player p ){
        super();
        update(p);
    }

    /**
     * getter for Hand Buttons
     * @return the hand buttons
     */
    public ArrayList<CardButton> getHandButtons(){
        return this.handButtons;
    }

    /**
     * setter for hand buttons
     * @param hb arraylist of cardbuttons
     */
    public void setHandButtons(ArrayList<CardButton> hb) {
        this.handButtons = hb;
    }

    /**
     * Updates the players button settings
     * @param p player
     */
    public void update(Player p ) {
        this.removeAll();
        this.revalidate();
        handButtons = new ArrayList<CardButton>(20);
        //setPreferredSize(new Dimension(500, 150));
        /////////////////uncomment when field class is created.
        //ArrayList<Card> hand = p.getField().getHand() ;
        this.setLayout(new FlowLayout());
        //////////////////////////////////Complete this section//////////////////////
        ImageIcon img = new ImageIcon("Cards Images Database/Card Back.png");
        Image img2 = img.getImage();
        Image newimg = img2.getScaledInstance( 100, 146, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        for(int i = 0; i< 20; i++){
            CardButton b = new CardButton();
            b.setIcon(newIcon);
            b.setPreferredSize(new Dimension(100, 146));
            handButtons.add(b);
            this.add(b);
            b.setVisible(false);
        }
        //for(int i = 0 ; i < hand.size() ; i++ ){
          //  handButtons.get(i).setVisible(true);
        //}
    }


}

