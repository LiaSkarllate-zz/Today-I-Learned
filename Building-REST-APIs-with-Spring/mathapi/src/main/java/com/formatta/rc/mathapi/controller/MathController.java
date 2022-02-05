package com.formatta.rc.mathapi.controller;

//Imports:
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formatta.rc.mathapi.service.MathService;
import com.formatta.rc.mathapi.model.MathBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/math")
public class MathController {
    //Attributes:
    @Autowired
    MathService mathService;
    
    //Methods:
    /** 
     * @param n The n value. It must be greater than zero (n > 0).
     * @return ResponseEntity<MathReturn> The n-th value of the Fibonacci sequence.
     */
    @GetMapping(value = "/fibonacci/{n}", produces = "application/json")
    public ResponseEntity<MathBody> getNfibonacci(@PathVariable(value = "n") int n) {
        if(n > 0){
            return new ResponseEntity<>(new MathBody(this.mathService.getNfibonacci(n)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MathBody(), HttpStatus.BAD_REQUEST);
    }
    
    /** 
     * @param n The n value. It must be greater than zero (n > 0).
     * @return ResponseEntity<MathReturn> A list of the first n-th elements of the Fibonacci sequence. 
     */
    @GetMapping(value = "/fibonacci/all/{n}", produces = "application/json")
    public ResponseEntity<MathBody> getAllFibonacci(@PathVariable(value = "n") int n) {
        if(n > 0){
            return new ResponseEntity<>(new MathBody(this.mathService.getAllfibonacci(n)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MathBody(), HttpStatus.BAD_REQUEST);
    }

    
    /** 
     * @param n  The n value. It must be equal or greater than zero (n >= 0).
     * @return ResponseEntity<MathReturn> The factorial of the number n.
     */
    @GetMapping(value = "/factorial/{n}", produces = "application/json")
    public ResponseEntity<MathBody> getNFactorial(@PathVariable(value = "n") int n) {
        if(n >= 0){
            return new ResponseEntity<>(new MathBody(this.mathService.getNfactorial(n)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MathBody(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param d The common difference of the arithmetic progression
     * @param a1 The first term of the arithmetic progression.
     * @param n The n value. It must be greater than zero (n > 0).
     * @param body
     * @return ResponseEntity<MathReturn> The n-th value of the arithmetic progression.
     */
    @GetMapping(value = "/pa", produces = "application/json")
    public ResponseEntity<MathBody> getNpa(@RequestParam double d, @RequestParam double a1, @RequestParam int n) {
        if(n > 0) {
            return new ResponseEntity<>(new MathBody(this.mathService.getNpa(d, a1, n)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MathBody(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param r The common ratio of the geometric progression
     * @param a1 The first term of the geometric progression.
     * @param n The n value. It must be greater than zero (n > 0).
     * @return ResponseEntity<MathReturn> The n-th value of the geometric progression
     */
    @GetMapping(value = "/pg", produces = "application/json")
    public ResponseEntity<MathBody> getNpg(@RequestParam double r, @RequestParam double a1, @RequestParam int n) {
        if(n > 0){
            return new ResponseEntity<>(new MathBody(this.mathService.getNpg(r, a1, n)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new MathBody(), HttpStatus.BAD_REQUEST);
    }
}