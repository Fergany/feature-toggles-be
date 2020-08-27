package com.swisscom.featuretoggles.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "CUSTOMERS_FEATURES")
public class CustomerFeature {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "FEATURE_ID")
    private Feature feature;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "ASSIGNED_AT", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern="MM/dd/yyyy")
    private LocalDate assignedAt;

    public CustomerFeature(Customer customer, Feature feature, boolean active) {
        this.customer = customer;
        this.feature = feature;
        this.active = active;
    }
}
