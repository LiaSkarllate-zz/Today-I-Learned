package br.com.alura.leilao.leilao;

//Imports:
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class CadastrarLeilaoTestSelenium {
    //Attributes:
    private ListarLeilaoPage listarLeilaoPage;
    private CadastrarLeilaoPage cadastrarLeilaoPage;
    private LoginPage loginPage;

    //Methods:
    @BeforeEach
    public void start() throws InterruptedException {
        //Variables:
        String username = "fulano";
        String password = "pass";

        //Logic:
        this.loginPage = new LoginPage();
        this.loginPage.navigateTo(this.loginPage.getUrl());
        this.loginPage.fillForm(username, password);
        this.listarLeilaoPage = this.loginPage.submitForm();
        this.listarLeilaoPage.sleep();

        this.cadastrarLeilaoPage = this.listarLeilaoPage.clickCadastrarLeilao();
    }

    @AfterEach
    public void quit() {
        this.cadastrarLeilaoPage.quit();
    }

    @Test
    public void successfulCadastroLeilao() throws InterruptedException {
        //Variables and Objects:
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String name = "Leilão Teste do Dia " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String value = "500.00";

        //Logic:
        this.cadastrarLeilaoPage.fillForm(name, value, date);
        this.listarLeilaoPage = this.cadastrarLeilaoPage.submitForm();
        Assertions.assertTrue(this.listarLeilaoPage.isThereInIt("Leilão salvo com sucesso"));

        this.listarLeilaoPage.navigateTo(this.listarLeilaoPage.getUrl());

        Assertions.assertTrue(this.listarLeilaoPage.isLeilaoCadastrado(name, value, date));
    }

    @Test
    public void unsuccessfulCadastroLeilao() throws InterruptedException {
        //Logic:
        this.cadastrarLeilaoPage.fillForm("", "", "");
        this.listarLeilaoPage = this.cadastrarLeilaoPage.submitForm();
        
        Assertions.assertFalse(this.listarLeilaoPage.isThereInIt("Leilão salvo com sucesso"));
        Assertions.assertTrue(this.listarLeilaoPage.isThereInIt("alert alert-danger"));
    }
}