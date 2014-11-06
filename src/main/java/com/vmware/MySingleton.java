package com.vmware;

public class MySingleton {

	private volatile static MySingleton instance = null;

	private MySingleton() {   
	    // private constructor prevents instantiation by untrusted callers
	}
	
	public static MySingleton getInstance() {
		if (instance == null) {
			synchronized (MySingleton.class) {
				if (instance == null) {
					instance = new MySingleton();
			    }
			}
		}
		return instance;	
	}
}
