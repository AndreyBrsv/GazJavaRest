package io.dao.impl;

import io.dao.TransactionRepository;
import io.entities.PageableView;
import io.entities.Transaction;
import io.exception.DocumentNotFoundException;
import io.exception.TransactionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Transaction> transactionRowMapper =
            (rs, rowNum) -> Transaction.builder()
                    .id(rs.getLong(1))
                    .documentId(rs.getLong(2))
                    .uuid((UUID) rs.getObject(3))
                    .time(rs.getTimestamp(4))
                    .sum(rs.getBigDecimal(5))
                    .fee(rs.getBigDecimal(6))
                    .build();

    @Override
    public Transaction create(Transaction transaction) {
        Transaction copyTransaction = transaction.copy();

        String sql =
                "INSERT INTO TRANSACTION " +
                        "(document_id, uuid, time, sum, fee) " +
                        "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setLong(1, copyTransaction.getDocumentId());
                    ps.setObject(2, copyTransaction.getUuid());
                    ps.setTimestamp(3, copyTransaction.getTime());
                    ps.setBigDecimal(4, copyTransaction.getSum());
                    ps.setBigDecimal(5, copyTransaction.getFee());

                    return ps;
                }, keyHolder);

        Number key = keyHolder.getKey();
        if(key == null) {
            throw new RuntimeException("Can't create transaction");
        }
        copyTransaction.setId(key.longValue());

        return copyTransaction;
    }

    @Override
    public Transaction getByIdentifier(UUID uuid) {
        String sql = "SELECT " +
                "id, document_id, uuid, time, sum, fee " +
                "FROM TRANSACTION " +
                "WHERE uuid = ?";

        List<Transaction> transactionList = jdbcTemplate.query(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, uuid);

                    return ps;
                }, transactionRowMapper);

        if(transactionList.isEmpty()) {
            throw new TransactionNotFoundException("No one transaction with this UUID");
        }
        if(transactionList.size() > 1) {
            throw new RuntimeException("More than one transaction with same UUID");
        }

        return transactionList.get(0);
    }

    @Override
    public boolean update(Transaction transaction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(UUID uuid) {
        String sql = "DELETE TRANSACTION WHERE UUID = '" + uuid + "'";
        jdbcTemplate.execute(sql);
    }

    @Override
    public PageableView<Transaction> pageView(Long idFrom, int limit) {
        String sql = "SELECT " +
                "id, document_id, uuid, time, sum, fee FROM TRANSACTION " +
                "WHERE id >= ? " +
                "ORDER BY id " +
                "LIMIT ? ";

        List<Transaction> transactionList = jdbcTemplate.query(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setLong(1, idFrom);
                    ps.setInt(2, limit);

                    return ps;
                }, transactionRowMapper);

        if(transactionList.isEmpty()) {
            throw new TransactionNotFoundException("No transactions in database with your params");
        }

        int allIds = jdbcTemplate.queryForList("SELECT COUNT(ID) FROM TRANSACTION", Integer.class).get(0);

        PageableView<Transaction> pageableView = new PageableView<>();
        pageableView.setEntities(transactionList);
        pageableView.setIdFrom(idFrom);
        pageableView.setLimit(limit);
        pageableView.setLastId(transactionList.get(transactionList.size() - 1).getId());
        pageableView.setAllIds(allIds);

        return pageableView;
    }

    @Override
    public List<Transaction> getByDocumentId(Long documentId) {
        String sql = "SELECT " +
                "id, document_id, uuid, time, sum, fee " +
                "FROM TRANSACTION " +
                "WHERE document_id = ?";

        List<Transaction> transactionList = jdbcTemplate.query(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setLong(1, documentId);

                    return ps;
                }, transactionRowMapper);

        if(transactionList.isEmpty()) {
            throw new TransactionNotFoundException("No one transaction with this documentId");
        }

        return transactionList;
    }
}
