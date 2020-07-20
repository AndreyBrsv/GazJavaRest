package io.service.impl;

import io.dao.TransactionRepository;
import io.entities.PageableView;
import io.entities.Transaction;
import io.entities.rq.GetPageRequest;
import io.exception.TransactionValidationException;
import io.exception.ValidationException;
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
        ValidateUtil.validateTransaction(transaction);
        Transaction copyTransaction = transaction.copy();
        copyTransaction.setUuid(UUID.randomUUID());
        copyTransaction.setTime(Timestamp.from(Instant.now()));
        return transactionRepository.create(copyTransaction);
    }

    @Override
    public Transaction findByUuid(UUID uuid) {
        ValidateUtil.validateUuid(uuid);
        return transactionRepository.getByIdentifier(uuid);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        ValidateUtil.validateUuid(uuid);
        transactionRepository.delete(uuid);
    }

    @Override
    public PageableView<Transaction> getTransactions(GetPageRequest getPageRequest) {
        return transactionRepository.pageView(getPageRequest.getIdFrom(), getPageRequest.getLimit());
    }
}
