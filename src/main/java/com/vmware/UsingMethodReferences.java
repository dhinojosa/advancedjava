package com.vmware;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UsingMethodReferences {

    public static class MyIntegerParser {
        public Integer parse(String string) {
            return Integer.parseInt(string);
        }
    }

    public static void main(String[] args) {

        List<String> numbersAsStrings = new ArrayList<>();
        numbersAsStrings.add("1");
        numbersAsStrings.add("2");
        numbersAsStrings.add("3");

        IntStream stream = IntStream.range(1, 10).map(s -> s * 50);


        MyIntegerParser mip = new MyIntegerParser();

        System.out.println(numbersAsStrings.stream().map(mip::parse).map(i -> i * 10)
                .collect(Collectors.toList()));
    }
}
