package com.BackendPrac.BackendPrac1.controller;
import com.BackendPrac.BackendPrac1.entity.Ticket;
import com.BackendPrac.BackendPrac1.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Creates a new ticket based on the provided request body
     * @param ticketRequest ticketRequest The ticket details received from the request body
     * @return ResponseEntity containing a "Ticket created" success message
     */
    @PostMapping
    public ResponseEntity<String> addTicket(@RequestBody Ticket ticketRequest) {
        ticketService.addTicket(ticketRequest);
        return ResponseEntity.ok("Ticket created successfully");
    }

    /**
     * Retrieves a list of all available tickets
     * @return ResponseEntity containing a list of all tickets
     */
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

}
