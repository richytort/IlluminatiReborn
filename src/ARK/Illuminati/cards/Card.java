package ARK.Illuminati.cards;

import ARK.Illuminati.board.Board;

public abstract class Card {

    private final String name;
    private String description;
    private boolean isHidden;
    private String type;
    private Location location;
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

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

}
