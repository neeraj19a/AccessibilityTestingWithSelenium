package test.java;


import io.github.sridharbandi.AccessibilityRunner;
import io.github.sridharbandi.util.Standard;
import main.java.DriverManager;
import main.java.DriverManagerFactory;
import main.java.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class AccessibilityManagerTest {

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
    public void launchGoogleTest2() throws IOException {
        driver.get("https://bugreaper.blogspot.in");
        Assert.assertEquals("Bug Reaper", driver.getTitle());
        accessibilityRunner.setStandard(Standard.SECTION508);
        accessibilityRunner.execute();

    }
    @AfterClass
    public static void generateReport(){
        //Generated Consolidated HTML Report
        accessibilityRunner.generateHtmlReport();
    }
}