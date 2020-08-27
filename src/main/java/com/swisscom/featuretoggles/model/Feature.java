package com.swisscom.featuretoggles.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "FEATURES")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "DISPLAY_NAME")
    String displayName;

    @Column(name = "TECHNICAL_NAME")
    String technicalName;

    @Column(name = "EXPIRES_ON")
    LocalDate expiresOn;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "INVERTED")
    boolean inverted;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "CUSTOMERS_FEATURES",
//            joinColumns = {
//                    @JoinColumn(name = "FEATURE_ID", referencedColumnName = "id",
//                            nullable = false, updatable = false)},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "id",
//                            nullable = false, updatable = false)})
//    private List<Customer> customers;

    public Feature(String displayName, String technicalName, LocalDate expiresOn, String description, boolean inverted) {
        this.displayName = displayName;
        this.technicalName = technicalName;
        this.expiresOn = expiresOn;
        this.description = description;
        this.inverted = inverted;
    }
}
