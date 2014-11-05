package com.vmware;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UsingMethodReferences {

    public static class MyIntegerParser {
        public Integer parse(String string) {
            return Integer.parseInt(string);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        List<String> numbersAsStrings = new ArrayList<>();
        numbersAsStrings.add("1");
        numbersAsStrings.add("2");
        numbersAsStrings.add("3");

        IntStream stream = IntStream.range(1, 10).map(s -> s * 50);

        MyIntegerParser mip = new MyIntegerParser();

        System.out.println(numbersAsStrings.stream().map(mip::parse).map(i -> i * 10)
                .collect(Collectors.toList()));

        Stream<List<Integer>> streams = Stream.of(
                Lists.newArrayList(1,2),
                Lists.newArrayList(3,5,6),
                Lists.newArrayList(7,8)
        );
        
//        System.out.println(
//                streams.<Integer>flatMap(lst ->
//                        lst.stream().map(i -> i * 20))
//                        .filter(j -> j % 3 == 0)
//                        .collect(Collectors.<Integer>summingInt(value -> value)));

        
        System.out.println(
                streams.<Integer>flatMap(lst ->
                        lst.parallelStream().parallel().map(i -> {
                                    System.out.println(
                                            Thread.currentThread().getId());
                                    return i * 20;
                                }
                        ))
                                .filter(j -> j % 4 == 0)
                                .reduce(10, (total, next) -> {
                                    System.out.println("total: " + total);
                                    System.out.println("next: " + next);
                                    return total * next;
                                }));


//                        .map(Object::toString)
//                        .collect(Collectors.joining(":", "{", "}")));

        System.out.println(IntStream.range(0, 10000).parallel().map(x -> {
            System.out.println(Thread.currentThread().getId());
            return x * 2;
        }).boxed().collect(Collectors.toList()));


        while (Thread.activeCount() > 2) {
        	System.out.println("Thread count:" + Thread.activeCount());
        	Thread.sleep(10000);
        }
        
    }
}







