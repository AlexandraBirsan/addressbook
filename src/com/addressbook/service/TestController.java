package com.addressbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by birsan on 5/18/2016.
 */
@RestController
public class TestController {

    @Autowired private ApplicationContext applicationContext;

    @RequestMapping(value ="/test",method = RequestMethod.GET)
    public String hello() {
        return "test";
    }
}