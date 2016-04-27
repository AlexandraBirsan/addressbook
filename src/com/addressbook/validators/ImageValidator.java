package com.addressbook.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 * Created by birsan on 4/22/2016.
 */
public class ImageValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Part file = (Part) o;
        if (o != null && !file.getContentType().contains("image")) {
            FacesMessage message = new FacesMessage("Invalid file extension!");
            throw new ValidatorException(message);
        } else if (o == null) {
            FacesMessage message = new FacesMessage("You should upload a picture!");
            throw new ValidatorException(message);
        }
    }
}
