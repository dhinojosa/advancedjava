package com.vmware;


import java.util.Locale;
import java.util.ResourceBundle;

public class UsingI18N {
    public static void main(String[] args) {
        ResourceBundle englishMessages =
                ResourceBundle.getBundle("messages", Locale.ENGLISH);

        ResourceBundle indianMessages =
                ResourceBundle.getBundle("messages", new Locale("hi", "IN"));

        ResourceBundle chineseMessages =
                ResourceBundle.getBundle("messages", Locale.CHINESE);

        System.out.println(chineseMessages.getString("Hello_World"));
        System.out.println();
        System.out.println(indianMessages.getString("Hello_World"));
        System.out.println(englishMessages.getString("Hello_World"));
    }
}
