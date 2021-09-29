package com.formatta.rc.diversosapi.controller;

import com.formatta.rc.diversosapi.service.Diversos2service;

//Imports:
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diversos/2")
public class Diversos2controller {
    //Attributes:
    @Autowired
    Diversos2service diversos2Service;
    
    //Methods:
}