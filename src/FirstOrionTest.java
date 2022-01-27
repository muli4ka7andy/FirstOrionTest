import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.file.Watchable;
import java.util.ArrayList;

public class FirstOrionTest {
    public static WebDriver driver;

    @Test
    public void Test() throws AWTException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        String oldTab = driver.getWindowHandle();
        driver.findElement(By.xpath("//*[contains(text(), 'Sutinku')]")).click();
        driver.findElement(By.name("q")).sendKeys("sql table online" + Keys.chord(Keys.ENTER));
        String result = driver.findElement(By.id("result-stats")).getText();
        Assert.assertNotEquals("There are Apie 67 200 000 rezult. (0,34 sek.)", "There are " + result);
        driver.findElement(By.xpath("//a[@aria-label='Page 5']")).click();
        Actions actions = new Actions(driver);


        WebElement link = driver.findElement(By.xpath("//*[contains(text(), 'SQL Exercises, Practice, Solution')]"));
        actions.contextClick(link).build().perform();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String backgroundColor = driver.findElement(By.cssSelector("body")).getCssValue("background-color");
        Assert.assertEquals("BackgroundColor is rgba(0, 0, 0, 0)", "BackgroundColor is " + backgroundColor);
        String currentYear = driver.findElement(By.xpath("//*[contains(text(), '©w3resource.com 2011-2022')]")).getText();
        Assert.assertEquals("Year is ©w3resource.com 2011-2022", "Year is " + currentYear);
        driver.close();
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.xpath("//*[contains(text(), '10 Best Online SQL Courses')]")).click();
    }
}
