package myproject.spring.boot.config;

import lombok.Data;

@Data
public class DBConfig {

     String username;
     String password;
     String engine;
     String host;
     String port;
     String dbname;


    public String getJdbcUrl() {
        return "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
    }
}
