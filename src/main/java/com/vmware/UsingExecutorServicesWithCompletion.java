package com.vmware;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UsingExecutorServicesWithCompletion {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newWorkStealingPool(4);
		ExecutorCompletionService<Integer> executorCompletionService = 
				new ExecutorCompletionService<>(executorService);
		
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println(Thread.currentThread().getId() + " getting to work!");
				Thread.sleep(4000);
				return (int) Thread.currentThread().getId(); //Don't do this at home.
			}		   	
		};
		
		Future<Integer> fi0 = executorCompletionService.submit(callable);
		Future<Integer> fi1 = executorCompletionService.submit(callable);
		Future<Integer> fi2 = executorCompletionService.submit(callable);

		for (int i = 0; i < 3; i++) {
			Future f = executorCompletionService.take();
			System.out.println(f.get());
		}
		
		System.out.println("-----");
		System.out.println(fi0.get());
		System.out.println(fi1.get());
		System.out.println(fi2.get());
	}
}
