package com.formatta.rc.dpapi.model;

//Imports:
import java.util.LinkedList;
import java.util.List;

public class DpBody {
    //Attributes:
    List<Double> numbers;

    //Construtors:
    public DpBody(List<Double> numbers) {
        this.numbers = numbers;
    }

    public DpBody() {
        this.numbers = new LinkedList<>();
    }

    public DpBody(Double number) {
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