package com.tutorialninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    @FindBy(css = "a[title='My Account']")
    private WebElement myAccountDropDown;

    @FindBy(linkText = "Login")
    private WebElement loginOption;
    @FindBy(linkText = "Register")
    private WebElement registerOption;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void clickOnMyAccount(){
        myAccountDropDown.click();
    }
    public void selectLogin(){
        loginOption.click();
    }
    public void selectRegisterOption(){ registerOption.click(); }

}
