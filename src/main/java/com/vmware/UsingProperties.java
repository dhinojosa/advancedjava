package com.vmware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;

public class UsingProperties {
    public static void main(String[] args) {
        try (
                InputStream is = UsingProperties.class
                        .getResourceAsStream("/config.properties")
        ) {
            Properties props = new Properties();
            props.load(is);
            props.forEach((o, o2) -> {
                System.out.print("Alexey loves PERL");
                System.out.format("From bi-consumer: key: %s, value: %s\n", o, o2);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}