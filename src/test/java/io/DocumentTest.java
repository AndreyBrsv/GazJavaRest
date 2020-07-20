package io;

import io.entities.Document;
import io.exception.DocumentAlreadyExistException;
import io.exception.DocumentNotFoundException;
import io.exception.DocumentValidationException;
import io.service.DocumentService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;

public class DocumentTest extends AbstractTest {
    @Autowired
    private DocumentService documentService;

    private static final Document document = Document.builder()
            .number(15151621L)
            .companyName("gazprombank")
            .openDate(Timestamp.from(Instant.now()))
            .inn("inn")
            .kpp("kpp")
            .build();

    @Test(expected = DocumentValidationException.class)
    public void createWithId() {
        Document documentWithId = document.copy();
        documentWithId.setId(1L);

        documentService.create(documentWithId);
    }

    @Test
    public void createWithOutId() {
        Document documentWithoutId = document.copy();
        var documentResult = documentService.create(documentWithoutId);

        Assertions.assertEquals(documentWithoutId.getNumber(), documentResult.getNumber());
        Assertions.assertTrue(documentResult.getId() > 0);
    }

    @Test(expected = DocumentAlreadyExistException.class)
    public void createTwoDocumentsWithOneNumber() {
        Document document1 = document.copy();
        Document document2 = document.copy();

        documentService.create(document1);
        documentService.create(document2);
    }

    @Test(expected = DocumentNotFoundException.class)
    public void findByNumberNotFound() {
        documentService.findByNumber(123L);
    }

    @Test
    public void findByNumberFound() {
        Document documentCreate = document.copy();
        Long documentNumber = 12345L;
        documentCreate.setNumber(documentNumber);
        documentService.create(documentCreate);

        Document documentResult = documentService.findByNumber(documentNumber);
        Assertions.assertTrue(documentResult.getId() > 0);
    }

    @Test(expected = RuntimeException.class)
    public void findByNumberDocumentNull() {
        documentService.findByNumber(null);
    }

    @Test
    public void updateDocument() {
        String companyNameAfterUpdate = "promgazbank";
        Long documentNumber = 123456L;
        Document documentCreate = document.copy();
        documentCreate.setNumber(documentNumber);

        Document documentResult = documentService.create(documentCreate);
        documentResult.setCompanyName(companyNameAfterUpdate);

        if(documentService.update(documentResult)) {
            String companyNameActual = documentService.findByNumber(documentNumber).getCompanyName();
            Assertions.assertEquals(companyNameAfterUpdate, companyNameActual);
        }
    }

    @Test(expected = DocumentNotFoundException.class)
    public void deleteDocument() {
        Long documentNumber = 1525162515L;
        Document documentCreate = document.copy();
        documentCreate.setNumber(documentNumber);

        Long id = documentService.create(documentCreate).getId();
        documentService.deleteById(id);
        documentService.findByNumber(documentNumber);
    }
}
