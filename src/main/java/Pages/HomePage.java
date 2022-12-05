package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){super(driver);}
//    Instancja WebDrivera jest przekazywana z testu, w tym miejscu przekazujemy ją dalej do konstruktora klasy bazowej

    By firstProductBy = By.cssSelector("article");
    By firstProductPriceBy = By.cssSelector("article .price");
//    Zebranie lokalizatorów w jednym miejscu

    public void openHomePage(){
        driver.get("http://sampleshop.inqa.pl/");
    }

    public void openFirstProduct(){
        driver.findElement(firstProductBy).click();
    }

    public boolean checkFirstProductIsDisplayed(){
        WebElement firstProduct = driver.findElement(firstProductBy);
        return firstProduct.isDisplayed();
    }
    public String getPriceOfFirstElement(){
        String actualPrice = driver.findElement(firstProductPriceBy).getText();

        return actualPrice;
    }
}
