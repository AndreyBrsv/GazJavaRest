package io.service.impl;

import io.dao.DocumentRepository;
import io.dao.TransactionRepository;
import io.entities.Document;
import io.entities.PageableView;
import io.entities.rq.GetPageRequest;
import io.exception.DocumentAlreadyExistException;
import io.exception.DocumentDeleteException;
import io.exception.DocumentNotFoundException;
import io.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Document create(Document document) {
        ValidateUtil.validateDocument(document);
        try {
            findByNumber(document.getNumber());
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
        ValidateUtil.validateId(id);
        return documentRepository.getByIdentifier(id);
    }

    @Override
    public boolean update(Document document) {
        ValidateUtil.validateForDocumentUpd(document);
        return documentRepository.update(document);
    }

    @Override
    public void deleteById(Long id) {
        ValidateUtil.validateId(id);
        if(!transactionRepository.getByDocumentId(id).isEmpty()) {
            throw new DocumentDeleteException("Document has transactions. " +
                    "If you want to delete this document you should delete all transactions bounded with one.");
        }

        documentRepository.delete(id);
    }

    @Override
    public PageableView<Document> getDocuments(GetPageRequest getPageRequest) {
        ValidateUtil.validateForView(getPageRequest);
        return documentRepository.pageView(getPageRequest.getIdFrom(), getPageRequest.getLimit());
    }
}
