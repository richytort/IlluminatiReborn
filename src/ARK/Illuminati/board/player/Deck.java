package ARK.Illuminati.board.player;

import ARK.Illuminati.board.Board;
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

    /**
     * Deck method to set up deck and throw exceptions
     * @throws IOException - exception error when IO error occurs
     * @throws NumberFormatException - error when incorrect number format occur
     * @throws UnexpectedFormatException - exception error for wrong format
     */
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

    /**
     * Loads cards from files to the arraylist
     * @param path - path for which Illuminati cards are loaded
     * @return temp variable containing cards
     * @throws IOException - IO error occurs, exception is thrown
     * @throws UnexpectedFormatException - error of unexpected format
     */
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

    /**
     * Builds the deck using arraylists of different card types
     * @param illuminati - Illuminati cards
     * @param special - arraylist of special actions cards
     * @param group- arraylist of group type cards
     */
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

    /**
     * Shuffles the deck
     */
    public void shuffle(){ Collections.shuffle(deck); }

    /**
     * gets the deck
     * @return the deck
     */
    public  ArrayList<Card> getDeck(){ return deck; }

    /**
     * Sets up Illuminati with deck
     * @param illuminati- arraylist of cards
     */
    public static void setIlluminati(ArrayList<Card> illuminati){ Deck.illuminati = illuminati; }

    /**
     * getter for Illuminati Arraylist
     * @return Arraylist of cards
     */
    public ArrayList<Card> getIlluminati(){ return illuminati; }

    /**
     * sets the special cards in deck
     * @param special special cards in deck
     */
    public static void setSpecial(ArrayList<Card> special){ Deck.special= special; }

    /**
     * getter for special cards
     * @return the special cards
     */
    public ArrayList<Card> getSpecial(){ return special; }

    /**
     * setter for groups in deck
     * @param group of the deck
     */
    public static void setGroup(ArrayList<Card> group){ Deck.group = group; }

    /**
     * getter for the group
     * @return group card in deck
     */
    public ArrayList<Card> getGroup(){ return group; }

    /**
     * @return special path in game
     */
    public static String getSpecialPath(){ return specialPath; }

    /**
     * @return-getter for other path
     */
    public static String getOtherPath(){ return otherPath; }

    /**
     * getter for Illuminati Path
     * @return path for Illuminati
     */
    public static String getIlluminatiPath(){ return illuminatiPath; }

    /**
     * setter for Illuminati Path
     * @param illuminatiPath- path in Illuminati game
     */
    public static void setIlluminatiPath(String illuminatiPath){ Deck.illuminatiPath = illuminatiPath; }

    /**
     * setter for special path
     * @param specialPath -path in game for specials
     */
    public static void setSpecialPath(String specialPath){Deck.specialPath = specialPath;}

    /**
     * gets other path for deck
     * @param otherPath - path for the deck of cards
     */
    public static void setOtherPath(String otherPath){ Deck.otherPath = otherPath;}

    /**
     * getter for size of the deck
     * @return the size of the deck
     */
    public int size(){
       return deck.size();
    }


}
