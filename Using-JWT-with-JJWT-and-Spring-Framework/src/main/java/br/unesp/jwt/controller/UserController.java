package br.unesp.jwt.controller;

//Imports:
import br.unesp.jwt.dto.AccessDTO;
import br.unesp.jwt.dto.UserDTO;
import br.unesp.jwt.model.Token;
import br.unesp.jwt.model.User;
import br.unesp.jwt.service.TokenService;
import br.unesp.jwt.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    //Attributes:
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    //Controllers:
    public UserController(){

    }

    //Methods:
    @PostMapping("/sign-up")
    public ResponseEntity<User> register(@Valid @RequestBody UserDTO userFromClient){
        User userFromDB = userService.register(userFromClient.toUser());
        return new ResponseEntity<>(userFromDB, HttpStatus.CREATED);
    }
    
    @PostMapping("/sign-in")
    public ResponseEntity<Token> authenticate(@Valid @RequestBody AccessDTO accessFromClient){
        User userFromDB = userService.authenticate(accessFromClient.toAccess());
        return new ResponseEntity<>(new Token(tokenService.generateToken(userFromDB)), HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/staff-only")
    public ResponseEntity<String> authenticate(@RequestHeader String Authorization){
        if(tokenService.validate(Authorization)){
            return new ResponseEntity<>("You've been authenticated to enter here.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("You haven't authenticated to enter here.", HttpStatus.UNAUTHORIZED);
        }
    }
}