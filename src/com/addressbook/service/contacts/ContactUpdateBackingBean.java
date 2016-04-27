package com.addressbook.service.contacts;

import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by birsan on 4/21/2016.
 */
@ManagedBean(name = "updateBean")
@SessionScoped
public class ContactUpdateBackingBean {
    private Integer toBeUpdatedId;
    private Contact toUpdateContact;
    private String firstName;
    private String lastName;
    private String company;
    private List<PhoneNumber> phoneNumber = new ArrayList<>();
    private Part photo;

    @PostConstruct
    public void init() {
        PhoneNumber initialPhoneNumber = new PhoneNumber();
        initialPhoneNumber.setNumber("");
        phoneNumber.add(initialPhoneNumber);
    }

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

    public List<PhoneNumber> getPhoneNumber() {
        return toUpdateContact.getPhoneNumber();
    }

    public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Part getPhoto() {
        return photo;
    }

    public void setPhoto(Part photo) {
        this.photo = photo;
    }

    public void setToBeUpdatedId(Integer toBeUpdatedId){
        this.toBeUpdatedId=toBeUpdatedId;
        toUpdateContact = ContactsServiceImpl.getInstance().getContact(toBeUpdatedId);
    }

    public String addPhoneNumber() {
        PhoneNumber number = new PhoneNumber();
        number.setNumber("");
        phoneNumber.add(number);
        return "added";
    }

    public String createUpdatedContact() throws IOException {
        Contact contact = new Contact();
        byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(photo.getInputStream());
        contact.setPhoneNumber(phoneNumber).setCompany(company).setPhoto(bytes).setFirstName(firstName)
                .setLastName(lastName).setContentType(photo.getContentType()).setId(toBeUpdatedId);
        ContactsServiceImpl.getInstance().updateContact(contact);
        return "updated";
    }
}
