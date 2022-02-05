package br.unesp.jwt.service;

//Imports:
import br.unesp.jwt.exception.InvalidAccessException;
import br.unesp.jwt.model.Access;
import br.unesp.jwt.model.User;
import br.unesp.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //Attributes:
    @Autowired
    private UserRepository userRepository;

    //Constructors:
    public UserService(){

    }

    //Methods:
    public User register(User user){
        return userRepository.save(user);
    }
    
    public User authenticate(Access access){
        User user = userRepository.findByEmail(access.getEmail());
        
        if(user.getAccess().getPassword().equals("test")) {
            return user;
        }else{
            throw new InvalidAccessException();
        }
    }
}
