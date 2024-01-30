package myproject.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class AppConfig  {

    @Bean
    @ConfigurationProperties
    public ConsulPropertiesImpl defaultProp() {
        return new ConsulPropertiesImpl();
    }

}
