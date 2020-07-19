package io.service;

import io.entities.Transaction;

public interface TransactionService {

    Transaction create(Transaction transaction);
}
