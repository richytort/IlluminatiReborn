package ARK.Illuminati.board.player;

import ARK.Illuminati.cards.Card;
import ARK.Illuminati.cards.IlluminatiCard;
import ARK.Illuminati.cards.specialCards.SpecialCard;
import ARK.Illuminati.cards.GroupCard;
import ARK.Illuminati.cards.specialCards.deepAgent;
import ARK.Illuminati.cards.specialCards.interference1;
import ARK.Illuminati.cards.specialCards.interference2;
import ARK.Illuminati.cards.specialCards.mediaCampaign;
import ARK.Illuminati.cards.specialCards.slushFund;
import ARK.Illuminati.cards.specialCards.swissBankAccount;
import ARK.Illuminati.cards.specialCards.whiteCollarCrime;
import ARK.Illuminati.cards.specialCards.senateInvestigatingCommittee;
import ARK.Illuminati.cards.specialCards.secretsManWasNotMeantToKnow;
import ARK.Illuminati.cards.specialCards.murphysLaw;
import ARK.Illuminati.cards.specialCards.marketManipulation;
import ARK.Illuminati.cards.specialCards.computerEspionage;
import ARK.Illuminati.cards.specialCards.whisperingCampaign;
import ARK.Illuminati.cards.Location;
import ARK.Illuminati.exceptions.UnexpectedFormatException;
import ARK.Illuminati.exceptions.EmptyFieldException;
import ARK.Illuminati.exceptions.MissingFieldException;
import ARK.Illuminati.exceptions.UnknownCardTypeException;
import ARK.Illuminati.exceptions.UnknownSpecialCardException;
import ARK.Illuminati.cards.specialCards.Assassination;
import ARK.Illuminati.cards.specialCards.Bribery;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Deck {

    //instance variables
    private static ArrayList<Card> illuminati ;
    private static ArrayList<Card> group ;
    private static ArrayList<Card> special ;

    private final ArrayList<Card> deck;

   private static String specialPath = "Database-specialCards.csv";
   private static String otherPath = "Database-othergroupsCards.csv";
   private static String illuminatiPath = "Database-IlluminatiCards.csv";
   int trials = 0;

   public Deck() throws IOException, NumberFormatException, UnexpectedFormatException{

       if((illuminati == null || special ==  null || group ==null )){

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
                   System.out.println(s);
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
        ArrayList<Card> temp = new ArrayList<Card>();
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
                if (cardInfo[0].equalsIgnoreCase("Illuminati") && cardInfo.length != 6) {
                    br.close();
                    System.out.println("there");
                    throw new MissingFieldException(
                            "The number of fields in the line did not match the expected.",
                            path, lineNumber);
                } else if (cardInfo[0].equalsIgnoreCase("Other group") && cardInfo.length != 8) {
                    br.close();
                    throw new MissingFieldException(
                            "The number of fields in the line did not match the expected.",
                            path,lineNumber);
                } else if (cardInfo[0].equalsIgnoreCase("special card") && cardInfo.length != 3) {
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
                temp.add(new GroupCard(cardInfo[1],cardInfo[0],cardInfo[2],cardInfo[7],Integer.parseInt(cardInfo[3]),Integer.parseInt(cardInfo[4]),Integer.parseInt(cardInfo[5]),Integer.parseInt(cardInfo[6])));

            }else{
                if(!cardInfo[0].equalsIgnoreCase("special card")){
                    br.close();
                    throw new UnknownCardTypeException("Unknown Card type.",
                            path, lineNumber, cardInfo[0]);

                }
                switch (cardInfo[1]){
                    case "Assassination":
                        temp.add(new Assassination(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Bribery":
                        temp.add(new Bribery(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Computer Espionage":
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
                        temp.add(new mediaCampaign(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Murphy's Law":
                        temp.add(new murphysLaw(cardInfo[1], cardInfo[0], cardInfo[2]));
                        break;
                    case "Secrets Man was not Meant to Know":
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
                        throw new UnknownSpecialCardException("Unknown Special Card,", path, lineNumber, cardInfo[1]);
                }
            }
        }
        br.close();
        return (temp);
   }

   public void buildDeck(ArrayList<Card> illuminati, ArrayList<Card> special, ArrayList<Card> group){
      for(int i =0; i < illuminati.size();i++) {
          IlluminatiCard illuminati1 = (IlluminatiCard) illuminati.get(i);
          IlluminatiCard clone = new IlluminatiCard(illuminati1.getName(), illuminati1.getType(),
                  illuminati1.getAbility(), illuminati1.getIncome(), illuminati1.getPower(),
                  illuminati1.gettPower());
          clone.setMode(illuminati1.getMode());
          clone.setHidden(illuminati1.isHidden());
          clone.setLocation(Location.DECK);
          deck.add(clone);
      }
      for(int i = 0; i < group.size();i++){
          GroupCard otherG = (GroupCard) group.get(i);
          GroupCard clone = new GroupCard(otherG.getName(), otherG.getType(),otherG.getAbility(),otherG.getAlignment(),
                  otherG.getPower(),otherG.gettPower(),otherG.getResistance(),
                  otherG.getIncome());
          clone.setMode(otherG.getMode());
          clone.setHidden(otherG.isHidden());
          clone.setLocation(Location.DECK);
          deck.add(clone);
      }
      for(int i = 0; i < special.size();i++){
          Card specialC = special.get(i);
          SpecialCard clone;
          if(specialC instanceof Assassination){
              clone = new Assassination(specialC.getName(), specialC.getType(),((Assassination) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof Bribery){
              clone = new Bribery(specialC.getName(), specialC.getType(),((Bribery) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof computerEspionage){
              clone = new computerEspionage(specialC.getName(), specialC.getType(),((computerEspionage) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof deepAgent){
              clone = new deepAgent(specialC.getName(), specialC.getType(),((deepAgent) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof interference1){
              clone = new interference2(specialC.getName(), specialC.getType(),((interference1) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof interference2){
              clone = new interference1(specialC.getName(), specialC.getType(),((interference2) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof marketManipulation){
              clone = new marketManipulation(specialC.getName(), specialC.getType(),((marketManipulation) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof mediaCampaign){
              clone = new mediaCampaign(specialC.getName(), specialC.getType(),((mediaCampaign) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof murphysLaw){
              clone = new murphysLaw(specialC.getName(), specialC.getType(),((murphysLaw) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof secretsManWasNotMeantToKnow){
              clone = new secretsManWasNotMeantToKnow(specialC.getName(), specialC.getType(),((secretsManWasNotMeantToKnow) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof senateInvestigatingCommittee){
              clone = new senateInvestigatingCommittee(specialC.getName(), specialC.getType(),((senateInvestigatingCommittee) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof slushFund){
              clone = new slushFund(specialC.getName(), specialC.getType(),((slushFund) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof swissBankAccount){
              clone = new swissBankAccount(specialC.getName(), specialC.getType(),((swissBankAccount) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof whisperingCampaign){
              clone = new whisperingCampaign(specialC.getName(), specialC.getType(),((whisperingCampaign) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          } if(specialC instanceof whiteCollarCrime){
              clone = new whiteCollarCrime(specialC.getName(), specialC.getType(),((whiteCollarCrime) specialC).getAbility());
              clone.setLocation(Location.DECK);
              deck.add(clone);
              continue;
          }
      }
   }

    public void shuffle(){

       Collections.shuffle(deck);
    }
    public int getIndex(Card e){
       int index = deck.indexOf(e);
       return index;
    }

    public void printDeck(){
       for(Card e: deck){
           System.out.println(getIndex(e));
           System.out.println(e + " ");
       }
    }

    public Card drawOneCard(){
       return deck.remove(0);
    }

    public Card drawOneCardB() {
        Card c = deck.get(0);
        if(deck.get(0).getType().equalsIgnoreCase("other group")){
           c = deck.remove(0);
        }
        else if (deck.get(0).getType().equalsIgnoreCase( "special Card")) {
            shuffle();
             c = deck.get(1);
             if(deck.get(1).getType().equalsIgnoreCase("Special Card")){
                 c = deck.get(2);
             }
        }return c;
    }
//    public static void main(String [] args)throws IOException, UnexpectedFormatException{
//       Deck deck1 = new Deck();
//       deck1.drawIlluminatiCard();
//       for(int e = 0; e< 4;e++) {
//           Card c = deck1.drawOneCardB();
//           System.out.println(c + " ");
//       }
//    }
    //figure out how to add to uncontrolled and hand
    public Card drawCards(){
       return deck.remove(0);
   }

    public Card drawIlluminatiCard(){
        Card c = deck.get(0);
        deck.remove(0);
        for(int e = deck.size() - 1; e >= 0; e--){
            if (deck.get(e).getType().equalsIgnoreCase("illuminati")) {
                deck.remove(e);
            }
        }shuffle();
        return c;
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
