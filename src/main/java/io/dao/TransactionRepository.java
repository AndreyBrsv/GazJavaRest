package io.dao;

import io.entities.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * DAO слой для работы с БД с сущностью {@link Transaction}
 */
public interface TransactionRepository extends Repository<Transaction, UUID> {

    /**
     * Получает список транзакций по номеру документа
     * @param documentId номер документа
     * @return список транзакций
     */
    List<Transaction> getByDocumentId(Long documentId);
}
