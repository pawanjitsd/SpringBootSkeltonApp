package myproject.spring.boot.controller;

import myproject.spring.boot.config.ConsulConfiguration;
import myproject.spring.boot.config.ConsulPropertiesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import myproject.spring.boot.repository.UserRepository;
import myproject.spring.boot.domain.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Properties;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class TestController {

    @Autowired
    private ConsulPropertiesImpl consulProperties;
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    ConfigurableEnvironment environment;

    @Autowired
    ConsulConfiguration consulConfiguration;

    /**
     *
     * @param federatedUserId
     * @return
     */
    @GetMapping("/profile/{federatedUserId}")
    public ResponseEntity<User> getProfile(@PathVariable String federatedUserId) {
        User user = userRepository.findByFederatedUserId(federatedUserId).orElse(null);
        HttpStatus responseStatus = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(responseStatus).body(user);
    }
    
    @GetMapping("/environment/config")
    public ResponseEntity<Properties> getEnvironmentConfig() {
    	Properties  properties = (Properties)environment.getPropertySources().get("app.config").getSource();
    	return ResponseEntity.status(HttpStatus.OK).body(properties);

    }
    @GetMapping("/system/config")
    public ResponseEntity<Properties> getSystemConfig() {
    	Properties  properties = System.getProperties();
    	return ResponseEntity.status(HttpStatus.OK).body(properties);

    }

    @GetMapping("/env/config")
    public ResponseEntity<Map> getEnvConfig() {
        Map<String,String>  env = System.getenv();
        return ResponseEntity.status(HttpStatus.OK).body(env);

    }

    @GetMapping("/app/config")
    public ResponseEntity<ConsulPropertiesImpl> getAppConfig() {
       return ResponseEntity.status(HttpStatus.OK).body(consulProperties);
    }


    @GetMapping("/consul/config")
    public ResponseEntity<String> getConsulConfig() {
        return ResponseEntity.status(HttpStatus.OK).body(consulConfiguration.getDisclaimer() + " -----" + consulConfiguration.getApr() + "--- " + consulConfiguration.getTimeout());

    }
/*
    @GetMapping("/consul/feature/flags")
    public ResponseEntity<String> getFeatureFlags() {
        return ResponseEntity.status(HttpStatus.OK).body("Anura Enable" + /*consulConfiguration.getAnuraEnable() + " ----- OCE Enable:" + consulConfiguration.getOceEnable());

    }
*/



}
