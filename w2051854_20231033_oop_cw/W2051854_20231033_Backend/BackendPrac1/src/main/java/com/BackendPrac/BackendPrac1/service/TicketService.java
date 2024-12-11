package com.BackendPrac.BackendPrac1.service;


import com.BackendPrac.BackendPrac1.entity.Configuration;
import com.BackendPrac.BackendPrac1.entity.Ticket;
import com.BackendPrac.BackendPrac1.repository.ConfigurationRepository;
import com.BackendPrac.BackendPrac1.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketsRepository;
    private final ConfigurationRepository configurationRepository;

    public TicketService(TicketRepository ticketRepository, ConfigurationRepository configurationRepository) {
        this.ticketsRepository = ticketRepository;
        this.configurationRepository = configurationRepository;
    }

    /**
     * Adds a new ticket to the database
     * @param ticket - The ticket entity to be saved
     */
    public void addTicket(Ticket ticket) {
        ticketsRepository.save(ticket);

    }

    /**
     * Retrieves all tickets from the database
     * @return A list of all tickets
     */
    public List<Ticket> getAllTickets() {
        return ticketsRepository.findAll();
    }



}
