package com.formatta.rc.diversosapi.service;

//Imports:
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class Diversos2service {
    //Methods:
    public List<String> getFirstNameMidNameAndLastName(String fullName){
        //Objects:
        String middleName = "";
        String[] names = fullName.split(" ", 0);
        List<String> body = new ArrayList<>();

        body.add(names[0]);
        
        for(int n = 1; n < (names.length - 1); n++){
            middleName = middleName + names[n] + " ";
        }

        body.add(middleName);

        body.add((names.length > 1)? names[names.length - 1] : "");

        return body;
    }

    public static List<Integer> getPrimeFactors(int number) {
        //Variables:
        int n = number;

        //Objects:
        List<Integer> factors = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        return factors;
    }

    public List<Double> getNpaList(double d, double a1, int n) {
        //Objects:
        List<Double> paList = new ArrayList<>();

        paList.add(a1);

        for (int index = 1; index < n; index++) {
            paList.add(a1 + (n - 1) * d);
        }


        return paList;
    }
}