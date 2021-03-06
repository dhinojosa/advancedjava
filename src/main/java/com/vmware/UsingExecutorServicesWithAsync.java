package com.vmware;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UsingExecutorServicesWithAsync {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newWorkStealingPool(4);
		
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println(Thread.currentThread().getName() + " getting to work!");
				Thread.sleep(4000);
				return 10 + 100;
			}		   	
		};
		
		Future<Integer> future = executorService.submit(callable);
		
		System.out.println("Getting started");
		while (!future.isDone()) {
			System.out.println(Thread.currentThread().getName() + "Doing other things");
			Thread.sleep(1000);
		}
		
		
		System.out.println("The future says" + future.get());
		System.out.println("Ended started");
	}
}
