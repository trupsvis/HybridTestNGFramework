package com.tutorialninja.qa.testcases;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pageObjects.AccountPage;
import com.tutorialninja.qa.pageObjects.HomePage;
import com.tutorialninja.qa.pageObjects.LoginPage;
import com.tutorialninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class Login extends Base {

    public WebDriver driver;
    public Login(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        homePage.selectLogin();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @DataProvider(name="accessTestData")
    public Object[][] testDataSupplier(){
        Object[][] data = {{"xxx.yyy@gmail.com","12345"},
                {"xxx.yyy123@gmail.com","12345"}};
        return data;
    }
    @DataProvider(name="accessTestDataFromExcel")
    public Object[][] testDataSupplierFromExcel(){
        Object[][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test
    public void verifyLoginWithValidCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(prop.getProperty("validEmail"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickLoginButton();
        AccountPage accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.editAccountInfoIsDisplayed());
        Assert.assertTrue(accountPage.changePasswordTextInfoIsDisplayed());
        Assert.assertTrue(accountPage.modifyAddressBookInfoIsDisplayed());
        Assert.assertTrue(accountPage.modifyWishListInfoIsDisplayed());
    }

    @Test(dataProvider = "accessTestData")
    public void verifyLoginWithMultipleUsersUsingDataProvider(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Test(dataProvider = "accessTestDataFromExcel")
    public void verifyLoginWithMultipleUsersUsingDataProviderExcel(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Test
    public void verifyLoginWithInValidCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(dataProp.getProperty("invalidUsername"));
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.clickLoginButton();

        Assert.assertTrue((dataProp.getProperty("emailPasswordWarning")).equals(loginPage.retrivedEmailWarningMessageText()),"Warning: No match for E-Mail Address and/or Password.");
    }

    @Test
    public void verifyLoginWithValidEmailAndInValidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(Utilities.generateEmailWithTimeStamp());
        loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
        loginPage.clickLoginButton();

        Assert.assertTrue((dataProp.getProperty("emailPasswordWarning")).equals(loginPage.retrivedEmailWarningMessageText()),"Warning: No match for E-Mail Address and/or Password.");
    }
}
