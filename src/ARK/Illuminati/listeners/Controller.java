package ARK.Illuminati.listeners;

import ARK.Illuminati.board.Board;
import ARK.Illuminati.board.player.Phase;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.IlluminatiCard;
import ARK.Illuminati.cards.Location;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.exceptions.DefenseGroupAttackException;
import ARK.Illuminati.exceptions.NoGroupSpaceException;
import ARK.Illuminati.exceptions.UnexpectedFormatException;
import ARK.Illuminati.exceptions.WrongActionException;
import ARK.Illuminati.gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Image.SCALE_SMOOTH;

public class Controller implements ActionListener, MouseListener {

    private JButton fc;
    private JButton sc ;
    private JButton tc ;
    private Board board ;
    private GUI gui ;
    private int summonset ;

    public Controller(Board board, GUI gui){
        this.board = board ;
        this.gui = gui ;
        addActionListeners() ;
        gui.getEndTurn().addActionListener(this);
        gui.getNextAction().addActionListener(this);
    }

    public void addActionListeners(){
        /////CHECK IF THIS IS RIGHT////////////////////////////////////////////////////////////
        ArrayList<GroupButton> structureP1 = this.gui.getStructureAreaP1().getGroups();
        ArrayList<GroupButton> structureP2 = this.gui.getStructureAreaP2().getGroups();
        ArrayList<GroupButton> handP1 = this.gui.getHandAreaP1().getGroupButtons();
        ArrayList<GroupButton> handP2 = this.gui.getHandAreaP2().getGroupButtons();
        ArrayList<GroupButton> uncontrolledGroup = this.gui.getUncontrolledArea().getGroupButtons();
        ArrayList<IlluminatiButton> handIlluminatiP1 = this.gui.getHandAreaP1().getIlluminatiButtons();
        ArrayList<IlluminatiButton> handIlluminatiP2 = this.gui.getHandAreaP2().getIlluminatiButtons();
        ArrayList<SpecialButton> handSpecialP1 = this.gui.getHandAreaP1().getSpecialButtons();
        ArrayList<SpecialButton> handSpecialP2 = this.gui.getHandAreaP2().getSpecialButtons();

        ///////not too sure of line 37. Investigate later.
        ArrayList<JButton> specialsP1 = this.gui.getSpecialAreaP1().getSpecials();
        ArrayList<JButton> specialsP2 = this.gui.getSpecialAreaP2().getSpecials();



        ///Alot in this area that I felt didn't need to be implemented. Check back later to see if missing something.

        for(int i = 0 ; i < structureP1.size() ; i++){
            structureP1.get(i).addActionListener(this);
            structureP1.get(i).addMouseListener(this);
        }

        for(int i = 0 ; i < structureP2.size(); i++){
            structureP2.get(i).addActionListener(this);
            structureP2.get(i).addMouseListener(this);
        }

        for(int i = 0 ; i < specialsP1.size(); i++){
            specialsP1.get(i).addActionListener(this );
            specialsP1.get(i).addMouseListener(this);
        }

        for(int i = 0 ; i < specialsP2.size();i++){
            specialsP2.get(i).addActionListener(this);
            specialsP2.get(i).addMouseListener(this);
        }

        for(int i = 0 ; i < handP1.size() ;i++){
            handP1.get(i).addActionListener(this);
            handP1.get(i).addMouseListener(this);
        }

        for(int i = 0 ; i < handP2.size();i++){
            handP2.get(i).addActionListener(this);
            handP2.get(i).addMouseListener(this);
        }

        for(int i = 0 ; i < handIlluminatiP1.size(); i++){
            handIlluminatiP1.get(i).addActionListener(this);
            handIlluminatiP1.get(i).addMouseListener(this) ;
        }

        for(int i = 0 ; i < handIlluminatiP2.size(); i++){
            handIlluminatiP2.get(i).addActionListener(this);
            handIlluminatiP2.get(i).addMouseListener(this) ;
        }

        for(int i = 0 ; i < handSpecialP1.size(); i++){
            handSpecialP1.get(i).addActionListener(this);
            handSpecialP1.get(i).addMouseListener(this );
        }

        for(int i = 0 ; i < handSpecialP2.size(); i++){
            handSpecialP2.get(i).addActionListener(this);
            handSpecialP2.get(i).addMouseListener(this );
        }

        for(int i = 0 ; i < uncontrolledGroup.size(); i++){
            uncontrolledGroup.get(i).addActionListener(this);
            uncontrolledGroup.get(i).addMouseListener(this);
        }


    }

    private void updateField(){
        Object[] options = {"End Game!","Start New Game"};
        int choice = JOptionPane.showOptionDialog(gui, "GAME Over!,The winner is "+board.getWinner().getName()+"",null, JOptionPane.INFORMATION_MESSAGE, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);

        if(choice==0)
            System.exit(0);

        else{
            try{
                GUI.main(null);
                gui.setVisible(false);
                gui.audioClip.close();
            } catch (IOException | UnexpectedFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


       // gui.getDeck().setText("" + gui.getP1().getField().getDeck().getDeck().size());
        //gui.setText("Income: "+gui.getP1().getTotalIncome());
        //gui.setText("Income: "+gui.getP2().getTotalIncome());
        //gui.getCurrAction().setText(Card.getBoard().getActivePlayer().getField().getPhase().name());

        ///////Seems that this area may need to have some work done.
        if (gui.getP1() == board.getActivePlayer() ) {
            gui.getSp1().remove(gui.getP1hid());
            gui.getSp1().remove(gui.getStructureAreaP1());
            gui.getPanel1().remove(gui.getSp1());
            gui.setStructureAreaP1(new StructurePanel(gui.getP1()));
            JScrollPane sp1 = new JScrollPane(gui.getStructureAreaP1());
            sp1.setPreferredSize(new Dimension(500, 150));
            sp1.setBorder(null);
            sp1.getViewport().setOpaque(false);
            sp1.setOpaque(false);
            sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            gui.setSp1(sp1);
            gui.getPanel1().add(gui.getSp1(),BorderLayout.SOUTH);
            gui.revalidate();
        }
        else{
            gui.getSp1().remove(gui.getP1hid());
            gui.getSp1().remove(gui.getStructureAreaP1());
            gui.getPanel1().remove(gui.getSp1());
            gui.setP1hid(new HiddenHandPanel(gui.getP1()));
            JScrollPane sp1 = new JScrollPane(gui.getP1hid());
            sp1.setBorder(null);
            sp1.getViewport().setOpaque(false);
            sp1.setPreferredSize(new Dimension(500,150));
            sp1.setOpaque(false);
            sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            gui.setSp1(sp1);
            gui.getPanel1().add(gui.getSp1(),BorderLayout.SOUTH);
            gui.revalidate();
        }

        if(gui.getP2()==board.getActivePlayer()){
            gui.getSp2().remove(gui.getP2hid());
            gui.getSp2().remove(gui.getStructureAreaP2());
            gui.getPanel2().remove(gui.getSp2());
            gui.setStructureAreaP2(new StructurePanel(gui.getP2()));
            JScrollPane sp2 = new JScrollPane(gui.getStructureAreaP2());
            sp2.setBorder(null);
            sp2.getViewport().setOpaque(false);
            sp2.setPreferredSize(new Dimension(500,150));
            sp2.setOpaque(false);
            sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            gui.setSp2(sp2);
            gui.getPanel2().add(gui.getSp2(),BorderLayout.NORTH);
            gui.revalidate();
        }
        else{
            gui.getSp2().remove(gui.getP2hid());
            gui.getSp2().remove(gui.getStructureAreaP2());
            gui.getPanel2().remove(gui.getSp2());
            gui.setP2hid(new HiddenHandPanel(gui.getP2()));
            JScrollPane sp2 = new JScrollPane(gui.getP2hid());
            sp2.setBorder(null);
            sp2.getViewport().setOpaque(false);
            sp2.setPreferredSize(new Dimension(500,150));
            sp2.setOpaque(false);
            sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            gui.setSp2(sp2);
            gui.getPanel2().add(gui.getSp2(),BorderLayout.NORTH);
            gui.revalidate();
        }

        gui.getPanel1().remove(gui.getStructureAreaP1());
        gui.setStructureAreaP1(new StructurePanel(gui.getP1()));
        gui.getPanel1().add(gui.getStructureAreaP1(),BorderLayout.NORTH);


        gui.getPanel2().remove(gui.getStructureAreaP2());
        gui.setStructureAreaP2(new StructurePanel(gui.getP2()));
        gui.getPanel2().add(gui.getStructureAreaP2(),BorderLayout.SOUTH);



        gui.getPanel1().remove(gui.getSpecialAreaP1());
        gui.setSpecialAreaP1(new SpecialPanel(gui.getP1()));
        gui.getPanel1().add(gui.getSpecialAreaP1(),BorderLayout.CENTER);



        gui.getPanel2().remove(gui.getSpecialAreaP2());
        gui.setSpecialAreaP2(new SpecialPanel(gui.getP2()));
        gui.getPanel2().add(gui.getSpecialAreaP2(),BorderLayout.CENTER);

        ////////////THIS WILL NEED SOME WORK DONE.....OR maybe it won't. Since this is the graveyard. Investigate.

        /*
        if(gui.getP1().getField().getGraveyard().size()>0){
            String url;
            if(gui.getP1().getField().getGraveyard().get(gui.getP1().getField().getGraveyard().size()-1) instanceof GroupCard){
                url = "Cards Images Database/Monsters/"+gui.getP1().getField().getGraveyard().get(gui.getP1().getField().getGraveyard().size()-1).getName()+".png";
            }else{
                url = "Cards Images Database/Spells/"+gui.getP1().getField().getGraveyard().get(gui.getP1().getField().getGraveyard().size()-1).getName()+".png";
            }
            ImageIcon img = new ImageIcon(url);
            Image img2 = img.getImage();
            Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);
            gui.getGrave().setIcon(newIcon);
        }
        if(gui.getP2().getField().getGraveyard().size()>0){
            String url;
            if(gui.getP2().getField().getGraveyard().get(gui.getP2().getField().getGraveyard().size()-1) instanceof GroupCard){
                url = "Cards Images Database/Monsters/"+gui.getP2().getField().getGraveyard().get(gui.getP2().getField().getGraveyard().size()-1).getName()+".png";
            }else{
                url = "Cards Images Database/Spells/"+gui.getP2().getField().getGraveyard().get(gui.getP2().getField().getGraveyard().size()-1).getName()+".png";
            }
            ImageIcon img = new ImageIcon(url);
            Image img2 = img.getImage();
            Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(newimg);
            gui.getGrave().setIcon(newIcon);
        }
        */
        addActionListeners();
        //gui.getHandp1().update(gui.getP1());
        gui.revalidate();
    }

    public void seticons(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }



    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Entering");
        if(e.getSource() instanceof GroupButton){

            GroupButton b = (GroupButton) e.getSource();
            GroupCard c = b.getGroup();
            if(c!=null){
                String url = c.getName()+".png";

                ImageIcon img = new ImageIcon(url);
                Image img2 = img.getImage();
                img = new ImageIcon(img2.getScaledInstance(338, 200, SCALE_SMOOTH));
                gui.getDescription().setIcon(img);
                gui.getDescription().revalidate();
                gui.revalidate();
            }
        }
        if(e.getSource() instanceof SpecialButton){
            SpecialButton b = (SpecialButton) e.getSource();
            SpecialCard c = b.getSpecial();
            if(c!=null){
                String url = c.getName()+".png";

                ImageIcon img = new ImageIcon(url);
                Image img2 = img.getImage();
                img = new ImageIcon(img2.getScaledInstance(338, 200, SCALE_SMOOTH));
                gui.getDescription().setIcon(img);
                gui.getDescription().revalidate();
                gui.revalidate();
            }
        }

        if(e.getSource() instanceof IlluminatiButton){
            IlluminatiButton b = (IlluminatiButton) e.getSource();
            IlluminatiCard c = b.getIlluminati();
            if(c!=null){
                String url = c.getName()+".png";

                ImageIcon img = new ImageIcon(url);
                Image img2 = img.getImage();
                img = new ImageIcon(img2.getScaledInstance(338, 200, SCALE_SMOOTH));
                gui.getDescription().setIcon(img);
                gui.getDescription().revalidate();
                gui.revalidate();
            }
        }

        if(e.getSource() instanceof CardButton){



            ImageIcon img = new ImageIcon("RegBack.png");
            gui.getDescription().setIcon(img);
            gui.getDescription().revalidate();
            gui.revalidate();
        }





    }



    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource() instanceof NextActionButton){
            board.getActivePlayer().endAction();
            //////////////IMPLEMENT WHEN FIELD IS DONE
            gui.getCurrAction().setText("Current Action: "+ Card.getBoard().getActivePlayer().getField().getPhase());
            updateField();
            //addActionListeners();
        }
        if(arg0.getSource() instanceof EndTurnButton){
            board.getActivePlayer().endTurn();
            updateField();
            //addActionListeners();
        }
        //need to do it for ILLUMINATI
        if(arg0.getSource() instanceof GroupButton){

            try{
                if(fc==null) {

                    GroupCard group = ((GroupButton) arg0.getSource()).getGroup();
                    //fc = button;

                    if (group.getLocation() == Location.HAND) {
                        fc = (GroupButton) arg0.getSource();
                        group = ((GroupButton) fc).getGroup();
                        //fc = button;
                        ///////////THIS MAY NEED TO BE CHANGED. BETTER YET, will be changed.
                        Object[] options = {"Set", "Cancel"};
                        summonset = JOptionPane.showOptionDialog(gui, "What is your action?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                        if (summonset == 1) {
                            fc = null;
                            return;
                        }
                        if (summonset == 0) {
                            Card.getBoard().getActivePlayer().setGroup(group);
                            fc = null;
                            updateField();
                            return;
                        }
                    } else {
                        if (board.getActivePlayer().getField().getPhase() != Phase.ACTION1 || board.getActivePlayer().getField().getPhase() != Phase.ACTION2) {
                            Object[] options2 = {"OK", "Cancel"};
                            int y = JOptionPane.showOptionDialog(gui, "Change Card's Mode ?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[1]);
                            if (y == 0) {
                                board.getActivePlayer().switchCardModeG(group);
                                updateField();
                                fc = null;
                                sc = null;
                                tc = null;
                            }

                        } else {
                            fc = (GroupButton) arg0.getSource();
                            group = ((GroupButton) fc).getGroup();
                            Object[] options2 = {"Free Action", "Regular Action", "Passing", "Cancel"};
                            int y = JOptionPane.showOptionDialog(gui, "What is your Action?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[1]);
                            if (y == 3) {
                                fc = null;
                                sc = null;
                                tc = null;
                                return;
                            }
                            if (y == 0) {
                                //havent added aid a group or use special card because they have not been implemented
                                Object[] options3 = {"Drop a Group", "Give Away a Special Card", "Cancel"};
                                int options = JOptionPane.showOptionDialog(gui, "What is your Action?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[1]);
                                if (options == 2) {
                                    fc = null;
                                    sc = null;
                                    tc = null;
                                    return;
                                }
                                if (options == 0) {
                                    board.getActivePlayer().dropAgroup(((GroupButton) fc).getGroup());
                                    fc = null;
                                    updateField();
                                    return;
                                }
                                if (options == 1) {
                                    //think about implementation how to select special card
                                }
                            }

                            if (y == 1) {
                                Object[] options3 = {"Attack a Group", "Transfer Money", "Move a Group", "Give a Group Away", "Cancel"};
                                int optionsRegular = JOptionPane.showOptionDialog(gui, "What is your Action?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[1]);
                                if (optionsRegular == 4) {
                                    fc = null;
                                    sc = null;
                                    tc = null;
                                    return;
                                }
                                if (optionsRegular == 0) {
                                    Object[] options4 = {"Attack to Control", "Attack To Neutralize", "Attack To Destroy", "Cancel"};
                                    int attacks = JOptionPane.showOptionDialog(gui, "What is your Action?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options4, options4[1]);
                                    if(attacks==3){
                                        fc = null;
                                        sc = null;
                                        tc = null;
                                        return;
                                    }if(attacks==0){
                                        board.getActivePlayer().getField().declareAttackToControlG(group,((GroupButton)fc).getGroup());
                                        fc = null;
                                        updateField();
                                        return;
                                    }if(attacks==1){
                                        board.getActivePlayer().getField().declareAttackToNeutralizeG(group,((GroupButton)fc).getGroup());
                                        fc = null;
                                        updateField();
                                        return;
                                    }if(attacks==2){
                                        board.getActivePlayer().getField().declareAttackToDestroyG(group,((GroupButton)fc).getGroup());
                                        fc = null;
                                        updateField();
                                        return;
                                    }
                                    JOptionPane.showMessageDialog(gui, "Choose what card to attack");
                                }
                                //WHAT DO I DOOO????
//                                JOptionPane.showMessageDialog(gui, "Choose what card to attack");
//                                return;
                                if (optionsRegular == 1) {
                                    Object [] incomeO = {1,2,3,5,10,20,50};
                                    int k = JOptionPane.showOptionDialog(gui, "How much do you want to transfer ?",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,incomeO, incomeO[1]);
                                    if(incomeO.equals(1)){
                                        board.getActivePlayer().getField().getGroup(group).transferMoney(((GroupButton) fc).getGroup(),1);
                                        fc = null;
                                        updateField();
                                        return;
                                    }
                                    if(incomeO.equals(2)){
                                        board.getActivePlayer().getField().getGroup(group).transferMoney(((GroupButton) fc).getGroup(),2);
                                        fc = null;
                                        updateField();
                                        return;
                                    }
                                    if(incomeO.equals(3)){
                                        board.getActivePlayer().getField().getGroup(group).transferMoney(((GroupButton) fc).getGroup(),3);
                                        fc = null;
                                        updateField();
                                        return;
                                    }
                                    if(incomeO.equals(5)){
                                        board.getActivePlayer().getField().getGroup(group).transferMoney(((GroupButton) fc).getGroup(),5);
                                        fc = null;
                                        updateField();
                                        return;
                                    }
                                    if(incomeO.equals(10)){
                                        board.getActivePlayer().getField().getGroup(group).transferMoney(((GroupButton) fc).getGroup(),10);
                                        fc = null;
                                        updateField();
                                        return;
                                    }
                                    if(incomeO.equals(20)){
                                        board.getActivePlayer().getField().getGroup(group).transferMoney(((GroupButton) fc).getGroup(),20);
                                        fc = null;
                                        updateField();
                                        return;
                                    }
                                    if(incomeO.equals(50)) {
                                        board.getActivePlayer().getField().getGroup(group).transferMoney(((GroupButton) fc).getGroup(), 50);
                                        fc = null;
                                        updateField();
                                        return;
                                    }
                                }
                                if (optionsRegular == 2) {
                                    //need to figure out implementation
                                 //   board.getActivePlayer().moveAGroup(((GroupButton) fc).getGroup());
                                }
                                if (optionsRegular == 3) {
                                    board.getActivePlayer().giveAgroupAway(group);
                                    fc=null;
                                    updateField();
                                    return;
                                }
                            }
                            if (y == 2) {
                                board.getActivePlayer().passing();
                                fc=null;
                                updateField();
                                return;
                            }
                        }
                    }
                }


/*
                else{


                    if(sc==null){

                        if(fc instanceof GroupButton){

                            GroupCard group = ((GroupButton)arg0.getSource()).getGroup();
                            if(board.getActivePlayer().getField().getPhase()!= Phase.BATTLE && !board.getActivePlayer().getField().getMonstersArea().contains(group)){
                                JOptionPane.showMessageDialog(gui, "You must choose monster cards from your field");
                                fc=null;
                                sc=null;
                                return;
                            }
                            if(((GroupButton) fc).getGroup().getLocation()==Location.HAND && group.getLocation()==Location.FIELD
                                    && board.getActivePlayer().getField().getGroupArea().contains(group)
                                    && board.getActivePlayer().getField().getPhase()!= Phase.BATTLE){


                                if(((GroupButton) fc).getGroup().getLevel()<=6){

                                    sc = (GroupButton) arg0.getSource();
                                    group = ((GroupButton) sc).getGroup();

                                    if(group.getLocation()==Location.FIELD){
                                        ArrayList<GroupCard> sacrifices = new ArrayList<GroupCard>();
                                        sacrifices.add(((GroupButton)sc).getGroup());
                                        if(summonset == 0){
                                            Card.getBoard().getActivePlayer().summonGroup(((GroupButton) fc).getGroup(), sacrifices);
                                        }
                                        else{
                                            board.getActivePlayer().setGroup(((GroupButton)fc).getGroup(), sacrifices);
                                        }
                                        updateField();
                                        fc=null;
                                        sc=null;
                                        return;
                                    }
                                }

                                ////////////CHECK THIS!!!!!!!!!!!!!
                                else{
                                    GroupButton button = (GroupButton) arg0.getSource();
                                    GroupCard card = button.getGroup();
                                    sc = button;
                                    Object[] options2 = {"OK","Cancel"};
                                    int y = JOptionPane.showOptionDialog(gui, "Choose the second sacrifice",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
                                    if(y==0){
                                        //updateField();
                                        return;
                                    }
                                    else{
                                        //updateField();
                                        fc = null;
                                        sc = null;
                                        return;
                                    }
                                }
                            }
                            else{
                                sc = (GroupButton) arg0.getSource();
                                group = ((GroupButton) sc).getGroup();
                                board.getActivePlayer().declareAttack(((GroupButton)fc).getGroup(), ((GroupButton)sc).getGroup());
                                fc=null;
                                sc=null;
                                tc = null;
                                updateField();
                                return;
                            }
                        }
                        else{//fc is a spellbutton
                            GroupCard group = ((GroupButton)arg0.getSource()).getGroup();

                            /////////EVENTIALLY CHANGE SPELL NAME/////////////
                            if(((SpecialButton)fc).getSpecial().getName().equalsIgnoreCase("Change Of Heart")){
                                if(!board.getOpponentPlayer().getField().getGroupArea().contains(group)){
                                    JOptionPane.showMessageDialog(gui, "You must choose monster cards from your opponent's field");
                                    fc=null;
                                    sc=null;
                                    return;
                                }
                                sc = (GroupButton)arg0.getSource();
                                GroupCard mons = ((GroupButton)sc).getGroup();
                                board.getActivePlayer().activateSpecial(((SpecialButton)fc).getSpecial(), ((GroupButton)sc).getGroup());
                                fc=null;
                                sc=null;
                                updateField();
                                return;
                            }
                            else{
                                if(!board.getActivePlayer().getField().getGroupArea().contains(group)){
                                    JOptionPane.showMessageDialog(gui, "You must choose monster cards from your field");
                                    fc=null;
                                    sc=null;
                                    return;
                                }
                                sc = (GroupButton)arg0.getSource();
                                GroupCard mons = ((GroupButton)sc).getGroup();
                                board.getActivePlayer().activateSpecial(((SpecialButton)fc).getSpecial(), ((GroupButton)sc).getGroup());
                                fc=null;
                                sc=null;
                                updateField();
                                return;
                            }
                        }
                    }
                    else{
                        if(arg0.getSource() instanceof GroupButton){
                            GroupCard group = ((GroupButton)arg0.getSource()).getGroup();

                            if(fc instanceof GroupButton && ((GroupButton) fc).getGroup().getLocation()==Location.HAND && group.getLocation()==Location.FIELD && board.getActivePlayer().getField().getGroupArea().contains(group)){

                                GroupButton button = (GroupButton) arg0.getSource();
                                group = button.getGroup();
                                tc = button;
                                if(tc==sc){
                                    JOptionPane.showMessageDialog(gui, "you have to choose two different monsters");
                                    fc=null;
                                    sc=null;
                                    tc=null;
                                    return;
                                }
                                ArrayList<GroupCard> sacrifices = new ArrayList<GroupCard>();
                                sacrifices.add(((GroupButton)sc).getGroup());
                                sacrifices.add(((GroupButton)tc).getGroup());
                                if(summonset == 0){
                                    Card.getBoard().getActivePlayer().summonGroup(((GroupButton) fc).getGroup(), sacrifices);
                                }
                                else{
                                    board.getActivePlayer().setGroup(((GroupButton) fc).getGroup(), sacrifices);
                                }
                                updateField();
                                fc=null;
                                sc=null;
                                tc=null;
                                return;

                            }
                            else{

                            }
                        }
                    }

                }

                */




            }
/*
            catch(MultipleMonsterAdditionException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "you can't play more than one card");
            }

 */
            catch(WrongActionException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "you can't set or summon a monster in this phase");
            }
            catch(NoGroupSpaceException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "There is no avaialble space in monster Area");
            }
            /*
            catch(MonsterMultipleAttackException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "You Can Attack Only Once");
            }

             */
            catch(DefenseGroupAttackException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "You Can't Attack in Defense Mode");
            }

        }

        //IM NOT SURE WHAT IT DOES!!!!!!!!!!!!
        if(arg0.getSource() instanceof SpecialButton){
            if(fc instanceof GroupButton){
                fc = null;
                sc=null;
                JOptionPane.showMessageDialog(gui, "you must sacrifice a monster card");
                return;
            }

            ///////eventally change the name or so
            if(fc!=null &&((SpecialButton)fc).getName().equalsIgnoreCase("Change Of Heart")){
                JOptionPane.showMessageDialog(gui, "you must choose a monster card");
                fc = null;
                sc=null;
                return;
            }

            if(fc==null){

                //if(board.getActivePlayer().getField().getSpecialArea().contains(((SpecialButton)arg0.getSource()).getSpecial()) || board.getActivePlayer().getField().getHand().contains(((SpecialButton)arg0.getSource()).getSpecial()))
                //repleace bottom line with top when field is implemented.
                if(true)
                {
                    if(((SpecialButton)arg0.getSource()).getSpecial().getLocation()==Location.HAND){
                        String[] buttons = { "Activate", "Set", "cancel"};

                        int rc = JOptionPane.showOptionDialog(null, "Activate or set spell ?", "SpellCard",
                                JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
                        SpecialButton button = (SpecialButton) arg0.getSource();
                        SpecialCard card = button.getSpecial();
                        fc=button;
                        if(rc==1){
                            Card.getBoard().getActivePlayer().setSpecial(card);
                            fc=null;
                            updateField();
                            return;

                        }
                        if(rc==2){
                            fc=null;
                            return;
                        }
                        else{
                            switch (card.getName()) {
///////////change eventually
                                case "Assassination":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;

                                case "Bribery":
                                    String[] options = { "ok", "cancel"};
                                    int x = JOptionPane.showOptionDialog(null, "Choose the monster you wish to control", "SpellCard",
                                            JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
                                    if(x==0){
                                        fc = button;
                                        return;
                                    }
                                    fc=null;
                                    return;
                                case "Computer Espionage":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Deep Agent":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Interference1":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Interferece2":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Market Manipulation":
                                    String[] options1 = { "ok", "cancel"};

                                    int x1 = JOptionPane.showOptionDialog(null, "Choose the monster you wish to enhance", "SpellCard",
                                            JOptionPane.WARNING_MESSAGE, 0, null, options1, options1[1]);
                                    if(x1==0){
                                        fc = button;
                                        return;
                                    }
                                    fc=null;
                                    return;
                                case "Media Campaign":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Murphy'S Law":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Secrets Man was not Meant to Know":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Senate Investigating Committee":
                                    board.getActivePlayer().activateSpecial(card,null);
                                case"Slush Fund ":
                                    board.getActivePlayer().activateSpecial(card,null);
                                case "Swiss Bank Account":
                                    board.getActivePlayer().activateSpecial(card,null);
                                case"Whispering Campaign":
                                    board.getActivePlayer().activateSpecial(card,null);
                                case "White Collar Crime":
                                    board.getActivePlayer().activateSpecial(card,null);
                                default:
                                    board.getActivePlayer().activateSpecial(((SpecialButton)fc).getSpecial(), null);
                                    updateField();

                            }
                        }
                    }
                    else{
                        String[] buttons = { "ok", "cancel"};

                        int rc = JOptionPane.showOptionDialog(null, "Activate special card ?", "SpecialCard",
                                JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
                        SpecialButton button = (SpecialButton) arg0.getSource();
                        SpecialCard card = button.getSpecial();
                        fc=button;
                        if(rc==1){
                            Card.getBoard().getActivePlayer().setSpecial(card);
                            fc=null;
                            updateField();
                            return;
                        }
                        else{
                            switch (card.getName()) {

                                case "Card Destruction":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Change Of Heart":
                                    String[] options = { "ok", "cancel"};

                                    int x = JOptionPane.showOptionDialog(null, "Choose the monster you wish to control", "SpellCard",
                                            JOptionPane.WARNING_MESSAGE, 0, null, options, options[1]);
                                    if(x==0){
                                        fc = button;
                                        return;
                                    }
                                    fc=null;
                                    return;
                                case "Assassination":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Bribery":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Computer Espionage":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Deep Agent":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Interference1":
                                    String[] options1 = { "ok", "cancel"};

                                    int x1 = JOptionPane.showOptionDialog(null, "Choose the monster you wish to enhance", "SpellCard",
                                            JOptionPane.WARNING_MESSAGE, 0, null, options1, options1[1]);
                                    if(x1==0){
                                        fc = button;
                                        return;
                                    }
                                    fc=null;
                                    return;
                                case "Interference2":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Market Manipulation":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Media Campaign":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Murphy's Law":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;

                                case "Secrets Man Was Not Meant to Know":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;

                                case "Senate Investigating Committee":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Slush Fund":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Swiss Bank Account":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "Whispering Campaign":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                case "White Collar Crime":
                                    board.getActivePlayer().activateSpecial(card, null);
                                    updateField();
                                    fc = null;
                                    return;
                                default:
                                    board.getActivePlayer().activateSpecial(((SpecialButton)fc).getSpecial(), null);
                                    updateField();

                            }
                        }
                    }


                }
            }


        }

    }










}
