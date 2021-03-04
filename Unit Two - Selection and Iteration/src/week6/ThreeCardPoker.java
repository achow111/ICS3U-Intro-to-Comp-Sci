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

    while (playAgain){
    int ante = getAnte(in);
    int pairPlus = getPairPlus(in);

    String playerHand = dealCards();

    System.out.println("Here are YOUR cards: " + playerHand);

    boolean playAnswer = getPlay(in);
    int play = 0;

    if(playAnswer)
      play = ante;

    String dealerHand = dealCards();

    System.out.println("Here are the DEALER'S cards: " + dealerHand);

    int playerHandType = handType(playerHand);
    int dealerHandType = handType(dealerHand);

    int payout = getPayout(ante, pairPlus, play, dealerHandType, dealerHand, playerHandType, playerHand);

    System.out.println("Here is your net gain/loss: " + payout);

    playAgain = playAnother(in);
    }
  }

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

  private static int getPayout(int ante, int pairPlus, int play, int dealerHandType, String dealerHand, int playerHandType, String playerHand) {
    int pairPlusPayout = playerHandType * pairPlus; 
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

  private static boolean getPlay(Scanner in) {
    boolean isValid = false;

    System.out.println("**A play wager is the same amount as the ante wager**");

    while(!isValid){
      System.out.print("Would you like to place an additional play wager? \"Y\" for yes and \"N\" for no: ");
      String answer = in.nextLine();
      if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes")){
        isValid = true;
        return true;
      } else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("No") ){
        isValid = true;
        return false;
      } else {
        System.out.print("Invalid Input! ");
      }
    }
    return false;
  }

  private static int getAnte(Scanner in) {
    boolean isValid = false;

    while(!isValid){
      System.out.print("Please enter your ante wager between 50 and 100: ");
      String anteAsString = in.nextLine();
      if(isNumeric(anteAsString) && Integer.parseInt(anteAsString) >= 50 && Integer.parseInt(anteAsString) <= 100){
          isValid = true;
          return Integer.parseInt(anteAsString);
      } else {
        System.out.print("Invalid Input! ");
      }
    }

    return 0;
  }

  private static int getPairPlus(Scanner in) {
    boolean isValid = false;
    while(!isValid){   
      System.out.print("Please enter your pair+ wager between 50 and 100 or press \"N\" to decline: ");
      String pairPlusAsString = in.nextLine();
    if(isNumeric(pairPlusAsString) && Integer.parseInt(pairPlusAsString) >= 50 && 
    Integer.parseInt(pairPlusAsString) <= 100 || pairPlusAsString.equalsIgnoreCase("N")){
      isValid = true;
      if(pairPlusAsString.equalsIgnoreCase("N"))
        return 0;
      else 
        return Integer.parseInt(pairPlusAsString);
    } else {
      System.out.print("Invalid Input! ");
    }
    
    }

    return 0;
  }

  public static boolean isNumeric(String str) { 
    try {  
      Integer.parseInt(str);  
      return true;
    } catch(NumberFormatException e){  
      return false;  
    }  
  }

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

  private static boolean isPair(String hand) {
    int space = hand.indexOf(" ");
    int space2 = hand.indexOf(" ", space+1);
    String face1 = hand.substring(0,space-1);
    String face2 = hand.substring(space+1,space2-1);
    String face3 = hand.substring(space2+1,hand.length()-2);

    if(face1.equals(face2) || face2.equals(face3) || face3.equals(face1))
        return true; 
    
    return false; 
}

private static boolean isStraightFlush(String hand){
  if(isFlush(hand) && isStraight(hand))
      return true;

  return false; 
}

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

  private static String getCard() {
    return getFace() + getSuit();
  }

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

  public static boolean isUnique(String playerHand, String card) {
    return playerHand.indexOf(card) == -1;
  }

} 