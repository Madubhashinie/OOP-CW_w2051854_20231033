package com.CLI;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;
    private final String vendorId;
    private boolean isRunning = true;


    public Vendor(TicketPool ticketPool, Configuration configuration, String vendorId) {
        this.ticketPool = ticketPool;
        this.releaseRate = configuration.getTicketReleaseRate();
        this.vendorId = vendorId;
    }

    /**
     * to stop vendor
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * add tickets to the pool at a fixed ticket release rate
     */
    @Override
    public void run() {
        while (isRunning) {
            Ticket ticket = new Ticket("Cinema Event", 250.0);
            ticketPool.addTicket(ticket,vendorId);
            try {
                Thread.sleep(releaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + vendorId + " thread interrupted.");
            }
        }
    }
}





