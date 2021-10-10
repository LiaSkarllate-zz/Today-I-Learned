package com.formatta.rc.diversosapi.controller;

//Imports:
import com.formatta.rc.diversosapi.model.ListListCharacterBody;
import com.formatta.rc.diversosapi.model.ListStringBody;
import com.formatta.rc.diversosapi.model.ListWord;
import com.formatta.rc.diversosapi.service.Diversos2service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diversos/2")
public class Diversos2controller {
    //Attributes:
    @Autowired
    Diversos2service diversos2Service;
    
    //Methods:
    @GetMapping(value = "/splitname", produces = "application/json")
    public ResponseEntity<ListStringBody> getFirstNameMidNameAndLastName(@RequestParam String fullName){
        if(fullName.length() > 0){
            return new ResponseEntity<>(new ListStringBody(this.diversos2Service.getFirstNameMidNameAndLastName(fullName)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ListStringBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/primefactors/{number}", produces = "application/json")
    public ResponseEntity<List<Integer>> getPrimeFactors(@PathVariable int number){
        if(number > 0){
            return new ResponseEntity<>(new ListStringBody(this.diversos2Service.getPrimeFactors(number)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ListStringBody(), HttpStatus.BAD_REQUEST);
    }


}