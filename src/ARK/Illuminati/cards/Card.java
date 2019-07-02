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


    /**
     * Card constructor
     * @param name of the card
     * @param desc description of the card
     */
    public Card(String name, String desc) {
        this.name = name;
        this.description = desc;
        this.isHidden = true;

    }

    /**
     * card constructor
     * @param name of the card
     * @param description of the card
     * @param hidden hidden boolean
     * @param location location of card
     */
    public Card(String name, String description, boolean hidden, Location location) {
        this.name = name;
        this.description = description;
        this.isHidden = hidden;
        this.location = location;
    }

    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for type
     * @return description
     */
    public String getType() {
        return description;
    }

    /**
     * setter for card type
     * @param type description of the card
     */
    public void setType(String type) { this.description = type; }

    /**
     * getter for income
     * @return income amount
     */
    public int getIncome() { return getIncome(); }

    /**
     * setter for mode
     * @param mode of the card
     */
    public void setMode(Mode mode){ this.mode = mode; }

    /**
     * getter for mode
     * @return mode of card
     */
    public Mode getMode(){ return mode; }

    /**
     * getter for power amount
     * @return amount
     */
    public int getPower(){ return getPower(); }

    /**
     * getter for resistance of card
     * @return resistance amount
     */
    public int getResistance(){ return getResistance(); }

    /**
     * boolean for hidden
     * @return true false for hidden identity
     */
    public boolean isHidden(){
        return isHidden;
    }

    /**
     * switches the mode of the card
     */
    public void switchMode(){
        if(mode == Mode.ATTACK){
            mode = Mode.DEFENSE;
            setHidden(true);
        }else{
            mode = Mode.ATTACK;
            setHidden(true);
        }
    }

    /**
     * setter for hidden
     * @param isHidden boolean value for hidden
     */
    public void setHidden(boolean isHidden){
        this.isHidden = isHidden;
    }

    /**
     * getter for location
     * @return card location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * setter for location
     * @param location of the card
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * getter for the board
     * @return the board
     */
    public static Board getBoard() {
        return board;
    }

    /**
     * setter for the board
     * @param board to be set
     */
    public static void setBoard(Board board) {
        Card.board = board;
    }

    /**
     * setter for attacked
     * @param attacked boolean value true or false
     */
    public void setAttacked(boolean attacked){this.attacked = attacked;}

    /**
     * boolean test for if mode switched
     * @return true or false if switched
     */
    public boolean isSwitchedMode(){ return switchedMode;}

    /**
     * 'setter for the switched mode
     * @param switchedMode - boolean value
     */
    public void setSwitchedMode(boolean switchedMode){
        this.switchedMode = switchedMode;
    }

}
