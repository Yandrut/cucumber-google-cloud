package org.yandrut.steps;

import org.yandrut.utils.TestListener;
import org.yandrut.drivers.DriverProvider;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;


@ExtendWith(TestListener.class)
public class BaseTest {

    @Before
    void openBrowser() {
        WebDriver driver = DriverProvider.getInstance();
    }

    @After
    void quitBrowser() {
        DriverProvider.quit();
    }
}