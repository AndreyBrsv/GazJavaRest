package io.controllers;

import io.entities.Document;
import io.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/document/create")
    public Document createDocument(@RequestBody Document document) {
        return documentService.create(document);
    }

    @PostMapping("/document/edit")
    public void editDocument(@RequestBody Document document) {

    }

    @PostMapping("/document/view")
    public void viewDocuments() {

    }
}
