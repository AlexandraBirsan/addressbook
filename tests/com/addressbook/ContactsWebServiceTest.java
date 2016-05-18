package com.addressbook;

import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;
import com.addressbook.service.contacts.ContactsWebService;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by birsan on 5/16/2016.
 */
public class ContactsWebServiceTest {
    private static final String VALID_NAME = "Name";
    private static final String VALID_COMPANY = "company";
    private static final String VALID_PHONE_NUMBER = "0000752028861";
    private static final int CONFLICT_STATUS_CODE=409;

    @Test
    public void testCreateInvalidContact() {
        ContactsWebService webService = new ContactsWebService();
        Response response = webService.create(givenInvalidContact());
        Assert.assertTrue(response.getStatus() == CONFLICT_STATUS_CODE);
    }

    @Test
    public void testCreateValidContact(){

    }

    @Test
    public void testUpdate() {
        ContactsWebService webService = new ContactsWebService();
        Response response = webService.update(givenInvalidContact());
        Assert.assertTrue(response.getStatus() == CONFLICT_STATUS_CODE);
    }

    private Contact givenInvalidContact() {
        Contact contact = dummyValidContact();
        contact.setCompany("");
        return contact;
    }


    private Contact dummyValidContact() {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber number = new PhoneNumber();
        number.setNumber(VALID_PHONE_NUMBER);
        phoneNumbers.add(number);
        Contact contact = new Contact();
        contact.setLastName(VALID_NAME);
        contact.setFirstName(VALID_NAME);
        contact.setCompany(VALID_COMPANY);
        contact.setPhoneNumbers(phoneNumbers);
        return contact;
    }
}