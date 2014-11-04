package com.vmware;

@FunctionalInterface
public interface DoubleConsumer<T, U> {
    public void accept(T t, U u);
}
