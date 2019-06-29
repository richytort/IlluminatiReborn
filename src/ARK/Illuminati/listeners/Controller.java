package ARK.Illuminati.listeners;

import ARK.Illuminati.board.Board;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.exceptions.UnexpectedFormatException;
import ARK.Illuminati.gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

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
        //addActionListeners() ;
        gui.getEndTurn().addActionListener(this);
        gui.getNextAction().addActionListener(this);
    }

    public void addActionListener(){
        /////CHECK IF THIS IS RIGHT////////////////////////////////////////////////////////////
        ArrayList<GroupButton> structureP1 = this.gui.getStructureAreaP1().getGroups();
        ArrayList<GroupButton> structureP2 = this.gui.getStructureAreaP2().getGroups();
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


        gui.getDeck().setText("" + getP1().getField().getDeck().getDeck().size());
        gui.getCurrAction().setText(Card.getBoard().getActivePlayer().getField().getPhase().name());

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

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
