package com.formatta.rc.diversosapi.model;

//Imports:
import java.util.ArrayList;
import java.util.List;

public class ListListCharacterBody {
    //Attributes:
    List<List<Character>> characters;

    //Construtors:
    public ListListCharacterBody(List<List<Character>> characters) {
        this.characters = characters;
    }

    public ListListCharacterBody() {
        this.characters = new ArrayList<>();
    }

    //Methods:
    public void addListCharacter(List<Character> listCharacter) {
        this.characters.add(listCharacter);
    }

    //Getters and setters:
    public List<List<Character>> getCharacters() {
        return characters;
    }

    public void setCharacters(List<List<Character>> characters) {
        this.characters = characters;
    }
}