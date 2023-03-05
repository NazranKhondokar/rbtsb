package com.rbtsb.dto;

import com.rbtsb.entities.CourierPrice;
import lombok.Data;

@Data
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
