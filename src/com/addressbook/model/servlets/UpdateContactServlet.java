package com.addressbook.model.servlets;

import com.addressbook.model.Contact;
import com.addressbook.service.ContactsServiceImpl;
import com.addressbook.service.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by birsan on 4/15/2016.
 */
@WebServlet(name = "UpdateContactServlet")
public class UpdateContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("updateId"));
        ContactsServiceImpl.getInstance().updateContact(id);
        //request.getRequestDispatcher("update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.valueOf(request.getParameter("updateId"));
        Contact contact=Database.CONTACTS.get(id.intValue());
        request.setAttribute("toBeUpdatedContact",contact);
        request.getRequestDispatcher("update.jsp").forward(request, response);

    }
}
