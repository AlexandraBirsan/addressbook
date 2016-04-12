package com.addressbook.controller;

/**
 * Created by birsan on 4/12/2016.
 */
public class Validator {
    public  boolean nameValidator(String s){
        if(s.isEmpty()||Character.isUpperCase(s.charAt(0)))
            return false;
        return true;

    }

    public  boolean phoneValidator(String s){
        if (s.isEmpty()||s.contains("[0-9]+"))
            return false;
        return true;
    }
}
