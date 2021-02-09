package week3;

public class WritingFunctions {
    public static void main(String[] args) {
        // The purpose of a function is to encapsulate or to abstract logic and place it into a method
        //The function performs a specific task (and can return a value)
        int x = 7;
        int y = 8;

        int z = sum(x, y);

        System.out.println(z);

        addOne(x);
        System.out.println(x);

        double x1, y1, x2, y2;

        x1 = 4;
        x2 = 7;
        y1 = -2;
        y2 = 6;

        double slope = getSlope(x1, y1, x2, y2);
    }

    private static double getSlope(double x1, double y1, double x2, double y2) {
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        return deltaY/deltaX;
    }

    private static void addOne(int num1) {
        num1 = num1++;
    }

    private static int sum(int num1, int num2) {
        int result = num1 + num2;

        return result; 
    }
}
