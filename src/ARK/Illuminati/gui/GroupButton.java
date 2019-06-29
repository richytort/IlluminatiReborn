package ARK.Illuminati.gui;

import ARK.Illuminati.cards.GroupCard;

import javax.swing.*;

public class GroupButton extends JButton {

    private GroupCard group ;

    public GroupButton(){
        this.setVisible(true);
    }

    public GroupButton(String name){
        super(name);
    }

    public GroupButton(GroupCard group){
        this.setVisible(true);
        this.setName(group.getName());
    }

    public GroupCard getGroup(){
        return group;
    }

    public void setGroup(GroupCard group){

        this.group = group ;
    }


}
