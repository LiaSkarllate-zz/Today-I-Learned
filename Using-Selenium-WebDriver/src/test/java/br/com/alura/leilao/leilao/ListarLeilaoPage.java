package br.com.alura.leilao.leilao;

//Imports:
import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListarLeilaoPage extends PageObject {
    //Constructors:
    public ListarLeilaoPage() {
        super("http://localhost:8080/leiloes");
    }

    public ListarLeilaoPage(WebDriver webDriver) {
        super("http://localhost:8080/leiloes", webDriver);
    }

    //Methods:
    public CadastrarLeilaoPage clickCadastrarLeilao() {
        this.navigateTo("http://localhost:8080/leiloes/new");
        return new CadastrarLeilaoPage(this.webDriver);
    }

    public boolean isLeilaoCadastrado(String name, String value, String date) {
        return this.isThereInIt(name) && this.isThereInIt(value) && this.isThereInIt(date);
    }
}