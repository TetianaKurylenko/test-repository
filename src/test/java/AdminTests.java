import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminTests {

    public WebDriver driver;
    public WebDriverWait wait;

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

        driver.get("http://192.168.64.2/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name='login'][type='submit'] ")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Logout']")));
    }

    @Test
    public void leftMenuTest() {

        driver.get("http://192.168.64.2/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name='login'][type='submit'] ")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='Logout']")));

        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul#box-apps-menu li")));

        if (elements.size() > 0) {
            WebElement first = elements.get(0);

            first.click();

            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul#box-apps-menu li.app.selected")));

            loopMenuElements(element);
        } else {
            // empty menu
        }

    }

    private void loopMenuElements(WebElement element) {

        WebElement next = checkElementAndReturnNext(element);

        if (next != null) {
            next.click();

            WebElement selectedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul#box-apps-menu li.app.selected")));
            loopMenuElements(selectedElement);
        }
    }


    private WebElement checkElementAndReturnNext(@NotNull WebElement element) {

        String text;

        try {

            WebElement subSelected = element.findElement(By.cssSelector("ul.docs li.doc.selected"));
            text = subSelected.findElement(By.cssSelector("span.name")).getText();

        } catch (Exception e) {

            text = element.findElement(By.cssSelector("span.name")).getText();

        }

        try {

            driver.findElement(By.xpath(String.format("//div[@class='panel-heading' and contains(string(), '%s')]", text))).isDisplayed();

        } catch (Exception e) {

            // Settings menu
            driver.findElement(By.xpath(String.format("//div[@class='panel-heading' and contains(string(), 'Settings')]", text))).isDisplayed();

        }


        WebElement next;
        try {

            next = element.findElement(By.cssSelector("ul.docs li.doc.selected + li"));
            return next;

        } catch (Exception e) {

            try {

                next = driver.findElement(By.cssSelector("ul#box-apps-menu li.app.selected + li"));
                return next;

            } catch (Exception exception) {

                return null;
            }

        }

    }

}