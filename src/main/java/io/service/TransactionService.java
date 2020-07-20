package io.service;

import io.entities.PageableView;
import io.entities.Transaction;
import io.entities.rq.GetPageRequest;

import java.util.UUID;

/**
 * Сервисный слой для проверки и валидации входных параметров {@link Transaction}
 */
public interface TransactionService {

    /**
     * Метод для создания транзакции
     * @param transaction транзакция для создания
     * @return созданную транзакцию
     */
    Transaction create(Transaction transaction);

    /**
     * Поиск транзакции по UUID
     * @param uuid UUID
     * @return возвращает найденную транзакцию
     */
    Transaction findByUuid(UUID uuid);

    /**
     * Удаление транзакции по UUID
     * @param uuid UUID
     */
    void deleteByUuid(UUID uuid);

    /**
     * Постраничный вывод транзакций
     * @param getPageRequest запрос с параметрами
     * @see GetPageRequest
     * @return возращает пролистываемый список
     * @see PageableView
     */
    PageableView<Transaction> getTransactions(GetPageRequest getPageRequest);
}
