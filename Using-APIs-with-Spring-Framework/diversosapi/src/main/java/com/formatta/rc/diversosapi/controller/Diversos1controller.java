package com.formatta.rc.diversosapi.controller;

//Imports:
import com.formatta.rc.diversosapi.model.ListListCharacterBody;
import com.formatta.rc.diversosapi.model.ListStringBody;
import com.formatta.rc.diversosapi.model.ListWord;
import com.formatta.rc.diversosapi.service.Diversos1service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diversos/1")
public class Diversos1controller {
    //Attributes:
    @Autowired
    Diversos1service diversos1Service;
    
    //Methods:
    @GetMapping(value = "/splitname", produces = "application/json")
    ResponseEntity<ListStringBody> getFirstNameMidNameAndLastName(@RequestParam String fullName){
        if(fullName.length() > 0){
            return new ResponseEntity<>(new ListStringBody(this.diversos1Service.getFirstNameMidNameAndLastName(fullName)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ListStringBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/vowelsandconsonants", produces = "application/json")
    ResponseEntity<ListListCharacterBody> getVowelsAndConsonants(@RequestParam String fullName){
        if(fullName.length() > 0){
            return new ResponseEntity<>(new ListListCharacterBody(this.diversos1Service.getVowelsAndConsonants(fullName)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ListListCharacterBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/eachwordanditslength", produces = "application/json")
    ResponseEntity<ListWord> getEachWordAndItsLength(@RequestParam String text){
        if(text.length() > 0){
            return new ResponseEntity<>(new ListWord(this.diversos1Service.getEachWordAndItsLength(text)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ListWord(), HttpStatus.BAD_REQUEST);
    }
}