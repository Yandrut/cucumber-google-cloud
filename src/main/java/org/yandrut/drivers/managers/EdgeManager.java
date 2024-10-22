package org.yandrut.drivers.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public final class EdgeManager {

    private EdgeManager() {}

    public static WebDriver getDriver() {
        return new EdgeDriver();
    }
}