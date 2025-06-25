package com.juaracoding.apitest.steps;

import com.juaracoding.apitest.DriverSingleton;
import com.juaracoding.apitest.pages.IzinTerlambatPage;
import com.juaracoding.apitest.pages.LoginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import java.time.Duration;

public class IzinTerlambatSteps {
    private final LoginPage loginPage;
    private final IzinTerlambatPage izinPage;
    private final WebDriver driver;

    public IzinTerlambatSteps() {
        driver = DriverSingleton.createOrGetDriver();
        this.loginPage = new LoginPage(driver);
        this.izinPage = new IzinTerlambatPage(driver);
    }

    @Given("Pengguna sudah login ke aplikasi")
    public void pengguna_sudah_login() {
        loginPage.login("hadirsqa1@gmail.com", "SQA@Hadir12345");
    }

    @When("Pengguna membuka menu izin")
    public void buka_menu_izin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='__next']/div/div/div[1]/div[3]/div/div/div[3]"))).click();
    }

    @When("Pengguna mengakses halaman izin terlambat")
    public void akses_halaman_izin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mui-p-74249-P-late > div.MuiBox-root.css-1hpygww > button")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        } catch (TimeoutException e) {
            System.out.println("Elemen tidak ditemukan dalam waktu yang ditentukan: " + e.getMessage());
        }
    }

    @When("Klik tombol {string} tanpa mengisi form")
    public void klik_tombol_ajukan(String tombol) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.MuiButton-fullWidth")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        } catch (TimeoutException e) {
            System.out.println("Tombol tidak ditemukan dalam waktu yang ditentukan: " + e.getMessage());
        }
    }

    @Then("Sistem menampilkan error {string}")
    public void verifikasi_error(String expectedError) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class, 'MuiFormHelperText-root') and contains(@class, 'Mui-error') and contains(text(), '" + expectedError + "')]")));
            Assert.assertTrue(errorElement.isDisplayed());
        } catch (TimeoutException e) {
            System.out.println("Pesan error tidak ditemukan: " + e.getMessage());
        }
    }
}