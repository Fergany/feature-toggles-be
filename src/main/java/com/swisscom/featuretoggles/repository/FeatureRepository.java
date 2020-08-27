package com.swisscom.featuretoggles.repository;

import com.swisscom.featuretoggles.model.Customer;
import com.swisscom.featuretoggles.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByIdNotIn(List<Long> featuresId);
}
