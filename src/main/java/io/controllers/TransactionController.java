package io.controllers;

import io.dao.TransactionRepository;
import io.entities.Document;
import io.entities.PageableView;
import io.entities.Transaction;
import io.entities.rq.GetPageRequest;
import io.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transaction/create")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }

    @GetMapping("/transaction/get/{uuid}")
    public Transaction getTransaction(@PathVariable("uuid") UUID uuid) {
        return transactionService.findByUuid(uuid);
    }

    @GetMapping("/transaction/remove/{uuid}")
    public void removeTransaction(@PathVariable("uuid") UUID uuid) {
        transactionService.deleteByUuid(uuid);
    }

    @GetMapping("/transaction/view")
    public PageableView<Transaction> viewTransactions(@RequestBody GetPageRequest getPageRequest) {
        return transactionService.getTransactions(getPageRequest);
    }
}
