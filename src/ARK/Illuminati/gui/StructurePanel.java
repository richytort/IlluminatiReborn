package ARK.Illuminati.gui;

import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StructurePanel extends JPanel {
    private ArrayList<GroupButton> groups ;
    public StructurePanel(Player p ){
        super();
        //////SETS DIMENSIONS OF STRUCTURE PANEL
        setPreferredSize( new Dimension(500 , 100));
        groups = new ArrayList<GroupButton>();
        //////test this, might set the grid format!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!        Play with it
        this.setLayout(new GridLayout(1, 5));
        this.setOpaque(true);
        this.setVisible(true);
        //////might have to play with this too.
        for(int i = 0 ; i < 5 ; i++){
            GroupButton groupButton = new GroupButton();
            groups.add(groupButton);
            groupButton.setBackground(Color.GRAY);
            groupButton.setOpaque(false);
            this.add(groupButton);
        }
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
    public ArrayList<GroupButton> getGroups() {
        return groups;
    }
    public void setGroups(ArrayList<GroupButton> groups) {
        this.groups = groups ;
    }


}
