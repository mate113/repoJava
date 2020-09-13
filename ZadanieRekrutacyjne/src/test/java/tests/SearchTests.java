package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.objects.TopMenuPage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SearchTests {

    private WebDriver driver;

    @DataProvider(name = "myDataProvider")
    public Object[][] testData() {
        return new Object[][]{
                {"Iphone 11", "czarny"}};
    }

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "myDataProvider")
    public void searchAndResultsTest(String searchQuery, String colour) {
        //wejście na stronę www.allegro.pl
        driver.navigate().to("https://www.allegro.pl/");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        //obsługa okna przetwarzania danych
        WebElement dialogWindow = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-labelledby='dialog-title']")));
        WebElement closeButton = driver.findElement(By.cssSelector("button[data-role='close-and-accept-consent']"));
        closeButton.click();
        webDriverWait.until(ExpectedConditions.invisibilityOf(dialogWindow));
        //wyszukiwanie produktu
        TopMenuPage topMenuPage = new TopMenuPage(driver);
        topMenuPage.typeAndSubmitIntoSearchField(searchQuery);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-box-name='Listing title']")));
        //wybranie koloru czarnego
        WebElement blackColour = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), '" + colour + "')]")));
        blackColour.click();
        //przegląd wyników wyszukiwania
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-box-name='Breadcrumb']")));
        List<WebElement> results = driver.findElements(By.cssSelector("article[data-role='offer']"));
        List<WebElement> prices = driver.findElements(By.cssSelector("div[class='msa3_z4 _9c44d_2K6FN']"));
        Double maxPrice = getMaxPrice(prices);
        //wyświetlenie informacji w konsoli
        System.out.println("Liczba produktów na stronie: " + results.size());
        System.out.println("Największa cena: " + maxPrice);
        System.out.println("Największa cena + 23%: " + (maxPrice * 1.23));
    }

    @AfterTest
    public void afterTest() {
        driver.close();
        driver.quit();
    }

    private Double getMaxPrice(List<WebElement> elements) {
        Stream<Double> doubleStream = elements.stream().map(webElement -> Double.parseDouble(webElement.getText().replaceAll("\\D+", "")) / 100);
        Optional<Double> maxValue = doubleStream.max(Double::compareTo);
        return maxValue.orElse(0.0);
    }
}