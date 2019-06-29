package ARK.Illuminati.gui;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UncontrolledPanel extends JPanel {
    private ArrayList<GroupButton> groupButtons;

    public UncontrolledPanel( ){
        super();
        //maybe make like in hand but instead, the uncontrolled area hand.
        update(); //update(p);
    }

    public ArrayList<GroupButton> getGroupButtons(){
        return this.groupButtons ;
    }

    public void setGroupButtons(ArrayList<GroupButton> groupButtons ){
        this.groupButtons = groupButtons ;
    }

    public void update(){
        this.removeAll();
        this.revalidate();
        groupButtons = new ArrayList<GroupButton>();
        ArrayList<Card> center = new ArrayList<Card>(); //ArrayList<Card> center = c.getField().getHand(); //set this to end up being a computer. Or no one owning these groups. CENter field.
        this.setLayout(new FlowLayout());

        for(int i = 0 ; i < 20 ; i++) {
            GroupButton b = new GroupButton();
            b.setVisible(false);
            this.add(b);
            groupButtons.add(b);
        }

        for(int i = 0; i <center.size();i++){
            if(center.get(i) instanceof GroupCard){
                //monsterbuttons.get(i).setText(hand.get(i).getName());
                groupButtons.get(i).setGroup((GroupCard) center.get(i));
                groupButtons.get(i).setVisible(true);
                ImageIcon img = new ImageIcon(center.get(i).getName()+".png"); //ImageIcon img = new ImageIcon("Cards Images Database/Monsters/"+center.get(i).getName()+".png");
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
/*
            else{
                //spellbuttons.get(i).setText(hand.get(i).getName());
                specialButton.get(i).setSpell((SpellCard) hand.get(i));
                spellbuttons.get(i).setVisible(true);
                ImageIcon img = new ImageIcon("Cards Images Database/Spells/"+hand.get(i).getName()+".png");
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(100, 146,  java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                spellbuttons.get(i).setIcon(newIcon);
                spellbuttons.get(i).setPreferredSize(new Dimension(100,146));
                spellbuttons.get(i).revalidate();
                spellbuttons.get(i).setOpaque(false);
                spellbuttons.get(i).repaint();
            }

*/
        }
    }



}
