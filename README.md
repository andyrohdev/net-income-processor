
# **Net Income Processor (NIP)**

## **Overview**
Welcome to the **Net Income Processor (NIP)**! This Java-based console application allows users to calculate their net earnings after deductions, offering a simple yet effective tool for paycheck management. Whether you're an hourly worker, salaried employee, or have a paycheck you want to plan for, this tool helps you get a breakdown of your gross income, deductions, and net income.

## **Features**
- **NetIncomeProcessor Calculation Process**: Choose from different pay methods (hourly, salary, or fixed paycheck) to calculate your gross income.
- **State Tax**: Enter your state tax rate, which is considered as a fixed deduction.
- **Add Custom Deductions**: Users can add custom deductions, both fixed amounts and percentage-based, to simulate taxes, insurance, retirement contributions, and other expenses.
- **Undo Deductions**: You can easily undo the last deduction, whether it was fixed or percentage-based.
- **Save Results**: Save the calculated paycheck details to a file in markdown format, with a breakdown of all deductions and the final net income.
- **Interactive Help**: Easy-to-follow menu system, with options for help and about sections.
- **Save to File**: Option to save the paycheck summary details into a .md file for easier storage and use.

## **Getting Started**
To get started with the Paycheck Calculator:

1. Clone the repository:
   ```bash
   git clone https://github.com/andyrohdev/net-income-processor.git
   ```

2. Navigate to the project directory:
   ```bash
   cd net-income-processor
   ```

3. Compile and run the program:
   ```bash
   javac src/NetIncomeProcessor.java
   java -cp src NetIncomeProcessor
   ```

## **Usage**
When you run the program, you will be presented with the following options on the main menu:

1. **Start Calculation**: Initiates the process of calculating your paycheck based on your chosen pay method (hourly, salary, or fixed amount).
   - Enter your wage/salary or paycheck amount.
   - Input your state tax rate.
   - Add custom deductions, either fixed amounts or percentages.
   - Review your paycheck summary and save it to a file if desired.
   
2. **About**: Provides a brief description of the application and its purpose.
   
3. **Help**: Directs you to further resources, such as the documentation, or provides contact details for support.

4. **Quit**: Exit the application.

## **Example**
### Start Calculation Example:
1. **Select Pay Method**: Choose between hourly, salary, or fixed paycheck.
2. **Enter Gross Income**: Based on the selected pay method, enter your wage or salary.
3. **Enter State Tax Rate**: Input the percentage for your state tax.
4. **Add Deductions**: Choose between fixed amount deductions (e.g., $200 for insurance) or percentage-based deductions (e.g., 5% for retirement).
5. **View Results**: The application will display your gross income, deductions, and net income.
6. **Save Results**: Optionally save the results to a markdown file.

### Sample Output:
```
Welcome to the Net Income Processor (NIP)!

Please select an option from the menu:
1. Start Calculation
2. About
3. Help
4. Quit

> 1

Choose your pay method:
1. Hourly
2. Annual Salary
3. Fixed paycheck value

> 1

Enter your hourly wage: $20.00
Enter hours worked this pay period: 80

Enter your state tax rate (e.g., 5 for 5%): 5

Choose your deductions:
1. Add fixed amount deduction
2. Add percentage deduction
3. Done with deductions
4. Undo last deduction

> 1

Enter deduction name: Health Insurance
Enter amount: $100.00

Choose your deductions:
1. Add fixed amount deduction
2. Add percentage deduction
3. Done with deductions
4. Undo last deduction

> 2

Enter deduction name: Retirement
Enter percentage: 5

Done with deductions.

Calculating...

Paycheck Summary:
-------------------
Gross Income: $1600.00
State Tax: $80.00
Fixed Deduction (Health Insurance): $100.00
Percentage Deduction (Retirement 5%): $80.00

Net Income: $1340.00

Would you like to save this summary to a file? (y/n)

> y

Paycheck summary saved to 'paycheck-summary.md' in the current directory.

Thank you for using the Net Income Processor!

```

## **Contributing**
We welcome contributions to NIP! To get involved:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Make your changes and commit (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Create a new pull request.

## **License**
This project is licensed under the MIT License - see the [LICENSE](LICENSE.txt) file for details.

## **Contact**
For further information or assistance, feel free to contact me at **rohjamesandy@gmail.com** or visit the [documentation on GitHub](https://github.com/andyrohdev/net-income-processor).
