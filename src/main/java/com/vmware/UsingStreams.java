package com.vmware;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class UsingStreams {

    public static void main(String[] args) {
        try (
                FileInputStream fileInputStream = new FileInputStream(
                        System.getProperty("user.home")
                                + "/advanced_file.txt");
                FileOutputStream fileOutputStream = new FileOutputStream(
                        System.getProperty("user.home") +
                                "/advanced_file_" +
                                LocalDateTime.now()
                                        .toString().replace(":", "_") + ".txt")
        ) {
            int b = 0;
            while ((b = fileInputStream.read()) != -1) {
                fileOutputStream.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
