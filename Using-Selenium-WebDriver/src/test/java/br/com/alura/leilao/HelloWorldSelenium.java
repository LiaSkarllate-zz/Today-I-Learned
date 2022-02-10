package br.com.alura.leilao;

//Imports:
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloWorldSelenium {
    //Methods:
    @Test
    public void hello() {
        //It sets the driver folder installation:
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");

        WebDriver browser = new FirefoxDriver();
        browser.navigate().to("http://localhost:8080/leiloes");
        browser.quit(); 
    }
}