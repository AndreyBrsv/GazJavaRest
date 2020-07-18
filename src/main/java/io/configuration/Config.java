package io.configuration;

import io.r2dbc.h2.*;
import io.r2dbc.spi.*;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
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
    public CloseableConnectionFactory closeableConnectionFactory() {
        Map<H2ConnectionOption, String> properties = new HashMap<>();
        properties.put(H2ConnectionOption.INIT, "RUNSCRIPT FROM '~/Projects/RestGazJava/src/main/resources/init.sql'");
        properties.put(H2ConnectionOption.DB_CLOSE_DELAY, "-1");
        return H2ConnectionFactory.inMemory("testdb", "sa", "", properties);
    }
}
