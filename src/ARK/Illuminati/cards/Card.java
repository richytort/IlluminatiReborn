package ARK.Illuminati.cards;

import ARK.Illuminati.board.Board;


public abstract class Card {

    private final String name;
    private String description;
    private boolean isHidden;
    private Location location;
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

    public int getPower(){ return getPower(); }

    public int getResistance(){ return getResistance(); }

    public boolean isHidden(){
        return isHidden;
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

}
