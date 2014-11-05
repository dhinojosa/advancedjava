package com.vmware;

public interface Person {
    public String getFirstName();

    public String getLastName();

    default public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    default public String getSirFullName() {
        return "Sir" + getFirstName() + " " + getLastName();
    }

    default public String getMadamFullName() {
        return "Madam" + getFirstName() + " " + getLastName();
    }
}
