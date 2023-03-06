package com.rbtsb.services;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.entities.CourierPrice;
import com.rbtsb.responses.CourierRateResponse;

import java.util.List;

public interface CourierPriceCheckService {

    List<CourierRateResponse> checkCourierPrice(CourierPriceCheckDto courierPriceCheckDto);
}

