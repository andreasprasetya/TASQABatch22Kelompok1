package com.juaracoding.apitest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class IzinTerlambatPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By menuIzin = By.xpath("//a[contains(@class,'user__menu__item')]//p[text()='Izin']");
    private final By btnSubmit = By.xpath("//button[contains(text(),'Ajukan Izin')]");
    private final By errorMessage = By.cssSelector(".MuiFormHelperText-root.Mui-error");

    public IzinTerlambatPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickMenuIzin() {
        wait.until(ExpectedConditions.elementToBeClickable(menuIzin)).click();
    }

    public void openIzinTerlambatPage() {
        driver.get("https://magang.dikahadir.com/izin/terlambat");
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
    }

    public boolean isErrorMessageDisplayed(String expectedError) {
        return wait.until(ExpectedConditions
            .visibilityOfElementLocated(errorMessage))
            .getText().contains(expectedError);
    }
}
