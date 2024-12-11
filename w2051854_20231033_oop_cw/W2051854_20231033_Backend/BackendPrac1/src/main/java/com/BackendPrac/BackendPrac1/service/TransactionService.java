package com.BackendPrac.BackendPrac1.service;

import com.BackendPrac.BackendPrac1.entity.TicketTransactions;
import com.BackendPrac.BackendPrac1.repository.TicketTransactionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TicketTransactionsRepository ticketTransactionsRepository;

    public TransactionService(TicketTransactionsRepository ticketTransactionsRepository) {
        this.ticketTransactionsRepository = ticketTransactionsRepository;
    }

    /**
     * Retrieves all recorded transactions from the database
     * @return A list of all transactions
     */
    public List<TicketTransactions> getAllTransactions() {
        return ticketTransactionsRepository.findAll();
    }
}
