package io;

import io.entities.Document;
import io.entities.Transaction;
import io.exception.TransactionNotFoundException;
import io.service.DocumentService;
import io.service.TransactionService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class TransactionTest extends AbstractTest {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DocumentService documentService;

    private static final Document document = Document.builder()
            .number(869175L)
            .companyName("gazprombank")
            .openDate(Timestamp.from(Instant.now()))
            .inn("inn")
            .kpp("kpp")
            .build();

    private static final Transaction transaction = Transaction.builder()
            .sum(BigDecimal.valueOf(105106.125))
            .fee(BigDecimal.valueOf(1415.14))
            .build();

    @Test(expected = DataAccessException.class)
    public void createTransactionNotFoundDocument() {
        Transaction transactionCreate = transaction.copy();
        transactionCreate.setDocumentId(1111L);

        Transaction transactionResult = transactionService.create(transactionCreate);
        Assertions.assertTrue(transactionResult.getId() > 0);
    }

    @Test
    public void createTransaction() {
        Transaction transactionCreate = transaction.copy();
        transactionCreate.setDocumentId(createDocument(1111L).getId());

        Transaction transactionResult = transactionService.create(transactionCreate);
        Assertions.assertTrue(transactionResult.getId() > 0);
    }

    @Test(expected = TransactionNotFoundException.class)
    public void findByUuidNotFound() {
        transactionService.findByUuid(UUID.randomUUID());
    }

    @Test
    public void findByUuid() {
        Transaction transactionCreate = transaction.copy();
        transactionCreate.setDocumentId(createDocument(2222L).getId());

        Transaction transactionResult =
                transactionService.findByUuid(transactionService.create(transactionCreate).getUuid());

        Assertions.assertTrue(transactionResult.getId() > 0);
    }

    @Test(expected = TransactionNotFoundException.class)
    public void deleteByUuid() {
        Transaction transactionCreate = transaction.copy();
        transactionCreate.setDocumentId(createDocument(3333L).getId());

        UUID uuid = transactionService.create(transactionCreate).getUuid();
        transactionService.deleteByUuid(uuid);
        transactionService.findByUuid(uuid);
    }

    private Document createDocument(Long documentNumber) {
        Document documentCopy = document.copy();
        documentCopy.setNumber(documentNumber);

        return documentService.create(documentCopy);
    }
}
