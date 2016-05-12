package com.addressbook.validators;

import com.addressbook.exceptions.ValidationException;
import com.addressbook.model.Contact;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by birsan on 5/12/2016.
 */
public class ContactValidator {
    private static ContactValidator ourInstance = new ContactValidator();
    private static final int MAX_LENGTH = 25;

    public static ContactValidator getInstance() {
        return ourInstance;
    }

    private ContactValidator() {
    }

    public void validateContact(Contact contact) throws ValidationException {
        ValidationException validationException = new ValidationException();
        if (StringUtils.isBlank(contact.getFirstName())) {
            validationException.getMessages().add("First name cannot be empty!");
        }
        if (!StringUtils.isAllUpperCase(contact.getFirstName().substring(0, 1))) {
            validationException.getMessages().add("First name must begin with an uppercase!");
        }
        String firstName = contact.getFirstName();
        validateMaxLength(validationException, firstName, "First name");
    }

    private void validateMaxLength(ValidationException validationException, String field, String fieldName) {
        if (field.length() > MAX_LENGTH) {
            validationException.getMessages().add(fieldName + " cannot contain more than " + MAX_LENGTH + " characters!");
        }
    }
}
