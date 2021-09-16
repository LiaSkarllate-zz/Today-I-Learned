package br.demo.rc.hexagonal.model;

public class BankAccount {
    //Attributes:
    private Long id;
    private Float balance; //It must be greater or equal than zero (balance > 0);

    //Constructor:
    public BankAccount(Long id, Float balance) {
        if(balance < 0) {
            throw new IllegalArgumentException("Age is not valid!");
        }

        this.balance = balance;
        this.id = id;
    }

    //Methods:
    public boolean withdraw(Float amount) {
        if(this.balance - amount < 0) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public void deposit(Float amount) {
        balance += amount;
    }
}
