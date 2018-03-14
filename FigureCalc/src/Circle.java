import static java.lang.Math.PI;

public class Circle extends Figure  {

    private double r;

    public Circle(double r) {
        try {
            if (r <= 0)
                throw new NumberFormatException();
             else
                this.r = r;
        }
        catch (NumberFormatException e){
            System.out.println("Enter the correct value !");
        }
    }

    @Override
    double calculatePerimeter() {
        return (2 * PI * r);
    }

    @Override
    double calculateArea() {
        return (PI * r*r);
    }

    @Override
    public void print() {
        System.out.println("\nCircle" + "\nRadius length : " + r);
        System.out.println("Perimeter = " + calculatePerimeter() + "\nArea = " + calculateArea());
    }
}
