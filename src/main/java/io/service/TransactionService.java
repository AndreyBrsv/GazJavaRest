package io.service;

import io.entities.Document;
import io.entities.PageableView;
import io.entities.Transaction;
import io.entities.rq.GetPageRequest;

import java.util.UUID;

public interface TransactionService {

    Transaction create(Transaction transaction);

    Transaction findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    PageableView<Transaction> getTransactions(GetPageRequest getPageRequest);
}
