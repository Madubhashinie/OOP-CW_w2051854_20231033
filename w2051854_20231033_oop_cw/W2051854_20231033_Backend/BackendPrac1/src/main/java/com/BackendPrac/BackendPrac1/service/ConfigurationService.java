package com.BackendPrac.BackendPrac1.service;

import com.BackendPrac.BackendPrac1.DataTransferObject.ConfigurationRequest;
import com.BackendPrac.BackendPrac1.entity.Configuration;
import com.BackendPrac.BackendPrac1.repository.ConfigurationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


@Service
public class ConfigurationService {
  private final ConfigurationRepository configurationRepository;
  private Configuration configuration;

  public ConfigurationService(ConfigurationRepository configurationRepository) {
      this.configurationRepository = configurationRepository;
  }

    /**
     * Initializes the configuration when the app starts
     */
    @PostConstruct
    public void initializeConfiguration() {
        configuration = configurationRepository.findById(1).orElse(new Configuration());
    }

    /**
     * Sets and saves the system configuration and updates the cache
     * @param configurationRequest  Request object containing new configuration values
     */
   public void  setConfigurations(ConfigurationRequest configurationRequest) {
       configuration = configurationRepository.findById(1).orElse(new Configuration());

       configuration.setTotalTickets(configurationRequest.getTotalTickets());
       configuration.setMaxTicketCapacity(configurationRequest.getMaxTicketCapacity());
       configuration.setTicketReleaseRate(configurationRequest.getTicketReleaseRate());
       configuration.setCustomerRetrievalRate(configurationRequest.getCustomerRetrievalRate());

       configurationRepository.save(configuration);
   }

    /**
     * Get the cached configuration or fetch from DB if not present
     * @return The current system configuration
     */
   public Configuration getConfiguration() {
       if (configuration == null) {
           configuration = configurationRepository.findById(1).orElse(new Configuration());
       }
       return configuration;
   }

}
