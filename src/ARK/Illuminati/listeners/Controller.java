package ARK.Illuminati.listeners;

import ARK.Illuminati.board.Board;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;
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

    public void addActionListeners(){
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

        ////////////THIS WILL NEED SOME WORK DONE.....OR maybe it won't. Since this is the graveyard. Investigate.
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
                String url = "Cards Images Database/Monsters/"+c.getName()+".png";

                ImageIcon img = new ImageIcon(url);
                gui.getDescription().setIcon(img);
                gui.getDescription().revalidate();
                gui.revalidate();
            }
        }
        if(e.getSource() instanceof SpecialButton){
            SpecialButton b = (SpecialButton) e.getSource();
            SpecialCard c = b.getSpecial();
            if(c!=null){
                String url = "Cards Images Database/Spells/"+c.getName()+".png";

                ImageIcon img = new ImageIcon(url);
                gui.getDescription().setIcon(img);
                gui.getDescription().revalidate();
                gui.revalidate();
            }
        }
        if(e.getSource() instanceof CardButton){


            /////////MAYBE CHANGE IT BACK IF WE DECIDE TO GO THE DATABASE ROUTE>
            ImageIcon img = new ImageIcon("Back.png"); //ImageIcon img = new ImageIcon("Cards Images Database/Card Back.png");
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
        if(arg0.getSource() instanceof NextPhBut){
            board.getActivePlayer().endPhase();
            gui.getCurrphase().setText("Current Phase: " + Card.getBoard().getActivePlayer().getField().getPhase());
            updatefield();
            //addActionListeners();
        }
        if(arg0.getSource() instanceof EndTurnBut){
            board.getActivePlayer().endTurn();
            updatefield();
            //addActionListeners();
        }
        if(arg0.getSource() instanceof MonsterButton){

            try{
                if(fc==null){

                    MonsterCard monster = ((MonsterButton) arg0.getSource()).getMonster();
                    //fc = button;

                    if(monster.getLocation()==Location.HAND){
                        fc = (MonsterButton) arg0.getSource();
                        monster = ((MonsterButton) fc).getMonster();
                        //fc = button;
                        Object[] options = {"Summon","Set","Cancel"};
                        summonset = JOptionPane.showOptionDialog(gui, "What is your action?",null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[2]);
                        if(summonset==2){
                            fc = null;
                            return;
                        }
                        if(monster.getLevel()<=4){
                            if(summonset==0){
                                Card.getBoard().getActivePlayer().summonMonster(monster);
                            }
                            else{
                                Card.getBoard().getActivePlayer().setMonster(monster);
                            }

                            fc = null;
                            updatefield();
                            return;
                        }
                        else{
                            if(monster.getLevel()<=6){
                                if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()==0){
                                    JOptionPane.showMessageDialog(gui, "No sufficient monsters");
                                    fc = null;
                                    //updatefield();
                                    return;
                                }
                                Object[] options2 = {"OK","Cancel"};
                                int y = JOptionPane.showOptionDialog(gui, "Choose one sacrifice",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
                                if(y==0){
                                    fc = (MonsterButton) arg0.getSource();
                                    //JOptionPane.showMessageDialog(gui, "nnnnjjs");
                                    //updatefield();
                                    return;
                                }
                                else{
                                    fc = null;
                                    //updatefield();
                                    return;
                                }


                            }
                            else{
                                if(Card.getBoard().getActivePlayer().getField().getMonstersArea().size()<=1){
                                    JOptionPane.showMessageDialog(gui, "No sufficient monsters");
                                    fc = null;
                                    updatefield();
                                    return;
                                }
                                Object[] options2 = {"OK","Cancel"};
                                int y = JOptionPane.showOptionDialog(gui, "Choose the first sacrifice",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
                                if(y==0){
                                    //updatefield();
                                    return;
                                }
                                else{
                                    fc = null;
                                    //updatefield();
                                    return;
                                }

                            }


                        }

                    }else{
                        if(board.getActivePlayer().getField().getPhase()!=Phase.BATTLE){
                            Object[] options2 = {"OK","Cancel"};
                            int y = JOptionPane.showOptionDialog(gui, "Change Monster's Mode ?",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
                            if(y==0){
                                board.getActivePlayer().switchMonsterMode(monster);
                                updatefield();
                                fc=null;
                                sc=null;
                                tc=null;
                            }
                        }else{
                            fc = (MonsterButton)arg0.getSource();
                            monster = ((MonsterButton) fc).getMonster();
                            Object[] options2 = {"OK","Cancel"};
                            int y = JOptionPane.showOptionDialog(gui, "Attack ?",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
                            if(y==1){
                                fc=null;
                                sc=null;
                                tc=null;
                                return;
                            }
                            if(board.getOpponentPlayer().getField().getMonstersArea().size()==0){
                                board.getActivePlayer().declareAttack(((MonsterButton)fc).getMonster());
                                fc=null;
                                updatefield();
                                return;
                            }
                            JOptionPane.showMessageDialog(gui, "Choose the monster you wish to attack");
                            return;

                        }
                    }
                }




                else{


                    if(sc==null){

                        if(fc instanceof MonsterButton){

                            MonsterCard monster = ((MonsterButton)arg0.getSource()).getMonster();
                            if(board.getActivePlayer().getField().getPhase()!= Phase.BATTLE && !board.getActivePlayer().getField().getMonstersArea().contains(monster)){
                                JOptionPane.showMessageDialog(gui, "You must choose monster cards from your field");
                                fc=null;
                                sc=null;
                                return;
                            }
                            if(((MonsterButton) fc).getMonster().getLocation()==Location.HAND && monster.getLocation()==Location.FIELD
                                    && board.getActivePlayer().getField().getMonstersArea().contains(monster)
                                    && board.getActivePlayer().getField().getPhase()!= Phase.BATTLE){
                                if(((MonsterButton) fc).getMonster().getLevel()<=6){

                                    sc = (MonsterButton) arg0.getSource();
                                    monster = ((MonsterButton) sc).getMonster();

                                    if(monster.getLocation()==Location.FIELD){
                                        ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
                                        sacrifices.add(((MonsterButton)sc).getMonster());
                                        if(summonset == 0){
                                            Card.getBoard().getActivePlayer().summonMonster(((MonsterButton) fc).getMonster(), sacrifices);
                                        }
                                        else{
                                            board.getActivePlayer().setMonster(((MonsterButton)fc).getMonster(), sacrifices);
                                        }
                                        updatefield();
                                        fc=null;
                                        sc=null;
                                        return;
                                    }
                                }
                                else{
                                    MonsterButton button = (MonsterButton) arg0.getSource();
                                    MonsterCard card = button.getMonster();
                                    sc = button;
                                    Object[] options2 = {"OK","Cancel"};
                                    int y = JOptionPane.showOptionDialog(gui, "Choose the second sacrifice",null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options2, options2[1]);
                                    if(y==0){
                                        //updatefield();
                                        return;
                                    }
                                    else{
                                        //updatefield();
                                        fc = null;
                                        sc = null;
                                        return;
                                    }
                                }
                            }
                            else{
                                sc = (MonsterButton) arg0.getSource();
                                monster = ((MonsterButton) sc).getMonster();
                                board.getActivePlayer().declareAttack(((MonsterButton)fc).getMonster(), ((MonsterButton)sc).getMonster());
                                fc=null;
                                sc=null;
                                tc = null;
                                updatefield();
                                return;
                            }
                        }
                        else{//fc is a spellbutton
                            MonsterCard monster = ((MonsterButton)arg0.getSource()).getMonster();

                            if(((SpellButton)fc).getSpell().getName().equalsIgnoreCase("Change Of Heart")){
                                if(!board.getOpponentPlayer().getField().getMonstersArea().contains(monster)){
                                    JOptionPane.showMessageDialog(gui, "You must choose monster cards from your opponent's field");
                                    fc=null;
                                    sc=null;
                                    return;
                                }
                                sc = (MonsterButton)arg0.getSource();
                                MonsterCard mons = ((MonsterButton)sc).getMonster();
                                board.getActivePlayer().activateSpell(((SpellButton)fc).getSpell(), ((MonsterButton)sc).getMonster());
                                fc=null;
                                sc=null;
                                updatefield();
                                return;
                            }
                            else{
                                if(!board.getActivePlayer().getField().getMonstersArea().contains(monster)){
                                    JOptionPane.showMessageDialog(gui, "You must choose monster cards from your field");
                                    fc=null;
                                    sc=null;
                                    return;
                                }
                                sc = (MonsterButton)arg0.getSource();
                                MonsterCard mons = ((MonsterButton)sc).getMonster();
                                board.getActivePlayer().activateSpell(((SpellButton)fc).getSpell(), ((MonsterButton)sc).getMonster());
                                fc=null;
                                sc=null;
                                updatefield();
                                return;
                            }
                        }
                    }
                    else{
                        if(arg0.getSource() instanceof MonsterButton){
                            MonsterCard monster = ((MonsterButton)arg0.getSource()).getMonster();

                            if(fc instanceof MonsterButton && ((MonsterButton) fc).getMonster().getLocation()==Location.HAND && monster.getLocation()==Location.FIELD && board.getActivePlayer().getField().getMonstersArea().contains(monster)){

                                MonsterButton button = (MonsterButton) arg0.getSource();
                                monster = button.getMonster();
                                tc = button;
                                if(tc==sc){
                                    JOptionPane.showMessageDialog(gui, "you have to choose two different monsters");
                                    fc=null;
                                    sc=null;
                                    tc=null;
                                    return;
                                }
                                ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
                                sacrifices.add(((MonsterButton)sc).getMonster());
                                sacrifices.add(((MonsterButton)tc).getMonster());
                                if(summonset == 0){
                                    Card.getBoard().getActivePlayer().summonMonster(((MonsterButton) fc).getMonster(), sacrifices);
                                }
                                else{
                                    board.getActivePlayer().setMonster(((MonsterButton) fc).getMonster(), sacrifices);
                                }
                                updatefield();
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
            }

            catch(MultipleMonsterAdditionException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "you can't play more than one card");
            }
            catch(WrongPhaseException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "you can't set or summon a monster in this phase");
            }
            catch(NoMonsterSpaceException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "There is no avaialble space in monster Area");
            }
            catch(MonsterMultipleAttackException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "You Can Attack Only Once");
            }
            catch(DefenseMonsterAttackException e){
                fc = null;
                sc = null;
                tc = null;
                JOptionPane.showMessageDialog(gui, "You Can't Attack in Defense Mode");
            }

        }

        if(arg0.getSource() instanceof SpellButton){
            if(fc instanceof MonsterButton){
                fc = null;
                sc=null;
                JOptionPane.showMessageDialog(gui, "you must sacrifice a monster card");
                return;
            }
            if(fc!=null &&((SpellButton)fc).getName().equalsIgnoreCase("Change Of Heart")){
                JOptionPane.showMessageDialog(gui, "you must choose a monster card");
                fc = null;
                sc=null;
                return;
            }

            if(fc==null){
                if(board.getActivePlayer().getField().getSpellArea().contains(((SpellButton)arg0.getSource()).getSpell())
                        || board.getActivePlayer().getField().getHand().contains(((SpellButton)arg0.getSource()).getSpell())){
                    if(((SpellButton)arg0.getSource()).getSpell().getLocation()==Location.HAND){
                        String[] buttons = { "Activate", "Set", "cancel"};

                        int rc = JOptionPane.showOptionDialog(null, "Activate or set spell ?", "SpellCard",
                                JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
                        SpellButton button = (SpellButton) arg0.getSource();
                        SpellCard card = button.getSpell();
                        fc=button;
                        if(rc==1){
                            Card.getBoard().getActivePlayer().setSpell(card);
                            fc=null;
                            updatefield();
                            return;

                        }
                        if(rc==2){
                            fc=null;
                            return;
                        }
                        else{
                            switch (card.getName()) {

                                case "Card Destruction":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
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
                                case "Dark Hole":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Graceful Dice":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Harpie's Feather Duster":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Heavy Storm":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Mage Power":
                                    String[] options1 = { "ok", "cancel"};

                                    int x1 = JOptionPane.showOptionDialog(null, "Choose the monster you wish to enhance", "SpellCard",
                                            JOptionPane.WARNING_MESSAGE, 0, null, options1, options1[1]);
                                    if(x1==0){
                                        fc = button;
                                        return;
                                    }
                                    fc=null;
                                    return;
                                case "Monster Reborn":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Pot of Greed":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Raigeki":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                default:
                                    board.getActivePlayer().activateSpell(((SpellButton)fc).getSpell(), null);
                                    updatefield();

                            }
                        }
                    }
                    else{
                        String[] buttons = { "ok", "cancel"};

                        int rc = JOptionPane.showOptionDialog(null, "Activate spell card ?", "SpellCard",
                                JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
                        SpellButton button = (SpellButton) arg0.getSource();
                        SpellCard card = button.getSpell();
                        fc=button;
                        if(rc==1){
                            Card.getBoard().getActivePlayer().setSpell(card);
                            fc=null;
                            updatefield();
                            return;
                        }
                        else{
                            switch (card.getName()) {

                                case "Card Destruction":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
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
                                case "Dark Hole":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Graceful Dice":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Harpie's Feather Duster":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Heavy Storm":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Mage Power":
                                    String[] options1 = { "ok", "cancel"};

                                    int x1 = JOptionPane.showOptionDialog(null, "Choose the monster you wish to enhance", "SpellCard",
                                            JOptionPane.WARNING_MESSAGE, 0, null, options1, options1[1]);
                                    if(x1==0){
                                        fc = button;
                                        return;
                                    }
                                    fc=null;
                                    return;
                                case "Monster Reborn":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Pot of Greed":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                case "Raigeki":
                                    board.getActivePlayer().activateSpell(card, null);
                                    updatefield();
                                    fc = null;
                                    return;
                                default:
                                    board.getActivePlayer().activateSpell(((SpellButton)fc).getSpell(), null);
                                    updatefield();

                            }
                        }
                    }


                }
            }


        }

    }










}
