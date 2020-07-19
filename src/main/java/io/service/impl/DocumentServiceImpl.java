package io.service.impl;

import io.dao.DocumentRepository;
import io.entities.Document;
import io.entities.PageableView;
import io.entities.rq.GetPageRequest;
import io.exception.DocumentAlreadyExistException;
import io.exception.DocumentNotFoundException;
import io.exception.DocumentValidationException;
import io.exception.ValidationException;
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
    public Document findById(Long id) {
        if(id == null) {
            throw new RuntimeException("Document id can't be null");
        }

        return documentRepository.getById(id);
    }

    @Override
    public boolean update(Document document) {
        validateForUpdate(document);
        return documentRepository.update(document);
    }

    @Override
    public void deleteById(Long id) {
        if(id == null) {
            throw new RuntimeException("Document id can't be null");
        }

        documentRepository.delete(id);
    }

    @Override
    public PageableView<Document> getDocuments(GetPageRequest getPageRequest) {
        validateForView(getPageRequest);
        return documentRepository.pageView(getPageRequest.getIdFrom(), getPageRequest.getLimit());
    }

    private void validate(Document document) {
        if(document.getCompanyName() == null) {
            throw new DocumentValidationException("Company name is null");
        }
        if(document.getDocumentNumber() == 0) {
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

    private void validateForView(GetPageRequest getPageRequest) {
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
