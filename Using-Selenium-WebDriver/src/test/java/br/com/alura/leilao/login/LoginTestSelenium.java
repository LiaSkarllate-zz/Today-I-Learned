package br.com.alura.leilao.login;

//Imports:
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class LoginTestSelenium {
    //Attributes:
    private LoginPage loginPage;

    private String username = "fulano";
    private String password = "pass";
    private String wrongPassword = "wrongPass";
    
    //Methods:
    @BeforeEach
    public void start() {
        this.loginPage = new LoginPage();
        this.loginPage.navigateTo(this.loginPage.getUrl());
    }

    @AfterEach
    public void quit() {
        this.loginPage.quit();
    }

    @Test
    public void successfulLogin() throws Exception {
        //Logic:
        this.loginPage.fillForm(username, password);
        this.loginPage.submitForm();
        this.loginPage.sleep();
  
        Assertions.assertFalse(this.loginPage.isInIt());
        Assertions.assertTrue(this.loginPage.isThereInIt(username));
    }

    @Test
    public void unsuccessfulLogin() throws Exception {
        //Variables:
        String username = "fulano";

        //Logic:
        this.loginPage.fillForm(username, wrongPassword);
        this.loginPage.submitForm();
        this.loginPage.sleep();
    
        Assertions.assertTrue(this.loginPage.isInIt(loginPage.getUrl() + "?error"));
        Assertions.assertTrue(this.loginPage.isThereInIt("Usuário e senha inválidos."));
        Assertions.assertFalse(this.loginPage.isThereInIt("fulano"));
    }

    @Test
    public void neededLogin() throws Exception {
        this.loginPage.navigateTo(loginPage.getUrl() + "/leiloes/2");

        Assertions.assertTrue(this.loginPage.isInIt());
        Assertions.assertFalse(this.loginPage.isThereInIt("Dados do Leilão"));
    }
}