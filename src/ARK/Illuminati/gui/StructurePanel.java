package ARK.Illuminati.gui;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.IlluminatiCard;
import ARK.Illuminati.cards.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StructurePanel extends JPanel {
    private ArrayList<IlluminatiButton> illuminatiButtons ;
    private ArrayList<GroupButton> groupButtons;

    public ArrayList<IlluminatiButton> getIlluminatiButtons() {
        return illuminatiButtons;
    }

    public void setIlluminatiButtons(ArrayList<IlluminatiButton> illuminatiButtons) {
        this.illuminatiButtons = illuminatiButtons;
    }

    public ArrayList<GroupButton> getGroupButtons() {
        return groupButtons;
    }

    public void setGroupButtons(ArrayList<GroupButton> groupButtons) {
        this.groupButtons = groupButtons;
    }

    public StructurePanel(ArrayList<Card> cards ){
        super();
        illuminatiButtons = new ArrayList<IlluminatiButton>();
        groupButtons = new ArrayList<GroupButton>();



        setPreferredSize( new Dimension(500 , 100));
        for(int i = 0 ; i < 15 ; i++){
            GroupButton groupButton = new GroupButton();
            //groups.add(groupButton);
            groupButton.setBackground(Color.GRAY);
            groupButton.setOpaque(false);
            this.add(groupButton);
        }
        this.setLayout(new GridLayout(3, 5));
        this.setOpaque(true);
        this.setVisible(true);

        for(int i = 0 ; i < cards.size(); i++){
            System.out.println(cards.get(i).getName());
            if(   cards.get(i) instanceof IlluminatiCard ){
                IlluminatiButton temp = new IlluminatiButton( (IlluminatiCard) cards.get(i) );
                illuminatiButtons.add(temp);
                this.add(temp);
                ImageIcon newIcon;
                ImageIcon img = new ImageIcon( cards.get(i).getName()+".png");
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(91, 62, Image.SCALE_SMOOTH);
                newIcon = new ImageIcon(newimg);
                illuminatiButtons.get(i).setIcon(newIcon);
                illuminatiButtons.get(i).setPreferredSize(new Dimension(91,62));
                illuminatiButtons.get(i).revalidate();
                illuminatiButtons.get(i).setOpaque(false);
                illuminatiButtons.get(i).repaint();
            }
            else{
                GroupButton temp = new GroupButton((GroupCard) cards.get(i) );
                groupButtons.add(temp);
                this.add(temp);
            }


        }



        //groups = new ArrayList<GroupButton>();
        //////test this, might set the grid format!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!        Play with it

        //////might have to play with this too.
        /*
        for(int i = 0 ; i < 15 ; i++){
            GroupButton groupButton = new GroupButton();
            //groups.add(groupButton);
            groupButton.setBackground(Color.GRAY);
            groupButton.setOpaque(false);
            this.add(groupButton);
        }
        */
        /******************************************************************************
        for(int i = 0 ; i< p.getField().getGroupsArea().size();i++){
            groups.get(i).setGroup(p.getField().getGroupsArea().get(i));
            groups.get(i).setVisible(true);
            ImageIcon newIcon;
            if(groups.get(i).getGroup().getMode() == Mode.ATTACK) {
                //////////////////////////////////////////////////////FIX////////////////////////////////////////////
                //ImageIcon img = new ImageIcon("Cards Images DataBase/Groups/"+p.getField().getGroupsArea().get(i).getName()+ ".png");
                ImageIcon img = new ImageIcon(); /////////////////////////////////delete when images are updated.
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(62, 91, java.awt.Image.SCALE_SMOOTH);
                newIcon = new ImageIcon(newimg);
            }
            else{
                ////////////////////////////FIX THIS PART ///////////////////////////////////
                ImageIcon img = new ImageIcon("Back.png"); //ImageIcon img = new ImageIcon("Cards Images Database/Card Back  Set.png");
                Image img2 = img.getImage();
                Image newimg = img2.getScaledInstance(91, 62, java.awt.Image.SCALE_SMOOTH);
                newIcon = new ImageIcon(newimg);
            }
            groups.get(i).setIcon(newIcon);
            groups.get(i).setPreferredSize(new Dimension(62, 91));
            groups.get(i).revalidate();
            groups.get(i).setOpaque(false);
            groups.get(i).repaint();
        }

         *************************************************************/

    }



}
