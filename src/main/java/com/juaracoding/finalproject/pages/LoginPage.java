package com.juaracoding.apitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final String loginUrl = "https://magang.dikahadir.com/absen/login";
    
    private final By emailField = By.xpath("//*[@id='email']");
    private final By passwordField = By.xpath("//*[@id='password']");
    private final By loginButton = By.xpath("//*[@id='__next']/div/div/div/form/button[2]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void load() {
        driver.get(loginUrl);
    }

    public void enterCredentials(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void login(String email, String password) {
        load(); 
        enterCredentials(email, password);
        clickLogin();
    }
}
