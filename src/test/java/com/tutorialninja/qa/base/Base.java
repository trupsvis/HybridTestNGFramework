package com.tutorialninja.qa.base;

import com.tutorialninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;


public class Base {
    WebDriver driver;
    public Properties prop;
    public Properties dataProp;

    public Base(){
        prop = new Properties();
        dataProp = new Properties();
        File propsFile = new File(System.getProperty("user.dir")+"//src//main//java//com//tutorialninja//qa//config//config.properties");
        File dataPropFile = new File(System.getProperty("user.dir")+"//src//main//java//com//tutorialninja//qa//testdata//testdata.properties");

        try {
            FileInputStream fis = new FileInputStream(propsFile);
            FileInputStream dataFis = new FileInputStream(dataPropFile);
            prop.load(fis);
            dataProp.load(dataFis);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
    public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME)));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        driver.get(prop.getProperty("url"));

        return driver;
    }
}
