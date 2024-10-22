package org.yandrut.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.yandrut.drivers.DriverWaiter.waitForElementToBeVisible;

public class DetailedViewPage extends AbstractPage {

    private final WebDriver driver;

    @FindBy(xpath = "//span[contains(text(), 'n1')]")
    private WebElement machineTypeText;

    @FindBy(xpath = "//span[text()='GPU Model']/following-sibling::span")
    private WebElement gpuModel;

    @FindBy(xpath = "//*[text()='Number of Instances']/following-sibling::span")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//*[text()='Add GPUs']/following-sibling::span")
    private WebElement addGpuBoolean;

    @FindBy(xpath = "//*[text()='Region']/following-sibling::span")
    private WebElement regionText;

    @FindBy(xpath = "//*[text()='Local SSD']/following-sibling::span")
    private WebElement localSsdText;

    private static final Logger log = LogManager.getLogger(DetailedViewPage.class);


    public DetailedViewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public DetailedViewPage moveToTheTab(int tabIndex, String tabInfo) {
        Object [] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[tabIndex]);
        log.info("Switching to a new tab: {}\tAt index: {}", tabInfo, tabIndex);
        return this;
    }

    public String getPageName() {
        return driver.getTitle();
    }

    public String getMachineTypeText() {
        return waitForElementToBeVisible(machineTypeText).getText();
    }

    public String getGpuModelText() {
        return waitForElementToBeVisible(gpuModel).getText();
    }

    public String getNumberOfInstancesText() {
        return waitForElementToBeVisible(numberOfInstances).getText();
    }

    public boolean getGpusBoolean() {
        String tagText = waitForElementToBeVisible(addGpuBoolean).getText();
        return Boolean.parseBoolean(tagText);
    }

    public String getRegionText() {
        return waitForElementToBeVisible(regionText).getText();
    }

    public String getLocalSsdText() {
        return waitForElementToBeVisible(localSsdText).getText();
    }
}