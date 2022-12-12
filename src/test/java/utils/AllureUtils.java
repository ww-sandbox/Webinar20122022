package utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Properties;

public class AllureUtils {
    private static final String ALLURE_SETTINGS_LOCATION = System.getProperty("user.dir") + "\\src\\test\\resources\\";
    private static final String ENV_SETTINGS = "environment.properties";
    public static void reportEnv(String browser, String browserVersion, String env) {
            String data = String.format("Browser=%s\nBrowser.Version=%s\nStand=%s",browser, browserVersion, env);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ALLURE_SETTINGS_LOCATION + ENV_SETTINGS));
            writer.write(data);
            writer.close();
            String resultsDir = getOutputLocation();
            File srcFile = new File(ALLURE_SETTINGS_LOCATION + ENV_SETTINGS);
            File destFile = new File(System.getProperty("user.dir") + "\\" + resultsDir +  "\\environment.properties");
            System.out.println(destFile.getAbsolutePath());
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String getOutputLocation(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(ALLURE_SETTINGS_LOCATION + "allure.properties"));
            return prop.getProperty("allure.results.directory");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

