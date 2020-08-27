package com.swisscom.featuretoggles.service;

import com.swisscom.featuretoggles.model.Customer;
import com.swisscom.featuretoggles.model.CustomerFeature;
import com.swisscom.featuretoggles.model.Feature;
import com.swisscom.featuretoggles.repository.CustomerFeatureRepository;
import com.swisscom.featuretoggles.repository.CustomerRepository;
import com.swisscom.featuretoggles.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeatureService {

    private FeatureRepository featureRepository;
    private CustomerRepository customerRepository;
    private CustomerFeatureRepository customerFeatureRepository;

    @Autowired
    public FeatureService(FeatureRepository featureRepository, CustomerRepository customerRepository, CustomerFeatureRepository customerFeatureRepository) {
        this.featureRepository = featureRepository;
        this.customerRepository = customerRepository;
        this.customerFeatureRepository = customerFeatureRepository;
    }

   public Feature add(Feature feature){
        return featureRepository.save(feature);
    }

   public Feature edit(Feature updatedFeature, Long id) {
        return featureRepository.findById(id)
                .map(feature -> {
                    feature.setDisplayName(updatedFeature.getDisplayName());
                    feature.setTechnicalName(updatedFeature.getTechnicalName());
                    feature.setExpiresOn(updatedFeature.getExpiresOn());
                    feature.setInverted(updatedFeature.isInverted());
                    return featureRepository.save(updatedFeature);
                })
                .orElseGet(() -> {
                    updatedFeature.setId(id);
                    return featureRepository.save(updatedFeature);
                });
    }

    public List<Feature> getAll(){
        return featureRepository.findAll();
    }

    public List<CustomerFeature> getAllFeaturesByCustomerId(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer " + id + " Not found"));
        return customerFeatureRepository.findByCustomer(customer);
    }

    public List<Feature> getUnassignedFeaturesByCustomerId(long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer " + customerId + " Not found"));
        List<Long> featuresId = new ArrayList();
        customerFeatureRepository.findByCustomer(customer)
                .forEach(customerFeature -> {
                    featuresId.add(customerFeature.getFeature().getId());
                });
        return featureRepository.findByIdNotIn(featuresId);
    }

    public CustomerFeature assignFeature(long customerId, long featureId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer " + customerId + " Not found"));

        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature " + featureId + " Not found"));

        CustomerFeature customerFeature = new CustomerFeature(customer, feature, true);

        return customerFeatureRepository.save(customerFeature);
    }
}
