package com.CLI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> ticketQueue = new LinkedList<>();
    private final int maxCapacity;
    private static final String TRANSACTION_FILE = "transactions.json";

    public TicketPool(Configuration configuration) {
        this.maxCapacity = configuration.getMaxTicketCapacity();
    }

    /**
     * Adding tickets to the ticket pool by vendors
     * @param ticket - The ticket to be added
     * @param vendorId - Identifier for the vendor
     */
    public synchronized void addTicket(Ticket ticket,String vendorId) {
        while (ticketQueue.size() >= maxCapacity) {
            try {
                System.out.println("Vendor " + vendorId + " is waiting... cannot add ticket. Pool is full.");
                System.out.println();
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + vendorId + " was interrupted.");
                return;
            }
        }
        ticketQueue.offer(ticket);
        System.out.println("Vendor " + vendorId + " added a ticket. "+ "Ticket Details: "+ ticket + " Current tickets: "+getCurrentTicketsCount() );
        System.out.println();
        notifyAll();
        transactions("Vendor " + vendorId," added a ticket" , ticket);


    }

    /**
     * Purchase of tickets from the ticket pool by customers
     * @param customerId - Identifier for the customer
     */
    public synchronized void removeTicket(String customerId) {

        while (ticketQueue.isEmpty()) {
            try {
                System.out.println("Customer " + customerId + " is waiting... found no tickets available. Pool is empty.");
                System.out.println();
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " was interrupted.");
                return;
            }
        }
        Ticket ticket = ticketQueue.poll();
        System.out.println("Customer " + customerId + " purchased a ticket. " + ticket + " Current tickets: "+getCurrentTicketsCount() );
        System.out.println();
        notifyAll();
        transactions("Customer " + customerId, " purchased a ticket", ticket);

    }

    /**
     * take current ticket count in the ticket pool
     * @return current ticket count
     */
    public synchronized int getCurrentTicketsCount() {
        return ticketQueue.size();
    }

    /**
     * store transactions (add tickets, buy tickets) in a JSON file
     * @param user -  vendor Id/ customer  Id
     * @param action - add ticket/ remove ticket
     * @param ticket - the ticket involved in the transaction
     */
    private void transactions(String user, String action, Ticket ticket) {
        JSONObject transaction = new JSONObject();
        transaction.put("User: ", user);
        transaction.put("action: ", action);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd , HH:mm:ss");
        transaction.put("Date & Time: ", LocalDateTime.now().format(dateTimeFormatter));
        transaction.put("Ticket Details: ", ticket.toString());

        JSONArray transactionsLog;
        File logFile = new File(TRANSACTION_FILE);
        try {
            if (logFile.exists() && logFile.length() > 0) {
                String details = new String(Files.readAllBytes(logFile.toPath()));
                transactionsLog = new JSONArray(details);
            }
            else {
                transactionsLog = new JSONArray();
            }

            transactionsLog.put(transaction);

            try (FileWriter writer = new FileWriter(logFile)){
                writer.write(transactionsLog.toString(4));
            }
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }catch (org.json.JSONException e){
            System.out.println("Error handling transactions: " + e.getMessage());
        }


    }


}





