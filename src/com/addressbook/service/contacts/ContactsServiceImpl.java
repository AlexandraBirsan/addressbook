package com.addressbook.service.contacts;

import com.addressbook.dao.ContactsDaoImpl;
import com.addressbook.dao.PhoneNumberDaoImpl;
import com.addressbook.model.Contact;

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

    private ContactsServiceImpl() {
    }

    private final ContactsDaoImpl contactsDao = new ContactsDaoImpl();
    private final PhoneNumberDaoImpl phoneNumberDao = new PhoneNumberDaoImpl();

    @Override
    public void createContact(Contact contact) {
        Long id = contactsDao.createContact(contact);
        phoneNumberDao.createPhoneNumbers(id, contact.getPhoneNumbers());
    }

    @Override
    public void updateContact(Contact contact) {
        contactsDao.updateContact(contact);
        phoneNumberDao.updatePhoneNumber(contact);
    }

    @Override
    public Contact getContact(Integer id) {
        return contactsDao.getContact(id);
    }

    @Override
    public void deleteContact(Integer id) {
        contactsDao.deleteContact(id);
    }

    @Override
    public List<Contact> getAll() {
        return contactsDao.getAll();
    }
}
