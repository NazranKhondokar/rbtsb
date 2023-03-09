package com.rbtsb.services.implementations;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.citylink.CityLinkPriceDataDto;
import com.rbtsb.dto.jt.JTPriceDataDto;
import com.rbtsb.entities.CourierPrice;
import com.rbtsb.repositories.CourierPriceRepository;
import com.rbtsb.responses.CourierRateResponse;
import com.rbtsb.services.CourierPriceCheckService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CourierPriceCheckServiceImpl implements CourierPriceCheckService {

    private final CourierPriceRepository courierPriceRepository;
    private final CityLinkServiceImpl cityLinkService;
    private final JTServiceImpl jtService;
    Logger logger = LoggerFactory.getLogger(CourierPriceCheckServiceImpl.class);

    @Override
    public List<CourierRateResponse> checkCourierPrice(CourierPriceCheckDto courierPriceCheckDto) {

        List<CourierRateResponse> courierRateResponses = new ArrayList<>();
        CityLinkPriceDataDto cityLinkPriceDataDto = cityLinkService.getCourierPrice(courierPriceCheckDto);

        CourierPrice courierPrice = courierPriceCheckDto.to();

        if (nonNull(cityLinkPriceDataDto)) {
            logger.info("CityLink express rate is " + cityLinkPriceDataDto.getRate());
            courierPrice.setCityLinkRate(cityLinkPriceDataDto.getRate());
            courierRateResponses.add(CourierRateResponse.to("citylink", cityLinkPriceDataDto.getRate()));
        } else logger.warn("CityLink express data is null");

        try {
            JTPriceDataDto jtPriceDataDto = jtService.getCourierPrice(courierPriceCheckDto);
            if (nonNull(jtPriceDataDto.getTotalRate())) {
                courierPrice.setJtRate(jtPriceDataDto.getTotalRate());
                courierRateResponses.add(CourierRateResponse.to("jt", jtPriceDataDto.getTotalRate()));
            } else logger.warn("JT data is null");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        courierPriceRepository.save(courierPrice);
        return courierRateResponses;
    }
}
