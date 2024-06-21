package com.tutorialninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    @FindBy(id="input-firstname")
    private WebElement firstNameField;

    @FindBy(id="input-lastname")
    private WebElement lastNameField;

    @FindBy(id="input-email")
    private WebElement emailAddressField;

    @FindBy(id="input-telephone")
    private WebElement telephoneField;

    @FindBy(id="input-password")
    private WebElement passwordField;

    @FindBy(id="input-confirm")
    private WebElement passwordConfirmField;

    @FindBy(name="agree")
    private WebElement privacyPolicyField;

    @FindBy(xpath="//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath="//input[@name='newsletter'][@value='1']")
    private WebElement yesNewsletterOption;

    @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
    private WebElement duplicateEmailWarning;

    @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
    private WebElement privacyPolicyWarning;

    @FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameWarning;

    @FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameWarning;

    @FindBy(xpath="//input[@id='input-email']/following-sibling::div")
    private WebElement emailWarning;

    @FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
    private WebElement telephoneWarning;
    @FindBy(xpath="//input[@id='input-password']/following-sibling::div")
    private WebElement passwordWarning;





    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void enterFirstName(String firstNameText){
        firstNameField.sendKeys(firstNameText);
    }
    public void enterLastName(String lastNameText){
        lastNameField.sendKeys(lastNameText);
    }
    public void enterEmailAddress(String emailId){
        emailAddressField.sendKeys(emailId);
    }
    public void enterTelephoneNo(String telephoneNo){
        telephoneField.sendKeys(telephoneNo);
    }
    public void enterPassword(String passwordText){
        passwordField.sendKeys(passwordText);
    }
    public void enterConfirmPassword(String confimPassword){
        passwordConfirmField.sendKeys(confimPassword);
    }
    public void checkPrivacyCheckbox(){
        privacyPolicyField.click();
    }
    public void clickContinueButton(){
        continueButton.click();
    }

    public String retriveDuplicateEmailWarning(){
        String duplicateEmailWarningText = duplicateEmailWarning.getText();
        return duplicateEmailWarningText;
    }

    public void selectYesNewLetterOption(){ yesNewsletterOption.click();}

    public String retriveFirstNameWarning(){
        String firstNameWarningText = firstNameWarning.getText();
        return firstNameWarningText;
    }

    public String retriveLastNameWarning(){
        String lastNameWarningText = lastNameWarning.getText();
        return lastNameWarningText;
    }

    public String retriveEmailWarning(){
        String emailWarningText = emailWarning.getText();
        return emailWarningText;
    }
    public String retriveTelephoneWarning(){
        String telephoneWarningText = telephoneWarning.getText();
        return telephoneWarningText;
    }
    public String retrivePasswordWarning(){
        String passwordWarningText = passwordWarning.getText();
        return passwordWarningText;
    }

    public String retrivePrivacyPolicyWarning(){
        String privacyPolicyWarningText = privacyPolicyWarning.getText();
        return privacyPolicyWarningText;
    }


    public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText) {

        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(lastNameText);
        emailAddressField.sendKeys(emailText);
        telephoneField.sendKeys(telephoneText);
        passwordField.sendKeys(passwordText);
        passwordConfirmField.sendKeys(passwordText);
        privacyPolicyField.click();
        continueButton.click();
        return new AccountSuccessPage(driver);

    }


}
