package ARK.Illuminati.cards;

import ARK.Illuminati.board.Board;

public abstract class Card {

    private final String name ;
    private String description;
    private boolean isHidden ;
    private Location location;
    private static Board board;

    private Card (String name , String description){
        this.name = name ;
        this.description = description ;
        this.isHidden = true ;
    }

    private Card( String name , String description , boolean hidden , Location location ) {
        this.name = name;
        this.description = description;
        this.isHidden = hidden;
        this.location = location;
    }

}
