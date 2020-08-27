package com.swisscom.featuretoggles.repository;

import com.swisscom.featuretoggles.model.Customer;
import com.swisscom.featuretoggles.model.CustomerFeature;
import com.swisscom.featuretoggles.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerFeatureRepository extends JpaRepository<CustomerFeature, Long> {
    List<CustomerFeature> findByCustomer(Customer customer);
}
