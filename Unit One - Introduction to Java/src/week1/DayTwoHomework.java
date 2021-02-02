package week1;

//Each question in order 

class DayTwoHomework {
    public static void main(String[] args) {
       circleArea();
       sphereVolume();
       standardForm();
       calcSlope();
       calcCoins();
    }

    // This method calculates the area of a circle
    private static void circleArea() {
        double radius = 2;
        double area = Math.PI * radius * radius;

        System.out.println(area);
    }

    // This method calculates the volumee of a sphere
    private static void sphereVolume() {
        double radius = 2;
        double volume = Math.PI * radius * radius * radius * 4 / 3;

        System.out.println(volume);
    }

    // This method calculates the standard form given a, b, c, x
    private static void standardForm() {
        double a = 2.2, b = 7, c = 4.3, x = 2.4;
        double y = (a * x * x) + (b * x) + c;
        double rootX1 = (-1 * b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
        double rootX2 = (-1 * b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);

        System.out.println(y);
        System.out.println("The roots are: (" + rootX1 +  ", " + rootX2 + ")");
    }

    // This method calculates the slope given x1, y1, x2, and y2
    private static void calcSlope() {
        double x1 = 4, y1 = 5;
        double x2 = 8, y2 = 10;
        double slope = (y2 - y1)/(x2 - x1);

        System.out.println(slope);
    }

    //This method calculates your total amount in $ based on the number of coins
    private static void calcCoins() {
        int numToonies = 2;
        int numLoonies = 3;
        int numQuarters = 7;
        int numDimes = 5;
        int numNickels = 8;
        int numPennies = 3;

        double total = numToonies * 2 + numLoonies + numQuarters * 0.25 
        + numDimes * 0.1 + numNickels * 0.05 + numPennies * 0.01;

        System.out.println("$" + total);

    }
}
