package myproject.spring.boot.infrastructure;

import java.io.IOException;
import java.util.Properties;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MapperFeature;
import myproject.spring.boot.config.DBConfig;


import lombok.extern.slf4j.Slf4j;

import org.apache.commons.configuration.ConfigurationRuntimeException;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import com.amazonaws.services.appconfig.AmazonAppConfig;
import com.amazonaws.services.appconfig.model.GetConfigurationRequest;
import com.amazonaws.services.appconfig.AmazonAppConfigClient;
import myproject.spring.boot.config.AppConfig;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Map;

/**
 * Created by XXXXXXXX IDEA.
 */

@Slf4j
public class AppConfigListener implements ApplicationListener<SpringApplicationEvent> {


    /**
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {

            ConfigurableEnvironment environment = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
            log.info("Environment Variable..." + System.getenv("aws_region"));
            if (environment.getProperty("local.environment") == null || Boolean.FALSE.equals(environment.getProperty("local.environment", Boolean.class))) {
                initializeAppConfig(environment);
                initializeDBConfig(environment);
            }
        }

    }


   void initializeDBConfig(ConfigurableEnvironment environment) {
        try {


            AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                    .withRegion(System.getenv("aws_region"))
                    .build();
            GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                    .withSecretId(environment.getProperty("database.accounts-service.aws.secret-name"));

            DBConfig dbConfig = new ObjectMapper().configure(MapperFeature.USE_ANNOTATIONS, true)
                    .readValue(client
                                    .getSecretValue(getSecretValueRequest).getSecretString(),
                            DBConfig.class
                    );
            environment.getPropertySources().addFirst(getR2DbcProperties(dbConfig));
        } catch (IOException ex) {
            log.error("Unable to fetch secrets.", ex);
            throw new ConfigurationRuntimeException(ex);
        }
    }


    void initializeAppConfig(ConfigurableEnvironment environment) {
        try {
           /* AmazonAppConfig client = AmazonAppConfigClient.builder().withRegion(System.getenv("aws_region")).build();
            GetConfigurationRequest getConfigurationRequest = new GetConfigurationRequest();
            getConfigurationRequest.setApplication(environment.getProperty("appconfig.application"));
            getConfigurationRequest.setConfiguration(environment.getProperty("appconfig.application"));
            getConfigurationRequest.setClientId(environment.getProperty("appconfig.application"));
            getConfigurationRequest.setEnvironment(System.getenv("environment"));
            Stream<String> stream = new String(client
                    .getConfiguration(getConfigurationRequest).getContent().array()).lines();
            Map map = stream.map(line-> line.split("=")).collect(Collectors.toMap(a->a[0],a->a[1]));
            Properties properties = new Properties();
            properties.putAll(map);
            environment.getPropertySources().addLast(new PropertiesPropertySource("app.config",System.getProperties()));*/
        }catch (Exception ex) {
            log.error("Unable to fetch app config.", ex);
            throw new ConfigurationRuntimeException(ex);
        }

    }



    PropertiesPropertySource getR2DbcProperties(DBConfig dbConfig) {
        Properties properties = new Properties();
        properties.put("spring.datasource.hikari.username", dbConfig.getUsername());
        properties.put("spring.datasource.hikari.password", dbConfig.getPassword());
        properties.put("spring.datasource.hikari.jdbc-url", dbConfig.getJdbcUrl());
        properties.put("spring.datasource.url", dbConfig.getJdbcUrl());
        return new PropertiesPropertySource("spring.datasource.hikari", properties);
    }









}
