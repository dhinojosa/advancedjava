package com.vmware;

public class American implements Person  {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    public American(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        return "Bob Barker";
    }

    public static void main(String[] args) {
        American paulRudd = new American("Paul", "Rudd");
        System.out.println(paulRudd.getFullName());
    }
}
