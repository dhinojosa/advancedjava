package com.vmware;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.format("This is running on thread %s\n",
                Thread.currentThread().getName());
    }
}
