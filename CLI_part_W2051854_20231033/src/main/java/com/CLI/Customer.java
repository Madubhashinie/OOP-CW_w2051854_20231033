package com.CLI;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRate;
    private final String customerId;
    private boolean isRunning = true;

    public Customer(TicketPool ticketPool, Configuration configuration, String customerId) {
        this.ticketPool = ticketPool;
        this.retrievalRate = configuration.getCustomerRetrievalRate();
        this.customerId = customerId;
    }

    /**
     * to stop customer
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * remove tickets from the pool at a fixed customer retrieval rate
     */
    @Override
    public void run() {
        while (isRunning) {
            ticketPool.removeTicket(customerId);
            try {
                Thread.sleep(retrievalRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " thread interrupted.");
            }
        }
    }
}




