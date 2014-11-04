package com.vmware;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class MyBag <E> {
    private ArrayList<E> list = new ArrayList<>();


    public void add(E e) {
        list.add(e);
    }

    public void forEach(BiConsumer<Integer, E> biConsumer) {
        int i = 1;
        for (E e: list) {
            biConsumer.accept(i++, e);
        }
    }

    public void forEach(DoubleConsumer<Integer, E> doubleConsumer) {
        for (E e: list) {
            doubleConsumer.accept(1, e);
        }
    }
}
