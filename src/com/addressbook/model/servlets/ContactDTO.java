package com.addressbook.model.servlets;

/**
 * Created by birsan on 4/14/2016.
 */
public class ContactDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String company;
    private byte[] photo;
    private String phoneNumber;

    public Integer getId() {
        return id;
    }

    public ContactDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ContactDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ContactDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public ContactDTO setCompany(String company) {
        this.company = company;
        return this;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public ContactDTO setPhoto(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ContactDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
