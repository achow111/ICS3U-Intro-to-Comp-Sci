package week6;

import java.util.Scanner;

public class ThreeCardPoker {

  private static final int HEARTS = 0;
  private static final int DIAMONDS = 1;
  private static final int CLUBS = 2;
  private static final int SPADES = 3;
  private static final int NUM_SUITS = 4;
  private static final int NUM_FACES = 13;
  private static final int JACK = 11;
  private static final int QUEEN = 12;
  private static final int KING = 13;
  private static final int ACE = 14;
  private static final int STRAIGHT_FLUSH = 40;
	private static final int THREE_OF_A_KIND = 30;
	private static final int STRAIGHT = 6;
	private static final int FLUSH = 3;
	private static final int PAIR = 1;
  private static final int HIGH_CARD = 0;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    boolean playAgain = true;
    int wallet = 1000;

    System.out.println("**Welcome to Poker!**");
    System.out.println("In your wallet you start with $" + wallet);

    while (playAgain){

      int ante = getAnte(in, wallet);
      wallet -= ante;
      int pairPlus = getPairPlus(in, wallet);
      wallet -= pairPlus;

      String playerHand = dealCards();

      System.out.println("Here are YOUR cards: " + playerHand);

      boolean playAnswer = getPlay(in, wallet, ante);
      int play = 0;

      if(playAnswer)
        play = ante;

      wallet -= play;

      String dealerHand = dealCards();

      System.out.println("Here are the DEALER'S cards: " + dealerHand);

      int playerHandType = handType(playerHand);
      int dealerHandType = handType(dealerHand);

      int payout = getPayout(ante, pairPlus, play, dealerHandType, dealerHand, playerHandType, playerHand);

      System.out.println("Here is your net gain/loss: " + payout);
      wallet += payout + ante + pairPlus + play;
      System.out.println("Here is your wallet: " + wallet);

      if(wallet < 50){
        System.out.println("Oh No! Looks like you don't have enough money to continue");
        playAgain = false;
      } else {
        playAgain = playAnother(in);
      }
    }
    System.out.println("Thanks for playing!");
  }
  /**
   * 
   * @param in passes in the Scanner
   * 
   * @return true if the play wants to play again and answers "yes"
   * false if the player does not want to play again and answers "no"
   */
  private static boolean playAnother(Scanner in) {
    boolean validInput = false;

    while(!validInput){
        System.out.print("Would you like to play again? Please enter \"yes\" or \"no\": ");
        String answer = in.nextLine();
        if(answer.equals("yes")){
            validInput = true;
            return true;
        } else if (answer.equals("no")){
            validInput = true;
            return false;
        } else {
            System.out.println("Invalid Input! Please enter \"yes\" or \"no\"");
        }
    }
    return false;
}
  /**
   * 
   * @param ante is the ante between 50 and 100 that the user chooses
   * @param pairPlus is the pair+ wager between 50 and 100 that the user chooses
   * @param play is the play wager that the user chooses
   * these are used to calculate the net gain/loss
   * 
   * @param dealerHandType the handtype of the dealer according to the payouts e.g. Highcard = 0
   * @param dealerHand the dealers hand in the form "3D 4D 5D"
   * @param playerHandType the handtype of the player
   * @param playerHand the players hand
   * helps determine the situation and which course of action to take when calculating payouts
   * 
   * @return calculates the net loss/gain according the payouts that the player select and who won
   */
  private static int getPayout(int ante, int pairPlus, int play, int dealerHandType, String dealerHand, int playerHandType, String playerHand) {
    int pairPlusPayout = playerHandType * pairPlus + pairPlus; 
    int netpairPlus = pairPlusPayout - pairPlus;
    int face = getFace(dealerHand, dealerHandType);
    String order = "2345678910JQKA";
    int loss = 0;
    int gain = 0;

    int winner = whoWon(playerHandType, playerHand, dealerHandType, dealerHand); 

    if(dealerHandType == 0 && face <= order.indexOf("J") || winner == 2){
      System.out.println("Since the Dealer had a hand of Jack-high or less, you get your (if you chose to) play wager (" + play  +") back but lose your ante (" + ante + ")");
      loss += ante;
      return -loss + netpairPlus;

    } else if(dealerHandType == 0 && face >= order.indexOf("Q") && winner == 1) {
      System.out.println("YAY! You've beat the dealer, you doubled your ante wager and  (if you chose to) play wager (Both " + ante + ")");
      gain = play + ante;
      return gain + netpairPlus;

    } else if (winner == 0){ 
      System.out.println("**OH NO! THE DEALER HAS A BETTER HAND!**");
      System.out.println("You lose your ante wager and your (if you chose to) play wager (Both " + ante + ")");
      loss = ante + play;
      return -loss + netpairPlus; 

    }
    return 0;
  }
  
  /**
   * 
   * @param playerHandType the players hand type e.g. straight, compared to the dealers to see who won
   * @param playerHand the players hand in the form "3D 4D 5D", used to identify the high card
   * @param dealerHandType the dealers hand type
   * @param dealerHand the dealers hand
   * 
   * @return based on the players hand and dealers hand, determines who won
   * 0 if the dealer won
   * 1 if the player won
   * 2 if the dealer and player tie
   */
  private static int whoWon(int playerHandType, String playerHand, int dealerHandType, String dealerHand) {
    int face = getFace(dealerHand, dealerHandType);
    int face2 = getFace(playerHand, playerHandType);

    if(dealerHandType > playerHandType){
      return 0;
    } else if(dealerHandType == playerHandType){
      if(face > face2)
        return 0;

      if(face == face2)
        return 2;

      return 1;
    }

    return 1;
  }
  /**
   * 
   * @param hand the inputed hand of 3 cards, compared to see which card has the highest face
   * @param handType the type of hand, used to indicate the situation of a pair 
   * 
   * @return the highest face/card in a hand 
   */
  private static int getFace(String hand, int handType) {
    int space = hand.indexOf(" ");
    int space2 = hand.indexOf(" ", space + 1);
    String face1 = hand.substring(0, space - 1);
    String face2 = hand.substring(space+1, space2 - 1);
    String face3 = hand.substring(space2+1, hand.length() - 2);
    String order = "2345678910JQKA";

    int getMax = Math.max(order.indexOf(face1), Math.max(order.indexOf(face2), order.indexOf(face3)));
    int getMin = Math.min(order.indexOf(face1), Math.min(order.indexOf(face2), order.indexOf(face3)));
    int getMiddle = (order.indexOf(face1)  + order.indexOf(face2) + order.indexOf(face3)) - (getMax + getMin);

    if(handType == 1){
      if(getMax == getMiddle)
        return getMax;
      
      if(getMiddle == getMin)
        return getMiddle;
    }

    if(getMin == 0 && getMiddle == 1 && getMax == 13)
        return getMiddle;

    return getMax;
  }
  /**
   * 
   * @param in passes in the scanner, used to get the players input
   * @param wallet how much money the player has, used to check whether the player can make the bet based on their money
   * @param ante the play wager eqyals the ante wager, used to check whether the player can make the bet 
   * 
   * prompts the user to enter in a play wager the same as the ante wager
   * @return true if the user would like place a play wager and has enough money
   * false if the user would not like to place a play wager or does not have enough money
   */
  private static boolean getPlay(Scanner in, int wallet, int ante) {
    boolean isValid = false;

    System.out.println("**A play wager is the same amount as the ante wager**");

    while(!isValid){
      System.out.print("Would you like to place an additional play wager? \"Y\" for yes and \"N\" for no: ");
      String answer = in.nextLine();
      if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes")){
        if(wallet >= ante){
        isValid = true;
        return true;
        } else {
          System.out.println("You do not have enough money to make this bet, you have: " + wallet);
          return false;
        }
      } else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("No") ){
        isValid = true;
        return false;
      } else {
        System.out.print("Invalid Input! ");
      }
    }
    return false;
  }

  /**
   * 
   * @param in passes in the Scanner
   * @param wallet used to check if the player has enough money to make thieir bet
   * 
   * prompts the user to enter an ante wager between 50-100
   * @return the amount they entered for the ante wager
   */
  private static int getAnte(Scanner in, int wallet) {
    boolean isValid = false;

    while(!isValid){
      System.out.print("Please enter your ante wager between 50 and 100: ");
      String anteAsString = in.nextLine();
      if(isNumeric(anteAsString) && Integer.parseInt(anteAsString) >= 50 && Integer.parseInt(anteAsString) <= 100){
        if(wallet >= Integer.parseInt(anteAsString)){
          isValid = true;
          return Integer.parseInt(anteAsString);
        } else {
          System.out.println("You do not have enough money to make this bet, you have: " + wallet);
        }
      } else {
        System.out.print("Invalid Input! ");
      }
    }

    return 0;
  }

  /**
   * 
   * @param in passes in the Scanner
   * @param wallet used to check if the player has enough money to make their bet
   * 
   * prompts the user to enter a pair+ wager between 50 and 100
   * @return the amount they entered for the pair+ wager
   */
  private static int getPairPlus(Scanner in, int wallet) {
    boolean isValid = false;
    while(!isValid){   
      System.out.print("Please enter your pair+ wager between 50 and 100 or press \"N\" to decline: ");
      String pairPlusAsString = in.nextLine();
    if(isNumeric(pairPlusAsString) && Integer.parseInt(pairPlusAsString) >= 50 && 
    Integer.parseInt(pairPlusAsString) <= 100) {
      if(wallet >= Integer.parseInt(pairPlusAsString)){
        isValid = true;
        return Integer.parseInt(pairPlusAsString);
      } else {
        System.out.println("You do not have enough money to make this bet, you have: " + wallet);
      }
    } else if (pairPlusAsString.equalsIgnoreCase("N")){
        return 0;
    } else {
      System.out.print("Invalid Input! ");
    }
    
    }

    return 0;
  }

  /**
   * 
   * @param str the input from the user when making bets, checks to see if this input is numeric
   * 
   * @return true if the string is a number
   * false if it is not a number
   */
  public static boolean isNumeric(String str) { 
    try {  
      Integer.parseInt(str);  
      return true;
    } catch(NumberFormatException e){  
      return false;  
    }  
  }

  /**
   * 
   * @param hand the hand of the player in "3D 4D 5D " form
   * evaluated through a series of functions to see which type of hand it is
   * 
   * determines the handtype for a given hand
   * @return the multiplier for the pair+ wager for a handtype
   */
  private static int handType(String hand) {
      if(isStraightFlush(hand))
        return STRAIGHT_FLUSH;
      else if (isThreeOfKind(hand))
        return THREE_OF_A_KIND;
      else if (isStraight(hand))
        return STRAIGHT;
      else if (isFlush(hand))
        return FLUSH;
      else if (isPair(hand))
        return PAIR;
      else
        return HIGH_CARD;
  }

  /**
   * 
   * @param hand the hand of the player, checked if it is a pair
   * 
   * @return true if the handtype is a pair
   * false if it is not a pair
   */
  private static boolean isPair(String hand) {
    int space = hand.indexOf(" ");
    int space2 = hand.indexOf(" ", space + 1);
    String face1 = hand.substring(0, space - 1);
    String face2 = hand.substring(space + 1,space2 - 1);
    String face3 = hand.substring(space2 + 1,hand.length() - 2);

    if(face1.equals(face2) || face2.equals(face3) || face3.equals(face1))
        return true; 
    
    return false; 
  }

  /**
   * 
   * @param hand the hand of the player, checked if it is a straight flush
   * 
   * uses the isFlush() and isStraightFlush() functions to determine if it is true for both
   * @return true if the hand is a straight flush
   * false if it is not a straight fluish
   */
  private static boolean isStraightFlush(String hand){
    if(isFlush(hand) && isStraight(hand))
        return true;

    return false; 
  }

  /**
   * 
   * @param hand the hand of the player, checked if it is a straight
   * 
   * @return true if the hand is a straight
   * false if the hand is not a straight
   */
  private static boolean isStraight(String hand) {
    int space = hand.indexOf(" ");
    int space2 = hand.indexOf(" ", space + 1);
    String face1 = hand.substring(0,space - 1);
    String face2 = hand.substring(space+1,space2 - 1);
    String face3 = hand.substring(space2+1,hand.length() - 2);
    String order = "2345678910JQKA";

    int getMax = Math.max(order.indexOf(face1), Math.max(order.indexOf(face2), order.indexOf(face3)));
    int getMin = Math.min(order.indexOf(face1), Math.min(order.indexOf(face2), order.indexOf(face3)));
    int getMiddle = (order.indexOf(face1)  + order.indexOf(face2) + order.indexOf(face3)) - (getMax + getMin);

    if(getMin == 0 && getMiddle == 1 && getMax == 13)
      return true;
      
    if(getMax - 1 == getMiddle && getMiddle - 1 == getMin)
      return true;

    return false;
  }

  /**
   * 
   * @param hand the hand of the player, checked if it is a flush
   * 
   * @return true if the hand is a flush
   * false if the hand is not a flush
   */
  private static boolean isFlush(String hand) {
    int space = hand.indexOf(" ");
    int space2 = hand.indexOf(" ", space+1);
    String card1 = hand.substring(0,space);
    String card2 = hand.substring(space+1,space2);
    String card3 = hand.substring(space2+1,hand.length() - 1);

    if(card1.substring(card1.length()-1).equals(card2.substring(card2.length()-1)) 
    && card2.substring(card2.length()-1).equals(card3.substring(card3.length()-1))) 
        return true;
    
    return false;
}
  /**
   *  
   * @param hand the hand of the player, checked if it is a three of a kind
   * 
   * @return true if the hand is a three of a kind
   * false if the hand is not a three of a kinds
   */
  private static boolean isThreeOfKind(String hand) {
    int space = hand.indexOf(" ");
    int space2 = hand.indexOf(" ", space + 1);
    String face1 = hand.substring(0, space - 1);
    String face2 = hand.substring(space + 1, space2 - 1);
    String face3 = hand.substring(space2 + 1, hand.length() - 2);

    if(face1.equals(face2) && face2.equals(face3))
        return true;

      return false;
  }

  /**
   * deals the cards for one hand
   * uses the getCard function to get a card
   * uses the isUnique functions to see if that card was dealt before
   * if it is unique add that card to the hand
   * 
   * @return a string of 3 cards for one hand
   */
  private static String dealCards() {
    String cards = "";

    for (int i = 0; i < 3; i++) {
      Boolean hasCard = false;
      while (!hasCard) {
        String card = getCard();
        if (isUnique(cards, card)) {
          cards += card + " ";
          hasCard = true;
        }
      }
    }
    return cards;
  }

  /**
   * 
   * @return a string of a face followed by a suit (combined to make a card) e.g. "5D"
   */
  private static String getCard() {
    return getFace() + getSuit();
  }

  /**
   * 
   * @return a random suit of hearts, diamonds, clubs or spades
   * H = heart, D = diamonds, C = clubs, S = spades
   */
  private static String getSuit() {
    int suit = (int) (Math.random() * NUM_SUITS);
    if (suit == HEARTS)
      return "H";
    else if (suit == DIAMONDS)
      return "D";
    else if (suit == CLUBS)
      return "C";
    else if (suit == SPADES)
      return "S";
    else
      return null;
  }

  /**
   * 
   * @return a random face of a card (between 2 - Ace)
   * Numbers are themselves, J = jack, Q = queen, K = king, A = ace
   */
  private static String getFace() {
    int face = (int) (Math.random() * NUM_FACES + 2);
    if (face >= 2 && face <= 10)
      return "" + face;
    else if (face == JACK)
      return "J";
    else if (face == QUEEN)
      return "Q";
    else if (face == KING)
      return "K";
    else if (face == ACE)
      return "A";
    else
      return null;
  }

  /**
   * 
   * @param playerHand the player hand in the process of dealing cards
   * @param card the card that has been chosen to be dealt
   * 
   * @return true if the card is unique
   * false if their is the same card in the hand
   */
  public static boolean isUnique(String playerHand, String card) {
    return playerHand.indexOf(card) == -1;
  }

} 