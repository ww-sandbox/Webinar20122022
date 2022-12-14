package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverProvider {
    private static WebDriver driver;

    public static WebDriver getDriver(String driverType, String url){
        if(driver != null){
            if(!url.isEmpty()){
                driver.get(url);
            }
            return driver;
        }
        return setupDriver(driverType, url);
    }

    public static WebDriver getDriver(String driverType){
        return getDriver(driverType, "");
    }

    private static WebDriver setupDriver(String driverType, String url){
        if(driverType.equalsIgnoreCase("firefox")){
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } else if (driverType.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        if(!url.isEmpty()){
            driver.get(url);
        }
        return driver;
    }

    public static void quitDriver(){
        driver.quit();
        driver = null;
    }
}
