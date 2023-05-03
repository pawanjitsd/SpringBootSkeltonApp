package myproject.spring.boot.config;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

@Data
public class AppConfig {

    private Map<String, String> properties = new HashMap<String,String>();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void setProperty(String key, String value){
        properties.put(key, value);
    }
}
