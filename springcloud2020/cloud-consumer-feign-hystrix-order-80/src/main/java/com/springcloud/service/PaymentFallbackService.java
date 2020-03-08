package com.springcloud.service;

import org.springframework.stereotype.Component;

@Component  //让springboot容器能够扫到
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fa11 back----paymentInfo_OK,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "-----PaymentFallbackService fa11 back----paymentInfo_Timeout,o(╥﹏╥)o";
    }
}
