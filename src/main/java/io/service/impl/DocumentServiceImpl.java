package io.service.impl;

import io.dao.DocumentRepository;
import io.entities.Document;
import io.exception.DocumentAlreadyExistException;
import io.exception.DocumentNotFoundException;
import io.exception.DocumentValidationException;
import io.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    @Override
    public Document create(Document document) {
        validate(document);
        try {
            findByNumber(document.getDocumentNumber());
        } catch (DocumentNotFoundException e) {
            return documentRepository.create(document);
        }

        throw new DocumentAlreadyExistException("Document with given number already exist");
    }

    @Override
    public Document findByNumber(Long documentNumber) {
        if(documentNumber == null) {
            throw new RuntimeException("Document number can't be null");
        }

        return documentRepository.getByNumber(documentNumber);
    }

    @Override
    public Document update(Document document) {
        validateForUpdate(document);
        return documentRepository.update(document);
    }

    @Override
    public List<Document> getDocumentsByPage(int page) {
        return null;
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
