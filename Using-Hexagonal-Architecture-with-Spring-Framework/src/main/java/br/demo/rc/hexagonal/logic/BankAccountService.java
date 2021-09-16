package br.demo.rc.hexagonal.logic;

//Imports:
import br.demo.rc.hexagonal.model.BankAccount;
import br.demo.rc.hexagonal.diplomat.in.DepositUseCase;
import br.demo.rc.hexagonal.diplomat.in.WithdrawUseCase;
import br.demo.rc.hexagonal.persistence.BankAccountRepository;

import java.util.NoSuchElementException;

public class BankAccountService implements DepositUseCase, WithdrawUseCase {
    //Attributes:
    private BankAccountRepository bankAccountRepository;

    //Constructors:
    public BankAccountService() {

    }

    //Methods:
    @Override
    public void deposit(Long id, Float amount) {
        BankAccount account = this.bankAccountRepository.load(id).orElseThrow(NoSuchElementException::new);

        account.deposit(amount);

        this.bankAccountRepository.save(account);
    }

    @Override
    public boolean withdraw(Long id, Float amount) {
        BankAccount account = this.bankAccountRepository.load(id).orElseThrow(NoSuchElementException::new);

        boolean hasWithdrawn = account.withdraw(amount);

        if(hasWithdrawn) {
            this.bankAccountRepository.save(account);
        }
        
        return hasWithdrawn;
    }
}