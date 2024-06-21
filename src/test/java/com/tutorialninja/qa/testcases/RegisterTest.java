package com.tutorialninja.qa.testcases;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pageObjects.AccountSuccessPage;
import com.tutorialninja.qa.pageObjects.HomePage;
import com.tutorialninja.qa.pageObjects.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterTest extends Base {

    public WebDriver driver;
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;
    public RegisterTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        homePage.selectRegisterOption();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority=1)
    public void verifyRegisteringAnAccountWithMandatoryFields() {

        accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));
        Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");

    }
    @Test
    public void verifyRegisteringAnAccountWithMandatoryFields1(){
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
        registerPage.enterTelephoneNo(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.checkPrivacyCheckbox();
        registerPage.clickContinueButton();
        driver.findElement(By.linkText("Logout")).isDisplayed();

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
