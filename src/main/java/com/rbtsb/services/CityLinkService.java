package com.rbtsb.services;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.citylink.CityLinkPriceDataDto;

public interface CityLinkService {

    /**
     * @param courierPriceCheckDto which provide source, destination and package info.
     * @return courier rate of city link with additional data
     */
    CityLinkPriceDataDto getCourierPrice(CourierPriceCheckDto courierPriceCheckDto);
}

