package com.BackendPrac.BackendPrac1.repository;

import com.BackendPrac.BackendPrac1.entity.TicketTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTransactionsRepository extends JpaRepository<TicketTransactions, Integer> {
}
