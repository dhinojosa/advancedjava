package com.vmware;

public class NotifiedBankAccount {
	private int pennies = 0;

	public synchronized void deposit(long pennies) throws InterruptedException {
		this.pennies += pennies;
		withdraw(1);
		System.out.println(Thread.currentThread().getName() + "We are going to notify threads that there is money");
		notifyAll();
	}

	public synchronized long withdraw(long pennies) throws InterruptedException {
		while(pennies > this.pennies) {
			System.out.println(Thread.currentThread().getName() + "is going wait");
			wait();
			System.out.println(Thread.currentThread().getName() + "is waking up and trying again");
		}
		this.pennies -= pennies;
		return pennies;
	}

	public synchronized int getPennies() {
	    return pennies;
	}

	public static void main(String[] args) throws InterruptedException {
		NotifiedBankAccount account = new NotifiedBankAccount();

		
		Thread t0 = new Thread(() -> {
			try {
				System.out.println(account.withdraw(3000) + "was snatched");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "w3000");
		
		Thread t1 = new Thread(() -> {
			try {
				account.withdraw(300);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "w300");
		
		
		Thread t2 = new Thread(() -> {
			try {
				account.withdraw(1300);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "w1300");
		
		Thread t3 = new Thread(() -> {
			try {
				account.deposit(6000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "d6000");
		

		t0.setPriority(10);
		t1.setPriority(5);
		t2.setPriority(5);
		t3.setPriority(5);
		
		t0.start();
		t1.start();
		t2.start();
		Thread.sleep(500);
		t3.start();
		
		t0.join();
		t1.join();
		t2.join();
        t3.join();
        
		System.out.println(account.getPennies());
	}
}
