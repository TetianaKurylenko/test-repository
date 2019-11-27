import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTestAdmin {

    private WebDriver[] driversArray;

    @Before
    public void start() {
        WebDriver chromeDriver = new ChromeDriver();
        WebDriver firefoxDriver = new FirefoxDriver();
        WebDriver safariDriver = new SafariDriver();

        driversArray = new WebDriver[] {chromeDriver, firefoxDriver, safariDriver};
    }

    @After
    public void stop() {
        for (int i = 0; i < driversArray.length; i++) {
            driversArray[i].quit();
        }
        driversArray = null;
    }

    @Test
    public void loginTest() {

        for (WebDriver driver : driversArray) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get("http://192.168.64.2/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.cssSelector("button[name='login'][type='submit'] ")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Logout']")));
        }

    }
}

