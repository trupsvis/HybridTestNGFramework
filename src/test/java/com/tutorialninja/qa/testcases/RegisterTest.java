package com.tutorialninja.qa.testcases;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pageObjects.AccountSuccessPage;
import com.tutorialninja.qa.pageObjects.HomePage;
import com.tutorialninja.qa.pageObjects.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


    @Test
    public void verifyRegisteringAnAccountWithMandatoryFields(){
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

        AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
        String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
        Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("expectedSuccessHeading"));

        Assert.assertEquals(driver.getCurrentUrl(),dataProp.getProperty("registerSuccessURL"));

        driver.findElement(By.linkText("Contact Us")).isDisplayed();

        driver.quit();
    }
    @Test
    public void verifyRegisteringAccountWithExistingEmailId(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmailAddress(prop.getProperty("validEmail"));
        registerPage.enterTelephoneNo(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectYesNewLetterOption();
        registerPage.checkPrivacyCheckbox();
        registerPage.clickContinueButton();

        String actualWarning = registerPage.retriveDuplicateEmailWarning();
        Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),"Warning message is not same as expected");

        driver.quit();
    }
    @Test
    public void verifyRegisteringAccountWithoutFillingAnyDetails(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickContinueButton();

        String actualPrivacyPolicyWarning = registerPage.retrivePrivacyPolicyWarning();
        Assert.assertEquals(actualPrivacyPolicyWarning,dataProp.getProperty("privacyPolicyWarning"),"Privacy Policy Warning is not displayed");

        String actualFirstNameWarning = registerPage.retriveFirstNameWarning();
        Assert.assertEquals(actualFirstNameWarning,dataProp.getProperty("firstNameWarning"),"First Name Warning is not displayed");

        String actualLastNameWarning = registerPage.retriveLastNameWarning();
        Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"),"Last Name Warning is not displayed");

        String actualEmailWarning = registerPage.retriveEmailWarning();
        Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailWarning"),"Email Warning is not displayed");

        String actualTelephoneWarning = registerPage.retriveTelephoneWarning();
        Assert.assertEquals(actualTelephoneWarning,dataProp.getProperty("telephoneWarning"),"Telephone Warning is not displayed");

        String actualPasswordWarning = registerPage.retrivePasswordWarning();
        Assert.assertEquals(actualPasswordWarning,dataProp.getProperty("passwordWarning"),"Password Warning is not displayed");
    }
}
