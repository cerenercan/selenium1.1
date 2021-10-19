package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppTest {

    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.get("https://www.google.com/");
    }

    @Test
    public void searchElement() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        WebElement element = driver.findElement(By.name("q"));
        System.out.println("Element value: " + element.getAttribute("maxlength")); //2048
        element.sendKeys("Testinium" + Keys.ENTER);
    }

    @Test
    public void actionsTest() throws InterruptedException {
        driver.navigate().to("https://www.gittigidiyor.com/");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver
                .findElement(By.cssSelector("div[data-cy='header-user-menu']")))
                .build()
                .perform();
        TimeUnit.SECONDS.sleep(3);
    }

    @Test
    public void switchToTest() throws InterruptedException {
        driver.navigate().to("https://demoqa.com/browser-windows");
        String currentWindow = driver.getWindowHandle();

        driver.findElement(By.id("tabButton")).click();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {
            if (!currentWindow.equals(window)){
                driver.switchTo().window(window);
            }
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println("New window text: " + driver.findElement(By.id("sampleHeading")).getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
