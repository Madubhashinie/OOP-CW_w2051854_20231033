package com.BackendPrac.BackendPrac1.service;

import com.BackendPrac.BackendPrac1.entity.Ticket;
import com.BackendPrac.BackendPrac1.entity.TicketTransactions;
import com.BackendPrac.BackendPrac1.repository.TicketRepository;
import com.BackendPrac.BackendPrac1.repository.TicketTransactionsRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class TicketPool {
    private final Queue<Ticket> ticketQueue = new LinkedList<>();
    private final int maxCapacity;
    private final TicketRepository ticketRepository;
    private final TicketTransactionsRepository ticketTransactionsRepository;

    public TicketPool(ConfigurationService configurationService, TicketRepository ticketRepository, TicketTransactionsRepository ticketTransactionsRepository) {
        this.maxCapacity = configurationService.getConfiguration().getMaxTicketCapacity();
        this.ticketRepository = ticketRepository;
        this.ticketTransactionsRepository = ticketTransactionsRepository;
    }

    /**
     * Adding tickets to ticket pool
     * save the new ticket and transaction in the databases
     * @param ticket The ticket to be added
     * @param vendorId Identifier for the vendor
     */
    public synchronized void addTicket(Ticket ticket,String vendorId) {
        while (ticketQueue.size() >= maxCapacity) {
            try {
                System.out.println("Vendor " + vendorId + " is waiting... cannot add ticket. Pool is full.");
                System.out.println();
                wait();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + vendorId + " was interrupted.");
                return;
            }
        }
        Ticket newTicket = new Ticket("Cinema Event", 250.0);
        ticketRepository.save(newTicket);  // Persist in DB

        // Add Ticket to the Queue
        ticketQueue.offer(newTicket);;
        System.out.println("Vendor " + vendorId + " added a ticket. "+ "Ticket Details: "+ newTicket + " Current tickets: "+getCurrentTicketsCount() );
        System.out.println();

        TicketTransactions transaction = new TicketTransactions(vendorId, "Added Ticket", newTicket);
        ticketTransactionsRepository.save(transaction);
        notifyAll();

    }

    /**
     * removing tickets from the ticket pool
     * save the new transaction in the database
     * @param customerId Identifier for the customer
     */
    public synchronized void removeTicket(String customerId) {

        while (ticketQueue.isEmpty()) {
            try {
                System.out.println("Customer " + customerId + " is waiting... found no tickets available. Pool is empty.");
                System.out.println();
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " was interrupted.");
                return;
            }
        }
        Ticket ticket = ticketQueue.poll();
        System.out.println("Customer " + customerId + " purchased a ticket. " + ticket + " Current tickets: "+getCurrentTicketsCount() );
        System.out.println();

        TicketTransactions transaction = new TicketTransactions(customerId, "Purchased Ticket", ticket);
        ticketTransactionsRepository.save(transaction);

        notifyAll();
    }

    /**
     *Resets the ticket pool by clearing all tickets in the queue.
     */
    public synchronized void reset() {
        ticketQueue.clear();
        System.out.println("Ticket pool has been reset.");
        notifyAll();
    }

    /**
     * Retrieves the current ticket count in the ticket pool
     * @return The number of tickets currently in the queue
     */
    public synchronized int getCurrentTicketsCount() {
        return ticketQueue.size();
    }

    /**
     * Retrieves the maximum ticket capacity in the ticket pool
     * @return The maximum number of tickets the pool can hold
     */
    public synchronized int getMaxTicketCapacity() {
        return maxCapacity;
    }
}
