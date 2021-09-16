package br.demo.rc.hexagonal.diplomat.out;

//Imports:
import br.demo.rc.hexagonal.model.BankAccount;

public interface SaveAccountPort {
    void save(BankAccount bankAccount);
}