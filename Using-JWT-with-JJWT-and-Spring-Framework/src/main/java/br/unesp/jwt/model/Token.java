package br.unesp.jwt.model;

public class Token {
    //Attributes:
    private String token;
    
    //Constructors:
    public Token(String token) {
        this.token = token;
    }

    public Token() {
        
    }
    
    //Getters and setters:
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
