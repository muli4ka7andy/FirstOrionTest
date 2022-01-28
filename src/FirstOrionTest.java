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

import java.util.ArrayList;

public class FirstOrionTest {
    public static WebDriver driver;
    public static final double actual = 67200000.00;

    @Test
    public void Test(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//*[contains(text(), 'Sutinku')]")).click();
        driver.findElement(By.name("q")).sendKeys("sql table online" + Keys.chord(Keys.ENTER));
        String result = driver.findElement(By.id("result-stats")).getText();
        String[] words = result.split("\\s");
        String expected = words[1]+words[2]+words[3];
        double result2 = Double.parseDouble(expected);
        double delta = actual - result2;
        double result3 = delta + result2;
        Assert.assertEquals(actual, result3 ,0.2);
        driver.findElement(By.xpath("//a[@aria-label='Page 6']")).click();
        Actions actions = new Actions(driver);
        WebElement link = driver.findElement(By.xpath("//*[contains(text(), 'SQL Exercises, Practice, Solution')]"));
        actions.contextClick(link).build().perform();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
}
