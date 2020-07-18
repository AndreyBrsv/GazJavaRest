package io.controllers;

import io.entities.Document;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    @PostMapping("/document/create")
    public void createDocument(@RequestBody Document document) {

    }

    @PostMapping("/document/edit")
    public void editDocument(@RequestBody Document document) {

    }

    @PostMapping("/document/view")
    public void viewDocuments() {

    }
}
