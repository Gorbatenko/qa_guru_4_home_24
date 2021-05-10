package team.dataart.tests;

import team.dataart.config.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {
    private static TestDataConfig getTestData() {
        return ConfigFactory.newInstance().create(TestDataConfig.class, System.getProperties());
    }

    public static String getWebUrl() {
        return getTestData().webUrl();
    }

    public static String getApiUrl() {
        return getTestData().apiUrl();
    }
}
