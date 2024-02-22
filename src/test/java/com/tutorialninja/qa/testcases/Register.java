package com.tutorialninja.qa.testcases;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Register extends Base {

    WebDriver driver;
    public Register(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
        driver.findElement(By.cssSelector("a[title='My Account'] ")).click();
        driver.findElement(By.linkText("Register")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void verifyRegisteringAnAccountWithMandatoryFields(){
        driver.findElement(By.cssSelector("#input-firstname")).sendKeys("xxx");
        driver.findElement(By.cssSelector("#input-lastname")).sendKeys("yyy");
        driver.findElement(By.cssSelector("#input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.cssSelector("#input-telephone")).sendKeys("3439893834");
        driver.findElement(By.cssSelector("#input-password")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.cssSelector("#input-confirm")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.cssSelector("input[value='1'][name='agree']")).click();
        driver.findElement(By.cssSelector("input[value='Continue']")).click();

        WebElement logoutOption = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutOption.isDisplayed());

        WebElement successMessage = driver.findElement(By.cssSelector("div[id='content'] h1"));
        successMessage.isDisplayed();

        Assert.assertEquals(successMessage.getText(),dataProp.getProperty("expectedSuccessHeading"));

        Assert.assertEquals(driver.getCurrentUrl(),dataProp.getProperty("registerSuccessURL"));

        driver.findElement(By.linkText("Contact Us")).isDisplayed();

        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        Assert.assertEquals(driver.getTitle(),dataProp.getProperty("expected_accountPageTitle"));

        driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
        Boolean noRadioButtonState = driver.findElement(By.cssSelector("input[value='0']")).isSelected();
        Assert.assertTrue(noRadioButtonState);

        driver.quit();
    }
    @Test
    public void verifyRegisteringAccountWithExistingEmailId(){
        driver.findElement(By.cssSelector("#input-firstname")).sendKeys("xxx");
        driver.findElement(By.cssSelector("#input-lastname")).sendKeys("yyy");

        driver.findElement(By.cssSelector("#input-email")).sendKeys(prop.getProperty("validEmail"));
        driver.findElement(By.cssSelector("#input-telephone")).sendKeys("3439893834");
        driver.findElement(By.cssSelector("#input-password")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.cssSelector("#input-confirm")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.cssSelector("input[value='1'][name='agree']")).click();
        driver.findElement(By.cssSelector("input[value='Continue']")).click();

        String actualExistingEmailIdMsg = driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
        Assert.assertTrue(actualExistingEmailIdMsg.contains("Warning: E-Mail Address is already registered!"),"Warning message is not same as expected");

        driver.quit();
    }
}
