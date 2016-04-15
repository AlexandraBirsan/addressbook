package com.addressbook.service;

import com.addressbook.model.Contact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by birsan on 4/12/2016.
 */
public class ContactsServiceImpl implements ContactsService {

    private static class LazyHolder {
        static final ContactsServiceImpl INSTANCE = new ContactsServiceImpl();
    }

    public static ContactsServiceImpl getInstance() {
        return LazyHolder.INSTANCE;
    }

    private ContactsServiceImpl(){

    }

    @Override
    public void createContact(Contact contact) {
        contact.setId(Database.CONTACTS.size());
        Database.CONTACTS.add(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        Contact existingContact = Database.CONTACTS.get(Integer.valueOf(contact.getId().toString()));
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setCompany(contact.getCompany());
        existingContact.setLastName(contact.getLastName());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        existingContact.setPhoto(contact.getPhoto());
        Database.CONTACTS.set(Integer.valueOf(contact.getId().toString()), existingContact);
    }

    @Override
    public Contact getContact(Integer id) {
        return Database.CONTACTS.get(Integer.valueOf(id.toString()));
    }

    @Override
    public void deleteContact(Integer id) {
        Database.CONTACTS.remove(id.intValue());
    }

    @Override
    public List<Contact> getAll() {
        return Database.CONTACTS;
    }
}
