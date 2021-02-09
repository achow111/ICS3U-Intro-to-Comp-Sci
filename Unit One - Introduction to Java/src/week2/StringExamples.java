package week2;

public class StringExamples {
    public static void main(String[] args) {
        //exampleOne();
        //exampleTwo();
        //exampleThree();

    }

    private static void exampleThree() {
        String s1 = new String("Steve"); //going to build a new String EVERYTIME
        String s2 = new String("Steve");
        String s3 = "Steve"; // does not use a contructor
        String s4 = "Steve"; // reuses the existing Steve if it is already there

        //System.out.println(s1 == s2);
        //System.out.println(s1 == s3); //never use == to compare strings

        System.out.println(s1.equals(s2)); // to check if two strings are equal use equals method
        System.out.println(s3 == s4); //using == cto compare String literals returns true
    }

    private static void exampleTwo() {
        String courseCode = "IS3U AP";
        int x = courseCode.length();
        String sub = courseCode.substring(2, 5);    //"SCU" (starts at index and ends at index 4)
        String sub2 = courseCode.substring(2);  //" SCU AP" (starts at 2 and goes to end)

        System.out.println("The length of \"" + courseCode + "\" is: " + x);
    }

    private static void exampleOne() {
        String simpleText = "This is a String.";
        int number = 7;

        System.out.println(simpleText);
        System.out.println(number);
    }
}
