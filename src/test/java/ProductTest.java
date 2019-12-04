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
import java.util.List;

public class ProductTest {

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

        List<WebElement> elements = driver.findElements(By.cssSelector("[class=product-column]"));

        System.out.println(String.format("Elements count = %s", elements.size()));

        for (WebElement element : elements) {
            //System.out.println(String.format("----->> %s", element.getText()));

            List<WebElement> stickers = element.findElements(By.cssSelector("div.sticker"));

            System.out.println(String.format("Stickers count = %s", stickers.size()));

            if  ( stickers.size() != 1 ) {
               throw new NoSuchElementException("");

            }
        }
    }
}