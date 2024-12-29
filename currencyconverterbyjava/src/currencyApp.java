import java.util.HashMap;
import java.util.Scanner;

public class currencyApp {

    public static void main(String[] args) {
        HashMap<String, Double> currencyRates = initializeRates();
        displayAvailableCurrencies(currencyRates);

        Scanner scanner = new Scanner(System.in);

        String sourceCurrency = chooseCurrency(scanner, currencyRates, "source");
        if (sourceCurrency == null) {
            System.out.println("Invalid selection for source currency. Program will terminate.");
            scanner.close();
            return;
        }

        String targetCurrency = chooseCurrency(scanner, currencyRates, "target");
        if (targetCurrency == null) {
            System.out.println("Invalid selection for target currency. Program will terminate.");
            scanner.close();
            return;
        }

        System.out.print("Please enter the amount to be converted: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be a positive value.");
            scanner.close();
            return;
        }

        double result = convertCurrency(currencyRates, sourceCurrency, targetCurrency, amount);
        System.out.printf("The converted amount is: %.2f %s\n", result, targetCurrency);

        scanner.close();
    }

    private static HashMap<String, Double> initializeRates() {
        HashMap<String, Double> rates = new HashMap<>();
        rates.put("ZAR", 15.43);
        rates.put("CHF", 0.91);
        rates.put("CNY", 7.09);
        rates.put("AUD", 1.36);
        rates.put("JPY", 131.2);
        rates.put("GBP", 0.74);
        rates.put("INR", 82.5);
        rates.put("USD", 1.0);
        rates.put("EUR", 0.92);
        rates.put("CAD", 1.27);
        return rates;
    }

    private static void displayAvailableCurrencies(HashMap<String, Double> rates) {
        System.out.println("Available currencies:");
        int index = 1;
        for (String currency : rates.keySet()) {
            System.out.println(index + ". " + currency);
            index++;
        }
    }

    private static String chooseCurrency(Scanner scanner, HashMap<String, Double> rates, String currencyType) {
        System.out.print("Enter the number for the " + currencyType + " currency: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > rates.size()) {
            System.out.println("Invalid choice. Please select a valid option.");
            return null;
        }

        int counter = 1;
        for (String currency : rates.keySet()) {
            if (counter == choice) {
                return currency;
            }
            counter++;
        }
        return null;
    }

    private static double convertCurrency(HashMap<String, Double> rates, String source, String target, double amount) {
        double sourceRate = rates.get(source);
        double targetRate = rates.get(target);
        return amount * (targetRate / sourceRate);
    }
}
