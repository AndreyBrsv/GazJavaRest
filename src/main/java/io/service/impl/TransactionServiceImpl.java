package io.service.impl;

import io.dao.TransactionRepository;
import io.entities.Transaction;
import io.exception.TransactionValidationException;
import io.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction create(Transaction transaction) {
        validate(transaction);
        Transaction copyTransaction = transaction.copy();
        copyTransaction.setUuid(UUID.randomUUID());
        return transactionRepository.create(copyTransaction);
    }

    private void validate(Transaction transaction) {
        if(transaction.getDocumentId() == 0) {
            throw new TransactionValidationException("DocumentId can't be 0");
        }
        if(transaction.getSum().doubleValue() <= 0) {
            throw new TransactionValidationException("TransactionSum must be > 0");
        }
        if(transaction.getTransactionFee().doubleValue() <= 0) {
            throw new TransactionValidationException("TransactionFee must be > 0");
        }
        if(transaction.getTime().before(Timestamp.from(Instant.now()))) {
            throw new TransactionValidationException("Transaction must be before " + Instant.now());
        }
        if(transaction.getUuid() != null) {
            throw new TransactionValidationException("Transaction UUID must be null");
        }
    }
}
