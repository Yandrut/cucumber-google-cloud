package org.yandrut.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.yandrut.drivers.DriverProvider;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements
        BeforeTestExecutionCallback, TestExecutionExceptionHandler {
    private static final Logger log = LogManager.getLogger(TestListener.class);


    public void saveScreenshot() {
        TakesScreenshot screenshot = (TakesScreenshot) DriverProvider.getInstance();
        File screenCapture = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenCapture,
                    new File(".//target/screenshots/" + getCurrentTime() + ".png"));

        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-dd-MM_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        log.info("Test running: {}", extensionContext.getDisplayName());
    }

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        saveScreenshot();
        log.error("Test execution stopped. Test name: {}", extensionContext.getDisplayName());
        throw throwable;
    }
}