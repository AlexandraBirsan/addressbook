package com.addressbook.service;

import com.addressbook.model.Contact;

import java.util.List;

/**
 * Created by birsan on 4/12/2016.
 */
public interface ContactsService {
    void createContact(Contact contact);
        void updateContact(Integer id);
    Contact getContact(Integer id);
    void deleteContact(Integer id);
    List<Contact> getAll();
}
