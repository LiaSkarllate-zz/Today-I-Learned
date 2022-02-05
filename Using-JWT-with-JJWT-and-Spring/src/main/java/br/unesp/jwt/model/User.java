package br.unesp.jwt.model;

import br.unesp.jwt.dto.UserDTO;

public class User {
    //Attribute:
    private long ID;
    private String name;
    private Access access;

    //Constructors:
    public User(String name, Access access) {
        this.name = name;
        this.access = access;
    }
    
    public User(){

    }
    
    //Methods:
    public UserDTO toDTO(){
        return new UserDTO(this.getName(), this.getAccess().getEmail(), this.getAccess().getPassword()); 
    }

    //Getters and setters:
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }
}