package com.formatta.rc.diversosapi.service;

//Imports:
import java.util.ArrayList;
import java.util.List;

import com.formatta.rc.diversosapi.model.Word;

import org.springframework.stereotype.Service;

@Service
public class Diversos1service {
    
    /** 
     * @param fullName
     * @return ListStringsBody
     */
    //Methods:
    public List<String> getFirstNameMidNameAndLastName(String fullName){
        //Objects:
        String middleName = "";
        String[] names = fullName.split(" ", 0);
        List<String> body = new ArrayList<String>();

        //It splits the first name;
        body.add(names[0]);
        
        //It splits the middle name;
        for(int n = 1; n < (names.length - 1); n++){
            middleName = middleName + names[n] + " ";
        }

        body.add(middleName);

        //It splits the last name;
        body.add((names.length > 1)? names[names.length - 1] : "");

        return body;
    }

    
    /** 
     * @param fullName
     * @return ListListCharacterBody
     */
    public List<List<Character>> getVowelsAndConsonants(String fullName) {
        //Constants:
        String LETTERS = "aeiouAEIOU";

        //Objects:
        List<List<Character>> body = new ArrayList<>();

        List<Character> vowels = new ArrayList<>();
        List<Character> consonants = new ArrayList<>();
        
        fullName = fullName.replace(" ", "");
        
        for(char ch : fullName.toCharArray()) {
            if(LETTERS.indexOf(ch) != -1){
                vowels.add(ch);
            }else{
                consonants.add(ch);
            }
        }

        body.add(vowels);
        body.add(consonants);

        return body;
    }


    
    /** 
     * @param text
     * @return ListWord
     */
    public List<Word> getEachWordAndItsLength(String text) {
        //Objects:
        Word newWord;
        String[] words = text.split(" ", 0);
        List<Word> body = new ArrayList<>();

        for(String word : words){
            newWord = new Word(word, word.length());
            body.add(newWord);
        }

        return body;
    }
}