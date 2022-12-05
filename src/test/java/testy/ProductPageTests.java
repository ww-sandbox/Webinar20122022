package testy;

import Pages.ProductPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class ProductPageTests extends BaseTest{
    public void takeScreenshot(String fileName){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./target/screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void orderShirtAllProperties(){
        ProductPage productPage = new ProductPage(driver);
        productPage.openProductPage();

        takeScreenshot("allProp1");

        productPage.selectColor("black");
        productPage.setNumberOfPieces("3");
        productPage.selectSize("L");
        productPage.clickAddToBasketButton();
        takeScreenshot("allProp2");
    }

    @Test
    public void orderShirtOnlySize(){
        ProductPage productPage = new ProductPage(driver);
        productPage.openProductPage();

        takeScreenshot("size1");

        productPage.selectSize("S");
        productPage.clickAddToBasketButton();

        takeScreenshot("size2");
    }
}
