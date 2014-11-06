package com.vmware;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicBankAccount {
    private AtomicInteger pennies = new AtomicInteger(0);

    public void deposit(int pennies) {
        this.pennies.addAndGet(pennies);
    }

    public long withdraw(int pennies) {
        boolean result = false;
        int amount = 0;

        while (!result) {
            AtomicInteger currentPennies = this.pennies;
            result = currentPennies.compareAndSet(currentPennies.get(),
                     currentPennies.get() - pennies);
            amount = currentPennies.get() - pennies;
        }
        return amount;
    }

    public int getPennies() {
        return pennies.get();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicBankAccount account = new AtomicBankAccount();

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
