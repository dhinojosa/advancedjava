package com.vmware;


public class InterruptedThread extends Thread {

    public void run() {
        while (!isInterrupted()) {
            System.out.println("Whoa");
            try {
                Thread.sleep(1 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    //Not sure about about any side effects
    public static void main(String[] args) throws InterruptedException {
        InterruptedThread t1 = new InterruptedThread();
        t1.start();
        Thread.currentThread().sleep(1000);
        t1.interrupt();
    }
}
