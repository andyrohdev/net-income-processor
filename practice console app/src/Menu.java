import java.util.Map;

class Menu {

    public static void displayMainMenu() {
        System.out.println("Welcome to Paycheck Calculator");
        System.out.println("By, Andy Roh");
        System.out.println("Main Menu:");
        System.out.println("1. Calculate");
        System.out.println("2. About");
        System.out.println("3. Help");
        System.out.println("4. Exit");
        System.out.print("Please select an option: ");
    }

    public static void displayAbout() {
        System.out.println("This is a paycheck calculator app to help you calculate net earnings after deductions.");
        System.out.println("Disclaimer: This tool is not 100% accurate. It serves to have a general guideline for expectations and future planning.");
        System.out.println("For best use, please refer to the documentation on GitHub.");
    }

    public static void displayHelp() {
        System.out.println("If you need assistance, check the documentation or contact support at rohjamesandy@gmail.com");
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

        System.out.println("\nPercentage Deductions:");
        for (Map.Entry<String, Double> entry : percentageDeductions.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "% | Amount: $" + percentageDeductions.get(entry.getKey()));
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
