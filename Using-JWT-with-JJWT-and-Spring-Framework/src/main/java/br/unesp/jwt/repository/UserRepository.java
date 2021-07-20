package br.unesp.jwt.repository;

//Imports:
import br.unesp.jwt.model.Access;
import br.unesp.jwt.model.User;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class UserRepository{
    //Constructors:
    public UserRepository() {    
        
    }

    //Methods:
    public User save(User user) {
        Random random = new Random();
        user.setID(random.nextInt());
        
        return user;
    } 

    public User findByEmail(String email) {
        return new User("Test", new Access(email, "test"));
    }
}