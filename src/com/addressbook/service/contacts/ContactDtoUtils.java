package com.addressbook.service.contacts;

import com.addressbook.model.Contact;
import com.addressbook.dto.ContactDto;
import com.addressbook.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by birsan on 5/10/2016.
 */
public class ContactDtoUtils {
    private ContactDtoUtils() {}

    public static List<ContactDto> getContacts() {
        List<Contact> contacts = ContactsServiceImpl.getInstance().getAll();
        List<ContactDto> contactDtos = new ArrayList<>(contacts.size());
        contacts.stream().forEach(contact -> {
            ContactDto contactDto = new ContactDto();
            contactDto.setCompany(contact.getCompany());
            contactDto.setLastName(contact.getLastName());
            contactDto.setFirstName(contact.getFirstName());
            contactDto.setPhoneNumber(buildPhoneNumber(contact));
            contactDto.setId(contact.getId());
            contactDtos.add(contactDto);
        });
        return contactDtos;
    }

    private static String buildPhoneNumber(Contact contact) {
        String phoneNum = "";
        for (PhoneNumber number : contact.getPhoneNumbers()) {
            phoneNum += number.getNumber() + ", ";
        }
        if (phoneNum.length() > 2) {
            phoneNum = phoneNum.substring(0, phoneNum.length() - 2);
        }
        return phoneNum;
    }
}