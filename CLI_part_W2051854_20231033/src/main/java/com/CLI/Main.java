package com.CLI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration config = Configuration.loadFromJson();
        config.setValues();

        TicketPool ticketPool = new TicketPool(config);

        Vendor vendor1 = new Vendor(ticketPool, config, "V1");
        Customer customer1 = new Customer(ticketPool, config, "C1");
        Vendor vendor2 = new Vendor(ticketPool, config, "V2");

        Thread vendorThread1 = new Thread(vendor1);
        Thread customerThread1 = new Thread(customer1);
        Thread vendorThread2 = new Thread(vendor2);

        Scanner scanner = new Scanner(System.in);
        System.out.println("**=== Ticket System CLI ===**");
        System.out.println("Commands: start, stop");

        boolean isRunning = false;
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "start":
                    if (!isRunning) {
                        vendorThread1.start();
                        customerThread1.start();
                        vendorThread2.start();
                        isRunning = true;
                        System.out.println("System started.");
                    } else {
                        System.out.println("System is already running.");
                    }
                    break;

                case "stop":
                    if (isRunning) {
                        vendor1.stop();
                        customer1.stop();
                        vendor2.stop();

                        // Wait for threads to finish execution
                        try {
                            vendorThread1.join();
                            customerThread1.join();
                            vendorThread2.join();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Error stopping threads: " + e.getMessage());
                        }

                        System.out.println("System stopped.");
                    }
                    config.setTotalTickets(ticketPool.getCurrentTicketsCount());
                    config.saveToJson();
                    System.out.println("Thanks for using our Ticket Management System!");
                    return;

                default:
                    System.out.println("Invalid command! Use 'start' or 'stop'.");
            }
        }
    }
}

