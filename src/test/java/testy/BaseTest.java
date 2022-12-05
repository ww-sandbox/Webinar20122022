package testy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        System.out.println(System.getProperty("TestDriver"));
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
