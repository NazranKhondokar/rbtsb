package com.rbtsb.services.implementations;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.courier.CourierPriceDataDto;
import com.rbtsb.entities.CourierPrice;
import com.rbtsb.repositories.CourierPriceRepository;
import com.rbtsb.services.CourierPriceCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierPriceCheckServiceImpl implements CourierPriceCheckService {

    private final CourierPriceRepository courierPriceRepository;
    private final CourierServiceImpl courierService;

    @Override
    public CourierPrice checkCourierPrice(CourierPriceCheckDto courierPriceCheckDto) {
        CourierPriceDataDto courierPriceDataDto = courierService.getCourierPrice(courierPriceCheckDto);

        CourierPrice courierPrice = courierPriceCheckDto.to();
        courierPrice.setRate(courierPriceDataDto.getRate());

        return courierPriceRepository.save(courierPrice);
    }
}
