package com.BackendPrac.BackendPrac1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class TicketTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    private String user;
    private String action;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime dateTime = LocalDateTime.now();


    @ManyToOne
    private Ticket ticketDetails;

    public TicketTransactions() {}

    public TicketTransactions(String user, String action, Ticket ticketDetails) {
        this.ticketDetails = ticketDetails;
        this.user = user;
        this.action = action;

    }
    public int getTransactionID() {
        return transactionID;
    }
    public String getUser() {
        return user;
    }
    public String getAction() {
        return action;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }


    @Override
    public String toString() {
        return "Transactions  =>  transactionID= " + transactionID + ", user= " + user  + ", action= " + action + ", dateTime= " + dateTime ;
    }
}
