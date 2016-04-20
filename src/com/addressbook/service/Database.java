package com.addressbook.service;

import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by birsan on 4/12/2016.
 */
public class Database {
    public static final List<Contact> CONTACTS = new ArrayList<>();

    static {
        CONTACTS.add(new Contact().setId(0).setFirstName("John").setLastName("Doe").setCompany("Optymyze")
                .setPhoneNumber(Arrays.asList(
                                new PhoneNumber().setContactId(0).setNumber("123"),
                                new PhoneNumber().setContactId(0).setNumber("1234"),
                                new PhoneNumber().setContactId(0).setNumber("123"))
                ));
        CONTACTS.add(new Contact().setId(1).setFirstName("Johnny").setLastName("Bravo").setCompany("Optymyze")
                .setPhoneNumber(Arrays.asList(
                                new PhoneNumber().setContactId(1).setNumber("1"),
                                new PhoneNumber().setContactId(1).setNumber("2"),
                                new PhoneNumber().setContactId(1).setNumber("3"))
                ));
        CONTACTS.add(new Contact().setId(2).setFirstName("John2").setLastName("Doe").setCompany("Optymyze")
                .setPhoneNumber(Arrays.asList(
                                new PhoneNumber().setContactId(2).setNumber("123"),
                                new PhoneNumber().setContactId(2).setNumber("1234"),
                                new PhoneNumber().setContactId(2).setNumber("123"))
                ));
    }
}
