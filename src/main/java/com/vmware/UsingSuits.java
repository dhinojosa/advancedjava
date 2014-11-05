package com.vmware;


import java.util.Arrays;

public class UsingSuits {

    public static void guessCard(Suits s) {
        System.out.println("You are using suit " + s.ordinal());
    }

    public static void main(String[] args) throws InterruptedException {
        for (Suits s : Suits.values()) {
            System.out.println(s);
        }

        guessCard(Suits.DIAMONDS);

        Arrays.stream(Suits.values()).map(Suits::ordinal)
                .forEach(System.out::println);

//
//        final IlyasSingleton s = IlyasSingleton.getInstance();
//        s.setI(3);
//        System.out.println(s.getI());
//
//        final IlyasSingleton s1 = IlyasSingleton.getInstance();
//        System.out.println(s1 == s);

        Thread thread1 = new Thread() {
            public void run() {
                System.out.println
                        ("1 " + IlyasSingleton.INSTANCE.hashCode());
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                System.out.println("2 " + IlyasSingleton.INSTANCE.hashCode());
            }
        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
