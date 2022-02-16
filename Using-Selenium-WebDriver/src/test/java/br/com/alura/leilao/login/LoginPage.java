package br.com.alura.leilao.login;

//Imports:
import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leilao.ListarLeilaoPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {
    //Constructors:
    public LoginPage() {
        super("http://localhost:8080/login");
    }

    //Methods:
    public void fillForm(String username, String password) 
        throws  NoSuchElementException, 
                IllegalArgumentException {
        this.webDriver.findElement(By.name("username")).sendKeys(username);
        this.webDriver.findElement(By.name("password")).sendKeys(password);
        this.webDriver.findElement(By.id("login-form")).submit();
    }

    public ListarLeilaoPage submitForm() 
        throws  NoSuchElementException {
        this.webDriver.findElement(By.id("login-form")).submit();

        return new ListarLeilaoPage(this.webDriver);
    }
}