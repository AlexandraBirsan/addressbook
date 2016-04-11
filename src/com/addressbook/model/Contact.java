package com.addressbook.model;

import java.util.List;
import java.util.Vector;

/**
 * Created by birsan on 4/11/2016.
 */
public class Contact {
    private String firstName;
    private String lastName;
    private String company;
    private byte[] photo;
    private List<PhoneNumber> phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public Contact setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Contact setCompany(String company) {
        this.company = company;
        return this;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Contact setPhoto(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public List<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }

    public Contact setPhoneNumber(List<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
