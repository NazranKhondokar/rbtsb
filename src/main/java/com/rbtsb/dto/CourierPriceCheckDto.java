package com.rbtsb.dto;

import com.rbtsb.entities.CourierPrice;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CourierPriceCheckDto {

    private String originCountry;
    private String originState;
    private Integer originPostcode;
    private String destinationCountry;
    private String destinationState;
    private Integer destinationPostcode;
    private Double length;
    private Double width;
    private Double height;
    private Integer selectedType;
    private Double parcelWeight;
    private Double documentWeight;

    public CourierPriceCheckDto(String originCountry, String originState, int originPostcode,
                                String destinationCountry, String destinationState, int destinationPostcode,
                                Double length, Double width, Double height,
                                int selectedType, Double parcelWeight, Double documentWeight) {
        this.originCountry = originCountry;
        this.originState = originState;
        this.originPostcode = originPostcode;
        this.destinationCountry = destinationCountry;
        this.destinationState = destinationState;
        this.destinationPostcode = destinationPostcode;
        this.length = length;
        this.width = width;
        this.height = height;
        this.selectedType = selectedType;
        this.parcelWeight = parcelWeight;
        this.documentWeight = documentWeight;
    }

    public CourierPrice to() {
        CourierPrice courierPrice = new CourierPrice();
        courierPrice.setOriginCountry(originCountry);
        courierPrice.setOriginState(originState);
        courierPrice.setOriginPostcode(originPostcode);
        courierPrice.setDestinationCountry(destinationCountry);
        courierPrice.setDestinationState(destinationState);
        courierPrice.setDestinationPostcode(destinationPostcode);
        courierPrice.setLength(length);
        courierPrice.setWidth(width);
        courierPrice.setHeight(height);
        courierPrice.setSelectedType(selectedType);
        courierPrice.setParcelWeight(parcelWeight);
        courierPrice.setDocumentWeight(documentWeight);
        return courierPrice;
    }
}
