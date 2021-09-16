package br.demo.rc.hexagonal.persistence;

//Imports:
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.demo.rc.hexagonal.diplomat.out.LoadAccountPort;
import br.demo.rc.hexagonal.diplomat.out.SaveAccountPort;
import br.demo.rc.hexagonal.model.BankAccount;

@Component
public class BankAccountRepository implements LoadAccountPort, SaveAccountPort {
    //Attributes:
    private SpringDataBankAccountRepository repository;

    //Constructors:
    public BankAccountRepository() {

    }

    //Methods:
    @Override
    public void save(BankAccount bankAccount) {
        repository.save(bankAccount);
        
    }

    @Override
    public Optional<BankAccount> load(Long id) {
        return repository.findById(id);
    }
}