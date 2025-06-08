import java.util.*;

// Custom Exception: for negative withdrawal
class NegativeWithdrawException extends Exception {
    NegativeWithdrawException(String message) {
        super(message);
    }
}

// Custom Exception: for deposit limit exceeded
class MoreAmountException extends Exception {
    MoreAmountException(String message) {
        super(message);
    }
}

// BankAccount class with deposit and withdrawal methods
class BankAccount {
    private double balance;

    BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) throws MoreAmountException {
        if (amount > 50000) {
            throw new MoreAmountException("❌ Deposit amount exceeds ₹50,000 limit.");
        } else {
            balance += amount;
            System.out.println("✅ Amount deposited successfully. Current Balance: ₹" + balance);
        }
    }

    public void withdraw(double amount) throws NegativeWithdrawException {
        if (amount < 0) {
            throw new NegativeWithdrawException("❌ Cannot withdraw a negative amount.");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient funds. Current Balance: ₹" + balance);
        } else {
            balance -= amount;
            System.out.println("✅ Withdrawal successful. Remaining Balance: ₹" + balance);
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class Bankapplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankAccount account = new BankAccount(50000); // initial balance ₹50,000

        System.out.println("🏦 Welcome to Java Bank!");

        while (true) {
            System.out.println("\n📋 Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ₹");
                        double depositAmount = input.nextDouble();
                        account.deposit(depositAmount);
                        break;

                    case 2:
                        System.out.print("Enter withdrawal amount: ₹");
                        double withdrawAmount = input.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;

                    case 3:
                        System.out.println("💰 Current Balance: ₹" + account.getBalance());
                        break;

                    case 4:
                        System.out.println("👋 Thank you for banking with us!");
                        input.close();
                        return;

                    default:
                        System.out.println("❗ Invalid choice. Please try again.");
                }
            } catch (NegativeWithdrawException | MoreAmountException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}