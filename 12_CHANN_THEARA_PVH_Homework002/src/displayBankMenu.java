import java.util.Scanner;

public class displayBankMenu {
    static String Red = "\u001b[31;1m";
    static String Green = "\u001b[32m";
    static String Reset = "\u001b[0m";
    static String
            Blue = "\u001b[34m";
    static String White = "\u001b[97m";
    static String LightYellow = "\u001b[93m";

    private CheckingAccount checkingAccount;
    private SavingAccount savingAccount;
    private double amount;
    private Account targetAcc;

    Scanner scanner = new Scanner(System.in);

    public void BankMenu(){
        int option;
        do {
            System.out.println(White + "\n=====================================> " + LightYellow + "ONLINE BANKING SYSTEM" + White + " <=====================================\n" + Reset);
            System.out.println(Blue + "1. Create An Account" + Reset);
            System.out.println(Blue + "2. Deposit Menu" + Reset);
            System.out.println(Blue + "3. Withdraw Money" + Reset);
            System.out.println(Blue + "4. Transfer Money" + Reset);
            System.out.println(Blue + "5. Display Account Information" + Reset);
            System.out.println(Blue + "6. Delete Account" + Reset);
            System.out.println(Blue + "7. Exit!" + Reset);
            System.out.println(White + "---------------------------------------------------------------------------------------------------" + Reset);
            System.out.print("Choose an Option(1-7)\t:\t");
            option = scanner.nextInt();

            switch (option){
                case 1:
                    CreateAnAccount();
                    break;
                case 2:
                    forDeposit();
                    break;
                case 3:
                    forWithdraw();
                    break;
                case 4:
                    forTransfer();
                    break;
                case 5:
                    checkingAccount.displayAccountInfo();
                    savingAccount.displayAccountInfo();
                    break;
                case 6:
                    forDeleteAccount();
                    break;
                case 7:
                    System.out.println("Keep up a good work.👌✌");
                    System.out.println("Susu ❤ Fighting 😁");
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }while (option!=7);
        scanner.close();
    }

    private void chooseAnAccount(){
        System.out.println(White+"=====================================> "+LightYellow+"CREATE AN ACCOUNT"+White+" <====================================="+Reset);
        System.out.println(Blue+"1. Checking Account"+Reset);
        System.out.println(Blue+"2. Saving Account"+Reset);
        System.out.println(Blue+"3. Back"+Reset);
        System.out.println(White+"---------------------------------------------------------------------------------------------------"+Reset);
    }

    private void chooseAccountForTransfer(){
        System.out.println(White+"=====================================> "+LightYellow+"CREATE AN ACCOUNT"+White+" <====================================="+Reset);
        System.out.println(Blue+"1. Checking Account\t->\tSavings Account"+Reset);
        System.out.println(Blue+"2. Savings Account\t->\tChecking Account"+Reset);
        System.out.println(Blue+"3. Back"+Reset);
        System.out.println(White+"---------------------------------------------------------------------------------------------------"+Reset);
    }

    private void forDeposit(){
        chooseAnAccount();
        System.out.print(LightYellow+"Choose an Account to Deposit\t:\t"+Reset);
        int choice = scanner.nextInt();

        if (choice==1) {
            checkingAccount.deposit(amount);
        } else if (choice==2) {
            savingAccount.deposit(amount);
        }else {
            BankMenu();
        }
    }

    private void forWithdraw(){
        chooseAnAccount();
        System.out.print(LightYellow+"Choose an Account to Withdraw\t:\t"+Reset);
        int choice = scanner.nextInt();

        if (choice==1) {
            checkingAccount.withdraw(amount);
        } else if (choice==2) {
            savingAccount.withdraw(amount);
        }else {
            BankMenu();
        }
    }

    private void forTransfer(){
        chooseAccountForTransfer();
        System.out.print(LightYellow+"Choose an Account to Transfer\t:\t"+Reset);
        int choice = scanner.nextInt();

        if (choice==1) {
            checkingAccount.transfer(amount,targetAcc);
        } else if (choice==2) {
            savingAccount.transfer(amount, targetAcc);
        }else {
            BankMenu();
        }
    }

    private void forDeleteAccount(){
        chooseAnAccount();
        System.out.print(LightYellow+"Choose an Account you want to Delete\t:\t"+Reset);
        int choice = scanner.nextInt();

        System.out.println("Are you sure you want to delete this accout? (Y/N)\t:\t");
        String YesNo = scanner.next();
        if (YesNo.matches("^[yY]$")){
            if (choice==1){
                if (checkingAccount.getBalance() > 0 && savingAccount != null){
                    checkingAccount.transfer(checkingAccount.getBalance(), savingAccount);
                    System.out.println("Transferred all balance from Checking Account to Savings Account!");
                }
                checkingAccount = null;
                System.out.println("Account Deleted Successfully!");
            } else if (choice==2) {
                if (savingAccount.getBalance() > 0 && checkingAccount != null){
                    savingAccount.transfer(savingAccount.getBalance(), checkingAccount);
                    System.out.println("Transferred all balance from Savings Account to Checking Account!");
                }
                savingAccount = null;
                System.out.println("Account Deleted Successfully!");
            } else {
                System.out.println("Account Not Found!");
            }
        }
    }

    private void confirmDeleted(){

    }

    private void CreateAnAccount(){
        chooseAnAccount();
        System.out.print(LightYellow+"What type of account do you want to create? ==> "+Reset);
        int choice = scanner.nextInt();
        String username="";
        String dob="";
        String gender="";
        String phoneNumber="";

        if (choice == 1 || choice == 2){
            System.out.println(White+"\n=====================================> "+LightYellow+"ACCOUNT INFORMATION"+White+" <====================================="+Reset);
            while (true){
                System.out.print(Blue + ">> Enter Username\t: " + Reset);
                username = scanner.next();
                if (username.matches("^[a-zA-Z\\s]+$")){
                    break;
                }else {
                    System.out.println(Red + "Please input only letters!!!" + Reset);
                }
            }

            while (true){
                System.out.print(Blue + ">> Enter Date of Birth (dd-mm-yyyy): " + Reset);
                dob = scanner.next();
                if (dob.matches("^[0-9]{2}-[0-9]{2}-[0-9]{4}+$")){
                    break;
                }else {
                    System.out.println(Red + "Please input only offer format (dd-mm-yyyy)!!!" + Reset);
                }
            }

            while (true){
                System.out.print(Blue + ">> Enter Gender\t: " + Reset);
                gender = scanner.next();
                if (gender.matches("^[Male]{4}||[Female]{6}+$")) {
                    break;
                } else{
                    System.out.println(Red + "Please input only 'Male' or 'Female'!!!" + Reset);
                }
            }

            while (true){
                System.out.print(Blue + ">> Enter Phone Number\t: " + Reset);
                phoneNumber = scanner.next();
                if (phoneNumber.matches("^[0]{1}\\d{8}+$")) {
                    break;
                } else {
                    System.out.println(Red + "Please input the right format (098765432)!!!" + Reset);
                }
            }

            if (choice == 1){
                checkingAccount = new CheckingAccount(username, dob, gender, phoneNumber);
                System.out.println(LightYellow+"\nYour checking account have been created successfully!\n"+Reset);
            } else if (choice == 2) {
                savingAccount = new SavingAccount(username, dob, gender, phoneNumber);
                System.out.println(LightYellow+"\nYour checking account have been created successfully!\n"+Reset);
            }else {
                BankMenu();
            }
        }else {
            scanner.close();
        }
    }
}
