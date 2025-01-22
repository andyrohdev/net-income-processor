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
}