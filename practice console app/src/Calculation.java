import java.util.ArrayList;
import java.util.Scanner;

public class Calculation {
    private final Scanner scanner;

    public Calculation(Scanner scanner) {
        this.scanner = scanner;
    }

    public void performCalculation() {
        double grossIncome = getGrossIncome();
        double stateTaxRate = getStateTaxRate();
        ArrayList<String> fixedDeductionNames = new ArrayList<>();
        ArrayList<Double> fixedDeductionAmounts = new ArrayList<>();
        ArrayList<String> percentageDeductionNames = new ArrayList<>();
        ArrayList<Double> percentageDeductionRates = new ArrayList<>();
        ArrayList<Double> percentageDeductionAmounts = new ArrayList<>(); // Store amounts for percentage deductions

        // Calculate state tax and add to fixed deductions
        double stateTax = (stateTaxRate / 100) * grossIncome;
        stateTax = Math.round(stateTax * 100.0) / 100.0;  // Round to two decimal places
        fixedDeductionNames.add("State Tax");
        fixedDeductionAmounts.add(stateTax);

        // Process deductions with undo option
        processDeductions(fixedDeductionNames, fixedDeductionAmounts, percentageDeductionNames, percentageDeductionRates);

        // Calculate total fixed deductions
        double totalFixedDeductions = 0;
        for (double amount : fixedDeductionAmounts) {
            totalFixedDeductions += amount;
        }
        totalFixedDeductions = Math.round(totalFixedDeductions * 100.0) / 100.0;  // Round to two decimal places

        // Calculate remaining income after fixed deductions
        double remainingAfterFixed = grossIncome - totalFixedDeductions;

        // Calculate total percentage deductions and store amounts
        double totalPercentageDeductions = 0;
        for (double rate : percentageDeductionRates) {
            double deductionAmount = (rate / 100) * remainingAfterFixed;
            deductionAmount = Math.round(deductionAmount * 100.0) / 100.0;  // Round to two decimal places
            percentageDeductionAmounts.add(deductionAmount);
            totalPercentageDeductions += deductionAmount;
        }
        totalPercentageDeductions = Math.round(totalPercentageDeductions * 100.0) / 100.0;  // Round to two decimal places

        // Calculate net income
        double netIncome = remainingAfterFixed - totalPercentageDeductions;

        // Display "Calculating..."
        System.out.println("Calculating...");

        // Display paycheck summary
        displayPaycheckSummary(grossIncome, fixedDeductionNames, fixedDeductionAmounts, percentageDeductionNames, percentageDeductionRates, percentageDeductionAmounts, totalFixedDeductions, totalPercentageDeductions, netIncome);

        // Save paycheck summary
        savePaycheckSummary(grossIncome, fixedDeductionNames, fixedDeductionAmounts, percentageDeductionNames, percentageDeductionRates, percentageDeductionAmounts, totalFixedDeductions, totalPercentageDeductions, netIncome);
    }

    private double getGrossIncome() {
        System.out.println("Choose your pay method:");
        System.out.println("1. Hourly");
        System.out.println("2. Annual Salary");
        System.out.println("3. Fixed paycheck value");
        int payMethod = scanner.nextInt();
        scanner.nextLine();

        switch (payMethod) {
            case 1:
                System.out.print("Enter your hourly wage: $");
                double hourlyWage = scanner.nextDouble();
                System.out.print("Enter hours worked this pay period: ");
                double hoursWorked = scanner.nextDouble();
                return hourlyWage * hoursWorked;
            case 2:
                System.out.print("Enter your annual salary: $");
                double annualSalary = scanner.nextDouble();
                return annualSalary / 26; // Bi-weekly pay periods
            case 3:
                System.out.print("Enter your fixed paycheck value: $");
                return scanner.nextDouble();
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return 0;
        }
    }

    private double getStateTaxRate() {
        System.out.print("Enter your state tax rate (e.g., 5 for 5%): ");
        return scanner.nextDouble();
    }

    private void processDeductions(ArrayList<String> fixedNames, ArrayList<Double> fixedAmounts, ArrayList<String> percentageNames, ArrayList<Double> percentageRates) {
        while (true) {
            System.out.println("1. Add fixed amount deduction");
            System.out.println("2. Add percentage deduction");
            System.out.println("3. Done with deductions");
            System.out.println("4. Undo last deduction");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter deduction name: ");
                String name = scanner.nextLine();
                System.out.print("Enter amount: $");
                double amount = scanner.nextDouble();
                amount = Math.round(amount * 100.0) / 100.0;  // Round to two decimal places
                fixedNames.add(name);
                fixedAmounts.add(amount);
            } else if (choice == 2) {
                System.out.print("Enter deduction name: ");
                String name = scanner.nextLine();
                System.out.print("Enter percentage: ");
                double percentage = scanner.nextDouble();
                percentageNames.add(name);
                percentageRates.add(percentage);
            } else if (choice == 3) {
                break;
            } else if (choice == 4) {
                // Undo last deduction
                undoDeduction(fixedNames, fixedAmounts, percentageNames, percentageRates);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void undoDeduction(ArrayList<String> fixedNames, ArrayList<Double> fixedAmounts, ArrayList<String> percentageNames, ArrayList<Double> percentageRates) {
        if (fixedNames.isEmpty() && percentageNames.isEmpty()) {
            System.out.println("No deductions to undo.");
            return;
        }

        System.out.println("Are you sure you want to delete the last deduction? (y/n)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            if (!fixedNames.isEmpty()) {
                System.out.println("Removing fixed deduction: " + fixedNames.get(fixedNames.size() - 1));
                fixedNames.remove(fixedNames.size() - 1);
                fixedAmounts.remove(fixedAmounts.size() - 1);
            } else if (!percentageNames.isEmpty()) {
                System.out.println("Removing percentage deduction: " + percentageNames.get(percentageNames.size() - 1));
                percentageNames.remove(percentageNames.size() - 1);
                percentageRates.remove(percentageRates.size() - 1);
            }
        }
    }

    private void displayPaycheckSummary(double grossIncome, ArrayList<String> fixedNames, ArrayList<Double> fixedAmounts,
                                        ArrayList<String> percentageNames, ArrayList<Double> percentageRates, ArrayList<Double> percentageAmounts,
                                        double totalFixedDeductions, double totalPercentageDeductions, double netIncome) {
        System.out.println("\nPaycheck Summary:");
        System.out.println("Gross Income: $" + grossIncome);

        System.out.println("\nFixed Deductions:");
        for (int i = 0; i < fixedNames.size(); i++) {
            System.out.println(fixedNames.get(i) + ": $" + fixedAmounts.get(i));
        }
        System.out.println("Total Fixed Deductions: $" + totalFixedDeductions);

        System.out.println("\nPercentage Deductions:");
        for (int i = 0; i < percentageNames.size(); i++) {
            System.out.println(percentageNames.get(i) + ": " + percentageRates.get(i) + "% | Amount: $" + percentageAmounts.get(i));
        }
        System.out.println("Total Percentage Deductions: $" + totalPercentageDeductions);

        System.out.println("\nNet Income: $" + netIncome);
    }

    private void savePaycheckSummary(double grossIncome, ArrayList<String> fixedNames, ArrayList<Double> fixedAmounts,
                                     ArrayList<String> percentageNames, ArrayList<Double> percentageRates, ArrayList<Double> percentageAmounts,
                                     double totalFixedDeductions, double totalPercentageDeductions, double netIncome) {
        System.out.print("Save summary to file? (y/n): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            FileSaver.saveToFile(grossIncome, fixedNames, fixedAmounts, percentageNames, percentageRates, percentageAmounts, totalFixedDeductions, totalPercentageDeductions, netIncome);
        }
    }
}
