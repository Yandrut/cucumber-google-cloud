package org.yandrut.utils;

import lombok.Builder;
import lombok.Getter;
import org.yandrut.config.BrowserType;

@Builder
@Getter
public class DriverData {
    private BrowserType browserType;
}