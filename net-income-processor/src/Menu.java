import java.util.Map;

class Menu {

    public static void displayMainMenu() {
        System.out.println("Welcome to NIP, Net Income Processor");
        System.out.println("By Andy Roh");
        System.out.println("Main Menu:");
        System.out.println("1. Calculate");
        System.out.println("2. About");
        System.out.println("3. Help");
        System.out.println("4. Exit");
        System.out.print("Please select an option: ");
    }

    public static void displayAbout() {
        System.out.println("Welcome to the NIP (Net Income Processor) App!");
        System.out.println("This tool is designed to help you break down your paycheck, manage deductions, and plan your finances with ease.");
        System.out.println("Whether you're budgeting for the month or exploring future financial goals, NIP provides a clear picture of your earnings.");
        System.out.println("Disclaimer: While this app aims to be as accurate as possible, it is not a substitute for professional financial advice.");
        System.out.println("For detailed instructions and updates, visit our GitHub page.");
    }

    public static void displayHelp() {
        System.out.println("Need a hand? No problem!");
        System.out.println("1. Visit our comprehensive documentation on GitHub for step-by-step guidance.");
        System.out.println("2. Reach out to support at rohjamesandy@gmail.com for any unresolved questions.");
        System.out.println("3. Don’t forget to double-check your inputs, such as state tax rates and deductions, to ensure accuracy.");
        System.out.println("We’re here to help you make the most out of your paycheck calculations!");
    }

    public static void displayPaycheckSummary(double grossIncome, Map<String, Double> fixedDeductions,
                                              Map<String, Double> percentageDeductions,
                                              double totalFixedDeductions, double totalPercentageDeductions, double netIncome) {
        System.out.println("\nPaycheck Summary:");
        System.out.println("Gross Income: $" + grossIncome);

        System.out.println("\nFixed Deductions:");
        for (Map.Entry<String, Double> entry : fixedDeductions.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
        System.out.println("Total Fixed Deductions: $" + totalFixedDeductions);

        double remainingAfterFixed = grossIncome - totalFixedDeductions;

        System.out.println("\nPercentage Deductions:");
        for (Map.Entry<String, Double> entry : percentageDeductions.entrySet()) {
            String deductionName = entry.getKey();
            double percentage = entry.getValue();
            double amount = Math.round((percentage / 100) * remainingAfterFixed * 100.0) / 100.0; // Calculate and round to two decimals
            System.out.println(deductionName + ": " + percentage + "% | Amount: $" + amount);
        }
        System.out.println("Total Percentage Deductions: $" + totalPercentageDeductions);

        System.out.println("\nNet Income: $" + netIncome);
    }

    public static void displayCalculatingMessage() {
        System.out.println("Calculating...");
    }

    public static void displaySavePrompt() {
        System.out.print("Save summary to file? (y/n): ");
    }
}
