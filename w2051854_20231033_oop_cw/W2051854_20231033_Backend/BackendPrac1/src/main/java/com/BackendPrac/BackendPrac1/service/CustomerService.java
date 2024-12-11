package com.BackendPrac.BackendPrac1.service;

public class CustomerService implements  Runnable{
    private final TicketPool ticketPool;
    private final int retrievalRate;
    private final String customerId;
    private boolean isRunning = true;

    public CustomerService(TicketPool ticketPool,ConfigurationService configurationService, String customerId) {
        this.ticketPool = ticketPool;
        this.retrievalRate = configurationService.getConfiguration().getCustomerRetrievalRate();
        this.customerId = customerId;
    }

    /**
     * Stops the ticket purchase process
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * continuously removes tickets from the ticket pool at the retrieval rate interval
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
