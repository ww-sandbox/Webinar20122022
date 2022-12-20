package ShopTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.AllureUtils;
import utils.DriverProvider;
import utils.ScreenshotUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BaseTest {
    public WebDriver driver;
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
//    deklaracja logera w klasie bazowej zapewnia dostep do niego w klasach pochodnych

    @BeforeClass(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        LOGGER.info("startuje klase " + this.getClass());

        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.setCapability("platformName", "Windows");
//        ffOptions.setCapability("browserVersion", "108.0.1");
//        W przypadku wykorzystania Grid 4 zalecane jest wykorzyanie obiekty klas Options (FirefoxOption/ChromeOptions)

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("firefox");
//        cap.setVersion("108");
//        W starszych wersjach zazwycaj wykorzystywało się obiekty klasy DesiredCapabilities

        driver = new RemoteWebDriver(new URL("http://localhost:4444"), ffOptions);
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
//        Dla Selenium Grid 4 nie musimy dodawać do adresu endpointa /wd/hub (jak miało to miejsce w starszych wersjach)
        driver.get("http://sampleshop.inqa.pl/");
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
//        DriverProvider.quitDriver();
    }
}
