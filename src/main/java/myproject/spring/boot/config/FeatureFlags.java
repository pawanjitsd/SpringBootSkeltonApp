package myproject.spring.boot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
/*@ConfigurationProperties(prefix = "feature-flags")
@Component
@RefreshScope
@Getter
@Setter*/
public class FeatureFlags {

   // @Value("${anura.enable}")
    private String anuraEnable;

    //@Value("${oce.enable}")
    private String oceEnable;
}
