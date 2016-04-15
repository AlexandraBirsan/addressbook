package com.addressbook.model.servlets;

import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;
import com.addressbook.service.ContactsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by birsan on 4/12/2016.
 */
@WebServlet(name = "ListContactsServlet")
public class ListAllContactsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contact> allContacts = ContactsServiceImpl.getInstance().getAll();
        List<ContactDTO> contactDTOs = convertListToDTO(allContacts);
        request.setAttribute("allDTOContacts", contactDTOs);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }


    public ContactDTO contactToDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setFirstName(contact.getFirstName());
        contactDTO.setLastName(contact.getLastName());
        contactDTO.setCompany(contact.getCompany());
        contactDTO.setContentType(contact.getContentType());
        String base64 = (contact.getPhoto() == null) ? "" : Base64.getEncoder().encodeToString(contact.getPhoto());
        contactDTO.setPhoto(base64);
        String num = "";
        for (PhoneNumber number : contact.getPhoneNumber()) {
            num += number.getNumber() + ", ";
        }
        num = num.substring(0, num.length() - 2);
        contactDTO.setPhoneNumber(num);
        return contactDTO;
    }

    public List<ContactDTO> convertListToDTO(List<Contact> contacts) {
        List<ContactDTO> contactDTOs = new ArrayList<>();
        ContactDTO contactDTO;
        for (Contact contact : contacts) {
            contactDTO = contactToDTO(contact);
            contactDTOs.add(contactDTO);
        }
        return contactDTOs;
    }
}

