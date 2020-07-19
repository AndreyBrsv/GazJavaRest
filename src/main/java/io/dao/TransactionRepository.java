package io.dao;

import io.entities.Transaction;
import java.util.UUID;

public interface TransactionRepository extends Repository<Transaction, Long> {

    Transaction findByUuid(UUID uuid);
}
