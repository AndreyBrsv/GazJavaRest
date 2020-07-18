package io.dao.impl;

import io.dao.DocumentRepository;
import io.entities.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
@RequiredArgsConstructor
public class DocumentRepositoryImpl implements DocumentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Document create(Document document) {
        Document copyDocument = document.copy();

        String sql =
                "INSERT INTO DOCUMENTS " +
                "(number, open_date, company_name, inn, kpp) " +
                "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setLong(1, copyDocument.getDocumentNumber());
                    ps.setTimestamp(2, copyDocument.getOpenDate());
                    ps.setString(3, copyDocument.getCompanyName());
                    ps.setString(4, copyDocument.getInn());
                    ps.setString(5, copyDocument.getKpp());

                    return ps;
                }, keyHolder);

        Number key = keyHolder.getKey();
        if(key == null) {
            throw new RuntimeException("Can't create document");
        }
        copyDocument.setId(key.longValue());

        return copyDocument;
    }


    @Override
    public Document get(Long aLong) {
        return null;
    }

    @Override
    public Document update(Document document) {
        return null;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
}
