package week3;

import java.util.Scanner;

public class CrossCountry {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    String name1 = processRunner(in); // runner 1
    collectAndDisplayTime(name1, in);
    String name2 = processRunner(in); // runner 2
    collectAndDisplayTime(name2, in);
    String name3 = processRunner(in); // runner 3
    collectAndDisplayTime(name3, in);

    in.close();
  }

  /**
   * Asks the runner to enter their name 
   * Welcomes them to the Cross Country System
   * Stores the name into a string to be used in collectAndDisplayTime()
   * 
   * @param in the Scanner
   * @return the first and last name of the runner
   */
  private static String processRunner(Scanner in) {
    System.out.print("\nPlease enter runner's first name: ");
    String fName = in.nextLine();

    System.out.print("\nPlease enter runner's last name: ");
    String lName = in.nextLine();

    System.out.println("___________________________________________________________");
    System.out.println("***Welcome " + fName + " " + lName + " to the Cross Country System***");
    System.out.println("___________________________________________________________");

    return fName + " " + lName;
  }
  /**
   * Prompts the user to complete the following steps
   * Asks the user to enter their times in the proper mm:sss.sss format
   * After user enters data, displays a summary of split 1, split 2, split 3 and total time
   * 
   * @param name the runners first name and last name entered from processRunner()
   * @param in the Scanner
   */
  private static void collectAndDisplayTime(String name, Scanner in){
    System.out.println("~~~Please complete the following steps:~~~");
    System.out.println("Please enter your times in a minutes:seconds.milliseconds format (e.g. 5:34.221)");
  
    System.out.print("\nTime to the first mile: ");
    String firstTime = in.nextLine();
    double firstTimeInSec = convertToSeconds(firstTime);
    System.out.print("Time to the second mile: ");
    String secondTime = in.nextLine();
    double secondTimeInSec = convertToSeconds(secondTime);
    System.out.print("Time to the fifth kilometre: ");
    String thirdTime = in.nextLine();
    double ThirdTimeInSec = convertToSeconds(thirdTime);

    System.out.println("\n___________________________________________________________");
    System.out.println("Here is the runner " + name + "'s summary");
    System.out.print("\nFirst split: ");
    System.out.println(convertToTime(firstTimeInSec));
    System.out.print("Second split: ");
    System.out.println(convertToTime(secondTimeInSec - firstTimeInSec));
    System.out.print("Third split: ");
    System.out.println(convertToTime(ThirdTimeInSec - secondTimeInSec));
    System.out.print("Total time: ");
    System.out.println(convertToTime(ThirdTimeInSec));

  } 

  /**
   * Converts a time (double) into a string in time format "334.221" => 5:34.221
   * 
   * @param timeInSec time in seconds only "sss.sss"
   * @return convert time into a minutes:seconds.milleseconds format "mm:ss.sss"
   */
  private static String convertToTime(double timeInSec){
    int minutes = (int) timeInSec / 60;
    double seconds = timeInSec % 60;

    String timeFormatted = String.format("%d:%06.3f", minutes, seconds);
    return timeFormatted;
  }

  /**
   * Converts a time into a double (seconds) "5:34.221" => 334.221
   * 
   * @param timeString time in the format "mm:ss.sss"
   * @return converts time into seconds
   */
  private static double convertToSeconds(String timeString) {
    int colon = timeString.indexOf(":");
    int minutesAsSeconds = Integer.parseInt(timeString.substring(0, colon)) * 60;
    double seconds = Double.parseDouble(timeString.substring(colon + 1));

    return minutesAsSeconds + seconds;
  }
}