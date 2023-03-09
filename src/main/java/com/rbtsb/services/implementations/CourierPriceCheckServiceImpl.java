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

import static java.util.Objects.isNull;
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

        List<CourierPrice> courierPrices = getCourierPrices(courierPriceCheckDto);
        logger.info("courierPrices {}", courierPrices);

        CourierPrice courierPrice = courierPriceCheckDto.to();

        if (courierPrices.isEmpty()) {
            logger.info("courierPrices is empty");
            getCityLinkRate(courierPriceCheckDto, courierRateResponses, courierPrice);
            getJTRate(courierPriceCheckDto, courierRateResponses, courierPrice);
            courierPriceRepository.save(courierPrice);
        } else {
            logger.info("courierPrices is not empty");
            if (isNull(courierPrices.get(0).getCityLinkRate()))
                getCityLinkRate(courierPriceCheckDto, courierRateResponses, courierPrice);
            else courierRateResponses.add(CourierRateResponse.to("citylink", courierPrices.get(0).getCityLinkRate()));

            if (isNull(courierPrices.get(0).getJtRate()))
                getJTRate(courierPriceCheckDto, courierRateResponses, courierPrice);
            else courierRateResponses.add(CourierRateResponse.to("jt", courierPrices.get(0).getJtRate()));
        }
        return courierRateResponses;
    }

    private List<CourierPrice> getCourierPrices(CourierPriceCheckDto courierPriceCheckDto) {
        // TODO I wanna check duplicate API call via redis but time is up ...
        // it's a dummy check
        return courierPriceRepository.search(
                courierPriceCheckDto.getOriginPostcode(),
                courierPriceCheckDto.getDestinationCountry(),
                courierPriceCheckDto.getDestinationPostcode(),
                courierPriceCheckDto.getWidth(),
                courierPriceCheckDto.getHeight(),
                courierPriceCheckDto.getParcelWeight());
    }

    private void getCityLinkRate(CourierPriceCheckDto courierPriceCheckDto, List<CourierRateResponse> courierRateResponses, CourierPrice courierPrice) {
        CityLinkPriceDataDto cityLinkPriceDataDto = cityLinkService.getCourierPrice(courierPriceCheckDto);

        if (nonNull(cityLinkPriceDataDto)) {
            logger.info("CityLink express rate is " + cityLinkPriceDataDto.getRate());
            courierPrice.setCityLinkRate(cityLinkPriceDataDto.getRate());
            courierRateResponses.add(CourierRateResponse.to("citylink", cityLinkPriceDataDto.getRate()));
        } else logger.warn("CityLink express data is null");
    }

    private void getJTRate(CourierPriceCheckDto courierPriceCheckDto, List<CourierRateResponse> courierRateResponses, CourierPrice courierPrice) {
        try {
            JTPriceDataDto jtPriceDataDto = jtService.getCourierPrice(courierPriceCheckDto);
            if (nonNull(jtPriceDataDto.getTotalRate())) {
                courierPrice.setJtRate(jtPriceDataDto.getTotalRate());
                courierRateResponses.add(CourierRateResponse.to("jt", jtPriceDataDto.getTotalRate()));
            } else logger.warn("JT data is null");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
