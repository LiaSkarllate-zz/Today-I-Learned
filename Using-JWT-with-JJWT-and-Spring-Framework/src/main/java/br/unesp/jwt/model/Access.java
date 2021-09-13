package br.unesp.jwt.model;

import br.unesp.jwt.dto.AccessDTO;

public class Access {
    //Attributes:
    private long ID;
    private String email;
    private String password;
    
    //Constructors:
    public Access(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Access() {
        
    }
    
    //Methods:
    public AccessDTO toDTO(){
        return new AccessDTO(this.getEmail(), this.getPassword());
    }
    
    //Getters and setters:
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