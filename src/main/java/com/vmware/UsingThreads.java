package com.vmware;

import java.time.LocalDateTime;

public class UsingThreads {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        myThread1.start();
        myThread2.start();
        myThread3.start();
        System.out.println(Thread.currentThread().getName());

        myThread1.join();
        myThread2.join();
        myThread3.join();
        // myThread1.start(); You cannot start a dead thread.

        MyRunnable myRunnable = new MyRunnable(); //Single Runnable
        Thread thread4 = new Thread(myRunnable);
        Thread thread5 = new Thread(myRunnable);
        thread4.start();
        thread5.start();

        thread4.join();
        thread5.join();

        System.out.println("----");
        Thread alexeyThread = new AlexeyThread(myRunnable);
        alexeyThread.start();

        new Thread(() ->
                System.out.printf
                        ("Running Arun's Thread....Yawnnnnnn!")).start();


        new Thread(() -> {});  //Java 8 FTW!

        Thread busyThread  = new Thread(() -> {
                while(true) {
                    System.out.println("All work and no play..."
                            + Thread.currentThread().getName()
                            + " " + LocalDateTime.now());
                }
        });

        busyThread.setDaemon(true);
        busyThread.start();

        Thread.sleep(5000);
        System.exit(0);
    }
}
