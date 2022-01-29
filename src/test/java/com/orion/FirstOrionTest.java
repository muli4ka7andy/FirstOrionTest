package com.orion;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class FirstOrionTest {

    public static final int EXPECTED = 67200000;
    public static final int DELTA = 50000000;

    public WebDriver driver;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//main//resources//drivers//chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        this.driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void Test() {
        driver.get("https://www.google.com");

        click(By.xpath("//div[text()='Принимаю']"));
        sendKeys(By.name("q"), "sql table online" + Keys.chord(Keys.ENTER));

        String resultNumbers = driver.findElement(By.id("result-stats")).getText();
        int actualCount = Integer.parseInt(resultNumbers
                .replaceAll("\\((.*?)\\)", "")
                .replaceAll("\\D", ""));
        Assert.assertEquals(EXPECTED, actualCount, DELTA);

        click(By.xpath("//a[@aria-label='Page 6']"));

        Actions actions = new Actions(driver);
        WebElement link = driver.findElement(By.xpath("//*[contains(text(), 'SQL Exercises, Practice, Solution')]"));
        actions.contextClick(link).build().perform();
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            System.out.println("Something wrong with Robot!");
            System.out.println(e.getMessage());
        }

        //  waiting for a new tab to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: " + e.getMessage());
        }

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String backgroundColor = driver.findElement(By.cssSelector("body")).getCssValue("background-color");
        Assert.assertEquals("BackgroundColor is rgba(0, 0, 0, 0)", "BackgroundColor is " + backgroundColor);

        String currentYear = driver.findElement(By.xpath("//*[contains(text(), '©w3resource.com 2011-2022')]")).getText();
        Assert.assertEquals("Year is ©w3resource.com 2011-2022", "Year is " + currentYear);

        driver.close();
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.xpath("//*[contains(text(), '10 Best Online SQL Courses')]")).click();
    }

    private void click(By xpath) {
        getWait()
                .until(ExpectedConditions.elementToBeClickable(xpath))
                .click();
    }

    private void sendKeys(By xpath, String text) {
        getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(xpath))
                .sendKeys(text);
    }

    private Wait<WebDriver> getWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
