import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void loginTest() {
        driver.get("http://192.168.64.2/litecart/");
        driver.findElement(By.className("account")).click();
        driver.findElement(By.name("email")).sendKeys("tania.yefimchuk@gmail.com");
        driver.findElement(By.name("password")).sendKeys("12345");
        driver.findElement(By.name("login")).click();

        WebElement element = driver.findElement(By.linkText("Tania"));

        if (element.isDisplayed()) {
            System.out.println("Login Test - completed");
        } else {
            throw new NoSuchElementException("can't find element with text - Tania");
        }
    }
}