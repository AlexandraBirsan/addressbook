package com.addressbook.dao;

import com.addressbook.model.Contact;
import com.addressbook.model.PhoneNumber;
import com.addressbook.service.ConnectionConstants;
import com.addressbook.service.QueriesManager;
import oracle.jdbc.OracleTypes;
import org.apache.commons.dbutils.DbUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by birsan on 4/26/2016.
 */
public class ContactsDaoImpl implements ContactsDao {

    private static final int FIRST_NAME_INDEX = 2;
    private static final int LAST_NAME_INDEX = 3;
    private static final int COMPANY_INDEX = 4;
    private static final int CONTENT_TYPE_INDEX = 5;
    private static final int PHOTO_INDEX = 6;
    private static final int PHONE_CURSOR_INDEX = 7;

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
            insertStatement.setString(FIRST_NAME_INDEX, contact.getFirstName());
            insertStatement.setString(LAST_NAME_INDEX, contact.getLastName());
            insertStatement.setString(COMPANY_INDEX, contact.getCompany());
            insertStatement.setString(CONTENT_TYPE_INDEX, contact.getContentType());
            InputStream photoInputStream = new ByteArrayInputStream(contact.getPhoto());
            insertStatement.setBlob(PHOTO_INDEX, photoInputStream);
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
        Connection connection = null;
        PreparedStatement updateStatement = null;
        try {
            connection = DriverManager.getConnection(ConnectionConstants.URL, ConnectionConstants.USERNAME, ConnectionConstants.PASSWORD);
            updateStatement = connection.prepareStatement(QueriesManager.getInstance().getUpdateContactSQL());
            updateStatement.setString(FIRST_NAME_INDEX - 1, contact.getFirstName());
            updateStatement.setString(LAST_NAME_INDEX - 1, contact.getLastName());
            updateStatement.setString(COMPANY_INDEX - 1, contact.getCompany());
            updateStatement.setString(CONTENT_TYPE_INDEX - 1, contact.getContentType());
            InputStream photoInputStream = new ByteArrayInputStream(contact.getPhoto());
            updateStatement.setBlob(PHOTO_INDEX - 1, photoInputStream);
            updateStatement.setLong(6, contact.getId());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(updateStatement);
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public Contact getContact(Integer id) {
        Connection connection = null;
        CallableStatement statement = null;
        Contact contact = null;
        try {
            connection = DriverManager.getConnection(ConnectionConstants.URL, ConnectionConstants.USERNAME, ConnectionConstants.PASSWORD);
            statement = connection.prepareCall(QueriesManager.getInstance().getGetContactSQL());
            statement.setInt(1, id);
            setOutputParameters(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString(FIRST_NAME_INDEX);
                String lastName = rs.getString(LAST_NAME_INDEX);
                String company = rs.getString(COMPANY_INDEX);
                Blob photo = rs.getBlob(PHOTO_INDEX);
                String contentType = rs.getString(CONTENT_TYPE_INDEX);
                List<PhoneNumber> phoneNumbers = buildPhoneNumbersFromResultSet(id, rs);
                contact = new Contact();
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setCompany(company);
                contact.setPhoto(photo.getBytes(1, (int) photo.length()));
                contact.setContentType(contentType);
                contact.setPhoneNumber(phoneNumbers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return contact;
    }

    private List<PhoneNumber> buildPhoneNumbersFromResultSet(Integer contactId, ResultSet rs) throws SQLException {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        ResultSet cursorPhoneNumbers = (ResultSet) rs.getObject(PHONE_CURSOR_INDEX);
        while (cursorPhoneNumbers.next()) {
            PhoneNumber number = new PhoneNumber();
            number.setContactId(contactId);
            number.setNumber(cursorPhoneNumbers.getString(1));
            phoneNumbers.add(number);
        }
        return phoneNumbers;
    }

    private void setOutputParameters(CallableStatement statement) throws SQLException {
        statement.registerOutParameter(FIRST_NAME_INDEX, Types.VARCHAR);
        statement.registerOutParameter(LAST_NAME_INDEX, Types.VARCHAR);
        statement.registerOutParameter(COMPANY_INDEX, Types.VARCHAR);
        statement.registerOutParameter(PHOTO_INDEX, Types.BLOB);
        statement.registerOutParameter(CONTENT_TYPE_INDEX, Types.VARCHAR);
        statement.registerOutParameter(PHONE_CURSOR_INDEX, OracleTypes.CURSOR);
    }

    @Override
    public void deleteContact(Integer id) {

    }

    @Override
    public List<Contact> getAll() {
        return null;
    }

}
