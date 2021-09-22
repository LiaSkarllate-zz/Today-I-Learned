package com.formatta.rc.dpapi.controller;

//Imports:
import com.formatta.rc.dpapi.model.DpBody;
import com.formatta.rc.dpapi.service.DpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dp")
public class DpController {
    //Attributes:
    @Autowired
    DpService dpService;
    
    /** 
     * @param grossSalary
     * @param dependents
     * @return ResponseEntity<DpBody>
     */
    //Methods:
    @GetMapping(value = "/netsalary", produces = "application/json")
    public ResponseEntity<DpBody> getNetSalary(@RequestParam double grossSalary, @RequestParam int dependents) {
        if(grossSalary > 0 && dependents >= 0){
            return new ResponseEntity<>(new DpBody(this.dpService.getNetSalary(grossSalary, dependents)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DpBody(), HttpStatus.BAD_REQUEST);
    }
}