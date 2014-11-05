package com.vmware;

public class American implements Person, Comparable<American> {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    public American(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public American(String firstName, String lastName,
                    String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    @Override
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public static void main(String[] args) {
        American paulRudd = new American("Paul", "Rudd");
        System.out.println(paulRudd.getFullName());
    }

    @Override
    public int compareTo(American o) {
        return this.socialSecurityNumber.compareTo(o.socialSecurityNumber);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("American: ");
        builder.append(firstName);
        builder.append(", ");
        builder.append(lastName);
        builder.trimToSize();
        return builder.toString();
    }
}
