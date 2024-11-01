package com.W2051854_20231033;

public class Customer implements Runnable {
    private  int customerId;
    private long retrievalInterval;
    private TicketPool ticketPool;

    public Customer(int customerId, long retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {

    }
}
