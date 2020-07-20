package io.controllers;

import io.dao.TransactionRepository;
import io.entities.Document;
import io.entities.PageableView;
import io.entities.Transaction;
import io.entities.rq.GetPageRequest;
import io.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/transaction/get-by-uuid/{uuid}")
    public Transaction getTransaction(@PathVariable("uuid") UUID uuid) {
        return transactionService.findByUuid(uuid);
    }

    @DeleteMapping("/transaction/delete-by-uuid/{uuid}")
    public ResponseEntity<?> removeTransaction(@PathVariable("uuid") UUID uuid) {
        transactionService.deleteByUuid(uuid);
        return ResponseEntity.ok().body("{\"status\" : \"ok\"}");
    }

    @GetMapping("/transaction/view")
    public PageableView<Transaction> viewTransactions(@RequestBody GetPageRequest getPageRequest) {
        return transactionService.getTransactions(getPageRequest);
    }
}
