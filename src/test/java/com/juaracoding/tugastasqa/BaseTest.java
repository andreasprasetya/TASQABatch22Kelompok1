package com.juaracoding.tugastasqa;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest {

        WebDriver driver;

        @BeforeClass
        public void setup() {
            driver = DriverSingleton.createOrGetDriver();
        }

}
