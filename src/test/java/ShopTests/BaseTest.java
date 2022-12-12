package ShopTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.AllureUtils;
import utils.ScreenshotUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
//    deklaracja logera w klasie bazowej zapewnia dostep do niego w klasach pochodnych

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        LOGGER.info("startuje klase " + this.getClass());
//        System.out.println(System.getProperty("TestDriver"));
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        WebDriverManager.firefoxdriver().setup();
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
            String formatedEndTime = formatter.format(result.getEndMillis());
            ScreenshotUtil.takeScreenshot(driver, formatedEndTime + "_" + result.getMethod().getMethodName());
//            ScreenshotUtil.takeScreenshotForReport(driver);
//            Metoda, która zapisuje screenshot tylko do raportu Allure. Zmodyfikoana została też metoda w linii 46
//            która pobiera screenshot do folderu target/screenshots
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        AllureUtils.reportEnv("firefox", "11", "prod");
        LOGGER.info("Kończę testy w klasie " + this.getClass());
        driver.quit();
    }
}
