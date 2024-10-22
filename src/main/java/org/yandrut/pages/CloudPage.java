package org.yandrut.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CloudPage extends AbstractPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='YSM5S']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement searchField;

    @FindBy(xpath = "//a[@track-type='search-result']")
    private List<WebElement> searchResults;

    private static final Logger log = LogManager.getLogger(CloudPage.class);


    public CloudPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CloudPage navigateToUrl(String url) {
        driver.get(url);
        log.info("Moving to URL: {}", url);
        return this;
    }

    public void sendKeys(WebElement element, String keysToSend) {
        log.info("Send character sequence: {}", keysToSend);
        element.sendKeys(keysToSend);
    }

    public CloudPage clickOnTheSearchIcon() {
        searchButton.click();
        return this;
    }

    public void sendSearchDetailsAndSubmit(String details) {
        log.info("Search prompt is: {}", details);
        sendKeys(searchField, details);
        searchField.submit();
    }

    public void clickOnProvidedResult(String prompt) {
        searchResults
                .stream()
                .filter((element) -> element.getText().equals(prompt))
                .findAny()
                .ifPresent(WebElement::click);
        log.info("Pricing calculator opened");
        new CalculatorPage(driver);
    }
}