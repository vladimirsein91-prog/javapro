package ru.vtb.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.*;

@Configuration
public class HikaryPoolConfig {



    @Bean
    @Primary
    public HikariDataSource dataSource(ConnectionProperties connectionProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(connectionProperties.getJdbcUrl());
        config.setDriverClassName(connectionProperties.getDriverClassName());
        config.setUsername(connectionProperties.getUsername());
        config.setPassword(connectionProperties.getPassword());
        return new HikariDataSource(config);
    }
}
