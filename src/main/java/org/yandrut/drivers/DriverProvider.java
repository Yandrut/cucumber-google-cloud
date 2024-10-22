package org.yandrut.drivers;

import org.openqa.selenium.WebDriver;
import org.yandrut.config.ConfigFactory;

import java.time.Duration;
import java.util.Objects;

public class DriverProvider {
    private static WebDriver driver;

    private DriverProvider() {
    }

    public static WebDriver getInstance() {
        if (Objects.isNull(driver)) {
            driver = LocalDriverFactory.getDriver(ConfigFactory.getConfig().browser());
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static void quit() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }
}