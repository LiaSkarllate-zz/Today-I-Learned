package br.demo.rc.hexagonal.persistence;

//Imports:
import org.springframework.data.mongodb.repository.MongoRepository;
import br.demo.rc.hexagonal.model.BankAccount;

public interface SpringDataBankAccountRepository extends MongoRepository<BankAccount, Long> {

}