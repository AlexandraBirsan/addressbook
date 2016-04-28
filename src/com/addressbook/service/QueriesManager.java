package com.addressbook.service;

/**
 * Created by birsan on 4/27/2016.
 */
public class QueriesManager {
    private QueriesManager queriesManager;

    public QueriesManager getQueriesManager() {
        return queriesManager;
    }

    public static QueriesManager getInstance() {
        return LazyHolder.INSTANCE;
    }
    private QueriesManager(){}

    public String getGetNextValueSQL() {
        return getNextValueSQL;
    }

    public void setGetNextValueSQL(String getNextValueSQL) {
        this.getNextValueSQL = getNextValueSQL;
    }


    private static class LazyHolder {
        static final QueriesManager INSTANCE = new QueriesManager();
    }

    private String createContactSQL;
    private String createPhoneNumberSQL;
    private String getNextValueSQL;
    private String updateContactSQL;
    private String updatePhoneNumberSQL;
    private String getContactSQL;

    public String getGetContactSQL() {
        return getContactSQL;
    }

    public void setGetContactSQL(String getContactSQL) {
        this.getContactSQL = getContactSQL;
    }

    public String getUpdateContactSQL() {
        return updateContactSQL;
    }

    public void setUpdateContactSQL(String updateContactSQL) {
        this.updateContactSQL = updateContactSQL;
    }

    public String getUpdatePhoneNumberSQL() {
        return updatePhoneNumberSQL;
    }

    public void setUpdatePhoneNumberSQL(String updatePhoneNumberSQL) {
        this.updatePhoneNumberSQL = updatePhoneNumberSQL;
    }

    public String getCreateContactSQL() {
        return createContactSQL;
    }

    public void setCreateContactSQL(String createContactSQL) {
        this.createContactSQL = createContactSQL;
    }

    public String getCreatePhoneNumberSQL() {
        return createPhoneNumberSQL;
    }

    public void setCreatePhoneNumberSQL(String createPhoneNumberSQL) {
        this.createPhoneNumberSQL = createPhoneNumberSQL;
    }

}
