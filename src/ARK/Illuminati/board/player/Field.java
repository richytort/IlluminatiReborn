package ARK.Illuminati.board.player;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import ARK.Illuminati.cards.*;
import ARK.Illuminati.board.player.Player;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.exceptions.UnexpectedFormatException;

public class Field {
    private final Deck deck;
    private ArrayList<Card> hand;
    Player p1;
    Player p2;
    private  ArrayList<Card> uncontrolledGroups;
    private ArrayList<Card> graveYard;

    public Field()throws IOException, UnexpectedFormatException {
        hand = new ArrayList<Card>();
        uncontrolledGroups = new ArrayList<Card>();
        deck = new Deck();
    }
    public void removeCard(Card e){
        hand.remove(e);
        uncontrolledGroups.add(e);
    }
    public void addCardToHand(){
        if(deck.getDeck().size()==0){
            if (this == Card.getBoard().getActivePlayer().getField())
                Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
            else
                Card.getBoard().setWinner(Card.getBoard().getActivePlayer());

            return;
        }
        Card temp = deck.drawCards();
        if (temp.getType().equalsIgnoreCase("special card")) {
            hand.add(temp);
            temp.setLocation(Location.HAND);
        }else{
            uncontrolledGroups.add(temp);
            temp.setLocation(Location.UNCONTROLLED);
        }
    }

    public void printUncontroled(){
        for(Card e: uncontrolledGroups){
            System.out.println(e+ " ");
        }
    }
    public void addIlluminatiCard(){
        Card temp = deck.drawIlluminatiCard();
        hand.add(temp);
        temp.setLocation(Location.HAND);
    }

    public void add4CardsToUncontrolled(){
        for(int i = 0; i <4;i++){
            Card temp = deck.drawOneCardB();
            uncontrolledGroups.add(temp);
            temp.setLocation(Location.UNCONTROLLED);
        }
    }
    public void printHand(){
        for(Card e: hand){
            System.out.println(e+ " ");
        }
    }

    public static void main(String [] args) throws IOException,UnexpectedFormatException{
        Field field = new Field();
        Deck deck = new Deck();
       // deck.printDeck();
        field.addIlluminatiCard();
      //  field.addCardToHand();
       field.add4CardsToUncontrolled();
        field.printHand();
        System.out.println("uncontrolled");
        field.printUncontroled();
      //  deck.printDeck();


    }
//
//    public boolean addGroupToField(Card group, Mode m, boolean isHidden) {
//        if (!(hand.contains(group) && group.getLocation() == Location.HAND))
//            return false;
//        if (hand.size() >= 5)
//            throw new NoSpaceException();
//        if (Action == Action.Attack)
//            throw new WrongActionException();
//        hand.remove(group);
//        group.setHidden(isHidden);
//        group.setLocation(Location.FIELD);
//        hand.add(group);
//        return true;
//    }
//    public void removeCardtoGraveYard(GroupCard group) {
//        if (illuminatiArea.contains(group)) {
//            illuminatiArea.remove(group);
//            uncontrolledGroups.add(group);
//            uncontrolledGroups.setLocation(Location.uncontrolledGroups);
//        }
//    }

    //need a remove group to uncontrolled are
//    public boolean useSpecialCard(SpecialCard card, GroupCard group) {
//        if (!specialArea.contains(card))
//            return false;
//        if (Action == Action.BATTLE)
//            throw new WrongActionException();
//        card.action(group);
//        removeCard(card);
//        return true;
//    }
//
//
//
//
//
//
    public void removeGroupToHand(Card target){
        if(p1.getHand().contains(target)){
            p1.getHand().remove(target);
            p2.getHand().add(target);
        }
    }

    //needs work
    public boolean addGroupToField(Card group, Mode attack, boolean b) {
        return true;
    }

    //needs work

    public boolean addSpecialToField(SpecialCard special, Object o, boolean b) {
   return true;
    }
}