package io.controllers;

import io.entities.Document;
import io.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/document/create")
    public Document createDocument(@RequestBody Document document) {
        return documentService.create(document);
    }

    @GetMapping("/document/get-by-number/{documentNumber}")
    public Document getDocumentByNumber(@PathVariable("documentNumber") Long documentNumber) {
        return documentService.findByNumber(documentNumber);
    }

    @GetMapping("/document/get-by-id/{id}")
    public Document getDocumentById(@PathVariable("id") Long id) {
        return documentService.findById(id);
    }

    @PostMapping("/document/update")
    public boolean updateDocument(@RequestBody Document document) {
        return documentService.update(document);
    }

    @PostMapping("/document/view")
    public void viewDocuments() {

    }
}
