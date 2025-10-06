package ru.vtb.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class ConnectionProperties {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;

}

