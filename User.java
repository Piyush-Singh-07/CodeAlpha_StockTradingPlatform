

public class User {
    String name;
    double balance;

    User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    void displayUser() {
        System.out.println("User Name: " + name);
        System.out.println("Balance: $" + balance);
    }
}