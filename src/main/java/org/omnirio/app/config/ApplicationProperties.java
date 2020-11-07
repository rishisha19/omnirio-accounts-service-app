package org.omnirio.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.net.URI;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private String customerServiceRegistryName;

    private String customerServiceUrl;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    @Autowired
    public URI getCustomerServiceUrl (String customerServiceRegistryName) {
        return this.discoveryClient.getInstances(customerServiceRegistryName).get(0).getUri();
    }

    @Autowired
    public void setCustomerServiceRegistryName (String customerServiceRegistryName) {
        this.customerServiceRegistryName = customerServiceRegistryName;
    }
}
