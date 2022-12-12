package ShopTests;

import Pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {
    @Test
    public void checkHomePageTitle(){
        driver.get("http://sampleshop.inqa.pl/");
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, "Automation Sample Shop");
    }

    @TmsLink("XYZ-123")
    @Issue("ZYX-321")
    @Epic("Moj epic w projekcie")
    @Story("A tutaj user story")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name="Google", url="https://www.google.com")
    @Test
    public void checkProductPrice(){
        HomePage homePage = new HomePage(driver);

        homePage.openHomePage();
        String actualPrice = homePage.getPriceOfFirstElement();

        Assert.assertEquals("10", actualPrice);
    }

    @Test
    @Description("To moj opis")
    public void checkQuickViewWorks(){
        HomePage homePage = new HomePage(driver);

        homePage.openHomePage();
        homePage.clickQuickViewForProduct(2);
        Assert.assertTrue(homePage.isQuickViewBoxDisplayed());
    }

}
