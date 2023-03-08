package com.rbtsb.services;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.citylink.CityLinkPriceDataDto;

public interface CityLinkService {

    CityLinkPriceDataDto getCourierPrice(CourierPriceCheckDto courierPriceCheckDto);
}

