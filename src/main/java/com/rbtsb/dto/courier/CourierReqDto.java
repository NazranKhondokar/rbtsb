package com.rbtsb.dto.courier;

import lombok.Data;

import java.util.List;

@Data
public class CourierReqDto {

    private CourierPriceDataDto data;
    private List<Object> headers;
    private Integer status;
}