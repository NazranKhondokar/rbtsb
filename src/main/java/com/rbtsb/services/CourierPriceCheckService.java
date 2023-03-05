package com.rbtsb.services;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.entities.CourierPrice;

public interface CourierPriceCheckService {

    Object[] checkCourierPrice(CourierPriceCheckDto courierPriceCheckDto);
}

