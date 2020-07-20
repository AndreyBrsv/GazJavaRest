package io.dao;

import io.entities.Document;

/**
 * DAO слой для работы с БД с сущностью {@link Document}
 */
public interface DocumentRepository extends Repository<Document, Long> {

    /**
     * Получает документ по номеру документа
     * @param documentNumber номер документа
     * @return документ
     */
    Document getByNumber(Long documentNumber);
}
