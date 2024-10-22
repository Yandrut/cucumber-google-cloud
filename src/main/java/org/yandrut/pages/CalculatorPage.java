package org.yandrut.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import static org.yandrut.drivers.DriverWaiter.*;

public class CalculatorPage extends AbstractPage {

    @FindBy(xpath = "//button[@data-idom-class='xhASFc']")
    private WebElement addToEstimate;
    @FindBy(xpath = "//div[@role='button' and @data-idx='0']")
    private WebElement computeEngine;

    @FindBy(xpath = "//ul[@aria-label='GPU Model']/../..")
    private WebElement gpuModelDropdown;
    @FindBy(xpath = "//ul[@aria-label='Region']/..//..")
    private WebElement locationDropdown;
    @FindBy(xpath = "//ul[@aria-label='Local SSD']/../..")
    private WebElement localSsdDropdown;
    @FindBy(xpath = "//ul[@aria-label='Machine type']/../..")
    private WebElement machineTypeDropdown;

    @FindBy(xpath = "//ul[@aria-label='GPU Model']/li")
    private List<WebElement> gpuModelList;
    @FindBy(xpath = "//ul[@aria-label='Local SSD']/li")
    private List<WebElement> localSsdList;
    @FindBy(xpath = "//ul[@aria-label='Region']/li")
    private List<WebElement> locationsList;
    @FindBy(xpath = "//ul[@aria-label='Machine type']/li")
    private List<WebElement> machineTypesList;

    @FindBy(xpath = "//input[@value='1']")
    private WebElement numberOfInstancesInput;
    @FindBy(xpath = "//button[@aria-label='Add GPUs']")
    private WebElement addGpu;
    @FindBy(xpath = "//label[text()='1 year']/..")
    private WebElement commitedUsage;
    @FindBy(xpath = "//div[text()='Estimated cost']/following::label")
    private WebElement estimatedCost;
    @FindBy(xpath = "//a[@aria-label='Open detailed view']")
    private WebElement openDetailedView;
    private final String suffixForTextLocators = "/../..//div/span/following-sibling::span/span";
    private final String machineTextSelectorString = "//ul[@aria-label='Machine type']" + suffixForTextLocators;
    @FindBy(xpath = machineTextSelectorString)
    private WebElement machineTypeText;

    private final String gpuTextSelectorString = "//ul[@aria-label='GPU Model']" + suffixForTextLocators;
    @FindBy(xpath = gpuTextSelectorString)
    private WebElement gpuModelText;

    private final String localSSDTextLocatorString = "//ul[@aria-label='Local SSD']" + suffixForTextLocators;
    @FindBy(xpath = localSSDTextLocatorString)
    private WebElement localSSDText;

    private final String regionTextLocatorString = "//ul[@aria-label='Region']" + suffixForTextLocators;
    @FindBy(xpath = regionTextLocatorString)
    private WebElement regionTextLocator;

    private final WebDriver driver;
    private static final Logger log = LogManager.getLogger(CalculatorPage.class);


    public CalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void navigateToUrl(String url) {
        driver.get(url);
        log.info("Moving to URL: {}", url);
    }

    public String getPageName() {
        return driver.getTitle();
    }

    public CalculatorPage clickOnComputeEngine() {
        waitForElementToBeVisible(addToEstimate).click();
        waitForElementToBeVisible(computeEngine).click();
        log.info("Clicked on compute engine");
        return this;
    }

    public CalculatorPage setNumberOfInstances(String numberOfInstances) {
        numberOfInstancesInput.clear();
        numberOfInstancesInput.sendKeys(numberOfInstances);
        log.info("Actual number of instances is: {}", numberOfInstances);
        return this;
    }

    public CalculatorPage setMachineType(String machineType) {
        machineTypeDropdown.click();
        selectFromDropdownList(machineTypesList, machineType);
        log.info("Actual machine type is: {}\n Expected type: {}", machineTypeText.getText(), machineType);
        return this;
    }

    public CalculatorPage addGpuModel(String gpuModel) {
        addGpu.click();
        gpuModelDropdown.click();
        selectFromDropdownList(gpuModelList, gpuModel);
        log.info("Actual GPU model is: {}\n Expected model: {}", gpuModelText.getText(), gpuModel);
        return this;
    }

    public CalculatorPage setLocalSSD(String ssdName) {
        localSsdDropdown.click();
        selectFromDropdownList(localSsdList, ssdName);
        log.info("Actual local SSD is: {}\n Expected is: {}", localSSDText.getText(), ssdName);
        return this;
    }

    public CalculatorPage setLocation(String location) {
        locationDropdown.click();
        selectFromDropdownList(locationsList, location);
        log.info("Actual location: {}\n Expected is: {}", regionTextLocator.getText(), location);
        return this;
    }

    public CalculatorPage clickOnCommitedUsage() {
        commitedUsage.click();
        return this;
    }

    public String getEstimatedCost() {
        var priceText = estimatedCost.getText();
        log.info("Actual price is {}", priceText);
        return priceText;
    }

    public DetailedViewPage clickOnDetailedView() {
        waitForElementToBeVisible(openDetailedView).click();
        log.info("Opening detailed view");
        return new DetailedViewPage(driver);
    }

    private void selectFromDropdownList(List<WebElement> dropdownList, String dropdownElementName) {
        dropdownList.stream()
                .filter((element) -> element.getText().equals(dropdownElementName))
                .findAny()
                .ifPresent(WebElement::click);
        log.info("Choosing an option: {}", dropdownElementName);
    }
}