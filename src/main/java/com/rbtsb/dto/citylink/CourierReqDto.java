package com.rbtsb.dto.citylink;

import lombok.Data;

import java.util.List;

@Data
public class CourierReqDto {

    private CityLinkPriceDataDto data;
    private List<Object> headers;
    private Integer status;
}
