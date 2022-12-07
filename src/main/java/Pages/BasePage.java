package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
//        inicjalizacja elementów dla danej strony. Wykorzystana w klasie bazowej aby nie powielać kodu

    }
//    Przypisujemy instancję WebDrivera do pola w klasie bazowej strony. Sama instancja tworzona jest w teście i
//    przekazywana dalej. W kolejnych miejscach nie możemy tworzyć nowych instancji ponieważ nie byłyby one ze sobą
//    zsynchronizowane

//    W klasie bazowej tworzymy metody, które będą wspólne dla wielu stron. Mogą to być metody odpowiedzialne za
//    oczekiwanie na dany element, wypełnianie pól tekstem, czy wszelkie inne, które musielibyśmy dublować w
//    poszczególnych klasach PageObject'ów

    public void waitForElementVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForPresenceOfElement(By elementLocator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void stableButtonClick(WebElement button){
        int attempts = 0;
        while( attempts < 2) {
            try {
                button.click();
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
//        Opowiadałem o stableButtonClick, w różnych projektach może się różnie objawiać i wymagać różnych sposobów
//        na jego implementację. Tu akurat musimy podjąć dwie próbu ackji i ignorować odpowiedni błąd
    }
}
