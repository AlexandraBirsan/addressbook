package tests;

import com.addressbook.dao.ContactsDao;
import com.addressbook.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by birsan on 5/16/2016.
 */
public class ContactDaoMockUp {
    Long createContact(Contact contact) {
        return new Long(12);
    }

    public void updateContact(Contact contact) {
    }

    public Contact getContact(Integer id) {
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }

    public void deleteContact(Integer id) {

    }

    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        contacts.add(new Contact());
        contacts.add(new Contact());
        return contacts;
    }
}
