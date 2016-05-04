package com.addressbook.service.contacts;

import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;
import com.addressbook.service.Database;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by birsan on 4/20/2016.
 */
@ManagedBean(name = "contactBean")
@SessionScoped
public class ContactCreateBackingBean {
    public Integer toBeUpdatedId;
    private Integer id = Database.CONTACTS.size();
    private String firstName;
    private String lastName;
    private String company;
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();
    private Part photo;

    @PostConstruct
    public void init() {
        PhoneNumber initialPhoneNumber = new PhoneNumber();
        initialPhoneNumber.setNumber("");
        phoneNumbers.add(initialPhoneNumber);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
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
    }

    public String addPhoneNumber() {
        PhoneNumber number = new PhoneNumber();
        number.setNumber("");
        phoneNumbers.add(number);
        return "added";
    }

    public String createContact() throws IOException {
        Contact contact = new Contact();
        byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(photo.getInputStream());
        contact.setPhoneNumbers(phoneNumbers).setCompany(company).setPhoto(bytes).setFirstName(firstName).setLastName(lastName).setContentType(photo.getContentType());
        ContactsServiceImpl.getInstance().createContact(contact);
        return "created";
    }

    public void phoneNumberValidation(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputPhone = (UIInput) components.findComponent("phoneNum");
        String phoneNumber = uiInputPhone.getLocalValue() == null ? "" : uiInputPhone.getLocalValue().toString();
        String phoneNumberId = uiInputPhone.getClientId();
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches() || phoneNumber.isEmpty()) {
            FacesMessage message = new FacesMessage("Invalid phoneNumbers!");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(phoneNumberId, message);
            fc.renderResponse();
        }
    }
}
