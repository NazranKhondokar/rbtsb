package com.rbtsb.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COURIER_PRICE")
public class CourierPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURIER_PRICE_ID")
    private Long id;

    @Column(name = "ORIGIN_COUNTRY")
    private String originCountry;

    @Column(name = "ORIGIN_STATE")
    private String originState;

    @Column(name = "ORIGIN_POSTCODE")
    private Integer originPostcode;

    @Column(name = "DESTINATION_COUNTRY")
    private String destinationCountry;

    @Column(name = "DESTINATION_STATE")
    private String destinationState;

    @Column(name = "DESTINATION_POSTCODE")
    private Integer destinationPostcode;

    @Column(name = "LENGTH")
    private Double length;

    @Column(name = "WIDTH")
    private Double width;

    @Column(name = "HEIGHT")
    private Double height;

    @Column(name = "SELECTED_TYPE")
    private Integer selectedType;

    @Column(name = "PARCEL_WEIGHT")
    private Double parcelWeight;

    @Column(name = "DOCUMENT_WEIGHT")
    private Double documentWeight;

    @Column(name = "RATE")
    private Double rate;
}