package com.W2051854_20231033;

public class Vendor implements  Runnable{
    private int vendorId;
    private int ticketsPerRelease;
    private long releaseInterval;
    private TicketPool ticketPool;

    public Vendor(int vendorId, int ticketsPerRelease, long releaseInterval, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < ticketsPerRelease; i++) {
                String ticket = "Vendor: "+vendorId + ", Ticket: " + (i+1);
                ticketPool.addTicket(ticket);
                Thread.sleep(releaseInterval);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
            //System.out.println("Vendor " + vendorId + " was interrupted.");

        }
    }
}
