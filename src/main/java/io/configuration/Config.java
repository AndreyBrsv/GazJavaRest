package io.configuration;

import io.r2dbc.spi.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("dbconfig.driver")
    private String driver;

    @Value("dbconfig.protocol")
    private String protocol;

    @Value("dbconfig.host")
    private String host;

    @Value("dbconfig.user")
    private String user;

    @Value("dbconfig.password")
    private String password;

    @Value("dbconfig.database")
    private String database;

    @Bean
    public ConnectionFactory connectionFactory(ConnectionFactoryOptions connectionFactoryOptions) {
        return ConnectionFactories.get(connectionFactoryOptions);
    }

    @Bean
    public ConnectionFactoryOptions connectionFactoriesOptions() {
        return ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, driver)
                .option(ConnectionFactoryOptions.PROTOCOL, protocol)
                .option(ConnectionFactoryOptions.HOST, host)
                .option(ConnectionFactoryOptions.USER, user)
                .option(ConnectionFactoryOptions.PASSWORD, password)
                .option(ConnectionFactoryOptions.DATABASE, database)
                .build();
    }
}
