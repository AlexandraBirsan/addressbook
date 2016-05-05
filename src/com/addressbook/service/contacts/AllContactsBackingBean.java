package com.addressbook.service.contacts;

import com.addressbook.model.Contact;
import com.addressbook.model.ContactDto;
import com.addressbook.model.PhoneNumber;
import org.apache.commons.io.IOUtils;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        List<Contact> contacts = ContactsServiceImpl.getInstance().getAll();
        List<ContactDto> contactDTOs = new ArrayList<>(contacts.size());
        contacts.stream().forEach(contact -> {

            ContactDto contactDTO = new ContactDto();
            contactDTO.setCompany(contact.getCompany());
            contactDTO.setLastName(contact.getLastName());
            contactDTO.setFirstName(contact.getFirstName());
            contactDTO.setPhoneNumber(buildPhoneNumber(contact));
            String base64 = null;
            try {
                String defaultPhoto = "resources/images/defaultPhoto.png";
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String absolutePath = servletContext.getRealPath(defaultPhoto);
                InputStream is = new FileInputStream(absolutePath);
               /* base64 = (contact.getPhoto() == null) ? Base64.getEncoder().encodeToString(IOUtils.toByteArray
                        (new FileInputStream("C:\\Dev\\addressbook\\web\\resources\\images\\defaultPhoto.png"))) : Base64.getEncoder().encodeToString(contact.getPhoto());*/
                base64 = (contact.getPhoto() == null) ? Base64.getEncoder().encodeToString(IOUtils.toByteArray
                        (is)) : Base64.getEncoder().encodeToString(contact.getPhoto());
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
        for (PhoneNumber number : contact.getPhoneNumbers()) {
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

    public String update() {
        return "update";
    }
}
