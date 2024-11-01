package com.W2051854_20231033;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Configuration implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Configuration.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TicketPool ticketPool = new TicketPool(10); // Maximum size of the ticket pool

        // Create and start vendor threads
        Thread vendor1 = new Thread(new Vendor(1, 5, 1000, ticketPool)); // Vendor 1 releases 5 tickets every second
        Thread vendor2 = new Thread(new Vendor(2, 5, 800, ticketPool));  // Vendor 2 releases 5 tickets every 0.8 seconds

        vendor1.start();
        vendor2.start();

        // Optionally join threads to ensure the main thread waits for their completion
        vendor1.join();
        vendor2.join();
    }
}

