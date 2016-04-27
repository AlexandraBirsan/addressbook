CREATE TABLE ADDRESSBOOK_CONTACTS (
  AC_CONTACT_ID   INTEGER,
  AC_FIRST_NAME   VARCHAR2(25),
  AC_LAST_NAME    VARCHAR2(25),
  AC_COMPANY      VARCHAR2(25),
  AC_CONTENT_TYPE VARCHAR2(25),
  AC_PHOTO        BLOB,
  PRIMARY KEY (AC_CONTACT_ID)
);

CREATE TABLE ADDRESSBOOK_PHONENUMBERS (
  AP_CONTACT_ID INTEGER,
  AP_NUMBER     VARCHAR2(25),
  FOREIGN KEY (AP_CONTACT_ID) REFERENCES ADDRESSBOOK_CONTACTS (AC_CONTACT_ID)
);

CREATE SEQUENCE contacts_sequence
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
CACHE 10;

INSERT INTO ADDRESSBOOK_CONTACTS (AC_CONTACT_ID, AC_FIRST_NAME, AC_LAST_NAME, AC_COMPANY)
VALUES (CONTACTS_SEQUENCE.NEXTVAL, 'Alex', 'Birsan', 'SRL');
INSERT INTO ADDRESSBOOK_CONTACTS (AC_CONTACT_ID, AC_FIRST_NAME, AC_LAST_NAME, AC_COMPANY)
VALUES (CONTACTS_SEQUENCE.NEXTVAL, 'Ion', 'Popescu', 'OPTY');
INSERT INTO ADDRESSBOOK_CONTACTS (AC_CONTACT_ID, AC_FIRST_NAME, AC_LAST_NAME, AC_COMPANY)
VALUES (CONTACTS_SEQUENCE.NEXTVAL, 'Alina', 'Dumitrescu', 'SRL');

INSERT INTO ADDRESSBOOK_PHONENUMBERS VALUES (1, '075-2028861');
INSERT INTO ADDRESSBOOK_PHONENUMBERS VALUES (1, '074-2025861');
INSERT INTO ADDRESSBOOK_PHONENUMBERS VALUES (3, '073-0028861');