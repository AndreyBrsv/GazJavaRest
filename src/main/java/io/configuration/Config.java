package io.configuration;

import io.r2dbc.spi.*;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class Config {

    private static final Option<? super String> DRIVER = Option.sensitiveValueOf("DRIVER");
    private static final Option<? super String> PROTOCOL = Option.sensitiveValueOf("PROTOCOL");
    private static final Option<? super String> HOST = Option.sensitiveValueOf("HOST");
    private static final Option<? super String> USER = Option.sensitiveValueOf("USER");
    private static final Option<? super String> PASSWORD = Option.sensitiveValueOf("PASSWORD");
    private static final Option<? super String> DATABASE = Option.sensitiveValueOf("DATABASE");

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
                .option(DRIVER, driver)
                .option(PROTOCOL, protocol)
                .option(HOST, host)
                .option(USER, user)
                .option(PASSWORD, password)
                .option(DATABASE, database)
                .build();
    }
}
