import java.util.HashMap;
import java.util.ArrayList;

public class Portfolio {

    HashMap<String, Integer> holdings = new HashMap<>();
    HashMap<String, Double> investedAmount = new HashMap<>();

    void buyStock(String symbol, int quantity, double totalCost) {

        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);

        investedAmount.put(
            symbol,
            investedAmount.getOrDefault(symbol, 0.0) + totalCost
        );
    }

    boolean sellStock(String symbol, int quantity, double sellPrice) {

        int currentQuantity = holdings.getOrDefault(symbol, 0);

        if (currentQuantity < quantity) {
            return false;
        }

        double averageCost =
            investedAmount.get(symbol) / currentQuantity;

        double remainingInvestment =
            investedAmount.get(symbol) - (averageCost * quantity);

        if (currentQuantity == quantity) {
            holdings.remove(symbol);
            investedAmount.remove(symbol);
        } else {
            holdings.put(symbol, currentQuantity - quantity);
            investedAmount.put(symbol, remainingInvestment);
        }

        return true;
    }

    void displayPortfolio(ArrayList<Stock> stocks) {

        System.out.println("\n--- Portfolio ---");

        if (holdings.isEmpty()) {
            System.out.println("No stocks owned.");
            return;
        }

        double totalCurrentValue = 0;
        double totalInvested = 0;

        for (String symbol : holdings.keySet()) {

            int quantity = holdings.get(symbol);
            double invested = investedAmount.get(symbol);

            double currentPrice = 0;

            for (Stock stock : stocks) {
                if (stock.symbol.equalsIgnoreCase(symbol)) {
                    currentPrice = stock.price;
                    break;
                }
            }

            double currentValue = quantity * currentPrice;
            double profitLoss = currentValue - invested;

            totalCurrentValue += currentValue;
            totalInvested += invested;

            System.out.println(
                symbol + " : " + quantity +
                " shares | Current Value: $" +
                currentValue +
                " | Profit/Loss: $" +
                profitLoss
            );
        }

        System.out.println("-----------------------------");
        System.out.println("Total Invested: $" + totalInvested);
        System.out.println("Current Portfolio Value: $" + totalCurrentValue);
        System.out.println(
            "Total Profit/Loss: $" +
            (totalCurrentValue - totalInvested)
        );
    }
}