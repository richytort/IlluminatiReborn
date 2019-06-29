package ARK.Illuminati.board.player;

import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.IlluminatiCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.Location;
import ARK.Illuminati.exceptions.UnexpectedFormatException;
import ARK.Illuminati.exceptions.EmptyFieldException;
import ARK.Illuminati.exceptions.MissingFieldException;
import ARK.Illuminati.exceptions.UnknownCardTypeException;
import ARK.Illuminati.exceptions.UnknownSpecialCardException;
import ARK.Illuminati.cards.specialCards.Assassionation;
import ARK.Illuminati.cards.specialCards.Bribery;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck {

    //instance variables
    private static ArrayList<Card> illuminati ;
    private static ArrayList<Card> group ;
    private static ArrayList<Card> special ;
    private static ArrayList<Card> deck;

   private static String specialPath = "Database-specialCards";
   private static String otherPath = "Database-othergroupsCards";
   private static String illuminatiPath = "Database-IlluminatiCards";
    int trials = 0;

   public Deck() throws IOException, NumberFormatException, UnexpectedFormatException{
       if((group == null || special ==  null || group ==null )){
           Scanner sc = new Scanner(System.in);
           while(true){
               try{
                   group = loadCardsFromFile(Deck.getOtherPath());
                   special= loadCardsFromFile(Deck.getSpecialPath());
                   illuminati = loadCardsFromFile(Deck.getIlluminatiPath());
                   break;
               }catch(UnexpectedFormatException e){
                   if(trials >= 3){
                       sc.close();
                       throw e;
                   }
                   System.out.println("Error in reading from file "
                           + e.getSourceFile() + " at line "
                           + e.getSourceLine());
                   System.out.println(e.getMessage());
                   System.out.println("Please enter another path:");

                   trials++;

                   if(e.getSourceFile().equalsIgnoreCase(Deck.getIlluminatiPath())){
                        Deck.setIlluminatiPath(sc.nextLine());

                   }
                   if(e.getSourceFile().equalsIgnoreCase(Deck.getOtherPath())){
                       Deck.setOtherPath(sc.nextLine());

                   }
                   if(e.getSourceFile().equalsIgnoreCase(Deck.getSpecialPath())){
                        Deck.setSpecialPath(sc.nextLine());
                   }
               }catch (FileNotFoundException e){
                   if(trials >= 3){
                       sc.close();
                       throw e;
                   }
                   String s;
                   if(illuminati == null){
                      s= Deck.getIlluminatiPath();
                   }else if(special == null){
                       s= Deck.getSpecialPath();
                   }else{
                       s =Deck.getOtherPath();
                   }
                   System.out.println("The file \"" + s + "\" is not found.");
                   System.out.println("Please enter another path:");

                   trials++;
                   String path = sc.nextLine();

                   if(illuminati == null){
                       Deck.setIlluminatiPath(path);
                   }else if(special==null){
                       Deck.setSpecialPath(path);
                   }else{
                       Deck.setOtherPath(path);
                   }
               }
           }
           sc.close();
       }
        deck = new ArrayList<Card>();
       buildDeck(illuminati,special,group);

   }

    public ArrayList<Card> loadCardsFromFile(String path) throws  IOException, UnexpectedFormatException {
        ArrayList<Card> temp = new ArrayList<>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        int lineNumber = 0;
        while ((line = br.readLine()) != null) {
            lineNumber++;
            String[] cardInfo = line.split(",");

            if (cardInfo.length == 0) {

                br.close();
                throw new MissingFieldException(
                        "The number of fields in the line did not match the expected.",
                        path, lineNumber);

            } else {
                if (cardInfo[0].equalsIgnoreCase("illuminati") && cardInfo.length != 6) {
                    br.close();
                    throw new MissingFieldException(
                            "The number of fields in the line did not match the expected.",
                            path, lineNumber);
                } else if (cardInfo[0].equalsIgnoreCase("other group") && cardInfo.length != 7) {
                    br.close();
                    throw new MissingFieldException(
                            "The number of fields in the line did not match the expected.",
                            path, lineNumber);
                } else if (cardInfo[0].equalsIgnoreCase("special card") && cardInfo.length != 2) {
                    br.close();
                    throw new MissingFieldException(
                            "The number of fields in the line did not match the expected.",
                            path, lineNumber);
                }
            }
            for (int i = 0; i < cardInfo.length; i++)
                if (cardInfo[i].equals("") || cardInfo[i].equals(" ")) {

                    br.close();
                    throw new EmptyFieldException("Empty Field.", path,
                            lineNumber, i + 1);
                }

            if(cardInfo[0].equalsIgnoreCase("illuminati")){
                temp.add(new IlluminatiCard(cardInfo[1],cardInfo[0],cardInfo[2],Integer.parseInt(cardInfo[5]),Integer.parseInt(cardInfo[3]),Integer.parseInt(cardInfo[4])));

            }else if(cardInfo[0].equalsIgnoreCase("other group")){
                temp.add(new GroupCard(cardInfo[1],cardInfo[0],cardInfo[2],Integer.parseInt(cardInfo[3]),Integer.parseInt(cardInfo[4]),Integer.parseInt(cardInfo[5]),Integer.parseInt(cardInfo[6]),cardInfo[7]));

            }else{
                if(!cardInfo[0].equalsIgnoreCase("special card")){
                    br.close();
                    throw new UnknownCardTypeException("Unknown Card type.",
                            path, lineNumber, cardInfo[0]);

                }
                switch (cardInfo[1]){
                    case "Assassination":
                        temp.add(new Assassionation(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Bribery":
                        temp.add(new Bribery(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Computer espionage":
                        temp.add(new computerEspionage(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Deep Agent":
                        temp.add(new deepAgent(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Interference1":
                        temp.add(new interference1(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Interference2":
                        temp.add(new interference2(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Market Manipulation":
                        temp.add(new marketManipulation(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Media Campaign":
                        temp.add(new medianCampaign(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Murphy's Law":
                        temp.add(new murphysLaw(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Secrets Man Was Not Meant To Know":
                        temp.add(new secretsManWasNotMeantToKnow(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Senate Investigating Committee":
                        temp.add(new senateInvestigatingCommittee(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Slush Fund":
                        temp.add(new slushFund(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Swiss Bank Account":
                        temp.add(new swissBankAccount(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Whispering Campaign":
                        temp.add(new whisperingCampaign(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "White Collar Crime":
                        temp.add(new whiteCollarCrime(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    default:
                        new UnknownSpecialCardException("Unknown Special Card,", path, lineNumber, cardInfo[1]);
                }
            }
        }
        br.close();
        return (temp);
   }

   public void buildDeck(ArrayList<Card> illuminati, ArrayList<Card> special, ArrayList<Card> group){
       int illuminatiQuota = 8;
       int  specialQuota = 15;
       int otherQuota = 83;
      for(int i =0; i <illuminatiQuota;i++) {
          IlluminatiCard illuminati1 = (IlluminatiCard) illuminati.get(i);
          IlluminatiCard clone = new IlluminatiCard(illuminati1.getName(), illuminati1.getType(),
                  illuminati1.getAbility(), illuminati1.getIncome(), illuminati1.getPower(),
                  illuminati1.gettPower());
          clone.setMode(illuminati1.getMode());
          clone.setHidden(illuminati1.isHidden());
          clone.setLocation(Location.DECK);
          deck.add(clone);
      }
      for(int i = 0; i <otherQuota;i++){
          GroupCard otherG = (GroupCard) group.get(i);
          GroupCard clone = new GroupCard(otherG.getName(), otherG.getType(),otherG.getAbility(),
                  otherG.getPower(),otherG.gettPower(),otherG.getResistance(),
                  otherG.getIncome(),otherG.getAlignment());
          clone.setMode(otherG.getMode());
          clone.setHidden(otherG.isHidden());
          clone.setLocation(Location.DECK);
          deck.add(clone);
      }
      for(int i = 0; i <specialQuota;i++){
          Card specialC = special.get(i);
          SpecialCard clone;
          if(specialC instanceof Assassionation){
              clone = new Assassionation(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof Bribery){
              clone = new Bribery(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof computerEspionage){
              clone = new computerEspionage(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof deepAgent){
              clone = new deepAgent(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof interference1){
              clone = new interference2(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof interference2){
              clone = new interference1(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof marketManipulation){
              clone = new marketManipulation(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof medianCampaign){
              clone = new mediacampaign(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof murphysLaw){
              clone = new murphysLaw(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof secretsManWasNotMeantToKnow){
              clone = new secretsManWasNotMeantToKnow(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof senateInvestigatingCommittee){
              clone = new senateInvestigatingCommittee(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof slushFund){
              clone = new slushFund(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof swissBankAccount){
              clone = new swissBankAccount(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof whisperingCampaign){
              clone = new whisperingCampaign(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof whiteCollarCrime){
              clone = new whiteCollarCrime(specialC.getName(), specialC.getType(),specialC.getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          }
      }
   }


   public void printDeck(){
       for(Card e: deck){
           System.out.println(e + " ");
       }
   }
   public static void main(String [] args){
       Deck deck1 = new Deck();
       deck1.printDeck();
   }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public Card drawOneCardB() {
        Card c = deck.get(0);
        if (c.getType().toLowerCase().equalsIgnoreCase( "special Card")) {
            shuffle();
            drawOneCardB();
        } else {
            c= deck.remove(0);
        }return c;
    }
    //figure out how to add to uncontrolled and hand
    public Card drawCards(){
       Card c = deck.get(0);
       if(c.getType().toLowerCase().equalsIgnoreCase("special card")){
           c = deck.remove(0);
       }else{
           c = deck.remove(0);
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

    public static void setIlluminatiPath(String illuminatiPath){ Deck.illuminatiPath = illuminatiPath; }

    public static void setSpecialPath(String specialPath){Deck.specialPath = specialPath;}

    public static void setOtherPath(String otherPath){ Deck.otherPath = otherPath;}
}
