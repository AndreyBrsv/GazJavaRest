package io.dao.impl;

import io.dao.TransactionRepository;
import io.entities.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Transaction create(Transaction transaction) {
        Transaction copyTransaction = transaction.copy();

        String sql =
                "INSERT INTO TRANSACTION " +
                        "(document_id, uuid, time, sum, transaction_fee) " +
                        "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                (Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setLong(1, copyTransaction.getDocumentId());
                    ps.setObject(2, copyTransaction.getUuid());
                    ps.setTimestamp(3, copyTransaction.getTime());
                    ps.setBigDecimal(4, copyTransaction.getSum());
                    ps.setBigDecimal(5, copyTransaction.getTransactionFee());

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
    public Transaction getById(Long aLong) {
        return null;
    }

    @Override
    public Transaction update(Transaction transaction) {
        return null;
    }


    @Override
    public boolean delete(Long uuid) {
        return false;
    }

    @Override
    public Transaction findByUuid(UUID uuid) {
        return null;
    }
}
