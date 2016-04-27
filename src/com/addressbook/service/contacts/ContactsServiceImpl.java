package com.addressbook.service.contacts;

import com.addressbook.dao.ContactsDaoImpl;
import com.addressbook.dao.PhoneNumberDaoImpl;
import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;
import com.addressbook.service.Database;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

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

    private ContactsServiceImpl() {}

    private final ContactsDaoImpl contactsDao = new ContactsDaoImpl();
    private final PhoneNumberDaoImpl phoneNumberDao = new PhoneNumberDaoImpl();

    @Override
    public void createContact(Contact contact) {
        Long id = contactsDao.createContact(contact);
        phoneNumberDao.createPhoneNumbers(id,contact.getPhoneNumber());
    }

    @Override
    public void updateContact(Contact contact) {
        contactsDao.updateContact(contact);
        phoneNumberDao.updatePhoneNumber(contact);
    }

    @Override
    public Contact getContact(Integer id) {
        return Database.CONTACTS.get(Integer.valueOf(id.toString()));
    }

    @Override
    public void deleteContact(Integer id) {
        Database.CONTACTS.remove(id.intValue());
        for (int i = id.intValue(); i < Database.CONTACTS.size(); i++) {
            Database.CONTACTS.get(i).setId(Database.CONTACTS.get(i).getId() - 1);
        }
    }

    @Override
    public List<Contact> getAll() {
        return Database.CONTACTS;
    }
}
