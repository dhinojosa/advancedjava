package com.vmware;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class UsingSorts {
    public static void main(String[] args) throws InterruptedException {
        String[] states = {"Iowa", "California",
                "New Mexico", "Texas", "Alabama", "Georgia"};

        Arrays.sort(states);
        Stream.of(states).forEach(System.out::println);

        System.out.println("----");

        List<String> items = Arrays.asList("Iowa", "California",
                "New Mexico", "Texas", "Alabama", "Georgia");

        Collections.sort(items);
        items.forEach(System.out::println);

        System.out.println("----");

        System.out.println(items.getClass().getCanonicalName());

        System.out.println("----");

        List<American> americans = Arrays.asList(
                new American("Martha", "Stewart", "123-55-3032"),
                new American("Kim", "Kardashian", "422-49-9399"),
                new American("Donald", "Trump", "199-09-1092"),
                new American("Charlie", "Sheen", "399-92-0049"));


        Collections.sort(americans);

        System.out.println(americans);

        System.out.println("----");

        Collections.sort(americans, new PersonFullNameComparator());

        System.out.println(americans);

        System.out.println("----");


        americans.sort((o1, o2) ->
                o1.getLastName().compareTo(o2.getLastName()));

        System.out.println(americans);

        americans.sort(American::compareTo);

        System.out.println(americans);


//        List<String> items2 = new java.util.concurrent.CopyOnWriteArrayList<>();
//        items2.add("Iowa");
//        items2.add("California");
//        items2.add("New Mexico");
//        items2.add("Texas");
//        items2.add("Alabama");
//        items2.add("Georgia");
//
//        Thread t = new Thread() {
//            public void run() {
//                items2.add("Maine");
//            }
//        };
//        t.start();
//
//        Collections.sort(items2);
//        items2.forEach(System.out::println);
//        t.join();
    }
}
