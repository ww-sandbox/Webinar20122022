package ShopTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        LOGGER.info("startuje klase " + this.getClass());
        System.out.println(System.getProperty("TestDriver"));
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void methodStart(ITestResult result){
        LOGGER.info("Startuję metodę " + result.getMethod().getMethodName());
    }

    @AfterMethod
    public void methodFinish(ITestResult result){
        LOGGER.info("Kończę metodę " + result.getMethod().getMethodName());
        if(result.getStatus() == ITestResult.FAILURE){
            DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmm");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
