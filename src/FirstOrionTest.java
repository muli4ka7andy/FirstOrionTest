import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FirstOrionTest {
    public static WebDriver driver;
    @Test
    public void Test(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//*[contains(text(), 'Sutinku')]")).click();
        driver.findElement(By.name("q")).sendKeys("sql table online" + Keys.chord(Keys.ENTER));
        String result = driver.findElement(By.id("result-stats")).getText();
        System.out.println("There are "+result);
        driver.findElement(By.xpath("//a[@aria-label='Page 5']")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Format SQL Server Queries Easily')]")).click();
        String backgroundColor = driver.findElement(By.cssSelector("body")).getCssValue("background-color");
        System.out.println("BackgroundColor is " +backgroundColor);
        String currentYear = driver.findElement(By.id("year")).getText();
        System.out.println("Year is "+currentYear);

    }
}
