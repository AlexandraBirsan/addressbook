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
 * Created by birsan on 4/14/2016.
 */
@WebServlet(name = "DeleteContactServlet")
public class DeleteContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("deletedId"));
        ContactsServiceImpl.getInstance().deleteContact(id);
        request.getRequestDispatcher("list").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
