package myproject.spring.boot;

/**
 * Created by psingh on 3/10/2017.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import myproject.spring.boot.infrastructure.AppConfigListener;




@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@ComponentScan
public class SpringBootMainClass {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootMainClass.class);
        application.addListeners(new AppConfigListener());
        application.run(args);
    }
}
