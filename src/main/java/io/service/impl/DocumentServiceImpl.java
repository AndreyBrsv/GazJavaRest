package io.service.impl;

import io.dao.DocumentRepository;
import io.entities.Document;
import io.exception.DocumentValidationException;
import io.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    @Override
    public Document create(Document document) {
        validate(document);
        return documentRepository.create(document);
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
}
