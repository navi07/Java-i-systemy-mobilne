import static java.lang.Math.sqrt;

public class Triangle extends Figure  {

    private double a, b, c;

    public Triangle(double a, double b, double c) {
        try {
            if (a <= 0 || b <= 0 || c <= 0)
                throw new NumberFormatException();
        else {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
        catch (NumberFormatException e){
            System.out.println("Enter the correct value !");
        }
    }

    @Override
    double calculatePerimeter() {
        return (a + b + c);
    }

    @Override
    double calculateArea() {
        double p = calculatePerimeter() / 2;
        return (sqrt(p * (p-a) * (p-b) * (p-c)));
    }

    @Override
    public void print() {
        System.out.println("\nTriangle" + "\nSide a length : " + a + "\nSide b length : " + b +
                "\nSide c length : " + c);
        System.out.println("Perimeter = " + calculatePerimeter() + "\nArea = " + calculateArea());
    }
}
