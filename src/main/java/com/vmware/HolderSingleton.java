package com.vmware;

public class HolderSingleton {
	static {
		System.out.println("Holder Singleton init");
	}
	
	static class SingletonHolder {
		
		static {
			System.out.println("Singleton Holder Init");
		}
		
		static HolderSingleton instance = new HolderSingleton();

		static {
			System.out.println("Singleton Holder Done");
		}
	}

	private HolderSingleton() {
		System.out.println("Created Holder Singleton");
	}
	
	public static HolderSingleton getInstance() {	
	    System.out.println("getInstance() called");
		return SingletonHolder.instance;
	}
	
	static {
		System.out.println("Holder Singleton done");	
	}
}
