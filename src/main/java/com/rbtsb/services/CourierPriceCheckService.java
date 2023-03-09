package com.rbtsb.services;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.entities.CourierPrice;
import com.rbtsb.responses.CourierRateResponse;

import java.util.List;

public interface CourierPriceCheckService {

    /**
     * @param courierPriceCheckDto which provide source, destination and package info.
     * @return list of courier rate or empty
     */
    List<CourierRateResponse> checkCourierPrice(CourierPriceCheckDto courierPriceCheckDto);
}

