package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver){
        super(driver);
    }

    By colorWhiteBy = By.xpath("//input[@title=\"Biały\"]");
    By colorBlackBy = By.xpath("//input[@title=\"czarny\"]");
    By selectSizeBy = By.id("group_1");
    By numberOfPiecesBy = By.xpath("//input[@name=\"qty\"]");
    By addToBasketBy = By.cssSelector(".add button");

    @FindBy(css=".add button")
    WebElement addToBasketButtonElement;
//    deklaracja elementów zgodnie ze stylem PageFactory
    @FindBy(id = "group_1")
    WebElement selectSizeElement;


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
//        WebElement selectElement = driver.findElement(selectSizeBy);
//        TODO : rozwiąć problem stale element
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
//        wait.until(ExpectedConditions.stalenessOf(selectSizeElement));

        int attempts = 0;
        while( attempts < 2) {
            try {
                Select selectSize = new Select(selectSizeElement);
                selectSize.selectByVisibleText(size);
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
//        Tak jak wspominałem czasem przy okazji nowoczesnych bibliotek do tworzenia UI trzeba się nakombinować.
//        W tym wypadku błąd nie jest taki prosty do załatnia i trzeba to robić trochę na około. Problem z selectem
//        rozwiązuję inline w klasie danego PageObjectu, problem buttona przeniosłem do BasePage
    }

    public void setNumberOfPieces(String number){
        WebElement numberOfPiecesElement = driver.findElement(numberOfPiecesBy);
        numberOfPiecesElement.clear();
        numberOfPiecesElement.sendKeys(number);
    }

    public void clickAddToBasketButton(){
        stableButtonClick(addToBasketButtonElement);
    }

    public void checkAddToBasketSummary(){
//        waitForElementVisibility(.....);
//        Przykład wykorzystania metody służącej do oczekiwania na określony element, zadeklarowanej w klasie bazowej.
//        Jest to jedynie rozpoczęty przykład, który wymaga dalszej implementacji.
    }
}
