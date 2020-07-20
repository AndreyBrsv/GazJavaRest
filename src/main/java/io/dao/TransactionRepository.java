package io.dao;

import io.entities.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends Repository<Transaction, UUID> {

    List<Transaction> getByDocumentId(Long documentId);
}
