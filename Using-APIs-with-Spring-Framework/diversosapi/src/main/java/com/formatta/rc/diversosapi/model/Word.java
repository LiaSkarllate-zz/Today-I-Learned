package com.formatta.rc.diversosapi.model;

public class Word {
    //Attributes:
    private String lexame; 
    private int length;
    
    //Constructors:
    public Word(String lexame, int length) {
        this.lexame = lexame;
        this.length = length;
    }

    public Word() {

    }

    //Getters and setters:
    public String getLexame() {
        return lexame;
    }

    public void setLexame(String lexame) {
        this.lexame = lexame;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
