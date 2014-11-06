package com.vmware;

public class BankAccount {
    private int pennies = 0;

    public synchronized void deposit(long pennies) {
        synchronized (BankAccount.class) {
            this.pennies += pennies;
            withdraw(1);
        }
    }

    public synchronized long withdraw(long pennies) {
        synchronized (BankAccount.class) {
            if (this.pennies > pennies) {
                this.pennies -= pennies;
                return pennies;
            }
            int result = this.pennies;
            this.pennies = 0;
            return result;
        }
    }

    public int getPennies() {
        synchronized (BankAccount.class) {
            return pennies;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();

        Thread t = new Thread(() -> {
            account.deposit(1000);
        });
        Thread t1 = new Thread(() -> {
            account.deposit(300);
        });
        Thread t2 = new Thread(() -> {
            System.out.println(account.withdraw(500) + " was snatched!");
        });

        t.start();
        t1.start();
        t2.start();

        t.join();
        t1.join();
        t2.join();

        System.out.println(account.getPennies());
    }
}
