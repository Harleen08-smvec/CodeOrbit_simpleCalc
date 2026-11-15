import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * SimpleCalculator
 * A console-based Java application that performs basic arithmetic operations:
 * Addition (+), Subtraction (-), Multiplication (*), Division (/), and Modulo (%).
 * 
 * Features:
 * - Reads user input using Scanner.
 * - Robust exception handling for invalid numeric inputs and division by zero.
 * - Interactive loop allowing multiple calculations until user chooses to exit.
 */
public class SimpleCalculator {

    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;

        System.out.println("=========================================");
        System.out.println("        Welcome to Simple Calculator     ");
        System.out.println("=========================================");

        // Main calculation loop
        while (continueCalculating) {
            try {
                // Prompt user for the first number
                System.out.print("\nEnter the first number: ");
                double num1 = readNumber(scanner);

                // Prompt user for the arithmetic operator
                System.out.print("Enter an operator (+, -, *, /, %): ");
                char operator = readOperator(scanner);

                // Prompt user for the second number
                System.out.print("Enter the second number: ");
                double num2 = readNumber(scanner);

                // Perform the calculation and handle potential arithmetic exceptions
                double result = calculate(num1, num2, operator);

                // Display the formatted result
                System.out.println("-----------------------------------------");
                System.out.printf("Result: %.4f %c %.4f = %.4f%n", num1, operator, num2, result);
                System.out.println("-----------------------------------------");

            } catch (ArithmeticException e) {
                // Handle mathematical errors like division by zero
                System.out.println("\n[Math Error] " + e.getMessage());
            } catch (IllegalArgumentException e) {
                // Handle unsupported operator errors
                System.out.println("\n[Input Error] " + e.getMessage());
            } catch (Exception e) {
                // Catch-all for any other unexpected exceptions
                System.out.println("\n[Error] An unexpected error occurred: " + e.getMessage());
            }

            // Ask user if they wish to perform another calculation
            System.out.print("\nDo you want to perform another calculation? (yes/no): ");
            String choice = scanner.next().trim().toLowerCase();
            if (!choice.equals("yes") && !choice.equals("y")) {
                continueCalculating = false;
                System.out.println("\nThank you for using Simple Calculator. Goodbye!");
            }
        }

        // Close scanner to release system resources
        scanner.close();
    }

    /**
     * Reads a valid double number from the scanner.
     * Uses exception handling to recover from invalid input.
     *
     * @param scanner Scanner instance to read from
     * @return a valid double value
     */
    private static double readNumber(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                // Clear the invalid input from scanner buffer
                String invalidInput = scanner.next();
                System.out.print("Invalid input ('" + invalidInput + "'). Please enter a valid number: ");
            }
        }
    }

    /**
     * Reads a valid arithmetic operator from the scanner.
     *
     * @param scanner Scanner instance to read from
     * @return valid character representing the operator
     */
    private static char readOperator(Scanner scanner) {
        while (true) {
            String input = scanner.next().trim();
            if (input.length() == 1) {
                char op = input.charAt(0);
                if (op == '+' || op == '-' || op == '*' || op == '/' || op == '%') {
                    return op;
                }
            }
            System.out.print("Invalid operator. Please enter one of (+, -, *, /, %): ");
        }
    }

    /**
     * Performs the arithmetic calculation based on the given operator.
     * Throws ArithmeticException when dividing or modulo by zero.
     *
     * @param a        First operand
     * @param b        Second operand
     * @param operator Arithmetic operator (+, -, *, /, %)
     * @return The result of the arithmetic operation
     * @throws ArithmeticException      if division or modulo by zero is attempted
     * @throws IllegalArgumentException if an invalid operator is passed
     */
    public static double calculate(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                // In Java double precision, dividing by 0.0 results in Infinity instead of throwing.
                // We explicitly validate and throw an ArithmeticException to handle it gracefully.
                if (b == 0.0) {
                    throw new ArithmeticException("Division by zero is undefined.");
                }
                return a / b;
            case '%':
                // Modulo by zero is also undefined
                if (b == 0.0) {
                    throw new ArithmeticException("Modulo by zero is undefined.");
                }
                return a % b;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
