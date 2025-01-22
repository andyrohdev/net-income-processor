import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculation calculation = new Calculation(scanner);

        while (true) {
            Menu.displayMainMenu();
            int mainMenuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (mainMenuChoice) {
                case 1:
                    calculation.performCalculation();
                    break;
                case 2:
                    Menu.displayAbout();
                    break;
                case 3:
                    Menu.displayHelp();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}