package com.rbtsb.services.implementations;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.courier.CourierPriceDataDto;
import com.rbtsb.entities.CourierPrice;
import com.rbtsb.repositories.CourierPriceRepository;
import com.rbtsb.responses.CourierRateResponse;
import com.rbtsb.services.CourierPriceCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourierPriceCheckServiceImpl implements CourierPriceCheckService {

    private final CourierPriceRepository courierPriceRepository;
    private final CourierServiceImpl courierService;

    @Override
    public List<CourierRateResponse> checkCourierPrice(CourierPriceCheckDto courierPriceCheckDto) {

        List<CourierRateResponse> courierRateResponses = new ArrayList<>();
        CourierPriceDataDto courierPriceDataDto = courierService.getCourierPrice(courierPriceCheckDto);

        CourierPrice courierPrice = courierPriceCheckDto.to();

        if (Objects.nonNull(courierPriceDataDto)) {
            courierPrice.setCityLinkRate(courierPriceDataDto.getRate());
            courierRateResponses.add(CourierRateResponse.to("citylink", courierPriceDataDto));
        }
        courierPriceRepository.save(courierPrice);
        return courierRateResponses;
    }
}
