package br.demo.rc.hexagonal.controller;

//Imports:
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.demo.rc.hexagonal.logic.BankAccountService;

@RestController
@RequestMapping("/account")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;
    
    //Constructors:
    public BankAccountController() {

    }

    @PostMapping(value = "/{id}/deposit/{amount}")
    void deposit(@PathVariable final Long id, @PathVariable final Float amount) {
        this.bankAccountService.deposit(id, amount);
    }

    @PostMapping(value = "/{id}/withdraw/{amount}")
    void withdraw(@PathVariable final Long id, @PathVariable final Float amount) {
        this.bankAccountService.withdraw(id, amount);
    }
}