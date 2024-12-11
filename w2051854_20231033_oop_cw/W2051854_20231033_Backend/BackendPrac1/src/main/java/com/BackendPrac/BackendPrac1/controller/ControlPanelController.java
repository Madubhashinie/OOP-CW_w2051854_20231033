package com.BackendPrac.BackendPrac1.controller;

import com.BackendPrac.BackendPrac1.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/control")
public class ControlPanelController {

    private final TicketPool ticketPool;
    private Thread vendorThread1;
    private Thread customerThread1;
    private Thread vendorThread2;

    private VendorService vendor1;
    private CustomerService customer1;
    private VendorService vendor2;


    public ControlPanelController(TicketPool ticketPool, ConfigurationService configurationService) {
        this.ticketPool = ticketPool;

        this.vendor1 = new VendorService(ticketPool, configurationService, "V1");
        this.customer1 = new CustomerService(ticketPool, configurationService, "C1");
        this.vendor2 = new VendorService(ticketPool, configurationService, "V2");
    }

    /**
     * Starts the ticket pool system and start threads
     * @return ResponseEntity containing a start success message
     */
    @PostMapping("/start")
    public ResponseEntity<String> start() {
            vendorThread1 = new Thread(vendor1);
            customerThread1 = new Thread(customer1);
            vendorThread2 = new Thread(vendor2);

            vendorThread1.start();
            customerThread1.start();
            vendorThread2.start();


            return ResponseEntity.ok("Ticket pool started.");
    }

    /**
     * Stops all active threads
     * @return ResponseEntity containing a stop success message
     */
    @PostMapping("/stop")
    public ResponseEntity<String> stop() {
       vendor1.stop();
       customer1.stop();
       vendor2.stop();

        try {
            vendorThread1.join();
            customerThread1.join();
            vendorThread2.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error stopping threads: " + e.getMessage());
        }
        return ResponseEntity.ok("Ticket pool stopped.");
    }

    /**
     * Resets the ticket pool
     * @return ResponseEntity containing a reset success message
     */
    @PostMapping("/reset")
    public ResponseEntity<String> reset() {
        ticketPool.reset();
        return ResponseEntity.ok("Ticket pool reset.");
    }

    /**
     * Retrieves the current ticket pool status
     * @return ResponseEntity containing a map of current pool status
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Integer>> getStatus() {
        Map<String, Integer> status = new HashMap<>();
        status.put("currentTickets", ticketPool.getCurrentTicketsCount());
        status.put("maxCapacity", ticketPool.getMaxTicketCapacity());
        return ResponseEntity.ok(status);
    }
}
