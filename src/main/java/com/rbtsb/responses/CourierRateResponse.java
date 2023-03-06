package com.rbtsb.responses;

import com.rbtsb.dto.courier.CourierPriceDataDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourierRateResponse {

    private String courier;
    private Double rate;

    public static CourierRateResponse to(String courierName, CourierPriceDataDto courierPriceDataDto) {
        CourierRateResponse courierRateResponse = new CourierRateResponse();
        courierRateResponse.setCourier(courierName);
        courierRateResponse.setRate(courierPriceDataDto.getRate());
        return courierRateResponse;
    }
}
