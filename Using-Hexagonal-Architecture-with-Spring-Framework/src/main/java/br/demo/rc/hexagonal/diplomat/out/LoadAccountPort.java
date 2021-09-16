package br.demo.rc.hexagonal.diplomat.out;

//Imports:
import java.util.Optional;
import br.demo.rc.hexagonal.model.BankAccount;

public interface LoadAccountPort {
    Optional<BankAccount> load(Long id);
}
