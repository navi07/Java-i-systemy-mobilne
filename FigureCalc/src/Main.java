import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int choice;

        while (!exit) {
            try {
                System.out.println("\nFigure calculator\n");
                System.out.println("Select figure :");
                System.out.println("1. Triangle");
                System.out.println("2. Square");
                System.out.println("3. Circle");
                System.out.println("4. Ellipse");
                System.out.println("5. Exit");
                System.out.print("Your choice : ");

                choice = scanner.nextInt();
                if ((Integer) choice instanceof Integer) {
                    switch (choice) {
                        case 1:
                            System.out.println("You selected Triangle");
                            System.out.println("Input lengths :");
                            System.out.print("a = ");
                            double triangleSideA = scanner.nextDouble();
                            System.out.print("b = ");
                            double triangleSideB = scanner.nextDouble();
                            System.out.print("c = ");
                            double  triangleSideC = scanner.nextDouble();

                            Figure triangle = new Triangle(triangleSideA, triangleSideB, triangleSideC);
                            triangle.print();
                            break;

                        case 2:
                            System.out.println("You selected Square");
                            System.out.println("Input lengths :");
                            System.out.print("a = ");
                            double squareSideA = scanner.nextDouble();
                            System.out.print("b = ");
                            double squareSideB = scanner.nextDouble();

                            Figure square = new Square(squareSideA, squareSideB);
                            square.print();
                            break;

                        case 3:
                            System.out.println("You selected Circle");
                            System.out.println("Input lengths :");
                            System.out.print("r = ");
                            double radius = scanner.nextDouble();

                            Figure circle = new Circle(radius);
                            circle.print();
                            break;

                        case 4:
                            System.out.println("You selected Ellipse");
                            System.out.println("Input lengths :");
                            System.out.print("Driveshaft a = ");
                            double driveshaftA = scanner.nextDouble();
                            System.out.print("Driveshaft b = ");
                            double driveshaftB = scanner.nextDouble();

                            Figure ellipse = new Ellipse(driveshaftA, driveshaftB);
                            ellipse.print();
                            break;

                        case 5:
                            exit = true;
                            break;
                    }
                } else {
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter the correct value !");
                scanner.next();
            }
        }
        System.exit(0);
    }
}
