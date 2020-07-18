package io.controllers;

import io.entities.Document;
import io.entities.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @PostMapping("/transaction/create")
    public void createTransaction(@RequestBody Transaction transaction) {

    }

    @PostMapping("/transaction/remove")
    public void removeTransaction(@RequestBody Document document) {

    }

    @PostMapping("/transaction/view")
    public void viewTransactions() {

    }
}
