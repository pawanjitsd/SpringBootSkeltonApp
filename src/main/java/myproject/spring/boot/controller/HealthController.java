package myproject.spring.boot.controller;

import myproject.spring.boot.config.ConsulPropertiesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController
{
    @GetMapping("/health")
    public ResponseEntity<String> getAppConfig() {
        return ResponseEntity.status(HttpStatus.OK).body("HEALTH CHECK OK");

    }
}
