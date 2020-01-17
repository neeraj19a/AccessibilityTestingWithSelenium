package test.java;

import io.github.sridharbandi.AccessibilityRunner;
import main.java.DriverManager;
import main.java.DriverManagerFactory;
import main.java.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class FactoryPatternTest {

    DriverManager driverManager;//Observe DriverManager is abstract class, so we can't create object,now every class has to extend DriverManager Class example  ChromeDriverManager extends DriverManager
    WebDriver driver;
    private static AccessibilityRunner accessibilityRunner;

    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);

    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
        accessibilityRunner = new AccessibilityRunner(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitDriver();
    }


    @Test
    public void launchGoogleTest1() throws IOException {
        driver.get("https://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());

    }


}