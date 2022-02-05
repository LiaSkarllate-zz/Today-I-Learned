package br.algaworks.maven;

//Imports:
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CalculatorTest {
    @Test
    public void sumTest() {
        //Objects:
        Calculator calculator = new Calculator();
        List<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        //Logic:
        int expected = 15;
        int actual = calculator.sum(values);

        assertEquals(expected, actual);
    }
}