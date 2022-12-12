package utils;

import io.qameta.allure.Attachment;
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

    @Attachment
    public static byte[] takeScreenshot(WebDriver driver, String fileName){
//        Statyczna metoda umożliwiająca zapis screenshota bez konieczności tworzenia instacji klasy
        LOGGER.info("Screenshot zapisany pod nazwą " + fileName + ".png");
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("./target/screenshots/" + fileName + ".png"));
            return FileUtils.readFileToByteArray(srcFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
//    Zmodyfikoana metoda która pobiera screenshot do folderu target/screenshots

    @Attachment
    public static byte[] takeScreenshotForReport(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
//    Metoda zapisująca screenshot wyłącznie do raportu Allure
}
