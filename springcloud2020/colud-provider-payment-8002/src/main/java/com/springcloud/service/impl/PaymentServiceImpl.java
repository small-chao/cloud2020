package com.springcloud.service.impl;


import com.springcloud.entities.Payment;

import org.springframework.stereotype.Service;
import com.springcloud.dao.PaymentDao;
import com.springcloud.service.PaymentService;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById( long id){
        return paymentDao.getPaymentById(id);
    }
}
