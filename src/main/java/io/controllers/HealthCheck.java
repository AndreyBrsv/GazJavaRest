package io.controllers;

import io.entities.Document;
import io.r2dbc.h2.CloseableConnectionFactory;
import io.r2dbc.h2.H2Result;
import io.r2dbc.h2.H2Statement;
import io.r2dbc.spi.Result;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.print.Doc;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

@RestController
@RequiredArgsConstructor
public class HealthCheck {

    private final CloseableConnectionFactory closeableConnectionFactory;

    @GetMapping("/health")
    public String healthCheck() {
        return "It's ok!";
    }

    @GetMapping("/document/{id}")
    public Mono<Object> findById(@PathVariable Long id) {
        System.out.println(id);

        return Mono.from(closeableConnectionFactory.create())
                .flatMap(c ->
                        Mono.from(c.createStatement("select * from DOCUMENTS where id = $1")
                                .bind("$1", id)
                                .execute())
                ).map(result -> result.map((row, meta) ->
                        Document.builder()
                        .id(row.get("id", Long.class))
                        .build())
                .flatMap(p -> Mono.just((p))));
    }

}
