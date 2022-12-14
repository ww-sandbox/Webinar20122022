package ShopTests;

import Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ScreenshotUtil;


public class ProductPageTests extends BaseTest{
    @Test
    public void orderShirtAllProperties(){
        LOGGER.warn("Wewnątrz metody");
        ProductPage productPage = new ProductPage(driver);
        productPage.openProductPage();

        ScreenshotUtil.takeScreenshot(driver, "allProp1");

        productPage.selectSize("L");
        productPage.selectColor("black");
        productPage.setNumberOfPieces("3");
        productPage.clickAddToBasketButton();
        ScreenshotUtil.takeScreenshot(driver, "allProp2");
//        jak widać testy pisane z wykorzystaniem POP są zdecydowanie bardziej czytelne i łatwe w pisaniu
        Assert.assertTrue(productPage.checkAddToBasketSummary("L", "3", "czarny"));
//        asercja zawiera celowy błąd aby wygenerować fail i zbieranie screenshota
    }

    @Test
    public void orderShirtOnlySize(){
        ProductPage productPage = new ProductPage(driver);
        productPage.openProductPage();

        ScreenshotUtil.takeScreenshot(driver, "size1");

        productPage.selectSize("S");
        productPage.clickAddToBasketButton();

        ScreenshotUtil.takeScreenshot(driver, "size2");
    }
}
