package io.service;

import io.entities.Document;

import java.util.List;

/**
 * Сервисный слой для проверки и валидации входных параметров
 * Использует DAO слой для работы с БД
 */
public interface DocumentService {

    /**
     * Создание документа
     * @param document входной документ, для создания
     * @return созданный документ
     */
    Document create(Document document);

    /**
     * Поиск документа по номеру
     * @param documentNumber номер документа
     * @return найденный документ
     */
    Document findByNumber(Long documentNumber);

    /**
     * Обновление полей документа
     * @param document документ
     * @return Обновленный документ
     */
    Document update(Document document);

    /**
     * Получение документов постранично
     * @param page номер страницы
     * @return список документов данной страницы
     */
    List<Document> getDocumentsByPage(int page);
}
