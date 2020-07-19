package io.dao;

import io.entities.Document;

public interface DocumentRepository extends Repository<Document, Long> {

    Document getByNumber(long documentNumber);
}
