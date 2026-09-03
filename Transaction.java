

public class Transaction {
    String type;
    String stockSymbol;
    int quantity;
    double price;

    Transaction(String type, String stockSymbol, int quantity, double price) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
    }

    void displayTransaction() {
        System.out.println(type + " | " + stockSymbol
                + " | Quantity: " + quantity
                + " | Price: $" + price);
    }
}
