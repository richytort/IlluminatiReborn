package ARK.Illuminati.gui;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.IlluminatiCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates the hand panel for the different button
 */
public class HandPanel extends JPanel {
    private ArrayList<GroupButton> groupButtons ;
    private ArrayList<SpecialButton> specialButtons;
    private ArrayList<IlluminatiButton> illuminatiButtons ;

    /**
     * Handpanel for the player
     * @param p player
     */
    public HandPanel(Player p ) {
        super();
        update(p);
    }

    /**
     * getter for hand button
     * @return group buttons
     */
    public ArrayList<GroupButton> getHandButtons(){
        return this.groupButtons;
    }

    /**
     * setter for hand buttons
     * @param hb arraylist of group buttons
     */
    public void setHandButtons (ArrayList<GroupButton> hb ) {
        this.groupButtons = hb ;
    }

    /**
     * Updates the players buttons
     * @param p player
     */
    public void update(Player p){
        this.removeAll();
        this.revalidate();
        groupButtons = new ArrayList<GroupButton>();
        specialButtons = new ArrayList<SpecialButton>();
        illuminatiButtons = new ArrayList<IlluminatiButton>() ;
        //setPreferredSize(new Dimension(500,150));
        ArrayList<Card> hand = p.getField().getHand();
        this.setLayout(new FlowLayout());
        this.setOpaque(false);

        for (int i = 0; i <100; i++) {
            GroupButton b = new GroupButton();
            b.setVisible(false);
            this.add(b);
            groupButtons.add(b);

        }
        for (int i = 0; i < 100; i++) {
            SpecialButton s = new SpecialButton();
            s.setVisible(false);
            this.add(s);
            specialButtons.add(s);
        }

        for(int i = 0 ; i < 100; i++) {
            IlluminatiButton ii = new IlluminatiButton();
            ii.setVisible(false);
            this.add(ii);
            illuminatiButtons.add(ii);
        }

        for(int i = 0; i <hand.size();i++){
            if(hand.get(i) instanceof GroupCard){
                //monsterbuttons.get(i).setText(hand.get(i).getName());
                groupButtons.get(i).setGroup((GroupCard) hand.get(i));
                groupButtons.get(i).setVisible(true);
                ImageIcon img = new ImageIcon(hand.get(i).getName()+ ".png");
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(146, 100,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                groupButtons.get(i).setIcon(newIcon);
                groupButtons.get(i).setPreferredSize(new Dimension(146,100));
                groupButtons.get(i).revalidate();
                groupButtons.get(i).setOpaque(false);
                groupButtons.get(i).repaint();
                //monsterbuttons.add(mb);
                //this.add(mb);
            }
            else if(hand.get(i) instanceof IlluminatiCard){
                //monsterbuttons.get(i).setText(hand.get(i).getName());
                illuminatiButtons.get(i).setIlluminati((IlluminatiCard) hand.get(i));
                illuminatiButtons.get(i).setVisible(true);
                ImageIcon img = new ImageIcon(hand.get(i).getName()+".png");
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(146, 100,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                illuminatiButtons.get(i).setIcon(newIcon);
                illuminatiButtons.get(i).setPreferredSize(new Dimension(146,100));
                illuminatiButtons.get(i).revalidate();
                illuminatiButtons.get(i).setOpaque(false);
                illuminatiButtons.get(i).repaint();
            }
            else{
                //spellbuttons.get(i).setText(hand.get(i).getName());
                specialButtons.get(i).setSpecial((SpecialCard) hand.get(i));
                specialButtons.get(i).setVisible(true);
                ImageIcon img = new ImageIcon(hand.get(i).getName()+".png");
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(146, 100,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                specialButtons.get(i).setIcon(newIcon);
                specialButtons.get(i).setPreferredSize(new Dimension(146,100));
                specialButtons.get(i).revalidate();
                specialButtons.get(i).setOpaque(false);
                specialButtons.get(i).repaint();
            }
        }
    }

    /**
     * getter for Group Buttons
     * @return groupbuttons
     */
    public ArrayList<GroupButton> getGroupButtons() {
        return groupButtons;
    }

    /**
     * setter for GroupButtons
     * @param groupButtons
     */
    public void setGroupButtons(ArrayList<GroupButton> groupButtons) {
        this.groupButtons = groupButtons;
    }

    /**
     * getter for special buttons
     * @return special buttons
     */
    public ArrayList<SpecialButton> getSpecialButtons() {
        return specialButtons;
    }

    /**
     * setter for special buttons
     * @param specialButtons to be set
     */
    public void setSpecialButtons(ArrayList<SpecialButton> specialButtons) {
        this.specialButtons = specialButtons;
    }

    /**
     * getter for Illuminati Buttons
     * @return Illuminati Buttons
     */
    public ArrayList<IlluminatiButton> getIlluminatiButtons(){
        return illuminatiButtons;
    }

    /**
     * setter for Illuminati Buttons
     * @param illuminatiButtons set
     */
    public void setIlluminatiButton(ArrayList<IlluminatiButton> illuminatiButtons ){
        this.illuminatiButtons = illuminatiButtons ;
    }

}
