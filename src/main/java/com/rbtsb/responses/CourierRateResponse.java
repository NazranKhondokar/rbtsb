package com.rbtsb.responses;

import com.rbtsb.dto.citylink.CityLinkPriceDataDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourierRateResponse {

    private String courier;
    private Double rate;

    public static CourierRateResponse to(String courierName, Double rate) {
        CourierRateResponse courierRateResponse = new CourierRateResponse();
        courierRateResponse.setCourier(courierName);
        courierRateResponse.setRate(rate);
        return courierRateResponse;
    }
}
