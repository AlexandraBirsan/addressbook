package com.addressbook.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by birsan on 4/22/2016.
 */
public class LastNameValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if(o.toString().isEmpty()||!Character.isUpperCase(o.toString().charAt(0))){
            FacesMessage message=new FacesMessage("Invalid first name or last name!");
            throw  new ValidatorException(message);
        }
    }
}
