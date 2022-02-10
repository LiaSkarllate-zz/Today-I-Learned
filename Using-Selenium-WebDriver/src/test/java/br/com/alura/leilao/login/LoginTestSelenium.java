package br.com.alura.leilao.login;


//Imports:
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.Assertions;

public class LoginTestSelenium {
    //Methods:
    @Test
    public void successfulLogin(){      
        //It sets the driver folder installation:
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");

        WebDriver browser = new FirefoxDriver();
        browser.navigate().to("http://localhost:8080/login");

        browser.findElement(By.name("username")).sendKeys("fulano");
        browser.findElement(By.name("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertEquals(browser.getCurrentUrl(), "http://localhost:8080/login");
        //Assertions.assertEquals("fulano", browser.findElement(By.xpath("//html//body//div[1]//span//span")).getText());
        
        browser.quit(); 
    }
}
