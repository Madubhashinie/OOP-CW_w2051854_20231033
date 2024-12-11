package com.CLI;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    private final int ticketId;
    private final String eventName;
    private final double ticketPrice;
    private static final AtomicInteger uniqueId = new AtomicInteger(1);

    public Ticket(String eventName, double ticketPrice) {
        this.ticketId = uniqueId.getAndIncrement();
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }
    public int getTicketId() {
        return ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket Number = " + getTicketId() + ", Event Name= " + getEventName()  + ", Ticket Price= " + getTicketPrice() ;
    }
}
