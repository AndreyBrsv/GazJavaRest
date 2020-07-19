package io.service.impl;

import io.dao.DocumentRepository;
import io.entities.Document;
import io.exception.DocumentValidationException;
import io.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    @Override
    public Document create(Document document) {
        validate(document);
        return documentRepository.create(document);
    }

    @Override
    public Document findByNumber(Long documentNumber) {
        if(documentNumber == null) {
            throw new RuntimeException("Document number can't be null");
        }
        return documentRepository.get(documentNumber);
    }

    @Override
    public Document update(Document document) {
        validateForUpdate(document);
        return documentRepository.update(document);
    }

    private void validate(Document document) {
        if(document.getCompanyName() == null) {
            throw new DocumentValidationException("Company name is null");
        }
        if(document.getDocumentNumber() == 0) { //todo unique number
            throw new DocumentValidationException("Document number is 0");
        }
        if(document.getInn() == null) {
            throw new DocumentValidationException("Inn number is null");
        }
        if(document.getKpp() == null) {
            throw new DocumentValidationException("Kpp number is null");
        }
    }

    private void validateForUpdate(Document document) {
        if(document.getId() == 0) {
            throw new DocumentValidationException("Id is null");
        }
    }
}
