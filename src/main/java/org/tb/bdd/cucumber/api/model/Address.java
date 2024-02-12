package org.tb.bdd.cucumber.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int streetNumber;
    private String streetName;
    private String suburb;
    private String postcode;
    private String city;
    private String state;
}
