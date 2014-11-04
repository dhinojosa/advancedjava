package com.vmware;


import java.util.function.BiConsumer;

public class UsingMyBag {
    public static void main(String[] args) {
        MyBag<String> myBag = new MyBag<>();
        myBag.add("Fi");
        myBag.add("Fo");
        myBag.add("Thumb");

        myBag.forEach((DoubleConsumer<Integer, String>) (integer, s) ->
                System.out.format("(Double) count: %d, item: %s\n", integer, s));
        myBag.forEach((BiConsumer<Integer, String>) (integer, s) ->
                System.out.format("(Bi) count: %d, item: %s\n", integer, s));
    }
}
