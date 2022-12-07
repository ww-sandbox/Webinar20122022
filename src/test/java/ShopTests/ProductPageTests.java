package ShopTests;

import Pages.ProductPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
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
        LOGGER.warn("Wewnątrz metody");
        ProductPage productPage = new ProductPage(driver);
        productPage.openProductPage();

        takeScreenshot("allProp1");

        productPage.selectSize("L");
        productPage.selectColor("black");
        productPage.setNumberOfPieces("3");
        productPage.clickAddToBasketButton();
        takeScreenshot("allProp2");
        productPage.checkAddToBasketSummary("L", "3", "czarny");
//        jak widać testy pisane z wykorzystaniem POP są zdecydowanie bardziej czytelne i łatwe w pisaniu
        Assert.assertEquals(1,2);
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
