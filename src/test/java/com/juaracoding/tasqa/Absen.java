package com.juaracoding.tasqa;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import static com.juaracoding.tasqa.DriverSingleton.driver;

public class Absen {
    @BeforeClass
    @Parameters({ "url" })
    public void init(String url) {
        driver = DriverSingleton.createOrGetDriver();
        driver.get(url);
    }
}
