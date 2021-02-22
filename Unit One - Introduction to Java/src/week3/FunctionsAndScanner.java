package week3;

public class FunctionsAndScanner {
    public static void main(String[] args) {
        int number = 53273;

        int sum = getSum(number);
        System.out.println(sum);

        sum = getSumVersion2(number);
        System.out.println(sum);

        String timeString = "5:34.221";
        double timeInSeconds = convertToSeconds(timeString);
        System.out.println(timeInSeconds);
    }

    private static double convertToSeconds(String timeString) {
        int colon = timeString.indexOf(":");
        int minutesAsSeconds = Integer.parseInt(timeString.substring(0,colon)) * 60;  
        double seconds = Double.parseDouble(timeString.substring(colon + 1)); 
        return minutesAsSeconds + seconds;
    }

    private static int getSumVersion2(int number) {
        String numAsString = "" + number;
        int digit1 = Integer.parseInt(numAsString.substring(0,1));
        int digit2 = Integer.parseInt(numAsString.substring(1,2));
        int digit3 = Integer.parseInt(numAsString.substring(2,3));
        int digit4 = Integer.parseInt(numAsString.substring(3,4));
        int digit5 = Integer.parseInt(numAsString.substring(4,6));

        return digit1 + digit2 + digit3 + digit4 + digit5;
    }

    public static int getSum(int number) {
        int digit1 = number / 10000;
        int digit2 = number / 1000 % 10;
        int digit3 = number / 100 % 10;
        int digit4 = number / 10 % 10;
        int digit5 = number % 10;

        return digit1 + digit2 + digit3 + digit4 + digit5;
    }
    
}
