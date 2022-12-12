package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){super(driver);}
//    Instancja WebDrivera jest przekazywana z testu, w tym miejscu przekazujemy ją dalej do konstruktora klasy bazowej

    By quickViewBoxBy = By.xpath("//div[contains(@id, 'quickview-modal')]");
//    Zebranie lokalizatorów w jednym miejscu

    @FindBy(css = "article")
    WebElement firstProduct;

    @FindBy(css = "article1 .price")
    WebElement firstProductPrice;


    public void openHomePage(){
        driver.get("http://sampleshop.inqa.pl/");
    }

    public void openFirstProduct(){
//        driver.findElement(firstProductBy).click();
        firstProduct.click();
    }

    public boolean checkFirstProductIsDisplayed(){
//        WebElement firstProduct = driver.findElement(firstProductBy);

        return firstProduct.isDisplayed();
    }
    public String getPriceOfFirstElement(){
//        String actualPrice = driver.findElement(firstProductPriceBy).getText();
        String actualPrice = firstProductPrice.getText();

        return actualPrice;
    }

    @Step
    public void clickQuickViewForProduct(int prodNum){
        WebElement productElement = driver.findElement(By.cssSelector(".product:nth-of-type(" + prodNum +")"));
        WebElement quickView = productElement.findElement(By.cssSelector(".quick-view"));
        Actions action = new Actions(driver);
        action.moveToElement(productElement).pause(Duration.ofMillis(500))
                .click(quickView).perform();
    }
    @Step("JAkis opis stepu")
    public boolean isQuickViewBoxDisplayed(){
        waitForPresenceOfElement(quickViewBoxBy);
        waitForElementVisibility(driver.findElement(quickViewBoxBy));

        return driver.findElement(quickViewBoxBy).isDisplayed();
    }
}
