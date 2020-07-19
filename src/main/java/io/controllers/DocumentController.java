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

    @GetMapping("/document/get/{documentNumber}")
    public Document createDocument(@PathVariable("documentNumber") Long documentNumber) {
        return documentService.findByNumber(documentNumber);
    }

    @PostMapping("/document/update")
    public Document editDocument(@RequestBody Document document) {
        return documentService.update(document);
    }

    @PostMapping("/document/view")
    public void viewDocuments() {

    }
}
