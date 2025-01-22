import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculation {
    private final Scanner scanner;
    private final Stack<String> deductionStack;

    public Calculation(Scanner scanner) {
        this.scanner = scanner;
        this.deductionStack = new Stack<>();  // Initialize the stack
    }

    public void performCalculation() {
        double grossIncome = getGrossIncome();
        double stateTaxRate = getStateTaxRate();
        Map<String, Double> fixedDeductions = new HashMap<>();
        Map<String, Double> percentageDeductions = new HashMap<>();

        // Calculate state tax and add to fixed deductions
        double stateTax = (stateTaxRate / 100) * grossIncome;
        stateTax = Math.round(stateTax * 100.0) / 100.0;  // Round to two decimal places
        fixedDeductions.put("State Tax", stateTax);
        deductionStack.push("State Tax"); // Push state tax to the stack

        // Process deductions with undo option
        processDeductions(fixedDeductions, percentageDeductions);

        // Calculate total fixed deductions
        double totalFixedDeductions = 0;
        for (double amount : fixedDeductions.values()) {
            totalFixedDeductions += amount;
        }
        totalFixedDeductions = Math.round(totalFixedDeductions * 100.0) / 100.0;  // Round to two decimal places

        // Calculate remaining income after fixed deductions
        double remainingAfterFixed = grossIncome - totalFixedDeductions;

        // Calculate total percentage deductions
        double totalPercentageDeductions = 0;
        for (double rate : percentageDeductions.values()) {
            double deductionAmount = (rate / 100) * remainingAfterFixed;
            deductionAmount = Math.round(deductionAmount * 100.0) / 100.0;  // Round to two decimal places
            totalPercentageDeductions += deductionAmount;
        }
        totalPercentageDeductions = Math.round(totalPercentageDeductions * 100.0) / 100.0;  // Round to two decimal places

        // Calculate net income
        double netIncome = Math.round(remainingAfterFixed - totalPercentageDeductions) / 100.00; // Round to two decimal places

        // Display "Calculating..."
        Menu.displayCalculatingMessage();

        // Display paycheck summary
        Menu.displayPaycheckSummary(grossIncome, fixedDeductions, percentageDeductions, totalFixedDeductions, totalPercentageDeductions, netIncome);

        // Save paycheck summary
        savePaycheckSummary(grossIncome, fixedDeductions, percentageDeductions, totalFixedDeductions, totalPercentageDeductions, netIncome);
    }

    private double getGrossIncome() {
        double grossIncome = 0;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("Choose your pay method:");
            System.out.println("1. Hourly");
            System.out.println("2. Annual Salary");
            System.out.println("3. Fixed paycheck value");

            // Handle invalid input for integer choice
            try {
                int payMethod = scanner.nextInt();
                scanner.nextLine();  // Clear the buffer

                switch (payMethod) {
                    case 1:
                        System.out.print("Enter your hourly wage: $");
                        double hourlyWage = scanner.nextDouble();
                        System.out.print("Enter hours worked this pay period: ");
                        double hoursWorked = scanner.nextDouble();
                        grossIncome = hourlyWage * hoursWorked;
                        validChoice = true; // Exit the loop
                        break;
                    case 2:
                        System.out.print("Enter your annual salary: $");
                        double annualSalary = scanner.nextDouble();
                        grossIncome = annualSalary / 26; // Bi-weekly pay periods
                        validChoice = true; // Exit the loop
                        break;
                    case 3:
                        System.out.print("Enter your fixed paycheck value: $");
                        grossIncome = scanner.nextDouble();
                        validChoice = true; // Exit the loop
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break; // Loop again until valid input
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        return grossIncome;
    }

    private double getStateTaxRate() {
        System.out.print("Enter your state tax rate (e.g., 5 for 5%): ");
        return scanner.nextDouble();
    }

    private void processDeductions(Map<String, Double> fixedDeductions, Map<String, Double> percentageDeductions) {
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("1. Add fixed amount deduction");
            System.out.println("2. Add percentage deduction");
            System.out.println("3. Done with deductions");
            System.out.println("4. Undo last deduction");
            System.out.print("Choose an option: ");

            // Handle invalid input for integer choice
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Clear the buffer

                switch (choice) {
                    case 1:
                        System.out.print("Enter deduction name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter amount: $");
                        double amount = scanner.nextDouble();
                        amount = Math.round(amount * 100.0) / 100.0;  // Round to two decimal places
                        fixedDeductions.put(name, amount);
                        deductionStack.push(name); // Push deduction name to stack
                        break;
                    case 2:
                        System.out.print("Enter deduction name: ");
                        String percentageName = scanner.nextLine();
                        System.out.print("Enter percentage: ");
                        double percentage = scanner.nextDouble();
                        percentageDeductions.put(percentageName, percentage);
                        deductionStack.push(percentageName); // Push deduction name to stack
                        break;
                    case 3:
                        validChoice = true; // Exit the loop when done
                        break;
                    case 4:
                        // Undo last deduction
                        undoDeduction(fixedDeductions, percentageDeductions);
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break; // Continue looping until valid input
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private void undoDeduction(Map<String, Double> fixedDeductions, Map<String, Double> percentageDeductions) {
        // Check if the stack is empty
        if (deductionStack.isEmpty()) {
            System.out.println("No deductions to undo.");
            return;
        }

        // Get the last added deduction from the stack
        String lastDeductionName = deductionStack.pop();  // Pop the top element from the stack

        // Ask for confirmation to remove the last deduction
        System.out.println("Are you sure you want to delete the last deduction: " + lastDeductionName + "? (y/n)");

        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("y")) {
            if (fixedDeductions.containsKey(lastDeductionName)) {
                // Remove the last fixed deduction
                System.out.println("Removing fixed deduction: " + lastDeductionName);
                fixedDeductions.remove(lastDeductionName);
            } else {
                // Remove the last percentage deduction
                System.out.println("Removing percentage deduction: " + lastDeductionName);
                percentageDeductions.remove(lastDeductionName);
            }
        }
    }

    private void savePaycheckSummary(double grossIncome, Map<String, Double> fixedDeductions, Map<String, Double> percentageDeductions,
                                     double totalFixedDeductions, double totalPercentageDeductions, double netIncome) {
        Menu.displaySavePrompt();
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            FileSaver.saveToFile(grossIncome, fixedDeductions, percentageDeductions, totalFixedDeductions, totalPercentageDeductions, netIncome);
        }
    }
}
