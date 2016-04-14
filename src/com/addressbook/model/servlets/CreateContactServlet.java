package com.addressbook.model.servlets;

import com.addressbook.controller.Validator;
import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;
import com.addressbook.service.ContactsServiceImpl;
import com.addressbook.service.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by birsan on 4/11/2016.
 */
@WebServlet(name = "CreateContactServlet")
public class CreateContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        PhoneNumber phoneNumber = new PhoneNumber();
        String company = request.getParameter("company");
        String[] phoneNumbers = request.getParameterValues("phoneNumber");
        List<PhoneNumber> phoneNums = new ArrayList<>();
        for (String numberValue : phoneNumbers) {
            PhoneNumber number = new PhoneNumber().setContactId(Database.CONTACTS.size()).setNumber(numberValue);
            phoneNums.add(number);
        }
        Contact contact = new Contact().setFirstName(firstName).setLastName(lastName)
                        .setCompany(company).setPhoneNumber(phoneNums);
        ContactsServiceImpl.getInstance().createContact(contact);
        request.getRequestDispatcher("submitted.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
