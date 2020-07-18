package io.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RequiredArgsConstructor
@Slf4j
public class DocumentRepositoryImpl {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


}
