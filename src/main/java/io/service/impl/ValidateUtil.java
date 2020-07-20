package io.service.impl;

import io.entities.Document;
import io.entities.Transaction;
import io.entities.rq.GetPageRequest;
import io.exception.DocumentValidationException;
import io.exception.TransactionValidationException;
import io.exception.ValidationException;

import java.util.UUID;

public class ValidateUtil {
    public static void validateDocument(Document document) {
        if(document.getCompanyName() == null) {
            throw new DocumentValidationException("Company name is null");
        }
        if(document.getNumber() == 0) {
            throw new DocumentValidationException("Document number is 0");
        }
        if(document.getInn() == null) {
            throw new DocumentValidationException("Inn number is null");
        }
        if(document.getKpp() == null) {
            throw new DocumentValidationException("Kpp number is null");
        }
    }

    public static void validateForDocumentUpd(Document document) {
        if(document.getId() == 0) {
            throw new DocumentValidationException("Id is null");
        }
    }
    
    public static void validateTransaction(Transaction transaction) {
        if(transaction.getTime() != null) {
            throw new TransactionValidationException("Transaction time must be null");
        }
        if(transaction.getDocumentId() == null) {
            throw new TransactionValidationException("DocumentId can't be null");
        }
        if(transaction.getSum().doubleValue() <= 0) {
            throw new TransactionValidationException("TransactionSum must be > 0");
        }
        if(transaction.getFee().doubleValue() <= 0) {
            throw new TransactionValidationException("TransactionFee must be > 0");
        }
        if(transaction.getUuid() != null) {
            throw new TransactionValidationException("Transaction UUID must be null");
        }
    }

    public static void validateUuid(UUID uuid) {
        if(uuid == null) {
            throw new ValidationException("UUID must be not null");
        }
    }

    public static void validateForView(GetPageRequest getPageRequest) {
        if(getPageRequest.getLimit() == null) {
            throw new ValidationException("limit is null");
        }
        if(getPageRequest.getLimit() <= 0 && getPageRequest.getLimit() >= 20) {
            throw new ValidationException("limit must be > 0 and <= 20");
        }
        if(getPageRequest.getIdFrom() == null ) {
            throw new ValidationException("idFrom is null");
        }
        if(getPageRequest.getIdFrom() <= 0) {
            throw new ValidationException("idFrom must be > 0");
        }
    }
}
