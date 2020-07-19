package io.controllers;

import io.dao.TransactionRepository;
import io.entities.Document;
import io.entities.Transaction;
import io.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transaction/create")
    public void createTransaction(@RequestBody Transaction transaction) {
        transactionService.create(transaction);
    }

    @PostMapping("/transaction/remove")
    public void removeTransaction(@RequestBody Document document) {

    }

    @PostMapping("/transaction/view")
    public void viewTransactions() {

    }
}
