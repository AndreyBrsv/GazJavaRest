package io.controllers;

import io.dao.TransactionRepository;
import io.entities.Document;
import io.entities.PageableView;
import io.entities.Transaction;
import io.entities.rq.GetPageRequest;
import io.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @DeleteMapping(value = "/transaction/delete-by-uuid/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeTransaction(@PathVariable("uuid") UUID uuid) {
        try {
            transactionService.deleteByUuid(uuid);
        } catch (Exception e) {
            return ResponseEntity.ok().body(
                    "{\"status\" : \"error\", " +
                    "\"message\" : \"" + e.getMessage() + "\"}");
        }

        return ResponseEntity.ok().body("{\"status\" : \"ok\"}");
    }

    @GetMapping("/transaction/view")
    public PageableView<Transaction> viewTransactions(@RequestBody GetPageRequest getPageRequest) {
        return transactionService.getTransactions(getPageRequest);
    }
}
