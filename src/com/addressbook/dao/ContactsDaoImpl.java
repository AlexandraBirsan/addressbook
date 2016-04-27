package com.addressbook.dao;

import com.addressbook.model.Contact;
import com.addressbook.service.ConnectionConstants;
import com.addressbook.service.QueriesManager;
import org.apache.commons.dbutils.DbUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

/**
 * Created by birsan on 4/26/2016.
 */
public class ContactsDaoImpl implements ContactsDao {

    @Override
    public Long createContact(Contact contact) {
        PreparedStatement insertStatement = null;
        Statement sequenceValue = null;
        Long contactId = null;
        Connection connection = null;
        try {
            String createContactSQL = QueriesManager.getInstance().getCreateContactSQL();
            String nextValSQL = QueriesManager.getInstance().getGetNextValueSQL();
            connection = DriverManager.getConnection(ConnectionConstants.URL, ConnectionConstants.USERNAME, ConnectionConstants.PASSWORD);
            sequenceValue = connection.createStatement();
            synchronized (this) {
                ResultSet rs = sequenceValue.executeQuery(nextValSQL);
                if (rs.next()) {
                    contactId = rs.getLong(1);
                }
            }
            insertStatement = connection.prepareStatement(createContactSQL);
            insertStatement.setLong(1, contactId);
            insertStatement.setString(2, contact.getFirstName());
            insertStatement.setString(3, contact.getLastName());
            insertStatement.setString(4, contact.getCompany());
            insertStatement.setString(5, contact.getContentType());
            InputStream photoInputStream = new ByteArrayInputStream(contact.getPhoto());
            insertStatement.setBlob(6, photoInputStream);
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(insertStatement);
            DbUtils.closeQuietly(sequenceValue);
            DbUtils.closeQuietly(connection);
        }
        return contactId;
    }

    @Override
    public void updateContact(Contact contact) {
        Connection connection=null;
        PreparedStatement updateStatement=null;
        try {
            connection=DriverManager.getConnection(ConnectionConstants.URL,ConnectionConstants.USERNAME,ConnectionConstants.PASSWORD);
            updateStatement=connection.prepareStatement(QueriesManager.getInstance().getUpdateContactSQL());
            updateStatement.setString(1,contact.getFirstName());
            updateStatement.setString(2,contact.getLastName());
            updateStatement.setString(3,contact.getCompany());
            updateStatement.setString(4,contact.getContentType());
            InputStream photoInputStream=new ByteArrayInputStream(contact.getPhoto());
            updateStatement.setBlob(5,photoInputStream);
            updateStatement.setLong(5,contact.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeQuietly(updateStatement);
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public Contact getContact(Integer id) {
        return null;
    }

    @Override
    public void deleteContact(Integer id) {

    }

    @Override
    public List<Contact> getAll() {
        return null;
    }

}
