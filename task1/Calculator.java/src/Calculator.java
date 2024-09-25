
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            
            while (!exit) {
                System.out.println("\n==== Calculator Menu ====");
                System.out.println("1. Basic Arithmetic");
                System.out.println("2. Scientific Calculations");
                System.out.println("3. Unit Conversion");
                System.out.println("4. Exit");
                System.out.print("Select an option (1-4): ");
                
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1 : basicArithmetic(scanner);
                    case 2 : scientificCalculations(scanner);
                    case 3 : unitConversion(scanner);
                    case 4 : exit = true;
                    default : System.out.println("Invalid option. Try again.");
                }
            }
        }
        System.out.println("Calculator Closed.");
    }

   
    private static void basicArithmetic(Scanner scanner) {
        System.out.println("\n--- Basic Arithmetic ---");
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        System.out.println("Choose an operation: +, -, *, /");
        char operation = scanner.next().charAt(0);

        switch (operation) {
            case '+' : System.out.println("Result: " + (num1 + num2));
            case '-' : System.out.println("Result: " + (num1 - num2));
            case '*' : System.out.println("Result: " + (num1 * num2));
            case '/' : {
                if (num2 != 0) {
                    System.out.println("Result: " + (num1 / num2));
                } else {
                    System.out.println("Error: Cannot divide by zero.");
                }
            }
            default : System.out.println("Invalid operation.");
        }
    }

   
    private static void scientificCalculations(Scanner scanner) {
        System.out.println("\n--- Scientific Calculations ---");
        System.out.println("1. Square Root");
        System.out.println("2. Exponentiation");
        System.out.print("Select an option (1-2): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 : {
                System.out.print("Enter a number: ");
                double num = scanner.nextDouble();
                if (num >= 0) {
                    System.out.println("Square Root: " + Math.sqrt(num));
                } else {
                    System.out.println("Error: Cannot calculate square root of a negative number.");
                }
            }
            case 2 : {
                System.out.print("Enter the base: ");
                double base = scanner.nextDouble();
                System.out.print("Enter the exponent: ");
                double exponent = scanner.nextDouble();
                System.out.println("Result: " + Math.pow(base, exponent));
            }
            default : System.out.println("Invalid option.");
        }
    }

    
    private static void unitConversion(Scanner scanner) {
        System.out.println("\n--- Unit Conversion ---");
        System.out.println("1. Temperature (Celsius to Fahrenheit / Fahrenheit to Celsius)");
        System.out.println("2. Currency (USD to EUR / EUR to USD)");
        System.out.print("Select an option (1-2): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 : temperatureConversion(scanner);
            case 2 : currencyConversion(scanner);
            default : System.out.println("Invalid option.");
        }
    }

    
    private static void temperatureConversion(Scanner scanner) {
        System.out.println("\n--- Temperature Conversion ---");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.print("Select an option (1-2): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 : {
                System.out.print("Enter temperature in Celsius: ");
                double celsius = scanner.nextDouble();
                double fahrenheit = (celsius * 9/5) + 32;
                System.out.println("Temperature in Fahrenheit: " + fahrenheit);
            }
            case 2 : {
                System.out.print("Enter temperature in Fahrenheit: ");
                double fahrenheit = scanner.nextDouble();
                double celsius = (fahrenheit - 32) * 5/9;
                System.out.println("Temperature in Celsius: " + celsius);
            }
            default : System.out.println("Invalid option.");
        }
    }

   
    private static void currencyConversion(Scanner scanner) {
        final double USD_TO_EUR = 0.85; // Example rate
        final double EUR_TO_USD = 1.18; // Example rate

        System.out.println("\n--- Currency Conversion ---");
        System.out.println("1. USD to EUR");
        System.out.println("2. EUR to USD");
        System.out.print("Select an option (1-2): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 : {
                System.out.print("Enter amount in USD: ");
                double usd = scanner.nextDouble();
                System.out.println("Amount in EUR: " + (usd * USD_TO_EUR));
            }
            case 2 : {
                System.out.print("Enter amount in EUR: ");
                double eur = scanner.nextDouble();
                System.out.println("Amount in USD: " + (eur * EUR_TO_USD));
            }
            default : System.out.println("Invalid option.");
        }
    }
}

