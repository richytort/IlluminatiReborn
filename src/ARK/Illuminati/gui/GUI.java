
package ARK.Illuminati.gui;

import ARK.Illuminati.board.Board;
import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.Card;
import ARK.Illuminati.exceptions.UnexpectedFormatException;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {
    public static Clip audioClip ;
    private JPanel panel2;
    public JLabel deck ;
    public JButton grave ;
    private NextActionButton nextAction ;
    private JLabel p1name ;
    private JLabel p2name ;
    public JLabel currAction ;
    private EndTurnButton endTurn ;
    private JPanel panel1 ;
    private JScrollPane pan ;
    private StructurePanel structureAreaP1;
    private StructurePanel structureAreaP2 ;
    private SpecialPanel specialAreaP1;
    private SpecialPanel specialAreaP2;
    private HiddenHandPanel p1hid;
    private HiddenHandPanel p2hid;
    private Player p1 ;
    private Player p2 ;
    private ImageIcon imgThisImg ;
    private JLabel description ;
    private JScrollPane sp1 ;
    private JScrollPane sp2 ;
    public static Clip getAudioClip(){ return audioClip ; }

    public static void setAudioClip( Clip audioClip ) { GUI.audioClip = audioClip ; }

    public StructurePanel getStructureAreaP1(){
        return structureAreaP1;
    }

    public void setStructureAreaP1(StructurePanel structureAreaP1 ){
        this.structureAreaP1 = structureAreaP1 ;
    }
    public StructurePanel getStructureAreaP2(){
        return structureAreaP2;
    }

    public void setStructureAreaP2(StructurePanel structureAreaP2 ){
        this.structureAreaP2 = structureAreaP2 ;
    }

    public SpecialPanel getSpecialAreaP1(){
        return specialAreaP1;
    }

    public void setSpecialAreaP1(SpecialPanel specialAreaP1 ){
        this.specialAreaP1 = specialAreaP1 ;
    }
    public SpecialPanel getSpecialAreaP2(){
        return specialAreaP2;
    }

    public void setSpecialAreaP2(SpecialPanel specialAreaP2 ){
        this.specialAreaP2 = specialAreaP2 ;
    }

    public JScrollPane getSp1() { return sp1 ; }

    public void setSp1(JScrollPane sp1) { this.sp1 = sp1 ;  }

    public JScrollPane getSp2() { return sp2 ; }

    public void setSp2( JScrollPane sp2) { this.sp2 = sp2 ; }

    public HiddenHandPanel getP1hid() {return p1hid; }

    public void setP1hid(HiddenHandPanel p1hid) {this.p1hid = p1hid ; }

    public HiddenHandPanel getP2hid() { return p2hid; }

    public void setP2hid(HiddenHandPanel p2hid){ this.p2hid = p2hid ; }

    public Player getP1() {return p1 ; }

    public void setP1(Player p1 ){ this.p1 = p1 ; }

    public Player getP2( ) { return p2 ; }

    public void setP2( Player p2 ) { this.p2 = p2 ; }

    public JPanel getPanel2() {return panel2 ; }

    public void setPanel2( JPanel panel2 ) { this.panel2 = panel2 ; }

    public JLabel getDeck(){ return deck ; }

    public void setDeck( JLabel deck ) { this.deck = deck ; }

    public JButton getGrave() {
        return grave;
    }

    public void setGrave(JButton gravep1) {
        this.grave = grave;
    }

    public NextActionButton getNextAction(){ return nextAction ; }

    public void setNextActionButton( NextActionButton nextAction ){ this.nextAction = nextAction ; }

    public JLabel getP1name( ) { return p1name ; }

    public void setP1name( JLabel p1name ){ this.p1name = p1name ; }

    public JLabel getP2name() { return p2name; }

    public void setP2name(JLabel p2name) { this.p2name = p2name; }

    public JLabel getCurrAction() { return currAction; }

    public void setCurrAction(JLabel currAction) { this.currAction = currAction; }

    public EndTurnButton getEndTurn() { return endTurn ; }

    public void setEndTurn( EndTurnButton endTurn ){ this.endTurn = endTurn ; }

    public JPanel getPanel1() { return panel1 ; }

    public void setPanel( JPanel panel1 ){ this.panel1 = panel1 ; }

    public JScrollPane getPan(){ return pan ; }

    public void setPan( JScrollPane pan ){ this.pan = pan ; }

    public GUI(Player p1, Player p2) throws IOException, UnexpectedFormatException {
        super("Illuminati!");
        Board b = new Board();
        b.startGame(p1, p2);
        setP1(p1);
        setP2(p2);

        ImageIcon bg = new ImageIcon("src/bg3.png");
        JLabel g = new JLabel(bg);
        g.setVisible(true);
        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("bg1.png"));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        this.add(g);
        setResizable(false);
        setSize(1366, 768);
        this.getContentPane().setLayout(null);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //groupAreap1 = new GroupsPanel(p1);
        structureAreaP1 = new StructurePanel(p1);
        //groupAreap2 = new GroupsPanel(p2);
        structureAreaP2 = new StructurePanel(p2);
        //specialAreap1 = new SpecialPanel(p1);
        specialAreaP1 = new SpecialPanel(p1);
        //specialAreap2 = new SpecialPanel(p2);
        specialAreaP2 = new SpecialPanel(p2);
        //I don't know why the previous added another set. Mistake?
        //groupAreap1= new MonstersPanel(p1);
        //groupAreap2 = new MonstersPanel(p2);
        structureAreaP1 = new StructurePanel(p1);
        structureAreaP2 = new StructurePanel(p2);
        nextAction = new NextActionButton("Next Action");
        endTurn = new EndTurnButton("End Turn");
        p1name = new JLabel(p1.getName());
        p2name = new JLabel(p2.getName());
        grave = new JButton();

        //finish field class
        //test deck and test currAction
        deck = new JLabel(); //deck = new JLabel(p1.getField().getDeck().getDeck().size() + "");
        currAction = new JLabel(); //currAction = new JLabel(Card.getBoard().getActivePlayer().getField().getPhase().name());

        deck.setFont(new Font("Ariel", Font.BOLD, 15));
        deck.setForeground(Color.WHITE);
        //Note: other has it as grave. I made it to graveIcon since we already have grave as a JButton.
        //Maybe change it later since it is
        //possible that there may be use of "grave" on other instances.
        //Test graveIcon
        ImageIcon graveIcon = new ImageIcon("RegBack.png");//ImageIcon graveIcon = new ImageIcon("Cards Images Database/GraveYard.png");
        Image img = graveIcon.getImage();
        img = img.getScaledInstance(91, 62 , java.awt.Image.SCALE_SMOOTH);
        graveIcon = new ImageIcon(img);
        grave.setIcon(graveIcon);

        //Note: Find out what description is used for. It may be important.
        description = new JLabel();
        p1hid = new HiddenHandPanel(p1);
        p2hid = new HiddenHandPanel(p2);

        p2name.setFont(new Font("Century Gothic", Font.BOLD, 25));
        p2name.setForeground(Color.BLACK);

        p1name.setFont(new Font("Century Gothic", Font.BOLD, 25));
        p1name.setForeground(Color.BLACK);

        currAction.setFont(new Font("Century Gothic", Font.BOLD, 20));
        currAction.setForeground(Color.BLACK);


        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        //finish these classes
        //panel1.add(groupAreap1,BorderLayout.NORTH);
        //panel1.add(specialAreap1 , BorderLayout.CENTER);
        panel1.setOpaque(false);

        //Consider deleting the sp1...Oh maybe not. SP could mean the "Special hand. We will see.
        if (p1 == Card.getBoard().getActivePlayer()) {
            sp1 = new JScrollPane(); //sp1 = new JScrollPane(handp1); may not need this since we aren't using "Hand".
            sp1.setBorder(null); //same as statedabove
            sp1.getViewport().setOpaque(false);
            sp1.setPreferredSize(new Dimension(500,150));
            sp1.setOpaque(false);
            sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            panel1.add(sp1,BorderLayout.SOUTH);
            this.revalidate();
        }
        //hmm, we may only need this one since we do have a p1hid. This is the specials that will
        //be hidden.
        else{
            sp1 = new JScrollPane(p1hid);
            sp1.setBorder(null);
            sp1.getViewport().setOpaque(false);
            sp1.setPreferredSize(new Dimension(500,150));
            sp1.setOpaque(false);
            sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            panel1.add(sp1,BorderLayout.SOUTH);
            this.revalidate();
        }


        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        //finish these classes////////
        //panel2.add(groupAreap2, BorderLayout.SOUTH);
        //panel2.add(specialAreap2,BorderLayout.CENTER);
        panel2.setOpaque(false);


        if (p2 == Card.getBoard().getActivePlayer()) {
            sp2 = new JScrollPane() ; //sp2 = new JScrollPane(handp2);
            sp2.setBorder(null);
            sp2.getViewport().setOpaque(false);
            sp2.setPreferredSize(new Dimension(500,150));
            sp2.setOpaque(false);
            sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            panel2.add(sp2,BorderLayout.NORTH);
            this.revalidate();
        }
        else{
            sp2 = new JScrollPane(p2hid);
            sp2.setBorder(null);
            sp2.getViewport().setOpaque(false);
            sp2.setPreferredSize(new Dimension(500,150));
            sp2.setOpaque(false);
            sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            panel2.add(sp2,BorderLayout.NORTH);
            this.revalidate();
        }


        JPanel panel3 = new JPanel();
        panel3.setOpaque(false);
        panel3.setLayout(null);
        panel3.add(p2name);
        panel3.add(endTurn);
        panel3.add(nextAction);
        panel3.add(p1name);
        this.add(panel3);
        panel3.setBounds(0,0, 200, 700);
        p2name.setBounds(2,150, 311, 57);
        endTurn.setBounds(5, 300, 157, 39 ) ;
        nextAction.setBounds(5, 400, 157, 39);
        p1name.setBounds(2, 500, 311, 57);


        //May have to change this eventually.
        //test ingThisImg
        imgThisImg = new ImageIcon("California.png");//imgThisImg = new ImageIcon("Cards Images Database/Card Back.png");
        //here is that description again. Find out what this description is for.
        description.setIcon(imgThisImg);




        this.add(panel1);
        panel1.setBounds(200, 380, 700, 350);
        this.add(panel2);
        panel2.setBounds(280, 0, 700, 350);
        this.add(deck);
        deck.setBounds(1277, 645, 100, 100);
        this.add(grave);
        grave.setBounds(1150, 670   , 91, 62);
        this.add(description);
        //here is that description again. See what it does.
        description.setBounds(1050, 140, 300, 438);
        this.add(currAction);
        currAction.setBounds(583, 315, 300, 100) ;

        this.validate();

        //finish controller class
        //new Controller(b, this);

    }

    public ImageIcon getImgThisImg(){
        return imgThisImg;
    }

    public void setImgThisImg(ImageIcon imgThisImg){
        this.imgThisImg = imgThisImg;
    }
    //Here is that description again.
    public JLabel getDescription(){
        return description;
    }

    public void setDescription(JLabel description){
        this.description = description;
    }

    public static void main(String[]args) throws IOException, UnexpectedFormatException {
        //find an appropriate audio file to add here.
        File audioFile = new File("Halo.wav");

        boolean playCompleted = false ;

        try{
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);

            audioClip.start();

            audioClip.loop(100);

        }catch (UnsupportedAudioFileException ex){
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        }catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        }catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }






        JFrame start = new JFrame() ;
        start.setSize(1366, 768);
        start.setVisible(true);
        ////////////////Make a start game icon.
        //test start.setContentPane
        start.setContentPane(new JLabel(new ImageIcon("bg3.png" )));

        start.revalidate();
        start.setLayout(null);
        JTextField p1 = new JTextField();
        start.add(p1);
        p1.setBounds(475, 350, 400, 50);

        JTextField p2 = new JTextField();
        start.add(p2);
        p2.setBounds(475, 520, 400, 50);

        //Get a start button png file.
        JButton startbut = new JButton (new ImageIcon("start.png"));

        startbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(p1.getText().length() ==0 || p2.getText().length() == 0 )
                    JOptionPane.showMessageDialog(start, "AllFields Are Required");

                else if( (p1.getText().length() >= 14 || p2.getText().length() >= 14 ))
                    JOptionPane.showMessageDialog(start, "Maximum Length for name is 14 digits");

                else{
                    start.setVisible(false);
                    try{
                        GUI gui = new GUI (new Player(p1.getText()), new Player(p2.getText()));
                    }catch (IOException | UnexpectedFormatException e1){
                        e1.printStackTrace();
                    }
                }

            }
        } );

        startbut.setBounds(510, 637, 311, 57);
        startbut.setVisible(true);
        startbut.setOpaque(true);
        startbut.revalidate();
        startbut.repaint();
        start.add(startbut);
        start.validate();
        System.out.println(p1.getText());




    }


}

