package com.addressbook.service.contacts;

import com.addressbook.model.Contact;
import com.addressbook.model.ContactDto;
import com.addressbook.model.PhoneNumber;
import com.addressbook.service.Database;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by birsan on 4/20/2016.
 */
@ManagedBean(name = "allContactsBB")
@ApplicationScoped
public class AllContactsBackingBean {
    public List<ContactDto> getContacts() {
        List<Contact> contacts = Database.CONTACTS;
        List<ContactDto> contactDTOs = new ArrayList<>(contacts.size());
        contacts.stream().forEach(contact -> {

            ContactDto contactDTO = new ContactDto();
            contactDTO.setCompany(contact.getCompany());
            contactDTO.setLastName(contact.getLastName());
            contactDTO.setFirstName(contact.getFirstName());
            contactDTO.setPhoneNumber(buildPhoneNumber(contact));
            String base64 = null;
            try {
                base64 = (contact.getPhoto() == null) ? Base64.getEncoder().encodeToString(IOUtils.toByteArray
                        (new FileInputStream("C:\\Dev\\addressbook\\web\\resources\\images\\defaultPhoto.png"))) : Base64.getEncoder().encodeToString(contact.getPhoto());
                contactDTO.setPhoto("data:" + contact.getContentType() + ";base64, " + base64);
            } catch (IOException e) {
                e.printStackTrace();
            }
            contactDTO.setId(contact.getId());
            contactDTOs.add(contactDTO);
        });
        return contactDTOs;
    }

    private String buildPhoneNumber(Contact contact) {
        String phoneNum = "";
        for (PhoneNumber number : contact.getPhoneNumber()) {
            phoneNum += number.getNumber() + ", ";
        }
        if (phoneNum.length() > 2) {
            phoneNum = phoneNum.substring(0, phoneNum.length() - 2);
        }
        return phoneNum;
    }

    public String deleteContact(Integer id) {
        ContactsServiceImpl.getInstance().deleteContact(id);
        return "deleted";
    }

//    public StreamedContent getPhoto() {
//
//        String defaultPhoto = "resources/images/defaultPhoto.png";
//
//        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        String absoluteDiskPath = servletContext.getRealPath(defaultPhoto);
//    retur}

    public String update() {
        return "update";
    }
}
