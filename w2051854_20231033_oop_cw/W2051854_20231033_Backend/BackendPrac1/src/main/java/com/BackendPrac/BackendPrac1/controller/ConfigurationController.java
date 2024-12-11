package com.BackendPrac.BackendPrac1.controller;


import com.BackendPrac.BackendPrac1.DataTransferObject.ConfigurationRequest;
import com.BackendPrac.BackendPrac1.entity.Configuration;
import com.BackendPrac.BackendPrac1.service.ConfigurationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/configuration")
public class ConfigurationController {
   private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Retrieves the current system configuration from the database.
     * @return ResponseEntity containing the current Configuration object
     */
    @GetMapping
    public ResponseEntity<Configuration> getConfigurations() {
        Configuration configuration = configurationService.getConfiguration();
        return ResponseEntity.ok(configuration);
    }

    /**
     * Updates the system configuration
     * @param configurationRequest The configuration request
     * @return ResponseEntity with a success or error message
     */
    @PostMapping
    public ResponseEntity<String> addConfiguration(@Valid @RequestBody ConfigurationRequest configurationRequest) {
        if (configurationRequest.getMaxTicketCapacity() < configurationRequest.getTotalTickets()) {
            return ResponseEntity.badRequest().body("Max ticket capacity cannot be less than total tickets.");
        }

        configurationService.setConfigurations(configurationRequest);
        return ResponseEntity.ok("Configuration complete!");
    }


}

