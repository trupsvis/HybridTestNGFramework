package com.tutorialninja.qa.testcases;

import com.tutorialninja.qa.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends Base {
    public WebDriver driver;

    public Search(){
        super();
    }
    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void verifySearchWithValidProduct(){
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys(dataProp.getProperty("validProduct"));
        driver.findElement(By.cssSelector(".fa.fa-search")).click();
        Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),dataProp.getProperty("searchValidProductResult"));
    }

    @Test
    public void verifySearchWithInvalidProduct(){
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys(dataProp.getProperty("invalidProduct"));
        driver.findElement(By.cssSelector(".fa.fa-search")).click();
        String actualSearchMessage =  driver.findElement(By.cssSelector("body > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > p:nth-child(7)")).getText();
        Assert.assertEquals(actualSearchMessage,dataProp.getProperty("searchNoProductResult"),dataProp.getProperty("NoProductTextInSearchResults"));
    }
}
