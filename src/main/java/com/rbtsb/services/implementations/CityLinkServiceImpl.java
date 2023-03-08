package com.rbtsb.services.implementations;

import com.rbtsb.clients.CityLinkClient;
import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.dto.citylink.CityLinkPriceDataDto;
import com.rbtsb.dto.citylink.CourierResponseDto;
import com.rbtsb.services.CityLinkService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CityLinkServiceImpl implements CityLinkService {

    private final CityLinkClient cityLinkClient;
    private static final Logger logger = LoggerFactory.getLogger(CityLinkServiceImpl.class);

    @Override
    public CityLinkPriceDataDto getCourierPrice(CourierPriceCheckDto courierPriceCheckDto) {
        CourierResponseDto courierResponseDto = cityLinkClient.getCourierPrice(
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

        if (courierResponseDto.getReq().getStatus() == 200) {
            if (isNull(courierResponseDto.getReq())) {
                logger.error("CityLink express request is null");
                return null;
            } else return courierResponseDto.getReq().getData();
        } else {
            logger.error("CityLink express data not found");
            return null;
        }
//        else throw new ResourceNotFoundException(String.valueOf(courierResponseDto.getReq().getStatus()));
    }
}
