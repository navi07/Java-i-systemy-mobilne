public class Square extends Figure  {

    private double a, b;

    public Square(double a, double b) {
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
        return 2 * (a + b);
    }

    @Override
    double calculateArea() {
        return (a * b);
    }

    @Override
    public void print() {
        System.out.println("\nSquare" + "\nSide a length : " + a + "\nSide b length : " + b);
        System.out.println("Perimeter = " + calculatePerimeter() + "\nArea = " + calculateArea());
    }
}
