package io.dao.impl;

import io.dao.DocumentRepository;
import io.entities.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

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
    @Nullable
    public Document get(Long documentNumber) {
        String sql = "SELECT " +
                "id, number, open_date, company_name, inn, kpp " +
                "FROM DOCUMENTS " +
                "WHERE number = (?)";
        List<Document> documentList = jdbcTemplate.query(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setLong(1, documentNumber);

                    return ps;
                }, ((rs, rowNum) -> Document.builder().id(rs.getLong(1))
                            .documentNumber(rs.getLong(2))
                            .openDate(rs.getTimestamp(3))
                            .companyName(rs.getString(4))
                            .inn(rs.getString(5))
                            .kpp(rs.getString(6))
                            .build()

        ));

        if(documentList.isEmpty()) {
            throw new RuntimeException("No one document with this document number");
        }
        if(documentList.size() > 1) {
            throw new RuntimeException("More than one documents with same document number!");
        }

        return documentList.get(0);
    }

    @Override
    public Document update(Document document) {
//        String sql = "UPDATE";
//        Document copyDocument = document.copy();
//        jdbcTemplate.update(
//                (Connection connection) -> {
//                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
//                    ps.setLong(1, copyDocument.getDocumentNumber());
//                    ps.setTimestamp(2, copyDocument.getOpenDate());
//                    ps.setString(3, copyDocument.getCompanyName());
//                    ps.setString(4, copyDocument.getInn());
//                    ps.setString(5, copyDocument.getKpp());
//
//                    return ps;
//                });
        return null;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
}
