package com.rbtsb.services.implementations;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.entities.CourierPrice;
import com.rbtsb.repositories.CourierPriceRepository;
import com.rbtsb.responses.CourierRateResponse;
import com.rbtsb.services.CourierPriceCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierPriceCheckServiceImpl implements CourierPriceCheckService {

    private final CourierPriceRepository courierPriceRepository;
    private final CourierServiceImpl courierService;

    @Override
    public Object[] checkCourierPrice(CourierPriceCheckDto courierPriceCheckDto) {
        CourierPrice courierPrice = courierPriceCheckDto.to();
        courierPriceRepository.save(courierPrice);

        return new Object[]{CourierRateResponse.to(courierService.getCourierPrice(courierPriceCheckDto))};
    }
}
