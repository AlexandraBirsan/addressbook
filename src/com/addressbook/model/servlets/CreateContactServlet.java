package com.addressbook.model.servlets;

import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;
import com.addressbook.service.ContactsServiceImpl;
import com.addressbook.service.Database;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by birsan on 4/11/2016.
 */
@WebServlet(name = "CreateContactServlet")
public class CreateContactServlet extends HttpServlet {

    private static final String TEMP_PATH = "javax.servlet.context.tempdir";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = (File) request.getServletContext().getAttribute(TEMP_PATH);
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            Contact contact = buildContactFromRequest(request, upload);
            ContactsServiceImpl.getInstance().createContact(contact);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("submitted.jsp").forward(request, response);
    }

    private Contact buildContactFromRequest(HttpServletRequest request, ServletFileUpload upload) throws FileUploadException {
        Contact contact = new Contact();
        List<FileItem> items = upload.parseRequest(request);
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (FileItem item : items) {
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                if (fieldName.equals("firstName")) {
                    contact.setFirstName(fieldValue);
                } else if (fieldName.equals("lastName")) {
                    contact.setLastName(fieldValue);
                } else if (fieldName.equals("company")) {
                    contact.setCompany(fieldValue);
                } else if (fieldName.equals("phoneNumber")) {
                    PhoneNumber number = new PhoneNumber().setContactId(Database.CONTACTS.size()).setNumber(fieldValue);
                    phoneNumbers.add(number);
                } else {
                    throw new IllegalArgumentException("Invalid field name detected.");
                }
            } else {
                if (item.getContentType().contains("image")) {
                    byte[] picture = item.get();
                    contact.setPhoto(picture);
                    contact.setContentType(item.getContentType());
                }
            }
        }
        contact.setPhoneNumber(phoneNumbers);
        return contact;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
