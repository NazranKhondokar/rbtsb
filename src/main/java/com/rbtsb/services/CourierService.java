package com.rbtsb.services;

import com.rbtsb.clients.CourierClient;
import com.rbtsb.dto.courier.CourierPriceDataDto;
import com.rbtsb.dto.courier.CourierResponseDto;
import com.rbtsb.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierClient courierClient;

    public CourierPriceDataDto getCourierPrice() {
        CourierResponseDto courierResponseDto = courierClient.getCourierPrice(
                "MY", "Kuala Lumpur", 50250, "BD",
                "Bangladesh", 50000, 10, 10, 10,
                1, 10, 0);
        if (courierResponseDto.getReq().getStatus() == 200)
            return courierResponseDto.getReq().getData();
        else throw new ResourceNotFoundException(String.valueOf(courierResponseDto.getReq().getStatus()));
    }
}

