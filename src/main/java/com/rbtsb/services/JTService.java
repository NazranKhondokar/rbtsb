package com.rbtsb.services;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.jt.JTPriceDataDto;

import java.io.IOException;

public interface JTService {

    JTPriceDataDto getCourierPrice(CourierPriceCheckDto courierPriceCheckDto) throws IOException;
}

