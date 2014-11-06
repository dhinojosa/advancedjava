package com.vmware;

import org.junit.Test;

public class MySingletonTest {
//	@Test
//	public final void testGetInstance() {
//		MySingleton myInstance = MySingleton.getInstance();
//		System.out.println(myInstance.hashCode());
//	}
//	
	@Test
	public void testGetInstanceOnDifferentThreads() {
		Thread t1 = new Thread(() -> {
			MySingleton myInstance = MySingleton.getInstance();
			System.out.println(myInstance.hashCode());
		});
		
		Thread t2 = new Thread(() -> {
			MySingleton myInstance = MySingleton.getInstance();
			System.out.println(myInstance.hashCode());
		});
		
		t1.start();
		t2.start();
	}
	
//	@Test
//	public void testGetInstanceOnDifferentThreads() {
//		Thread t1 = new Thread(() -> {
//			HolderSingleton myInstance = HolderSingleton.getInstance();
//			System.out.println(myInstance.hashCode());
//		});
//		
//		Thread t2 = new Thread(() -> {
//			HolderSingleton myInstance = HolderSingleton.getInstance();
//			System.out.println(myInstance.hashCode());
//		});
//		
//		t1.start();
//		t2.start();
//	}
}
