package myproject.spring.boot.config;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties
public class DBConfig {

     String username;
     String password;
     String engine;
     String host;
     String port;
     String dbname;
    // String dbInstanceIdentifier;


    public String getJdbcUrl() {
        return "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
    }
}
