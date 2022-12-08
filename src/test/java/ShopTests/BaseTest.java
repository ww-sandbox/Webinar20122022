package ShopTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
//        LOGGER.error("Przykładowy log na poziomie error");
//        LOGGER.warn("Przykładowy log na poziomie warn");
//
//        System.out.println(System.getProperty("TestDriver"));
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void methodStart(ITestResult result){
        LOGGER.info("Startuję metodę " + result.getMethod().getMethodName());
    }

    @AfterMethod
    public void methodFinish(ITestResult result){
//        wykorzystujemy listener przetrzymujący wszystkie dane odnoszące się do metody testowej, która była właśnie
//        uruchomiona
        LOGGER.info("Kończę metodę " + result.getMethod().getMethodName());
        if(result.getStatus() == ITestResult.FAILURE){
//            sprawdzamy status wykonania danej metody testowej i sprawdzamy czy failował. Jeśli tak wywołujemy zbieranie
//            screenshota z nazwą zawierającą nazwę metody testowej oraz odpowiednio sformatowany timestamp
            DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmm");
            String formatedEndTime = formatter.format(result.getEndMillis());
            ScreenshotUtil.takeScreenshot(driver, formatedEndTime + "_" + result.getMethod().getMethodName());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        LOGGER.info("Kończę testy w klasie " + this.getClass());
        driver.quit();
    }
}
