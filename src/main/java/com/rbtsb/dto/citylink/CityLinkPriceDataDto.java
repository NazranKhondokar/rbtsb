package com.rbtsb.dto.citylink;

import lombok.Data;

@Data
public class CityLinkPriceDataDto {

    private Double rate;
    private String code;
    private Integer apiDays;
    private Integer finalDays;
    private String dayString;
    private Integer weekendDays;
    private String message;
}
