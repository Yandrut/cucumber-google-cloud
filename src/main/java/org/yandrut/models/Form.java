package org.yandrut.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Form {
    private final String numberOfInstances;
    private final String machineType;
    private final String gpuModel;
    private final String localSSD;
    private final String dataCenterLocation;
    private final String estimatedCost;
}