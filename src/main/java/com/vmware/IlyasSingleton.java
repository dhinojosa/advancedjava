package com.vmware;

public enum IlyasSingleton {
   INSTANCE;

    static {
        System.out.println("Loading");
    }

    private int i = 0;


//    public static IlyasSingleton getInstance() {
//        return INSTANCE;
//    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    static {
        System.out.println("Loaded");
    }
}
