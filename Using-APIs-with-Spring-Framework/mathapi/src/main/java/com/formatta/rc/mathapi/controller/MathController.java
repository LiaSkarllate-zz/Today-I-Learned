package com.formatta.rc.mathapi.controller;

//Imports:
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formatta.rc.mathapi.service.MathService;
import com.formatta.rc.mathapi.model.MathReturn;


@RestController
@RequestMapping("/math")
public class MathController {
    //Attributes:
    @Autowired
    MathService mathService;
    
    //Methods:
    /** 
     * @param n
     * @return ResponseEntity<MathReturn>
     */
    @GetMapping(value = "/fibonacci/{n}", produces = "application/json")
    public ResponseEntity<MathReturn> getNFibonacci(@PathVariable(value = "n") int n) {
        if(n > 0){
            return new ResponseEntity<>(new MathReturn(this.mathService.getNFibonacci(n)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MathReturn(), HttpStatus.BAD_REQUEST);
    }

    
    /** 
     * @param n
     * @return ResponseEntity<MathReturn>
     */
    @GetMapping(value = "/fibonacci/all/{n}", produces = "application/json")
    public ResponseEntity<MathReturn> getAllFibonacci(@PathVariable(value = "n") int n) {
        if(n > 0){
            return new ResponseEntity<>(new MathReturn(this.mathService.getAllFibonacci(n)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MathReturn(), HttpStatus.BAD_REQUEST);
    }
}
