package com.addressbook.model.servlets;

/**
 * Created by birsan on 4/14/2016.
 */
public class ContactDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String company;
    private String photo;
    private String phoneNumber;
    private String contentType;

    public ContactDTO setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public ContactDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public ContactDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactDTO setCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactDTO setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public ContactDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
