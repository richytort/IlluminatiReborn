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
        if(board.isGameOver()) {
            Object[] options = {"End Game!", "Start New Game"};
            int choice = JOptionPane.showOptionDialog(gui, "GAME Over!,The winner is " + board.getWinner().getName() + "", null, JOptionPane.INFORMATION_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (choice == 0)
                System.exit(0);
            else {
                try {
                    GUI.main(null);
                    gui.setVisible(false);
                    gui.audioClip.close();
                } catch (IOException | UnexpectedFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


            try {
                GUI.main(null);
                gui.setVisible(false);
                gui.audioClip.close();
            } catch (IOException | UnexpectedFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

       gui.getDeck().setText("" + Board.deck.size() );
        //gui.setText("Income: "+gui.getP1().getTotalIncome());
        //gui.setText("Income: "+gui.getP2().getTotalIncome());
        gui.getCurrAction().setText(Card.getBoard().getActivePlayer().getField().getPhase().name());
        ///////Seems that this area may need to have some work done.

        gui.getPanel1().removeAll();
        gui.getPanel2().removeAll();

        StructurePanel structureAreaP1 = new StructurePanel( gui.getP1().getField().cardArea ) ;

        gui.setHandAreaP1(new HandPanel( gui.getP1() ) );
        JScrollPane sp1 = new JScrollPane(gui.getHandAreaP1());
        sp1.setBorder(null);
        sp1.getViewport().setOpaque(false);
        sp1.setPreferredSize(new Dimension(200, 150));
        sp1.setOpaque(false);
        sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        gui.setHand1SP( sp1 );
        gui.getPanel1().add(structureAreaP1, BorderLayout.CENTER);
        gui.getPanel1().add(gui.getHand1SP(),BorderLayout.EAST);


        StructurePanel structureAreaP2 = new StructurePanel( gui.getP2().getField().cardArea ) ;

        gui.setHandAreaP2(new HandPanel( gui.getP2() ) );
        JScrollPane sp2 = new JScrollPane(gui.getHandAreaP2());
        sp2.setBorder(null);
        sp2.getViewport().setOpaque(false);
        sp2.setPreferredSize(new Dimension(200, 150));
        sp2.setOpaque(false);
        sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        gui.setHand2SP( sp2 );
        gui.getPanel2().add(structureAreaP2, BorderLayout.CENTER);
        gui.getPanel2().add(gui.getHand2SP(),BorderLayout.EAST);


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
    public void actionPerformed(ActionEvent click) {
        if(click.getSource() instanceof NextActionButton){
            board.getActivePlayer().endAction();
            //////////////IMPLEMENT WHEN FIELD IS DONE
            gui.getCurrAction().setText("Current Action: "+ Card.getBoard().getActivePlayer().getField().getPhase());
            updateField();
            //addActionListeners();
        }
        if(click.getSource() instanceof EndTurnButton){
            board.getActivePlayer().endTurn();
            updateField();
            //addActionListeners();
        }
        if(click.getSource() instanceof IlluminatiButton) {
            try {
                if (fc == null) {

                    IlluminatiCard illuminati = ((IlluminatiButton) click.getSource()).getIlluminati();
                    //fc = button;

                    if (illuminati.getLocation() == Location.HAND) {
                        fc = (IlluminatiButton) click.getSource();
                        illuminati = ((IlluminatiButton) fc).getIlluminati();
                        //fc = button;
                        ///////////THIS MAY NEED TO BE CHANGED. BETTER YET, will be changed.
                        Object[] options = {"Set", "Cancel"};
                        summonset = JOptionPane.showOptionDialog(gui, "What is your action?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        if (summonset == 1) {
                            fc = null;
                            return;
                        }
                        if (summonset == 0) {
                            Card.getBoard().getActivePlayer().setIlluminati(((IlluminatiButton) fc).getIlluminati());
                        }

                        fc = null ;
                        updateField();
                        return ;

                    }

                }

                else {
                    //do i put it here or on top
                }

            } catch (WrongActionException e) {
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "you can't set or summon a monster in this phase");
            } catch (NoGroupSpaceException e) {
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

             */ catch (DefenseGroupAttackException e) {
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "You Can't Attack in Defense Mode");
            }
        }



    }
}

