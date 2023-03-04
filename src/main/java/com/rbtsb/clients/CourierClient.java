package com.rbtsb.clients;

import com.rbtsb.config.FeignConfig;
import com.rbtsb.dto.courier.CourierResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "CourierClient", configuration = FeignConfig.class, url = "${courierService}", decode404 = true)
public interface CourierClient {

    @GetMapping("api/v1/approve-review-config/find/forAppEntityId/{forAppEntityId}")
    CourierResponseDto getCourierPrice(@RequestParam("forAppEntityId") Long forAppEntityId);
}
