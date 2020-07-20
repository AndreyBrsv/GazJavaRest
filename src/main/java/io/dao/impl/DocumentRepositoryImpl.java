package io.dao.impl;

import io.dao.DocumentRepository;
import io.entities.Document;
import io.entities.PageableView;
import io.exception.DocumentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DocumentRepositoryImpl implements DocumentRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Document> documentRowMapper =
            (rs, rowNum) -> Document.builder().id(rs.getLong(1))
            .number(rs.getLong(2))
            .openDate(rs.getTimestamp(3))
            .companyName(rs.getString(4))
            .inn(rs.getString(5))
            .kpp(rs.getString(6))
            .build();

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
                    ps.setLong(1, copyDocument.getNumber());
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
    public Document getByIdentifier(Long id) {
        String sql = "SELECT " +
                "id, number, open_date, company_name, inn, kpp " +
                "FROM DOCUMENTS " +
                "WHERE id = ?";

        List<Document> documentList = jdbcTemplate.query(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setLong(1, id);

                    return ps;
                }, documentRowMapper);

        if(documentList.isEmpty()) {
            throw new DocumentNotFoundException("No one document with this id");
        }
        if(documentList.size() > 1) {
            throw new RuntimeException("More than one documents with same id");
        }

        return documentList.get(0);
    }


    @Override
    public Document getByNumber(long documentNumber) {
        String sql = "SELECT " +
                "id, number, open_date, company_name, inn, kpp " +
                "FROM DOCUMENTS " +
                "WHERE number = ?";
        List<Document> documentList = jdbcTemplate.query(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setLong(1, documentNumber);

                    return ps;
                }, documentRowMapper);

        if(documentList.isEmpty()) {
            throw new DocumentNotFoundException("No one document with this document number");
        }
        if(documentList.size() > 1) {
            throw new RuntimeException("More than one documents with same document number!");
        }

        return documentList.get(0);
    }

    @Override
    public boolean update(Document document) {
        String sql =
                "UPDATE DOCUMENTS SET " +
                "NUMBER = ISNULL(?, NUMBER), " +
                "OPEN_DATE = ISNULL(?, OPEN_DATE), " +
                "COMPANY_NAME = ISNULL(?, COMPANY_NAME), " +
                "INN = ISNULL(?, INN), " +
                "KPP = ISNULL(?, KPP) " +
                "WHERE ID = ?";

        int strings = jdbcTemplate.update((Connection con) -> {
            PreparedStatement ps = con.prepareStatement(sql);
            fillPreparedStatement(ps, document);
            return ps;
        });

        return strings > 0;
    }

    @Override
    public void delete(Long id) {
        String sqlDelete = "DELETE DOCUMENTS WHERE ID = " + id;
        jdbcTemplate.execute(sqlDelete);
    }

    @Override
    public PageableView<Document> pageView(Long idFrom, int limit) {
        String sql = "SELECT " +
                "id, number, open_date, company_name, inn, kpp FROM DOCUMENTS " +
                "WHERE id >= ? " +
                "ORDER BY id " +
                "LIMIT ? ";

        List<Document> documentList = jdbcTemplate.query(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setLong(1, idFrom);
                    ps.setInt(2, limit);

                    return ps;
                }, documentRowMapper);

        if(documentList.isEmpty()) {
            throw new DocumentNotFoundException("No documents in database with your params");
        }

        int allIds = jdbcTemplate.queryForList("SELECT COUNT(ID) FROM DOCUMENTS", Integer.class).get(0);

        PageableView<Document> pageableView = new PageableView<>();
        pageableView.setEntities(documentList);
        pageableView.setIdFrom(idFrom);
        pageableView.setLimit(limit);
        pageableView.setLastId(documentList.get(documentList.size() - 1).getId());
        pageableView.setAllIds(allIds);

        return pageableView;
    }

    private void fillPreparedStatement(PreparedStatement ps, Document document) throws SQLException {
        if(document.getNumber() == null) {
            ps.setNull(1, Types.BIGINT);
        } else {
            ps.setLong(1, document.getNumber());
        }

        if(document.getOpenDate() == null) {
            ps.setNull(2, Types.TIMESTAMP);
        } else {
            ps.setTimestamp(2, document.getOpenDate());
        }

        if(document.getCompanyName() == null) {
            ps.setNull(3, Types.VARCHAR);
        } else {
            ps.setString(3, document.getCompanyName());
        }

        if(document.getInn() == null) {
            ps.setNull(4, Types.VARCHAR);
        } else {
            ps.setString(4, document.getInn());
        }

        if(document.getKpp() == null) {
            ps.setNull(5, Types.VARCHAR);
        } else {
            ps.setString(5, document.getKpp());
        }

        ps.setLong(6, document.getId());
    }
}
