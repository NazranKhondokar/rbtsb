package com.rbtsb.services;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.courier.CourierPriceDataDto;

public interface CourierService {

    CourierPriceDataDto getCourierPrice(CourierPriceCheckDto courierPriceCheckDto);
}
