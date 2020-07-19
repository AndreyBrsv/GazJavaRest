package io.dao.impl;

import io.dao.TransactionRepository;
import io.entities.Transaction;

import java.util.UUID;

public class TransactionRepositoryImpl implements TransactionRepository {
    @Override
    public Transaction create(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction get(UUID uuid) {
        return null;
    }

    @Override
    public Transaction update(Transaction transaction) {
        return null;
    }

    @Override
    public boolean delete(UUID uuid) {
        return false;
    }
}
