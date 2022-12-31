package org.example.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage {
    public void buttonCompanies(WebDriver driver, WebDriverWait wait) {
        WebElement buttonCompanies = driver.findElement(By.xpath("//a[@href = \"/for-companies\"]"));
        buttonCompanies.click();
        wait.until(ExpectedConditions.attributeContains(buttonCompanies, "aria-current", ""));
        closeFrame("//*[@id = 'fl-616371']", "//*[contains(@data-fl-track, \"click-close-login\")]", driver);
    }

    public void buttonHealth(WebDriver driver) {
        WebElement buttonHealth = driver.findElement(By.xpath("//span[@class=\"padding\" and text() = \"Здоровье\"]"));
        buttonHealth.click();
        Assert.assertTrue("Не получилось нажать кнопку здоровье", buttonHealth.findElement(By.xpath("./..")).getAttribute("class").contains("item text--second active"));
    }

    public void healthInsurance(WebDriver driver) {
        WebElement healthInsurance = driver.findElement(By.xpath("//a[@href=\"/for-companies/zdorove/dobrovolnoe-meditsinskoe-strakhovanie\" and text() = \"Добровольное медицинское страхование\"]"));
        healthInsurance.click();
        WebElement titleHealthInsurance = driver.findElement(By.xpath("//h1"));
        Assert.assertEquals("Не получилось перейти Добровольное медицинское страхование",
                "Добровольное медицинское страхование", titleHealthInsurance.getText());
    }


    public void closeFrame(String xPath, String elem, WebDriver driver) {
        driver.switchTo().frame(waitElement(xPath, driver));
        driver.findElement(By.xpath(elem)).click();
        driver.switchTo().defaultContent();
    }

    private WebElement waitElement(String elem, WebDriver driver) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elem)));
    }
}
