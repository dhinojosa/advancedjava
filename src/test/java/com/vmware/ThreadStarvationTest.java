package com.vmware;

import org.junit.Test;

public class ThreadStarvationTest {
	
	public static class MyResource {
		public synchronized void doSomething() 
				throws InterruptedException {
			 System.out.println(Thread.currentThread().getName() + "starting");
	         Thread.sleep(6000);
	         System.out.println(Thread.currentThread().getName() + "end");
		}
	}
	
	@Test
	public void testStarvation() throws InterruptedException {
		MyResource myResource = new MyResource();
		
		Thread t1 = new Thread(() -> {
			try {
				myResource.doSomething();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "t1");
		
		Thread t2 = new Thread(() -> {
			try {
				myResource.doSomething();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "t2");
		
		Thread t3 = new Thread(() -> {
			try {
				myResource.doSomething();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "t3");
		
		Thread t4 = new Thread(() -> {
			try {
				myResource.doSomething();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "t4");
		
		t1.setPriority(5);
		t2.setPriority(9);
		t3.setPriority(4);
		t4.setPriority(10);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
	}
}
