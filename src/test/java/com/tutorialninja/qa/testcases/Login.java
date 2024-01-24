package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Login {
    @Test
    public void verifyLoginWithValidCredentials(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("");

        driver.quit();
    }

}
