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
        Contact contact1 = new Contact().setId(0).setFirstName("John").setLastName("Doe").setCompany("Optymyze");
        PhoneNumber p1 = new PhoneNumber();
        p1.setContactId(0);
        p1.setNumber("123");
        PhoneNumber p2 = new PhoneNumber();
        p2.setContactId(0);
        p2.setNumber("1234");
        PhoneNumber p3 = new PhoneNumber();
        p3.setContactId(0);
        p3.setNumber("123");
        contact1.setPhoneNumber(Arrays.asList(p1, p2, p3));
        CONTACTS.add(contact1);

    }

}
