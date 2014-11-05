package com.vmware;

public class StoppableThread extends Thread {
    private volatile boolean shouldBeRunning = true;

    //TODO: Will this work?
    public void run() {
        while (shouldBeRunning) {
            System.out.println("Stoppable Thread Running");
            try {
                Thread.sleep(1 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void takeItDown() {
        this.shouldBeRunning = false;
        this.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        StoppableThread t = new StoppableThread();
        t.start();
        Thread.sleep(6000); //main 6 secs
        t.takeItDown();
    }
}