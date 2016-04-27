package com.addressbook.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by birsan on 4/22/2016.
 */
public class PhoneValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(o.toString());
        if (!matcher.matches() || o.toString().isEmpty()) {
            FacesMessage message = new FacesMessage("Invalid phoneNumber!");
            throw new ValidatorException(message);
        }
    }
}
