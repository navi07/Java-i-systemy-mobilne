import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

public class Ellipse extends Figure  {

    private double a, b;

    public Ellipse(double a, double b) {
        try {
            if (a <= 0 || b <= 0)
                throw new NumberFormatException();
            else {
                this.a = a;
                this.b = b;
            }
        }
        catch (NumberFormatException e){
            System.out.println("Enter the correct value to build a figure !");
        }
    }

    @Override
    double calculatePerimeter() {
        return PI*((3/2)*(a + b) - sqrt(a*b));
    }

    @Override
    double calculateArea() {
        return (PI * a * b);
    }

    @Override
    public void print() {
        System.out.println("\nEllipse" + "\nDriveshaft a length : " + a + "\nDriveshaft b length : " + b);
        System.out.println("Perimeter = " + calculatePerimeter() + "\nArea = " + calculateArea());
    }
}
