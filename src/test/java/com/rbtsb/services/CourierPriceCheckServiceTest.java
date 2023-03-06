package com.rbtsb.services;

import com.rbtsb.dto.CourierPriceCheckDto;
import com.rbtsb.repositories.CourierPriceRepository;
import com.rbtsb.responses.CourierRateResponse;
import com.rbtsb.services.implementations.CourierPriceCheckServiceImpl;
import com.rbtsb.services.implementations.CourierServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourierPriceCheckServiceTest {

    @Mock
    private CourierPriceRepository courierPriceRepository;

    @Mock
    private CourierServiceImpl courierService;

    @InjectMocks
    private CourierPriceCheckServiceImpl courierPriceCheckService;

    @Test
    void checkCourierPrice() {
        CourierPriceCheckDto courierPriceCheckDto = new CourierPriceCheckDto(
                "MY", "Kuala Lumpur", 50250, "BD",
                "Bangladesh", 50000, 10.0, 10.0, 10.0,
                1, 10.0, 0.0);
        when(courierPriceRepository.save(any())).then(returnsFirstArg());

        List<CourierRateResponse> courierRateResponseList = courierPriceCheckService.checkCourierPrice(courierPriceCheckDto);
        assertTrue(courierRateResponseList.stream().anyMatch(item -> "citylink".equals(item.getCourier())));
    }
}
