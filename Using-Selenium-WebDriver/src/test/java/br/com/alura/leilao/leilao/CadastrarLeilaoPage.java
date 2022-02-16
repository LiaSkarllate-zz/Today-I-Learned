package br.com.alura.leilao.leilao;

//Imports:
import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CadastrarLeilaoPage extends PageObject {
    //Constructors:
    public CadastrarLeilaoPage(WebDriver webDriver) {
        super("http://localhost:8080/leiloes/new", webDriver);
    }

    //Methods:
    public void fillForm(String name, String value, String date) 
        throws  NoSuchElementException, 
                IllegalArgumentException {
        this.webDriver.findElement(By.id("nome")).sendKeys(name);  
        this.webDriver.findElement(By.id("valorInicial")).sendKeys(value); 
        this.webDriver.findElement(By.id("dataAbertura")).sendKeys(date);     
    }

    public ListarLeilaoPage submitForm() throws NoSuchElementException {
        this.webDriver.findElement(By.id("button-submit")).click();

        return new ListarLeilaoPage(this.webDriver);
    }
}