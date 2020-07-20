package io.service;

import io.entities.Document;
import io.entities.PageableView;
import io.entities.rq.GetPageRequest;

/**
 * Сервисный слой для проверки и валидации входных параметров {@link Document}
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
     * Поиск документа по id
     * @param id id документа
     * @return найденный документ
     */
    Document findById(Long id);

    /**
     * Обновление полей документа
     * @param document документ
     * @return успешность обновления (true/false)
     */
    boolean update(Document document);

    /**
     * Удаление документа по ID
     * @param id id документа
     */
    void deleteById(Long id);

    /**
     * Постраничный вывод документов
     * @param getPageRequest запрос с параметрами
     * @see GetPageRequest
     * @return возращает пролистываемый список
     * @see PageableView
     */
    PageableView<Document> getDocuments(GetPageRequest getPageRequest);
}
