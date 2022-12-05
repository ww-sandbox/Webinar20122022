package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){super(driver);}
//    Instancja WebDrivera jest przekazywana z testu, w tym miejscu przekazujemy ją dalej do konstruktora klasy bazowej

    By firstProductBy = By.cssSelector("article");
    By firstProductPriceBy = By.cssSelector("article .price");
//    Zebranie lokalizatorów w jednym miejscu

    @FindBy(css = "article")
    WebElement firstProduct;

    @FindBy(css = "article .price")
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
}
