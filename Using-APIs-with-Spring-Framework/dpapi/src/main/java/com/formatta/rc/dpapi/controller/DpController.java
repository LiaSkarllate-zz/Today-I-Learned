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
    
    //Methods:
    @GetMapping(value = "/netsalary", produces = "application/json")
    public ResponseEntity<DpBody> getNetSalary(@RequestParam double grossSalary, @RequestParam int numberOfDependents) {
        if(grossSalary > 0 && numberOfDependents >= 0){
            return new ResponseEntity<>(new DpBody(this.dpService.getNetSalary(grossSalary, numberOfDependents)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DpBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/inssvalue", produces = "application/json")
    public ResponseEntity<DpBody> getInssValue(@RequestParam double grossSalary) {
        if(grossSalary > 0){
            return new ResponseEntity<>(new DpBody(this.dpService.getInssValue(grossSalary)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DpBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/incometaxvalue", produces = "application/json")
    public ResponseEntity<DpBody> getIncomeTaxValue(@RequestParam double grossSalary, @RequestParam int numberOfDependents) {
        if(grossSalary > 0 && numberOfDependents >= 0){
            return new ResponseEntity<>(new DpBody(this.dpService.getIncomeTaxValue(grossSalary, numberOfDependents)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DpBody(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/vacationvalue", produces = "application/json")
    public ResponseEntity<DpBody> getVacationValue(@RequestParam double grossSalary, @RequestParam(defaultValue = "0") double averageOvertimeHours, @RequestParam int numberOfDependents, @RequestParam int numberOfVacationDays, @RequestParam boolean sold, @RequestParam boolean advance13th) {
        if(grossSalary > 0 && numberOfDependents >= 0 && numberOfVacationDays > 0 && numberOfVacationDays <= 30){
            return new ResponseEntity<>(new DpBody(this.dpService.getVacationValue(grossSalary, averageOvertimeHours, numberOfDependents, numberOfVacationDays, sold, advance13th)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new DpBody(), HttpStatus.BAD_REQUEST);
    }
}