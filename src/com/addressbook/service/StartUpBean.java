package com.addressbook.service;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by birsan on 4/26/2016.
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class StartUpBean {

    @PostConstruct
    public void init() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            loadDatabaseProperties();
            loadDatabaseQueries();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while initialising database properties and queries.");
            throw new RuntimeException("An error occurred while initialising database properties and queries.");
        }
    }

    private void loadDatabaseQueries() throws IOException {
        String queriesTemplate = "resources/sql/queries.properties";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String absoluteDiskPath = servletContext.getRealPath(queriesTemplate);
        InputStream is = new FileInputStream(absoluteDiskPath);
        Properties properties = new Properties();
        properties.load(is);
        String createPhoneNumber = properties.getProperty("createPhoneNumber");
        String createContact = properties.getProperty("createContact");
        String nextVal = properties.getProperty("nextVal");
        String updateContactSQL=properties.getProperty("updateContact");
        String updatePhoneNumbersSQL=properties.getProperty("updatePhoneNumbers");
        String getContactSQL=properties.getProperty("getContact");
        QueriesManager.getInstance().setCreateContactSQL(createContact);
        QueriesManager.getInstance().setCreatePhoneNumberSQL(createPhoneNumber);
        QueriesManager.getInstance().setGetNextValueSQL(nextVal);
        QueriesManager.getInstance().setUpdateContactSQL(updateContactSQL);
        QueriesManager.getInstance().setUpdatePhoneNumberSQL(updatePhoneNumbersSQL);
        QueriesManager.getInstance().setGetContactSQL(getContactSQL);
    }

    private void loadDatabaseProperties() throws IOException, SQLException {
        Properties props = new Properties();
        String queriesTemplate = "resources/sql/connection.properties";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String absoluteDiskPath = servletContext.getRealPath(queriesTemplate);
        InputStream resourceAsStream = new FileInputStream(absoluteDiskPath);
        props.load(resourceAsStream);
        String url = props.getProperty("database.url");
        String username = props.getProperty("database.username");
        String pass = props.getProperty("database.password");
        ConnectionConstants.USERNAME = username;
        ConnectionConstants.PASSWORD = pass;
        ConnectionConstants.URL = url;
    }
}
