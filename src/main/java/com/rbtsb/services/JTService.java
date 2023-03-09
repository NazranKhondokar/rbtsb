package com.rbtsb.services;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.jt.JTPriceDataDto;

import java.io.IOException;

public interface JTService {

    /**
     * This is the main method which makes use of addNum method.
     * @param courierPriceCheckDto which provide source, destination and package info.
     * @return a shippingRate and totalRate object of JT.
     * @exception IOException On input error.
     * @see IOException
     */
    JTPriceDataDto getCourierPrice(CourierPriceCheckDto courierPriceCheckDto) throws IOException;
}

