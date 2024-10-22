package org.yandrut.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources(
        { "system:properties",
        "system:env",
        "file:${user.dir}/src/main/resources/config.properties"
        })
public interface ConfigureFramework extends Config {
    @DefaultValue("CHROME")
    @ConverterClass(StringToBrowserType.class)
    BrowserType browser();
}