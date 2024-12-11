package com.BackendPrac.BackendPrac1.DataTransferObject;
import jakarta.validation.constraints.*;


public class ConfigurationRequest {
    @NotNull(message = "This field is required")
    @Min(value = 1, message = "Minimum total ticket must be one.")
    @Max(value = 100, message = "Maximum total ticket should be one hundred.")
    private int totalTickets;

    @NotNull(message = "This field is required")
    @Min(value = 1, message = "Minimum ticket release rate must be one.")
    @Max(value = 10000, message = "Maximum ticket release rate should be 10000")
    private int ticketReleaseRate;

    @NotNull(message = "This field is required")
    @Min(value = 1, message = "Minimum customer retrieval rate must be one.")
    @Max(value = 10000, message = "Maximum customer retrieval rate should be 10000")
    private int customerRetrievalRate;

    @NotNull(message = "This field is required")
    @Min(value = 1, message = "Minimum max ticket capacity must be one.")
    @Max(value = 100, message = "Maximum max ticket capacity should be one hundred.")
    private int maxTicketCapacity;


    public int getTotalTickets() {
        return totalTickets;
    }
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }


    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }


    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }


    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
