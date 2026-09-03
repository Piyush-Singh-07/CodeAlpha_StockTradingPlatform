

public class Stock {
    String symbol;
    String name;
    double price;

    Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    void displayStock() {
        System.out.println(symbol + " - " + name + " : $" + price);
    }
}