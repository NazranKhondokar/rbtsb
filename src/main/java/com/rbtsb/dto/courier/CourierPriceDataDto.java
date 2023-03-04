package com.rbtsb.dto.courier;

import lombok.Data;

@Data
public class CourierPriceDataDto {

    private Double rate;
    private String code;
    private Integer apiDays;
    private Integer finalDays;
    private String dayString;
    private Integer weekendDays;
    private String message;
}
