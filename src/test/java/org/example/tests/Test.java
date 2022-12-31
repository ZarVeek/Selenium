package org.example.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Test {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void before(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10, 1000);
    }
    @org.junit.Test
    public void testCase(){
        //BASE_PAGE
        driver.get("http://www.rgs.ru");
        StartPage startPage = new StartPage();
        startPage.buttonCompanies(driver, wait);
        startPage.buttonHealth(driver);
        startPage.healthInsurance(driver);

        //DMC_PAGE
        driver.get("https://www.rgs.ru/for-companies/zdorove/dobrovolnoe-meditsinskoe-strakhovanie");
        DmcPage dmcPage = new DmcPage();
        dmcPage.sendApplication(driver);
        dmcPage.fillFields(driver);
        dmcPage.checkForCorrectValue(driver);
        dmcPage.clickAccept(driver);
        dmcPage.clickSubmit(driver);
        dmcPage.checkErrorForMail(driver);



    }

    @After
    public void after(){
        driver.quit();
    }





}
