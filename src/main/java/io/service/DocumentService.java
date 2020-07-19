package io.service;

import io.entities.Document;

public interface DocumentService {

    Document create(Document document);

    Document findByNumber(Long documentNumber);

    Document update(Document document);
}
