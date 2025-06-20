package com.juaracoding.tasqa;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class Login extends BaseTest {

    @BeforeClass
    @Parameters({ "url" })
    public void init(String url) {
        driver = DriverSingleton.createOrGetDriver();
        driver.get(url);
    }

    @BeforeMethod
    public void refresh() {
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    public void testLoginValid() {
        inputLogin("hadirsqa1@gmail.com", "SQA@Hadir12345");

        // Validasi: teks selamat datang muncul di halaman home
        WebElement welcomeText = driver.findElement(By.id("email"));
        WebElement passwordText = driver.findElement(By.id("password"));
        Assert.assertTrue(passwordText.isDisplayed(), "Login gagal: tidak masuk ke halaman Home.");
    }

    @Test(priority = 2)
    public void testUsernameOnly() {
        inputLogin("hadirsqa1@gmail.com", "");

        WebElement errorMsg = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/input[1]"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error tidak muncul saat password kosong.");
    }

    @Test(priority = 3)
    public void testPasswordOnly() {
        inputLogin("", "SQA@Hadir12345");

        WebElement errorMsg = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error tidak muncul saat username kosong.");
    }

    @Test(priority = 4)
    public void testEmptyUsernameAndPassword() {
        inputLogin("", "");

        WebElement emailText = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]"));
        WebElement passwordText = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/input[1]"));
        Assert.assertTrue(emailText.isDisplayed(), "Error tidak muncul saat form kosong.");
        Assert.assertTrue(passwordText.isDisplayed(), "Error tidak muncul saat form kosong.");
    }

//    @Test(priority = 4)
//    public void logOut() {
//        // Setup WebDriver
//         WebDriverManager;
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//
//        // Arahkan ke halaman yang ingin diuji
//        driver.get("https://example.com"); // Ganti dengan URL yang relevan
//
//        // Tunggu sebentar jika perlu
//        Thread.sleep(2000);
//
//        // Cari tombol (contoh menggunakan ID)
//        WebElement button = driver.findElement(By.id("submitBtn")); // Ganti dengan selector tombol kamu
//
//        // Klik tombol
//        button.click();
//
//        // Tunggu hasil dari klik
//        Thread.sleep(2000);
//
//        // Verifikasi hasil klik, contoh: halaman berubah, atau teks muncul
//        String currentUrl = driver.getCurrentUrl();
//        if (currentUrl.contains("success")) {
//            System.out.println(" Button click berhasil, halaman berubah ke: " + currentUrl);
//        } else {
//            System.out.println("Button click tidak berhasil, halaman sekarang: " + currentUrl);
//        }
//
//        // Tutup browser
//        driver.quit();
//    }

    private void inputLogin(String username, String password) {
        WebElement emailField = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]"));
        WebElement passwordField = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/input[1]"));
        WebElement loginButton = driver.findElement(By.id("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/button[2]"));

        emailField.clear();
        passwordField.clear();

      emailField.sendKeys(username);
       passwordField.sendKeys(password);
       loginButton.click();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}