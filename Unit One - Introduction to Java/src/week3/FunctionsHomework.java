package week3;

public class FunctionsHomework {
    public static void main(String[] args) {
        System.out.println(purchase(985, 5.5));
        AreaAndPerimeter(4.5, 2.3);
        System.out.println(MinInYear(1));
        System.out.println(DistLight(1));
        System.out.println(WinPercent(110, 44) + "%");
        System.out.println(calcMomentum(4, 12));
        farToCel(98);
        squareAndSquareRoot(4);
        Pay(10);
        System.out.println(CalcKinEnergy(100, 100));
    }

    private static double CalcKinEnergy(double mass, double speed) {
        return 1/2 * mass * Math.pow(speed,2);
    }

    private static void Pay(int sales) {
        System.out.println(0.27 * sales);
    }

    private static void squareAndSquareRoot(double num) {
        System.out.println(Math.pow(num,2));
        System.out.println(Math.sqrt(num));
    }

    private static void farToCel(int i) {
    }

    private static double calcMomentum(double mass, double velocity) {
        return mass * velocity;
    }

    private static double WinPercent(int wins, int losses) {
        double win = (double) (wins)/(double)(wins + losses) * 100;
        return win;
    }

    private static double DistLight(double year) {
        double distance = year * 31536000 * 3 * Math.pow(10,8);
        return distance;
    }

    private static double MinInYear(double year) {
        return year * 365 * 24 * 60;
    }

    private static void AreaAndPerimeter(double length, double width) {
        double perimeter = length + width;
        System.out.println("The perimeter is "+ perimeter + ".");
        double area = length * width;
        System.out.println("The area is "+ area + ".");
    }

    private static double purchase(double price, double tax) {
        return price * ((100 + tax)/100);
    }
}
