import java.util.Random;

class SavingAccount implements Account {
    private String AccName;
    private String DOB;
    private String Gender;
    private String PhoneNumber;
    private double balance;
    private String AccNumber;
    private final double rate = 0.05;
    private static final Random random = new Random();

    public SavingAccount(String name, String dob, String gender, String phoneNumber) {
        this.AccName = name;
        this.DOB = dob;
        this.Gender = gender;
        this.PhoneNumber = phoneNumber;
    }

    private String randomAccNumber(){
        int accNum = random.nextInt(900000)+100000;
        return String.valueOf(accNum);
    }

    @Override
    public String getAccNumber(){
        return AccNumber;
    }

    @Override
    public double getBalance(){
        return balance;
    }

    @Override
    public void deposit(double amount){
        if (amount > 0){
            balance += amount;
            System.out.println("Received\t:\t$"+displayBankMenu.LightYellow+amount+displayBankMenu.Reset);
            System.out.println("Total Amount\t:\t$"+displayBankMenu.LightYellow+amount+displayBankMenu.Reset);
        }
    }

    @Override
    public boolean withdraw(double amount){
        double minimumBalance = balance*0.02;
        if (amount > 0 && (balance-amount)>=minimumBalance){
            balance -= amount;
            System.out.println("Withdraw\t:\t$"+displayBankMenu.LightYellow+amount+displayBankMenu.Reset);
            System.out.println("Total Balance\t:\t$"+displayBankMenu.LightYellow+amount+displayBankMenu.Reset);
            return true;
        }else {
            System.out.println(displayBankMenu.Red+"Cannot Transfer $"+amount+". At least $"+(balance*0.02)+" must remain in the account."+displayBankMenu.Reset);
            return false;
        }
    }

    @Override
    public boolean transfer(double amount, Account targetAccount){
        if (amount>0 && amount<=balance){
            if (withdraw(amount)){
                System.out.println("Transferred\t:\t$"+amount);
                System.out.println("From\t:\tSaving Account with ID\t:\t"+this.AccNumber);
                System.out.println("To\t:\t"+(targetAccount instanceof CheckingAccount ? "Checking" : "Saving")+"Account with ID\t:\t"+targetAccount.getAccNumber());
                System.out.println("Total Remain\t:\t$"+balance);
                System.out.println("⬇");
                targetAccount.deposit(amount);
                return true;
            }
        }
        return false;
    }

    @Override
    public void displayAccountInfo(){
        System.out.println(displayBankMenu.White+"\n=====================================> "+displayBankMenu.LightYellow+"SAVINGS ACCOUNT"+displayBankMenu.White+" <=====================================\n"+displayBankMenu.Reset);
        System.out.println(displayBankMenu.LightYellow);
        System.out.println("Account Type\t:\tSavings Account");
        System.out.println("Account Number\t: "+AccNumber);
        System.out.println("User Name\t: "+AccName);
        System.out.println("Date of Birth\t: "+DOB);
        System.out.println("Gender\t: "+Gender);
        System.out.println("Phone Number\t: "+PhoneNumber);
        System.out.println("Balance\t: "+balance+"$");
        System.out.println("Interest Rate\t:\t"+(rate*100)+"%");
        System.out.println(displayBankMenu.White+"---------------------------------------------------------------------------------------------------"+displayBankMenu.Reset);
    }
}
