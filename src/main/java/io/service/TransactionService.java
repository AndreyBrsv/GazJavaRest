package io.service;

import io.entities.Document;
import io.entities.PageableView;
import io.entities.Transaction;
import io.entities.rq.GetPageRequest;

import java.util.UUID;

public interface TransactionService {

    Transaction create(Transaction transaction);

    Transaction findByUuid(UUID uuid);

    Transaction findById(Long id);

    boolean update(Document document);

    void deleteById(Long id);

    PageableView<Document> getDocuments(GetPageRequest getPageRequest);
}
