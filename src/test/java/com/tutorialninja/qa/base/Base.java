package com.tutorialninja.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Base {
    WebDriver driver;
    public WebDriver initializeBrowserAndOpenApplicationURL(String browsername) {
        String browserName = "chrome";
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        }
//        else if (browserName.equals("edge")) {
//            driver = new EdgeDriver();s
//        }
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/index.php");

        return driver;
    }
}
