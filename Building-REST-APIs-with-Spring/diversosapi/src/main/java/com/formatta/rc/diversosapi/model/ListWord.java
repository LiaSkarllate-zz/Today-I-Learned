package com.formatta.rc.diversosapi.model;

//Imports:
import java.util.List;
import java.util.ArrayList;

public class ListWord {
    //Attributes:
    private List<Word> words;
    
    //Constructors:
    public ListWord(List<Word> words) {
        this.words = words;
    }

    public ListWord() {
        this.words = new ArrayList<Word>();
    }

    public ListWord(Word word) {
        this.words = new ArrayList<Word>();
        this.words.add(word);
    }

    //Methods:
    public void addWord(Word word){
        this.words.add(word);
    }

    //Getters and setters:
    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
