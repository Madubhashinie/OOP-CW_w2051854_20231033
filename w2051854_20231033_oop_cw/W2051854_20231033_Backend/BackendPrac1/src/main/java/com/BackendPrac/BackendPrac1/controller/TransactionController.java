package com.BackendPrac.BackendPrac1.controller;

import com.BackendPrac.BackendPrac1.entity.TicketTransactions;
import com.BackendPrac.BackendPrac1.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Retrieves a list of all recorded ticket transactions
     * @return A list of all ticket transactions
     */
    @GetMapping
    public List<TicketTransactions> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}

