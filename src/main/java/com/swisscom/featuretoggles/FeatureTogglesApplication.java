package com.swisscom.featuretoggles;

import com.swisscom.featuretoggles.model.Customer;
import com.swisscom.featuretoggles.model.CustomerFeature;
import com.swisscom.featuretoggles.model.Feature;
import com.swisscom.featuretoggles.repository.CustomerFeatureRepository;
import com.swisscom.featuretoggles.repository.CustomerRepository;
import com.swisscom.featuretoggles.repository.FeatureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
public class FeatureTogglesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeatureTogglesApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CustomerRepository customerRepository
			, FeatureRepository featureRepository
			, CustomerFeatureRepository customerFeatureRepository){
		return args -> {
			Customer customer1 = new Customer("Lukas");
			Customer customer2 = new Customer("Hazem");
			customerRepository.saveAll(Arrays.asList(customer1, customer2));

			Feature feature1 = new Feature("Feature 1", "show_warning", LocalDate.now().plusDays(10), "show warning screen", false);
			Feature feature2 = new Feature("Feature 2", "switch_on", LocalDate.now().plusDays(5), "switch on light", true);
			Feature feature3 = new Feature("Feature 3", "switch_off", LocalDate.now().minusDays(3), "switch off light", false);

			featureRepository.saveAll(Arrays.asList(feature1, feature2, feature3));

			CustomerFeature customerFeature1 = new CustomerFeature(customer1, feature1, true);
			CustomerFeature customerFeature2 = new CustomerFeature(customer1, feature2, true);
			CustomerFeature customerFeature3 = new CustomerFeature(customer2, feature1, true);
			CustomerFeature customerFeature4 = new CustomerFeature(customer2, feature2, true);
			CustomerFeature customerFeature5 = new CustomerFeature(customer2, feature3, false);

			customerFeatureRepository.saveAll(Arrays.asList(customerFeature1, customerFeature2, customerFeature3, customerFeature4, customerFeature5));
		};
	}
}
