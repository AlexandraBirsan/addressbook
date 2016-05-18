package com.addressbook.service.contacts;

import com.addressbook.dao.ContactsDaoImpl;
import com.addressbook.dao.PhoneNumberDaoImpl;
import com.addressbook.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by birsan on 4/12/2016.
 */
@Component
public class ContactsServiceImpl implements ContactsService {

    @Autowired private ContactsDaoImpl contactsDaoImpl;
    @Autowired private PhoneNumberDaoImpl phoneNumberDaoImpl;

    @Override
    public void createContact(Contact contact) {
        Long id = contactsDaoImpl.createContact(contact);
        phoneNumberDaoImpl.createPhoneNumbers(id, contact.getPhoneNumbers());
    }

    @Override
    public void updateContact(Contact contact) {
        contactsDaoImpl.updateContact(contact);
        phoneNumberDaoImpl.updatePhoneNumber(contact);
    }

    @Override
    public Contact getContact(Integer id) {
        return contactsDaoImpl.getContact(id);
    }

    @Override
    public void deleteContact(Integer id) {
        contactsDaoImpl.deleteContact(id);
    }

    @Override
    public List<Contact> getAll() {
        return contactsDaoImpl.getAll();
    }
}
