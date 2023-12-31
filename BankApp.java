import java.util.Scanner;

class Bank {
    private double accountBalance;

    public Bank(double initialBalance) {
        this.accountBalance = initialBalance;
    }

    public double getBalance() {
        return accountBalance;
    }

    public void deposit(double amount) {
        accountBalance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > accountBalance) {
            System.out.println("\u001B[31mInsufficient funds. Withdrawal failed.\u001B[0m");
            return false;
        }
        accountBalance -= amount;
        return true;
    }
}

class TransactionMachine {
    private Bank userBank;

    public TransactionMachine(Bank bank) {
        this.userBank = bank;
    }

    public void displayMenu() {
        System.out.println("\n\033[1m*******************************************\033[0m");
        System.out.println("\033[1m            Transaction Machine Menu\033[0m");
        System.out.println("\033[1m*******************************************\033[0m");
        System.out.println("1. \u001B[34mWithdraw\u001B[0m");
        System.out.println("2. \u001B[32mDeposit\u001B[0m");
        System.out.println("3. \u001B[33mCheck Balance\u001B[0m");
        System.out.println("0. \u001B[31mExit\u001B[0m");
        System.out.println("\033[1m*******************************************\033[0m");
    }

    public void performTransaction(int choice, double amount) {
        switch (choice) {
            case 1:
                withdraw(amount);
                break;
            case 2:
                deposit(amount);
                break;
            case 3:
                checkBalance();
                break;
            case 0:
                System.out.println("\033[1mThank you for using the Transaction Machine. Goodbye!\033[0m");
                System.exit(0);
            default:
                System.out.println("\u001B[31mInvalid option. Please choose a valid option.\u001B[0m");
        }
    }

    private void withdraw(double amount) {
        if (userBank.withdraw(amount)) {
            System.out.println("\u001B[32mWithdrawal successful.\u001B[0m Current balance: \u001B[33m$"
                    + userBank.getBalance() + "\u001B[0m");
        }
    }

    private void deposit(double amount) {
        userBank.deposit(amount);
        System.out.println("\u001B[32mDeposit successful.\u001B[0m Current balance: \u001B[33m$"
                + userBank.getBalance() + "\u001B[0m");
    }

    private void checkBalance() {
        System.out.println("\u001B[33mCurrent balance: $" + userBank.getBalance() + "\u001B[0m");
    }
}

public class BankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial balance: $");
        double initialBalance = scanner.nextDouble();

        Bank userBank = new Bank(initialBalance);
        TransactionMachine transactionMachine = new TransactionMachine(userBank);

        while (true) {
            transactionMachine.displayMenu();

            System.out.print("\u001B[36mChoose an option:\u001B[0m ");
            int choice = scanner.nextInt();

            if (choice != 0) {
                System.out.print("\u001B[36mEnter amount: $\u001B[0m");
                double amount = scanner.nextDouble();
                if (amount < 0) {
                    System.out.println("\u001B[31mInvalid amount. Please enter a positive value.\u001B[0m");
                    continue;
                }
                transactionMachine.performTransaction(choice, amount);
            } else {
                transactionMachine.performTransaction(choice, 0); // Exit the Transaction Machine
            }
        }
    }
}
