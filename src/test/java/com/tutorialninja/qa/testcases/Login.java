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
    public Login(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
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
        driver.findElement(By.cssSelector("#input-email")).sendKeys(prop.getProperty("validEmail"));
        driver.findElement(By.cssSelector("#input-password")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.cssSelector("input[value='Login']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.linkText("Change your password")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.linkText("Modify your address book entries")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.linkText("Modify your wish list")).isDisplayed());
    }
    @Test
    public void verifyLoginWithInValidCredentials(){
        driver.findElement(By.cssSelector("#input-email")).sendKeys(dataProp.getProperty("invalidUsername"));
        driver.findElement(By.cssSelector("#input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
        driver.findElement(By.cssSelector("input[value='Login']")).click();

        String actualWarningMsg = driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
        System.out.println(actualWarningMsg);
        String expectedWarningMsg = dataProp.getProperty("emailPasswordWarning");
        Assert.assertTrue(expectedWarningMsg.equals(actualWarningMsg),"Warning: No match for E-Mail Address and/or Password.");
    }

    @Test
    public void verifyLoginWithValidEmailAndInValidPassword(){
        driver.findElement(By.cssSelector("#input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.cssSelector("#input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
        driver.findElement(By.cssSelector("input[value='Login']")).click();

        String actualWarningMsg = driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
        String expectedWarningMsg = dataProp.getProperty("emailPasswordWarning");
        Assert.assertTrue(expectedWarningMsg.equals(actualWarningMsg),"Warning: No match for E-Mail Address and/or Password.");
    }
}
