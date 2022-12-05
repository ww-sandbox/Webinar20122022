package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;

    BasePage(WebDriver driver) {this.driver = driver;}
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
}
