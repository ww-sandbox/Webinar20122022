package testy;

import Pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {
    @Test()
    public void checkHomePageTitle(){
        driver.get("http://sampleshop.inqa.pl/");
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, "Automation Sample Shop");
    }

    @Test
    public void checkProductPrice(){
        HomePage homePage = new HomePage(driver);

        homePage.openHomePage();
        String actualPrice = homePage.getPriceOfFirstElement();

        Assert.assertEquals("10", actualPrice);
    }

}
