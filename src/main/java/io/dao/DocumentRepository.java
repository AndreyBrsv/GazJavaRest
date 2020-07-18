package io.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Slf4j
public class DocumentRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostConstruct
    public void method() {

    }
}
