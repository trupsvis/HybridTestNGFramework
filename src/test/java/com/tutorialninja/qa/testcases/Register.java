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

    @BeforeMethod
    public void setUp(){
        driver = initializeBrowserAndOpenApplicationURL("chrome");
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
        WebElement first_name = driver.findElement(By.cssSelector("#input-firstname"));
        first_name.sendKeys("xxx");
        WebElement last_name = driver.findElement(By.cssSelector("#input-lastname"));
        last_name.sendKeys("yyy");

        WebElement email_Id = driver.findElement(By.cssSelector("#input-email"));
        email_Id.sendKeys(Utilities.generateEmailWithTimeStamp());
        WebElement telephone_no = driver.findElement(By.cssSelector("#input-telephone"));
        telephone_no.sendKeys("3439893834");
        WebElement password = driver.findElement(By.cssSelector("#input-password"));
        password.sendKeys("12345");
        WebElement confirm_password = driver.findElement(By.cssSelector("#input-confirm"));
        confirm_password.sendKeys("12345");
        WebElement check_privacy_notice  = driver.findElement(By.cssSelector("input[value='1'][name='agree']"));
        check_privacy_notice.click();
        WebElement continue_button = driver.findElement(By.cssSelector("input[value='Continue']"));
        continue_button.click();

        WebElement logoutOption = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutOption.isDisplayed());

        WebElement successMessage = driver.findElement(By.cssSelector("div[id='content'] h1"));
        String actualHeading = successMessage.getText();
        successMessage.isDisplayed();

        String expectedSuccessHeading = "Your Account Has Been Created!";
        Assert.assertEquals(actualHeading,expectedSuccessHeading);

        String actualURL  = driver.getCurrentUrl();
        String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/success";
        Assert.assertEquals(actualURL,expectedURL);

        WebElement contact_us = driver.findElement(By.linkText("Contact Us"));

        Assert.assertTrue(contact_us.isDisplayed());

        WebElement register_continue_button = driver.findElement(By.cssSelector(".btn.btn-primary"));
        register_continue_button.click();

        String actual_accountPageTitle = driver.getTitle();
        String expected_accountPageTitle = "My Account";
        Assert.assertEquals(actual_accountPageTitle,expected_accountPageTitle);

        driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
        Boolean noRadioButtonState = driver.findElement(By.cssSelector("input[value='0']")).isSelected();
        Assert.assertTrue(noRadioButtonState);

        driver.quit();
    }
    @Test
    public void verifyRegisteringAccountWithExistingEmailId(){
        WebElement first_name = driver.findElement(By.cssSelector("#input-firstname"));
        first_name.sendKeys("xxx");
        WebElement last_name = driver.findElement(By.cssSelector("#input-lastname"));
        last_name.sendKeys("yyy");

        WebElement email_Id = driver.findElement(By.cssSelector("#input-email"));
        email_Id.sendKeys("xxx.yyy@gmail.com");
        WebElement telephone_no = driver.findElement(By.cssSelector("#input-telephone"));
        telephone_no.sendKeys("3439893834");
        WebElement password = driver.findElement(By.cssSelector("#input-password"));
        password.sendKeys("12345");
        WebElement confirm_password = driver.findElement(By.cssSelector("#input-confirm"));
        confirm_password.sendKeys("12345");
        WebElement check_privacy_notice  = driver.findElement(By.cssSelector("input[value='1'][name='agree']"));
        check_privacy_notice.click();
        WebElement continue_button = driver.findElement(By.cssSelector("input[value='Continue']"));
        continue_button.click();

        String actualExistingEmailIdMsg = driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
        Assert.assertTrue(actualExistingEmailIdMsg.contains("Warning: E-Mail Address is already registered!"),"Warning message is not same as expected");

        driver.quit();
    }
}
