package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    final static Logger LOGGER = LoggerFactory.getLogger(ScreenshotUtil.class);

    public static void takeScreenshot(WebDriver driver, String fileName){
//        Statyczna metoda umożliwiająca zapis screenshota bez konieczności tworzenia instacji klasy
        LOGGER.info("Screenshot zapisany pod nazwą " + fileName + ".png");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./target/screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
