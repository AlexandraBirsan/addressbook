DROP TABLE ADDRESSBOOK_CONTACTS;
DROP TABLE ADDRESSBOOK_PHONENUMBERS;
DROP SEQUENCE CONTACTS_SEQUENCE;

DELETE FROM ADDRESSBOOK_CONTACTS;
DELETE FROM ADDRESSBOOK_PHONENUMBERS;

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
  FOREIGN KEY (AP_CONTACT_ID) REFERENCES ADDRESSBOOK_CONTACTS (AC_CONTACT_ID) ON DELETE CASCADE
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

UPDATE ADDRESSBOOK_CONTACTS
SET AC_FIRST_NAME = 'Andreea'
WHERE AC_FIRST_NAME = 'Ion';

CREATE OR REPLACE PROCEDURE GET_ALL_CONTACT_DATA
  (ID_CONTACT      IN  INTEGER, v_first_name OUT VARCHAR2,
   v_last_name     OUT VARCHAR2, v_company OUT VARCHAR2,
   v_content_type  OUT VARCHAR2, v_photo OUT BLOB,
   v_phone_numbers OUT SYS_REFCURSOR)
IS

  CURSOR ALL_DATA(ID_CONTACT INTEGER) IS
    SELECT
      ADDRESSBOOK_CONTACTS.AC_FIRST_NAME,
      ADDRESSBOOK_CONTACTS.AC_LAST_NAME,
      ADDRESSBOOK_CONTACTS.AC_COMPANY,
      ADDRESSBOOK_CONTACTS.AC_CONTENT_TYPE,
      ADDRESSBOOK_CONTACTS.AC_PHOTO
    FROM ADDRESSBOOK_CONTACTS
    WHERE AC_CONTACT_ID = ID_CONTACT;

  BEGIN
    OPEN all_data(id_contact);
    LOOP
      FETCH all_data INTO v_first_name, v_last_name, v_company, v_content_type, v_photo;
      EXIT WHEN all_data%NOTFOUND;
    END LOOP;
    CLOSE all_data;
    OPEN v_phone_numbers FOR
    SELECT AP_NUMBER
    FROM ADDRESSBOOK_PHONENUMBERS
    WHERE AP_CONTACT_ID = ID_CONTACT
    ORDER BY AP_NUMBER;

  END;


SET SERVEROUTPUT ON;
DECLARE
  v_first_name   VARCHAR2(25);
  v_last_name    VARCHAR2(25);
  v_company      VARCHAR2(25);
  v_photo        BLOB;
  v_content_type VARCHAR2(25);
  c_phone_number SYS_REFCURSOR;
  v_phone_number VARCHAR2(500);
BEGIN
  GET_ALL_CONTACT_DATA(24, v_first_name, v_last_name, v_company, v_content_type, v_photo, c_phone_number);
  LOOP
    FETCH c_phone_number INTO v_phone_number;
    EXIT WHEN c_phone_number%NOTFOUND;
    dbms_output.put_line(v_phone_number);
  END LOOP;
  CLOSE c_phone_number;
END;

SELECT *
FROM ADDRESSBOOK_PHONENUMBERS
WHERE AP_CONTACT_ID = 24;


INSERT ALL
INTO ADDRESSBOOK_CONTACTS (AC_CONTACT_ID) VALUES (contacts_sequence.nextval)
INTO ADDRESSBOOK_PHONENUMBERS VALUES (2, '075454654')
SELECT *
FROM dual;

SELECT *
FROM ADDRESSBOOK_PHONENUMBERS;

INSERT INTO ADDRESSBOOK_PHONENUMBERS VALUES (9, 000);
UPDATE ADDRESSBOOK_PHONENUMBERS
SET AP_NUMBER = 0121
WHERE AP_CONTACT_ID = 9;
DELETE FROM ADDRESSBOOK_PHONENUMBERS
WHERE AP_CONTACT_ID = 9;
COMMIT;

SELECT *
FROM addressbook_contacts;