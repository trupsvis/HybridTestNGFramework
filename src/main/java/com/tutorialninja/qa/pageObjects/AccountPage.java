package com.tutorialninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    WebDriver driver;
    @FindBy(linkText = "Edit your account information")
    WebElement editYourAccountInformation;
    @FindBy(linkText = "Change your password")
    WebElement changePassword;

    @FindBy(linkText = "Modify your address book entries")
    WebElement modifyAddressBook;

    @FindBy(linkText = "Modify your wish list")
    WebElement modifyWishList;

    public AccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean editAccountInfoIsDisplayed(){
        boolean displayStatus = editYourAccountInformation.isDisplayed();
        return displayStatus;
    }
    public boolean changePasswordTextInfoIsDisplayed(){
        boolean changePasswordText = changePassword.isDisplayed();
        return changePasswordText;
    }
    public boolean modifyAddressBookInfoIsDisplayed(){
        boolean modifyAddressBookText = modifyAddressBook.isDisplayed();
        return modifyAddressBookText;
    }
    public boolean modifyWishListInfoIsDisplayed(){
        boolean modifyWishListText = modifyWishList.isDisplayed();
        return modifyWishListText;
    }
}
