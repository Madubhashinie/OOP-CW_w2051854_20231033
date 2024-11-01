package com.W2051854_20231033;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private List<String> ticketList;
    private int ticketListSize;

    public TicketPool(int ticketListSize) {
        this.ticketListSize = ticketListSize;
        ticketList = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Add tickets to the pool
     * @param ticket
     * @throws InterruptedException
     */
    public void addTicket(String ticket) throws InterruptedException {
        synchronized (ticketList) {
           while (ticketList.size() >= ticketListSize) {
               ticketList.wait();
           }
            ticketList.add(ticket);
            System.out.println("Ticket added: " + ticket);
           ticketList.notify();
        }

    }

    /**
     * Remove ticket from the pool
     * @param ticket
     * @throws InterruptedException
     */
    public void removeTicket(String ticket) throws InterruptedException {
        synchronized (ticketList) {
            while (ticketList.isEmpty()) {
                ticketList.wait();
            }
            if(ticketList.remove(ticket)) {
                System.out.println("Ticket removed: " + ticket);
                ticketList.notifyAll();
            }else {
                System.out.println("Ticket not removed: " + ticket);
            }

        }

    }

}
