import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class FileSaver {
    public static void saveToFile(
            double grossIncome,
            List<String> fixedNames,
            List<Double> fixedAmounts,
            List<String> percentageNames,
            List<Double> percentageRates,
            List<Double> percentageAmounts,
            double totalFixedDeductions,
            double totalPercentageDeductions,
            double netIncome
    ) {
        try {
            String filePath = "paycheck_summary.md";
            FileWriter writer = new FileWriter(filePath);

            writer.write("# Paycheck Summary\n\n");
            writer.write("## Gross Income\n");
            writer.write("| Description | Amount |\n");
            writer.write("|-------------|--------|\n");
            writer.write("| Gross Income | $" + grossIncome + " |\n");

            writer.write("\n## Fixed Deductions\n");
            writer.write("| Deduction Name | Amount |\n");
            writer.write("|----------------|--------|\n");

            for (int i = 0; i < fixedNames.size(); i++) {
                writer.write("| " + fixedNames.get(i) + " | $" + fixedAmounts.get(i) + " |\n");
            }
            writer.write("| **Total Fixed Deductions** | **$" + totalFixedDeductions + "** |\n");

            writer.write("\n## Percentage Deductions\n");
            writer.write("| Deduction Name | Rate (%) | Amount |\n");
            writer.write("|----------------|----------|--------|\n");

            for (int i = 0; i < percentageNames.size(); i++) {
                writer.write("| " + percentageNames.get(i) + " | " + percentageRates.get(i) + "% | $" + percentageAmounts.get(i) + " |\n");
            }
            writer.write("| **Total Percentage Deductions** |  | **$" + totalPercentageDeductions + "** |\n");

            writer.write("\n## Net Income\n");
            writer.write("| Description | Amount |\n");
            writer.write("|-------------|--------|\n");
            writer.write("| Net Income | $" + netIncome + " |\n");

            writer.close();

            File file = new File(filePath);
            System.out.println("Paycheck summary has been saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
