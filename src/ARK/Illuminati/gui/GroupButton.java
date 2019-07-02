package ARK.Illuminati.gui;

import ARK.Illuminati.cards.GroupCard;

import javax.swing.*;

/**
 * This class creates a group button for Illuminati
 */
public class GroupButton extends JButton {
    private GroupCard group;

    /**
     * getter for group for interface
     * @return
     */
    public GroupCard getGroup() {
        return group;
    }

    /**
     * setter for group in graphics
     * @param group to be set
     */
    public void setGroup(GroupCard group) {
        this.group = group;
    }

    /**
     * Sets visiblity of group button
     */
    public GroupButton() {
        this.setVisible(true);
    }

    /**
     * Overloaded constructor for Group button
     * @param group
     */
    public GroupButton(GroupCard group){
        this.setVisible(true);
        this.setName(group.getName());

    }

    /**
     * Group Button
     * @param name string name
     */
    public GroupButton(String name){

        super(name);
    }
}
