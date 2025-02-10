public interface Account {
    void deposit(double amount);
    boolean withdraw(double amount);
    boolean transfer(double amount, Account targetAccount);
    void displayAccountInfo();
    String getAccNumber();
    double getBalance();
}
