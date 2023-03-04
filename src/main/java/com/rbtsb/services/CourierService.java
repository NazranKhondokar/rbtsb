package com.rbtsb.services;

import com.rbtsb.clients.CourierClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierClient courierClient;

    public ApproverReviewerInfo getCourier() {
        ApproveReviewResponseDto approveReviewResponseDto = courierClient.getCourierPrice();
        if (!"success".equals(approveReviewResponseDto.getStatus())) {
            throw new ResourceNotFoundException(String.format("%s. parameters {approveReviewId=%s}", approveReviewResponseDto.getError(), forAppEntityId));
        }
        return approveReviewResponseDto.getData();
    }
}

