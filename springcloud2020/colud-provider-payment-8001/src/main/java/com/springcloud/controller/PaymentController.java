package com.springcloud.controller;


import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;


    @PostMapping("/payment/create")
    public CommonResult create(Payment payment){
        int result=paymentService.create(payment);
        System.out.println("**********插入结果:"+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功",null);
        }
        return new CommonResult(444,"插入数据库失败",null);
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id){
        Payment payment=paymentService.getPaymentById(id);
        System.out.println("**********插入结果:"+payment);
        if (payment!=null){
            return new CommonResult(200,"查询数据库成功",payment);
        }
        return new CommonResult(444,"查询数据库失败,没有对应记录:"+id,null);
    }
}
