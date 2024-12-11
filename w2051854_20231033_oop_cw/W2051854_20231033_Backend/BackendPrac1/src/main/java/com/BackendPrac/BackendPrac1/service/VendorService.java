package com.BackendPrac.BackendPrac1.service;

import com.BackendPrac.BackendPrac1.entity.Ticket;

public class VendorService implements Runnable{
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final String vendorId;
    private boolean isRunning = true;

    public VendorService(TicketPool ticketPool, ConfigurationService configurationService, String vendorId) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = configurationService.getConfiguration().getTicketReleaseRate();
        this.vendorId = vendorId;
    }

    /**
     * Stops the ticket add process
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * continuously creates and adds tickets to the ticket pool at the ticket release rate intervals.
     */
    @Override
    public void run() {
        while (isRunning) {
            Ticket ticket = new Ticket( "Cinema Event", 250.0);
            ticketPool.addTicket(ticket,vendorId);
            try {
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + vendorId + " thread interrupted.");
            }
        }
    }

}
