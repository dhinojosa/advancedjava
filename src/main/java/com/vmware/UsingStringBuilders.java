package com.vmware;

public class UsingStringBuilders {

    public static void main(String[] args) {
        //Thread-safe
        StringBuffer stringBuffer = new StringBuffer();


        //Non-thread-safe, but generally preferred
        StringBuilder stringBuilder = new StringBuilder();
    }
}
