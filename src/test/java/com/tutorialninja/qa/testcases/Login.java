package com.tutorialninja.qa.testcases;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class Login extends Base {

    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        driver = initializeBrowserAndOpenApplicationURL("chrome");
        driver.findElement(By.cssSelector("a[title='My Account'] ")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void verifyLoginWithValidCredentials(){
        driver.findElement(By.cssSelector("#input-email")).sendKeys("xxx.yyy@gmail.com");
        driver.findElement(By.cssSelector("#input-password")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Login']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.linkText("Change your password")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.linkText("Modify your address book entries")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.linkText("Modify your wish list")).isDisplayed());
    }
    @Test
    public void verifyLoginWithInValidCredentials(){
        driver.findElement(By.cssSelector("#input-email")).sendKeys("xxx123@gmail.com");
        driver.findElement(By.cssSelector("#input-password")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Login']")).click();

        String actualWarningMsg = driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
        System.out.println(actualWarningMsg);
        String expectedWarningMsg = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
        Assert.assertTrue(expectedWarningMsg.equals(actualWarningMsg),"Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");
    }

    @Test
    public void verifyLoginWithInValidEmailAndValidPassword(){
        driver.findElement(By.cssSelector("#input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.cssSelector("#input-password")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Login']")).click();

        String actualWarningMsg = driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
        String expectedWarningMsg = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(expectedWarningMsg.equals(actualWarningMsg),"Warning: No match for E-Mail Address and/or Password.");
    }



}
