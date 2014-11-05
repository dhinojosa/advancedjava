package com.vmware;

public class BankAccount {
    private long pennies = 0L;

    public void deposit(long pennies) {
        this.pennies += pennies;
    }

    public long withdraw(long pennies) {
        if (this.pennies > pennies) {
            this.pennies -= pennies;
            return pennies;
        }
        long result = this.pennies;
        this.pennies = 0;
        return result;
    }

    public long getPennies() {
        return pennies;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Thread t = new Thread(() ->{
            account.deposit(1000);
        });
        Thread t1 = new Thread(() ->{
            account.deposit(300);
        });
        Thread t2 = new Thread(() ->{
            account.withdraw(500);
        });

        t.start();
        t1.start();
        t2.start();

        System.out.println(account.getPennies());
    }
}
