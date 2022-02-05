package com.formatta.rc.mathapi.service;

//Imports:
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MathService {
    //Methods:
    /** 
     * Given a value n, it determines recursively and returns the n-th 
     * value of the Fibonacci sequence calculated by the following formula.
     * 
     * F[n] = F[n - 1] + F[n - 2]
     * 
     * @param n The n value. It must be greater than zero (n > 0).
     * @return The n-th value of the Fibonacci sequence.
     */
    public double getNfibonacci(int n) {
        switch(n) {
            case 1:
                return 0d;
            case 2:
                return 1d;
            default:
                return getNfibonacci(n - 1) + getNfibonacci(n - 2);
        }
    }

     /** 
     * Given a value n, it determines iteratively and returns a list of the first
     * n-th elements of the Fibonacci sequence calculated by the following formula.
     * 
     * F[n] = F[n - 1] + F[n - 2]
     * 
     * @param n The n value. It must be greater than zero (n > 0).
     * @return A list of the first n-th elements of the Fibonacci sequence. 
     */
    public List<Double> getAllfibonacci(int n) {
        //Variables:
        double n1 = 0;
        double n2 = 1;
        double n3;

        //Objects:
        List<Double> fibonacci = new LinkedList<>();

        switch(n) {
            case 1:
                fibonacci.add(n1);
            default:
                fibonacci.add(n1);
                fibonacci.add(n2);

                for(int i = 2; i < n; ++i) {   
                    n3 = n1 + n2;    
                    fibonacci.add(n3);
                    n1 = n2;    
                    n2 = n3;    
                };
        }

        return fibonacci;
    }

    /** 
     * Given a value n, it determines recursively and returns the 
     * factorial of the number n calculated by the following formula.
     * 
     * n! = n * (n - 1) * (n - 2) ... 3 * 2 * 1
     * 
     * @param n The n value. It must be equal or greater than zero (n >= 0).
     * @return The factorial of the number n.
     */
    public double getNfactorial(int n) {
        switch(n) {
            case 0:
                return 1d;
            default:
                return n * getNfactorial(n - 1);  
        }
    }

    
    /** 
     * Given a value n, the first term and the common difference of a arithmetic 
     * progression, it determines and returns the n-th value of the the arithmetic 
     * progression calculated by the following formula.
     * 
     * a[n] = a[1] + (n – 1) * d
     * 
     * @param d The common difference of the arithmetic progression
     * @param a1 The first term of the arithmetic progression.
     * @param n The n value. It must be greater than zero (n > 0).
     * @return The n-th value of the arithmetic progression.
     */
    public double getNpa(double d, double a1, int n) {
        return a1 + (n - 1) * d;
    }

    /** 
     * Given a value n, the first term and the common ratio of a geometric 
     * progression, it determines and returns the n-th value of the the
     * geometric progression calculated by the following formula.
     * 
     * a[n] = a[1] * r^(n - 1)
     * 
     * @param r The common ratio of the geometric progression
     * @param a1 The first term of the geometric progression.
     * @param n The n value. It must be greater than zero (n > 0).
     * @return The n-th value of the geometric progression.
     */
    public double getNpg(double r, double a1, int n) {
        return a1 * Math.pow(r, n - 1);
    }
}