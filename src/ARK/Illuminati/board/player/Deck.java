package ARK.Illuminati.board.player;

import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.IlluminatiCard;
import ARK.Illuminati.cards.specialCard;
import ARK.Illuminati.cards.otherGroups;
import ARK.Illuminati.cards.Location;
import ARK.Illuminati.cards.Mode;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {

    //instance variables
    private static ArrayList<Card> illuminati ;
    private static ArrayList<Card> group ;
    private static ArrayList<Card> special ;
    private static ArrayList<Card> deck;

   private static String specialPath = "Database-specialCards";
   private static String otherPath = "Database-othergroupsCards";
   private static String illuminatiPath = "Database-IlluminatiCards";

   public void buildDeck(ArrayList<Card> illuminati, ArrayList<Card> special, ArrayList<Card> group){
       int illuminatiQuota = 8;
       int  specialQuota = 15;
       int otherQuota = 83;


   }
    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card drawOneCard() {
        Card c = deck.get(0);
        if (c.getType().toLowerCase().equals( "special Card")) {
            shuffle();
            drawOneCard();
        } else {
            deck.remove(0);
        }return c;
    }
    public Card drawIlluminatiCard(){
        Card c = deck.get(0);
        deck.remove(0);
        for(int e = 0; e <deck.size(); e++){
            if(deck.get(e).getType().toLowerCase().equals( "illuminati")){
                deck.remove(0);
            }
        }return c;
    }
    public ArrayList<Card> draw4cards(){
        ArrayList cards = new ArrayList<Card>(4);
        for(int i= 0; i <4;i++){
            cards.add(deck.remove(0));
        }return cards;
    }
    public  ArrayList<Card> getDeck(){ return deck; }

    public static void setIlluminati(ArrayList<Card> illuminati){ Deck.illuminati = illuminati; }

    public ArrayList<Card> getIlluminati(){ return illuminati; }

    public static void setSpecial(ArrayList<Card> special){ Deck.special= special; }

    public ArrayList<Card> getSpecial(){ return special; }

    public static void setGroup(ArrayList<Card> group){ Deck.group = group; }

    public ArrayList<Card> getGroup(){ return group; }

    public static String getSpecialPath(){ return specialPath; }

    public static String getOtherPath(){ return otherPath; }

    public static String getIlluminatiPath(){ return illuminatiPath; }

}
