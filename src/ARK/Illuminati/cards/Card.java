package ARK.Illuminati.cards;

import ARK.Illuminati.board.Board;


public abstract class Card {

    private final String name;
    private String description;
    private boolean isHidden;
    private Location location;
    private Mode mode;
    private boolean attacked;
    private boolean switchedMode;

    private int income;
    private static Board board;


    public Card(String name, String desc) {
        this.name = name;
        this.description = desc;
        this.isHidden = true;

    }
    public Card(String name, String description, boolean hidden, Location location) {
        this.name = name;
        this.description = description;
        this.isHidden = hidden;
        this.location = location;
    }

  //  public abstract void action(Card cardA);

    public String getName() {
        return name;
    }

    public String getType() {
        return description;
    }

    public void setType(String type) { this.description = type; }

    public int getIncome() { return getIncome(); }
    public void setMode(Mode mode){ this.mode = mode; }

    public Mode getMode(){ return mode; }


    public int getPower(){ return getPower(); }

    public int getResistance(){ return getResistance(); }

    public boolean isHidden(){
        return isHidden;
    }
    public void switchMode(){
        if(mode == Mode.ATTACK){
            mode = Mode.DEFENSE;
            setHidden(true);
        }else{
            mode = Mode.ATTACK;
            setHidden(true);
        }
    }

    public void setHidden(boolean isHidden){
        this.isHidden = isHidden;
    }
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        Card.board = board;
    }

    public void setAttacked(boolean attacked){this.attacked = attacked;}

    public boolean isSwitchedMode(){ return switchedMode;}

    public void setSwitchedMode(boolean switchedMode){
        this.switchedMode = switchedMode;
    }

}
