package br.algaworks.maven;

//Imports:
import java.util.List;

public class Calculator {
    //Methods:
    int sum(List<Integer> values){
        //Variables:
        int sum = 0;

        //Logic:
        for(Integer value : values) {
            sum += value.intValue();
        }

        return sum;
    }
}