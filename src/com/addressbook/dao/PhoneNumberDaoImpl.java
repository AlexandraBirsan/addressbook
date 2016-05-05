package com.addressbook.dao;

import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;
import com.addressbook.service.ConnectionConstants;
import com.addressbook.service.QueriesManager;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by birsan on 4/27/2016.
 */
public class PhoneNumberDaoImpl implements PhoneNumberDao {
    @Override
    public void createPhoneNumbers(Long id, List<PhoneNumber> phoneNumbers) {
        Connection connection = null;
        PreparedStatement createStatement = null;
        try {
            connection = DriverManager.getConnection(ConnectionConstants.URL, ConnectionConstants.USERNAME, ConnectionConstants.PASSWORD);
            createStatement = connection.prepareStatement(QueriesManager.getInstance().getCreatePhoneNumberSQL());
            for (PhoneNumber number : phoneNumbers) {
                createStatement.setLong(1, id);
                createStatement.setString(2, number.getNumber());
                createStatement.addBatch();
            }
            createStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DbUtils.closeQuietly(createStatement);
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public void updatePhoneNumber(Contact contact) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        PreparedStatement updateStatement = null;
        try {
            connection = DriverManager.getConnection(ConnectionConstants.URL, ConnectionConstants.USERNAME, ConnectionConstants.PASSWORD);
            deleteStatement = connection.prepareStatement(QueriesManager.getInstance().getDeletePhoneNumber());
            deleteStatement.setInt(1, contact.getId());
            deleteStatement.executeUpdate();
            updateStatement = connection.prepareStatement(QueriesManager.getInstance().getUpdatePhoneNumbersSQL());
            for (int i = 0; i < contact.getPhoneNumbers().size(); i++) {
                updateStatement.setInt(1, contact.getId());
                updateStatement.setString(2, contact.getPhoneNumbers().get(i).getNumber());
                updateStatement.addBatch();
            }
            updateStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(deleteStatement);
            DbUtils.closeQuietly(updateStatement);
            DbUtils.closeQuietly(connection);
        }
    }
}
