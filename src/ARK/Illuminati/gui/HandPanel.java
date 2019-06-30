package ARK.Illuminati.gui;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HandPanel extends JPanel {
    private ArrayList<GroupButton> groupButtons ;
    private ArrayList<SpecialButton> specialButtons;

    public HandPanel( Player p ) {
        super();
        update(p);
    }

    public ArrayList<GroupButton> getHandButtons(){
        return this.groupButtons;
    }

    public void setHandButtons (ArrayList<GroupButton> hb ) {
        this.groupButtons = hb ;
    }

    public void update(Player p){
        this.removeAll();
        this.revalidate();
        groupButtons = new ArrayList<GroupButton>(20);
        specialButtons = new ArrayList<SpecialButton>(20);
        //setPreferredSize(new Dimension(500,150));
        ArrayList<Card> hand = p.getField().getHand();
        this.setLayout(new FlowLayout());
        this.setOpaque(false);

        for (int i = 0; i <20; i++) {
            GroupButton b = new GroupButton();
            b.setVisible(false);
            this.add(b);
            groupButtons.add(b);

        }
        for (int i = 0; i < 20; i++) {
            SpecialButton s = new SpecialButton();
            s.setVisible(false);
            this.add(s);
            specialButtons.add(s);
        }

        for(int i = 0; i <hand.size();i++){
            if(hand.get(i) instanceof GroupCard){
                //monsterbuttons.get(i).setText(hand.get(i).getName());
                groupButtons.get(i).setGroup((GroupCard) hand.get(i));
                groupButtons.get(i).setVisible(true);
                ImageIcon img = new ImageIcon(hand.get(i).getName()+ ".png");
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(100, 146,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                groupButtons.get(i).setIcon(newIcon);
                groupButtons.get(i).setPreferredSize(new Dimension(100,146));
                groupButtons.get(i).revalidate();
                groupButtons.get(i).setOpaque(false);
                groupButtons.get(i).repaint();
                //monsterbuttons.add(mb);
                //this.add(mb);
            }
            else{
                //spellbuttons.get(i).setText(hand.get(i).getName());
                specialButtons.get(i).setSpecial((SpecialCard) hand.get(i));
                specialButtons.get(i).setVisible(true);
                ImageIcon img = new ImageIcon(hand.get(i).getName()+".png");
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(100, 146,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                specialButtons.get(i).setIcon(newIcon);
                specialButtons.get(i).setPreferredSize(new Dimension(100,146));
                specialButtons.get(i).revalidate();
                specialButtons.get(i).setOpaque(false);
                specialButtons.get(i).repaint();
            }
        }
    }
    public ArrayList<GroupButton> getGroupButtons() {
        return groupButtons;
    }
    public void setMonsterbuttons(ArrayList<GroupButton> groupButtons) {
        this.groupButtons = groupButtons;
    }
    public ArrayList<SpecialButton> getSpellbuttons() {
        return specialButtons;
    }
    public void setSpecialButtons(ArrayList<SpecialButton> specialButtons) {
        this.specialButtons = specialButtons;
    }

}
