package com.addressbook.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

/**
 * Created by birsan on 4/22/2016.
 */
public class FirstNameValidator implements javax.faces.validator.Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o.toString().isEmpty() || !Character.isUpperCase(o.toString().charAt(0))) {
            FacesMessage message = new FacesMessage("Invalid first name!");
            throw new ValidatorException(message);
        }
    }
}
