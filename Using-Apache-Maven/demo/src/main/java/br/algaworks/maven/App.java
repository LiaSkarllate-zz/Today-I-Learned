package br.algaworks.maven;

import org.apache.commons.lang3.StringUtils;

public class App {
    public static void main(String[] args) {
        //Variables:
        String name = "William Fernandes Dorante";

        System.out.println(StringUtils.abbreviate(name, 10));
    }
}

