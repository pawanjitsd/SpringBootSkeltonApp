package myproject.spring.boot.config;

import java.util.Map;
import java.util.HashMap;

public class ConsulPropertiesImpl {

    private Map<String, String> dataProperties = new HashMap<>();

    public Map<String, String> getDataProperties() {
        return dataProperties;
    }

    public String getDataByKey(String key) {
        if (dataProperties.containsKey(key))
            return dataProperties.get(key);
        return "";
    }
}
