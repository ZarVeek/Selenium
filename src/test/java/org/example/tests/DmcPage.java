package org.example.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DmcPage extends StartPage {
    private static final String PATH_SEND_APP = "//button[@data-v-997a51ea]";
    private static final String PATH_NAME = "//input[@name=\"userName\"]";
    private static final String PATH_PHONE = "//input[@name=\"userTel\"]";
    private static final String PATH_MAIL = "//input[@name=\"userEmail\"]";
    private static final String PATH_ADDRESS = "//input[@type=\"text\"]";
    private static final String PATH_CHECK_BOX = "//span[text() = \"Я соглашаюсь с условиями\"]";
    private static final String PATH_SUBMIT = "//button[@type=\"submit\"]";
    private static final String CHECK_MAIL = "//span[text()=\"Введите корректный адрес электронной почты\"]";

    FormRegistration formRegistration = new FormRegistration();

    public void sendApplication(WebDriver driver) {
        closeFrame("//*[@id = 'fl-546299']", "//button[@class=\"close\"]", driver);
        WebElement sendApplication = driver.findElement(By.xpath(PATH_SEND_APP));
        sendApplication.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Не получилось перейти Добровольное медицинское страхование",
                driver.findElement(By.xpath("//h2[text() = \"Оперативно перезвоним\"]")).getText().contains("Оперативно перезвоним"));
    }
    public void fillFields(WebDriver driver){
        driver.findElement(By.xpath(PATH_NAME)).sendKeys(formRegistration.generateData().getName());
        driver.findElement(By.xpath(PATH_PHONE)).sendKeys(formRegistration.generateData().getPhone());
        driver.findElement(By.xpath(PATH_MAIL)).sendKeys(formRegistration.generateData().getMail());
        driver.findElement(By.xpath(PATH_ADDRESS)).sendKeys(formRegistration.generateData().getAddress());
    }

    public void checkForCorrectValue(WebDriver driver) {
        List<String> list = new ArrayList<>();
        list.add(driver.findElement(By.xpath(PATH_NAME)).getAttribute("value"));
        list.add(driver.findElement(By.xpath(PATH_PHONE)).getAttribute("value"));
        list.add(driver.findElement(By.xpath(PATH_MAIL)).getAttribute("value"));
        list.add(driver.findElement(By.xpath(PATH_ADDRESS)).getAttribute("value"));

        Assert.assertEquals("поля не заполнены введенными значениями", formRegistration.getListValue(), list);
    }

    public void clickAccept(WebDriver driver) {
        driver.findElement(By.xpath(PATH_CHECK_BOX)).click();
    }
    public void clickSubmit(WebDriver driver) {
        driver.findElement(By.xpath(PATH_SUBMIT)).click();
    }
    public void checkErrorForMail(WebDriver driver) {
        Assert.assertEquals("ошибки нет(", "Введите корректный адрес электронной почты", driver.findElement(By.xpath(CHECK_MAIL)).getText());
    }
}
