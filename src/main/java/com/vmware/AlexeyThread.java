package com.vmware;


public class AlexeyThread extends Thread {
    public AlexeyThread(Runnable target) {
        super(target);
    }

    @Override
    public void run() {
        super.run();
        System.out.printf("Running Alexey's Thread: %s\n",
          Thread.currentThread().getName());
    }
}
