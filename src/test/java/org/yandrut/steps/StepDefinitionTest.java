package org.yandrut.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.assertj.core.api.SoftAssertions;
import org.yandrut.drivers.DriverProvider;
import org.yandrut.models.Form;
import org.yandrut.pages.CalculatorPage;
import org.yandrut.pages.CloudPage;
import org.yandrut.pages.DetailedViewPage;
import org.yandrut.utils.DataReader;
import org.yandrut.utils.FormInitializer;
import org.yandrut.utils.TestListener;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestListener.class)
public class StepDefinitionTest extends BaseTest {
    public static final String EXPECTED_PAGE_NAME = DataReader.getTestData("data.provided.expectedPageName");
    public static final String TAB_NAME = DataReader.getTestData("data.provided.tabInfo");
    public static final int TAB_INDEX = Integer.parseInt(DataReader.getTestData("data.provided.tabIndex"));

    CloudPage cloudPage = new CloudPage(DriverProvider.getInstance());
    CalculatorPage calculator = new CalculatorPage(DriverProvider.getInstance());
    DetailedViewPage detailedView = new DetailedViewPage(DriverProvider.getInstance());

    @Given("I open the Google Cloud {string}")
    public void iOpenTheGoogleCloud(String url) {
        cloudPage.navigateToUrl(url);
    }

    @When("I type a search prompt in the search bar {string}")
    public void iTypeASearchPromptInTheSearchBar(String searchPrompt) {
        cloudPage.clickOnTheSearchIcon()
                .sendSearchDetailsAndSubmit(searchPrompt);
    }

    @And("I click on one of the provided results {string}")
    public void iClickOnOneOfTheProvidedResults(String displayName) {
        cloudPage.clickOnProvidedResult(displayName);
    }

    @Then("The calculator page should be displayed")
    public void theCalculatorPageShouldBeDisplayed() {
        String actual = new CalculatorPage(DriverProvider.getInstance()).getPageName();
        assertEquals(EXPECTED_PAGE_NAME, actual);
    }

    @Given("I open the Cloud Calculator Page {string}")
    public void iOpenTheCloudCalculatorPage(String url) {
        calculator.navigateToUrl(url);
    }

    @When("I input the required data into the form")
    public void iInputTheRequiredDataIntoTheForm() {
        Form form = FormInitializer.initializeForm();
        var expected = form.getEstimatedCost();
        var actual = calculator
                .clickOnComputeEngine()
                .setNumberOfInstances(form.getNumberOfInstances())
                .setMachineType(form.getMachineType())
                .addGpuModel(form.getGpuModel())
                .setLocalSSD(form.getLocalSSD())
                .setLocation(form.getDataCenterLocation())
                .clickOnCommitedUsage()
                .getEstimatedCost();

        assertEquals(expected,actual);
    }

    @And("Click on detailed view icon")
    public void clickOnDetailedViewIcon() {
        detailedView = calculator.clickOnDetailedView();
    }

    @And("Move to the new tab")
    public void moveToTheNewTab() {
        detailedView.moveToTheTab(TAB_INDEX, TAB_NAME);
        assertEquals(TAB_NAME, detailedView.getPageName());
    }

    @Then("All inputted data should be displayed on the View page")
    public void allInputtedDataShouldBeDisplayedOnTheViewPage() {
        Form form = FormInitializer.initializeForm();

        SoftAssertions softAssert = new SoftAssertions();

        var numberOfInstancesActual = detailedView.getNumberOfInstancesText();
        softAssert.assertThat(numberOfInstancesActual).isEqualTo(form.getNumberOfInstances());

        String machineTypeActual = detailedView.getMachineTypeText();
        var expectedType = form.getMachineType();
        softAssert.assertThat(machineTypeActual).contains(expectedType);

        var addedGPUsActual = detailedView.getGpusBoolean();
        softAssert.assertThat(addedGPUsActual).isTrue();

        var gpuModelActual = detailedView.getGpuModelText();
        softAssert.assertThat(form.getGpuModel()).isEqualTo(gpuModelActual);

        String localSsdActual = detailedView.getLocalSsdText();
        softAssert.assertThat(form.getLocalSSD()).isEqualTo(localSsdActual);

        var locationActual = detailedView.getRegionText();
        softAssert.assertThat(form.getDataCenterLocation()).isEqualTo(locationActual);

        softAssert.assertAll();
    }
}