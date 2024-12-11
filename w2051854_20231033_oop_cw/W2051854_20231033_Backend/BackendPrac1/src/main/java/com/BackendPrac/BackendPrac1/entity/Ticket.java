package com.BackendPrac.BackendPrac1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int ticketId;

    private  String eventName;
    private double ticketPrice;



    public Ticket( String eventName, double ticketPrice ) {
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    public Ticket() {

    }


    public int getTicketId() {
        return ticketId;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }



    @Override
    public String toString() {
        return "ticketId= " + ticketId+ ", eventName= " + eventName  + ", ticketPrice= " + ticketPrice  ;
    }
}
