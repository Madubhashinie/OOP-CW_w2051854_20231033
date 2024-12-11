package com.CLI;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    private final Scanner scanner = new Scanner(System.in);
    private static final String CONFIG_FILE = "config.json";

    /**
     * Prompts the user to input system configurations
     */
    public void setValues() {

        System.out.println("**==== Ticket System Configuration ====**");
        if(totalTickets == 0) {
            totalTickets = promptForValidInput("Enter total tickets: ", 1, 100);
        }
        maxTicketCapacity = promptForValidInput( "Enter max ticket capacity: ", totalTickets,100);
        ticketReleaseRate = promptForValidInput("Enter ticket release rate (ms): ", 1, 10000);
        customerRetrievalRate = promptForValidInput("Enter customer retrieval rate (ms): ", 1, 10000);
        saveToJson();
        System.out.println("Configuration complete!");
    }

    /**
     * To perform validation
     * @param prompt - the message displayed to the user
     * @param minVal - the minimum required value
     * @param maxVal - the maximum possible value
     * @return - a valid integer
     */
    private int promptForValidInput( String prompt, int minVal, int maxVal) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                int value = Integer.parseInt(input);
                if (value >= minVal && value <= maxVal) {
                    return value;
                }else {
                    System.out.println("Invalid input! Value must be between " + minVal + " and " + maxVal + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    /**
     * Save the current system configuration to a JSON file
     */
    public void saveToJson(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalTickets", totalTickets);
        jsonObject.put("ticketReleaseRate", ticketReleaseRate);
        jsonObject.put("customerRetrievalRate", customerRetrievalRate);
        jsonObject.put("maxTicketCapacity", maxTicketCapacity);

        try (FileWriter file = new FileWriter(CONFIG_FILE)) {
            file.write(jsonObject.toString(4));
            System.out.println("Configuration saved to " + CONFIG_FILE);
        }catch (IOException e){
            System.out.println("Error saving configuration: " + e.getMessage());
        }

    }

    /**
     * Loads configurations from the JSON file
     * @return - configuration object
     */
    public static Configuration loadFromJson(){
        Configuration configuration = new Configuration();
        File file = new File(CONFIG_FILE);
        if(!file.exists()){
            System.out.println("Configuration file not found.");
            return configuration;
        }
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            JSONObject json = new JSONObject(content);

            configuration.totalTickets = json.getInt("totalTickets");
            configuration.ticketReleaseRate = json.getInt("ticketReleaseRate");
            configuration.customerRetrievalRate = json.getInt("customerRetrievalRate");
            configuration.maxTicketCapacity = json.getInt("maxTicketCapacity");
            System.out.println("Configuration loaded from JSON.");

        }catch (Exception e){
            System.out.println("Error reading configuration: " + e.getMessage());
        }
        return configuration;
    }

    public int getTotalTickets() {
        return totalTickets;
    }
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}


