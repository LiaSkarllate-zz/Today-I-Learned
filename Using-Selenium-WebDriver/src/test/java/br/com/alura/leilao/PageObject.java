package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

//Imports:
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class PageObject implements WebDriverSettings { 
    //Attributes:
    protected WebDriver webDriver;
    protected String url;

    //Constructors:
    public PageObject(String url) {
        System.setProperty(DRIVER_PROPERTY, DRIVER_LOCATION);
        this.setWebDriver(new FirefoxDriver());
        this.setUrl(url);

        this.webDriver.manage().timeouts()
            .implicitlyWait(TIMEOUT, TimeUnit.MILLISECONDS)
            .pageLoadTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public PageObject(String url, WebDriver webDriver) {
        this.setWebDriver(webDriver);
        this.setUrl(url);
    }

    //Methods:
    public void navigateTo(String url) {
        this.webDriver.navigate().to(url);
    }

    public void quit() {
        this.webDriver.quit();
    }

    public boolean isThereInIt(String str) {
        return webDriver.getPageSource().contains(str);
    }

    public boolean isInIt() {
        return this.webDriver.getCurrentUrl().equals(this.getUrl());
    }

    public boolean isInIt(String url) {
        return this.webDriver.getCurrentUrl().equals(url);
    }

    public void sleep() throws InterruptedException {
        Thread.sleep(TIMEOUT);
    }

    public void sleep(long time) throws InterruptedException {
        Thread.sleep(time);
    }

    //Getters and setters:
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}