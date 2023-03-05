package com.rbtsb.controllers;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.services.implementations.CourierPriceCheckServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.rbtsb.constant.ResponseStatus.SUCCESS;
import static com.rbtsb.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courier-price")
@Api(tags = "Courier Price API ")
public class CourierPriceController {

    private final CourierPriceCheckServiceImpl courierPriceCheckService;

    @PostMapping(value = "/check")
    @ApiOperation(value = "Courier Price check", response = String.class)
    public ResponseEntity<JSONObject> checkCourierPrice(@RequestBody CourierPriceCheckDto courierPriceCheckDto) {

        courierPriceCheckService.checkCourierPrice(courierPriceCheckDto);
        return ok(success(null, SUCCESS).getJson());
    }
}
