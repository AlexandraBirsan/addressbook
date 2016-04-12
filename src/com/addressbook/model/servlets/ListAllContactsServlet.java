package com.addressbook.model.servlets;

import com.addressbook.model.Contact;
import com.addressbook.service.ContactsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by birsan on 4/12/2016.
 */
@WebServlet(name = "ListContactsServlet")
public class ListAllContactsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contact> allContacts=ContactsServiceImpl.getInstance().getAll();
        request.setAttribute("allContacts",allContacts);
        request.getRequestDispatcher("list.jsp").forward(request,response);

    }
}
