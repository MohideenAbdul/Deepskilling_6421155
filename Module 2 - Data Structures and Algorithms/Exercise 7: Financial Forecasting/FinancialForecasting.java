import java.util.Scanner;

public class FinancialForecasting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter current value (or type 'exit' to quit): ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            double currentValue;
            try {
                currentValue = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value or 'exit'.");
                continue;
            }

            System.out.print("Enter annual growth rate (in %, e.g., 5 for 5%): ");
            double growthRatePercent;
            try {
                growthRatePercent = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.nextLine();
                continue;
            }

            System.out.print("Enter number of years to forecast: ");
            int years;
            try {
                years = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
                continue;
            }

            double futureValue = forecastFutureValue(currentValue, growthRatePercent / 100, years);

            System.out.printf("Predicted value after %d years: %.2f\n", years, futureValue);
        }
        // scanner.close();
        System.out.println("Exiting Financial Forecasting Tool.");
    }

    static double forecastFutureValue(double value, double growthRate, int years) {
        if (years == 0) {
            return value;
        }
        return forecastFutureValue(value * (1 + growthRate), growthRate, years - 1);
    }
}
