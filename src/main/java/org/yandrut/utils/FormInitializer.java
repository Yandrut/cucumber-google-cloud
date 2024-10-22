package org.yandrut.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yandrut.models.Form;

public class FormInitializer {
    private static final String NUMBER_OF_INSTANCES = "data.form.NUMBER_OF_INSTANCES";
    private static final String MACHINE_TYPE = "data.form.MACHINE_TYPE";
    private static final String GPU_TYPE = "data.form.GPU_TYPE";
    private static final String LOCAL_SSD = "data.form.LOCAL_SSD";
    private static final String DATA_CENTER_LOCATION = "data.form.DATA_CENTER_LOCATION";
    private static final String ESTIMATED_COST = "data.form.ESTIMATED_COST";
    private static final Logger log = LogManager.getLogger(FormInitializer.class);

    public static Form initializeForm() {
        log.info("Initializing test data");
        return new Form(DataReader.getTestData(NUMBER_OF_INSTANCES),
                DataReader.getTestData(MACHINE_TYPE),
                DataReader.getTestData(GPU_TYPE),
                DataReader.getTestData(LOCAL_SSD),
                DataReader.getTestData(DATA_CENTER_LOCATION),
                DataReader.getTestData(ESTIMATED_COST));
    }
}