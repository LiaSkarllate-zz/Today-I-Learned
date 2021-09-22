package com.formatta.rc.mathapi.model;

//Imports:
import java.util.LinkedList;
import java.util.List;

public class MathBody {
    //Attributes:
    List<Double> numbers;

    //Construtors:
    public MathBody(List<Double> numbers) {
        this.numbers = numbers;
    }

    public MathBody() {
        this.numbers = new LinkedList<>();
    }

    public MathBody(Double number) {
        this.numbers = new LinkedList<>();
        this.numbers.add(number);
    }

    //Methods:
    public void addNumber(Double number) {
        this.numbers.add(number);
    }

    //Getters and setters:
    public List<Double> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Double> numbers) {
        this.numbers = numbers;
    }
}