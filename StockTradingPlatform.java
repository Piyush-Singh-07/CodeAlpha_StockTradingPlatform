

import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {

    static ArrayList<Stock> stocks = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();

    static User user = new User("Piyush", 100000);
    static Portfolio portfolio = new Portfolio();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Available stocks
        stocks.add(new Stock("AAPL", "Apple", 220));
        stocks.add(new Stock("GOOG", "Google", 180));
        stocks.add(new Stock("TSLA", "Tesla", 250));
        stocks.add(new Stock("AMZN", "Amazon", 200));

        int choice;

        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. View User Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayMarketData();
                    break;

                case 2:
                    buyStock();
                    break;

                case 3:
                    sellStock();
                    break;

                case 4:
                   portfolio.displayPortfolio(stocks);
                    break;

                case 5:
                    displayTransactions();
                    break;

                case 6:
                    user.displayUser();
                    break;

                case 7:
                    System.out.println("Thank you for using Stock Trading Platform!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        sc.close();
    }

    static void displayMarketData() {
        System.out.println("\n--- Market Data ---");

        for (Stock stock : stocks) {
            stock.displayStock();
        }
    }

    static Stock findStock(String symbol) {

        for (Stock stock : stocks) {
            if (stock.symbol.equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }

    static void buyStock() {

        System.out.print("Enter stock symbol: ");
        String symbol = sc.next();

        Stock stock = findStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        double totalCost = stock.price * quantity;

        if (user.balance < totalCost) {
            System.out.println("Insufficient balance.");
            return;
        }

        user.balance -= totalCost;

       portfolio.buyStock(stock.symbol, quantity, totalCost);

        transactions.add(
            new Transaction("BUY", stock.symbol, quantity, stock.price)
        );

        System.out.println("Stock purchased successfully!");
        System.out.println("Total Cost: $" + totalCost);
    }

    static void sellStock() {

        System.out.print("Enter stock symbol: ");
        String symbol = sc.next();

        Stock stock = findStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        boolean sold = portfolio.sellStock(stock.symbol, quantity, stock.price);

        if (!sold) {
            System.out.println("You don't own enough shares.");
            return;
        }

        double totalAmount = stock.price * quantity;

        user.balance += totalAmount;

        transactions.add(
            new Transaction("SELL", stock.symbol, quantity, stock.price)
        );

        System.out.println("Stock sold successfully!");
        System.out.println("Amount Received: $" + totalAmount);
    }

    static void displayTransactions() {

        System.out.println("\n--- Transaction History ---");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }
}
