package org.yandrut.config;

import org.aeonbits.owner.ConfigCache;

public final class ConfigFactory {

    private ConfigFactory(){}


    public static ConfigureFramework getConfig() {
        return ConfigCache.getOrCreate(ConfigureFramework.class);
    }
}
