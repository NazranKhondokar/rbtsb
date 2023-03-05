package com.rbtsb.services.implementations;

import com.rbtsb.clients.CourierClient;
import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.courier.CourierPriceDataDto;
import com.rbtsb.dto.courier.CourierResponseDto;
import com.rbtsb.exceptions.ResourceNotFoundException;
import com.rbtsb.services.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final CourierClient courierClient;

    @Override
    public CourierPriceDataDto getCourierPrice(CourierPriceCheckDto courierPriceCheckDto) {
        CourierResponseDto courierResponseDto = courierClient.getCourierPrice(
                courierPriceCheckDto.getOriginCountry(),
                courierPriceCheckDto.getOriginState(),
                courierPriceCheckDto.getOriginPostcode(),
                courierPriceCheckDto.getDestinationCountry(),
                courierPriceCheckDto.getDestinationState(),
                courierPriceCheckDto.getDestinationPostcode(),
                courierPriceCheckDto.getLength(),
                courierPriceCheckDto.getWidth(),
                courierPriceCheckDto.getHeight(),
                courierPriceCheckDto.getSelectedType(),
                courierPriceCheckDto.getParcelWeight(),
                courierPriceCheckDto.getDocumentWeight());

        if (courierResponseDto.getReq().getStatus() == 200)
            return courierResponseDto.getReq().getData();
        else throw new ResourceNotFoundException(String.valueOf(courierResponseDto.getReq().getStatus()));
    }
}
