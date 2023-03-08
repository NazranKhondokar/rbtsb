package com.rbtsb.clients;

import com.rbtsb.config.FeignConfig;
import com.rbtsb.dto.citylink.CourierResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CityLinkClient", configuration = FeignConfig.class, url = "${cityLinkService}", decode404 = true)
public interface CityLinkClient {

    @RequestMapping(method = RequestMethod.POST, value = "wp-json/wp/v2/getShippingRate")
    CourierResponseDto getCourierPrice(@RequestParam("origin_country") String origin_country,
                                       @RequestParam("origin_state") String origin_state,
                                       @RequestParam("origin_postcode") int origin_postcode,
                                       @RequestParam("destination_country") String destination_country,
                                       @RequestParam("destination_state") String destination_state,
                                       @RequestParam("destination_postcode") int destination_postcode,
                                       @RequestParam("length") double length,
                                       @RequestParam("width") double width,
                                       @RequestParam("height") double height,
                                       @RequestParam("selected_type") int selected_type,
                                       @RequestParam("parcel_weight") double parcel_weight,
                                       @RequestParam("document_weight") double document_weight);
}
