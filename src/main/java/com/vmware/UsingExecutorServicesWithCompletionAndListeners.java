package com.vmware;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class UsingExecutorServicesWithCompletionAndListeners {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newWorkStealingPool(2);
		ListeningExecutorService listeningExecutorService = 
				MoreExecutors.listeningDecorator(executorService); 
		
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println(Thread.currentThread().getId() + " getting to work!");
				Thread.sleep(4000);
				return (int) Thread.currentThread().getId(); //Don't do this at home.
			}		   	
		};
		
		ListenableFuture<Integer> f = listeningExecutorService.submit(callable);
		
		Futures.addCallback(f, new FutureCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				System.out.println("Hooray the answer is" + result);
			}

			@Override
			public void onFailure(Throwable t) {
				System.out.println("Boom!");
				t.printStackTrace();
			}		
		});
		
//		Thread.sleep(10000);
		
//		System.out.println("The answer should still be"  + f.get());
		listeningExecutorService.shutdown();

		listeningExecutorService.awaitTermination(50, TimeUnit.MINUTES);
	}
}
