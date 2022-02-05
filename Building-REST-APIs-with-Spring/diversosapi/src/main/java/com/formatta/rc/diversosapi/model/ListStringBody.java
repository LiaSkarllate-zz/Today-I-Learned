package com.formatta.rc.diversosapi.model;

//Imports:
import java.util.LinkedList;
import java.util.List;

public class ListStringBody {
    //Attributes:
    List<String> strings;

    //Construtors:
    public ListStringBody(List<String> strings) {
        this.strings = strings;
    }

    public ListStringBody() {
        this.strings = new LinkedList<>();
    }

    public ListStringBody(String string) {
        this.strings = new LinkedList<>();
        this.strings.add(string);
    }

    //Methods:
    public void addString(String string) {
        this.strings.add(string);
    }

    //Getters and setters:
    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }
}