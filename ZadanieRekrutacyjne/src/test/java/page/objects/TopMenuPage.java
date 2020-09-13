package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopMenuPage {
    private WebDriver driver;

    public TopMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void typeAndSubmitIntoSearchField(String searchPhrase){
        WebElement searchField = driver.findElement(By.name("string"));
        searchField.sendKeys(searchPhrase);
        searchField.submit();
    }
}
