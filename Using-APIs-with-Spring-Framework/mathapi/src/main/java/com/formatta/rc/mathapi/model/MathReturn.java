package com.formatta.rc.mathapi.model;

//Imports:
import java.util.LinkedList;
import java.util.List;

public class MathReturn {
    //Attributes:
    List<Integer> math;

    //Construtors:
    public MathReturn(List<Integer> numbers) {
        this.math = numbers;
    }

    public MathReturn() {
        this.math = new LinkedList<>();
    }

    public MathReturn(int number) {
        this.math = new LinkedList<>();
        this.math.add(number);
    }

    //Methods:
    public void addNumber(Integer number) {
        this.math.add(number);
    }

    //Getters and setters:
    public List<Integer> getNumbers() {
        return math;
    }

    public void setNumbers(List<Integer> numbers) {
        this.math = numbers;
    }
}
