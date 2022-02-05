package br.unesp.jwt.dto;

//Imports:
import br.unesp.jwt.model.Access;
import br.unesp.jwt.model.User;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
    //Attributes:
    @NotNull(message = "The field 'name' is mandatory.")
    private String name;
    @NotNull(message = "The field 'email' is mandatory.")
    private String email;
    @NotNull(message = "The field 'password' is mandatory.")
    @Size(min = 8, max = 16, message = "The field 'password' size must be equal or greater than 8 characters and less than 16 characters.")
    private String password;

    //Constructors:
    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserDTO(){
    
    }

    //Methods:
    public User toUser(){
        return new User(this.getName(), new Access(this.getEmail(), this.getPassword()));
    }

    //Getters and setters:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}