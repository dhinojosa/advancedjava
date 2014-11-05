package com.vmware;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.format("Running on MyRunnable thread %s\n",
                Thread.currentThread().getName());
    }
}
