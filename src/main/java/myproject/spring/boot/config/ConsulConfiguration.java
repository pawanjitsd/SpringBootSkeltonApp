package myproject.spring.boot.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import org.springframework.stereotype.Component;

@Component
@RefreshScope
@Getter
@Setter
public class ConsulConfiguration {

        @Value("${disclaimer}")
        private String disclaimer;

        @Value("${timeout}")
        private String timeout;

        @Value("${apr}")
        private String apr;

      /*  @Value("${/feature-flags/anura.enable}")
        private String anuraEnable;

        @Value("${/feature-flags/oce.enable}")
        private String oceEnable;*/

}
