package com.vmware;

import java.text.DecimalFormat;

public class UsingNumberFormat {
    public static void main(String[] args) {
        int i = 127;
        DecimalFormat decimalFormat = new DecimalFormat("00000000E000");
        System.out.println(decimalFormat.format(i));

        DecimalFormat decimalFormatPerMille = new DecimalFormat("\u2030###");
        System.out.println(decimalFormatPerMille.format(.0050));

        //Caching of autoboxing
        Integer i1 = i;
        Integer i2 = i;
        System.out.println(i1 == i2);

        String s = "X";
        String s1 = "X";
        String s2 = new String("X");
        System.out.println(s == s1);
        System.out.println(s == s2);

        s = s + s;
        s1 = s1 + s1;
        System.out.println(s == s1);
    }
}
