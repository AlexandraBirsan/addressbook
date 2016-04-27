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
            for (int i = 0; i < phoneNumbers.size(); i++) {
                PhoneNumber number = phoneNumbers.get(i);
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
        Connection connection=null;
        PreparedStatement updateStatement=null;
        try{
            connection=DriverManager.getConnection(ConnectionConstants.URL,ConnectionConstants.USERNAME,ConnectionConstants.PASSWORD);
            updateStatement=connection.prepareStatement(QueriesManager.getInstance().getUpdateContactSQL());
            for(PhoneNumber number:contact.getPhoneNumber()){
                updateStatement.setString(1,number.getNumber());
                updateStatement.setLong(2,contact.getId());
                updateStatement.addBatch();
            }
            updateStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeQuietly(updateStatement);
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public List<PhoneNumber> getPhoneNumbers(Long id) {
        return null;
    }

    @Override
    public void deletePhoneNumbers(Long id) {

    }
}
