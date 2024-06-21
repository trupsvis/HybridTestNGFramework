package com.tutorialninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{
    public WebDriver driver;
    @FindBy(css = "#input-email")
    private WebElement emailAddressUsername;
    @FindBy(css = "#input-password")
    private WebElement passwordText;
    @FindBy(css = "input[value='Login']")
    private  WebElement loginButton;

    @FindBy(css = ".alert.alert-danger.alert-dismissible")
    private WebElement loginWarningMessage;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username){
        emailAddressUsername.sendKeys(username);
    }
    public void  enterPassword(String password){
        passwordText.sendKeys(password);
    }
    public AccountPage clickLoginButton(){
        loginButton.click();
        return new AccountPage(driver);
    }
    public AccountPage login(String emailText, String passwordValue){
        emailAddressUsername.sendKeys(emailText);
        passwordText.sendKeys(passwordValue);
        loginButton.click();
        return new AccountPage(driver);
    }
    public String retrivedEmailWarningMessageText(){
        String warningText = loginWarningMessage.getText();
        return warningText;
    }


}
