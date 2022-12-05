package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    By colorWhiteBy = By.xpath("//input[@title=\"Biały\"]");
    By colorBlackBy = By.xpath("//input[@title=\"czarny\"]");
    By selectSizeBy = By.id("group_1");
    By numberOfPiecesBy = By.xpath("//input[@name=\"qty\"]");
    By addToBasketBy = By.cssSelector(".add button");

    @FindBy(css=".add button")
    WebElement addToBasketButtonElement;


    public void openProductPage(){
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");
    }

    public void selectColor(String color){
        if(color.equals("white")){
            driver.findElement(colorWhiteBy).click();
        } else if (color.equals("black")) {
            driver.findElement(colorBlackBy).click();
        }
    }

    public void selectSize(String size){
        WebElement selectElement = driver.findElement(selectSizeBy);
        Select selectSize = new Select(selectElement);
        selectSize.selectByVisibleText("M");
    }

    public void setNumberOfPieces(String number){
        WebElement numberOfPiecesElement = driver.findElement(numberOfPiecesBy);
        numberOfPiecesElement.sendKeys(number);
    }

    public void clickAddToBasketButton(){
        addToBasketButtonElement.click();
    }

    public void checkAddToBasketSummary(){
//        waitForElementVisibility(.....);
//        Przykład wykorzystania metody służącej do oczekiwania na określony element, zadeklarowanej w klasie bazowej.
//        Jest to jedynie rozpoczęty przykład, który wymaga dalszej implementacji.
    }
}
