package com.addressbook.service.contacts;

import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by birsan on 4/21/2016.
 */
@ManagedBean(name = "updateBean")
@SessionScoped
public class ContactUpdateBackingBean {
    private static final String PHONE_NUMBERS_PARAMETER = "phoneNumbers";
    private Integer toBeUpdatedId;
    private Contact toUpdateContact;
    private String firstName;
    private String lastName;
    private String company;
    private List<String> phoneNumbers = new ArrayList<>();
    private Part photo;

    public String getFirstName() {
        return toUpdateContact.getFirstName();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return toUpdateContact.getLastName();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return toUpdateContact.getCompany();
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<String> getPhoneNumbers() {
        return toUpdateContact.getPhoneNumbers().stream()
                .map(PhoneNumber::getNumber).collect(Collectors.toList());
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Part getPhoto() {
        return photo;
    }

    public void setPhoto(Part photo) {
        this.photo = photo;
    }

    public void setToBeUpdatedId(Integer toBeUpdatedId) {
        this.toBeUpdatedId = toBeUpdatedId;
        toUpdateContact = ContactsServiceImpl.getInstance().getContact(toBeUpdatedId);
    }

    public String addPhoneNumber() {
        PhoneNumber number = new PhoneNumber();
        number.setNumber("");
        phoneNumbers.add(number.getNumber());
        return "added";
    }

    public String updateContact() throws IOException {
        Contact contact = new Contact();
        Contact existingContact = ContactsServiceImpl.getInstance().getContact(toBeUpdatedId);
        String contentType = photo == null ? existingContact.getContentType() : photo.getContentType();
        byte[] photoBytes = photo == null ? existingContact.getPhoto() : org.apache.commons.io.IOUtils.toByteArray(photo.getInputStream());
        List<String> phoneNumbers = getPhoneNumbersFromExternalContext(FacesContext.getCurrentInstance().getExternalContext());
        List<PhoneNumber> phones = new ArrayList<>(phoneNumbers.size());
        phoneNumbers.stream().forEach(number -> {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setContactId(toBeUpdatedId);
            phoneNumber.setNumber(number);
            phones.add(phoneNumber);
        });
        contact.setPhoneNumbers(phones).setCompany(company).setPhoto(photoBytes).setFirstName(firstName)
                .setLastName(lastName).setContentType(contentType).setId(toBeUpdatedId);
        ContactsServiceImpl.getInstance().updateContact(contact);
        return "updated";
    }

    private List<String> getPhoneNumbersFromExternalContext(ExternalContext externalContext) {
        List<String> phoneNumbers = new ArrayList<>();
        Map<String, String[]> requestParameterValuesMap = externalContext.getRequestParameterValuesMap();
        List<String> phoneNumberKeys = requestParameterValuesMap.keySet().stream().filter(key -> key.contains(PHONE_NUMBERS_PARAMETER)).collect(Collectors.toList());
        phoneNumberKeys.stream().forEach(key -> {
            String[] values = requestParameterValuesMap.get(key);
            phoneNumbers.add(values[0]);
        });
        return phoneNumbers;
    }
}
