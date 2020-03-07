package com.springcloud.controller;


import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.springcloud.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;


    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        System.out.println("**********插入结果:"+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功,端口号："+serverPort,result);
        }
        return new CommonResult(444,"插入数据库失败",null);
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id){
        Payment payment=paymentService.getPaymentById(id);
        System.out.println("**********插入结果:"+payment);
        if (payment!=null){
            return new CommonResult(200,"查询数据库成功端口号："+serverPort,payment);
        }
        return new CommonResult(444,"查询数据库失败,没有对应记录:"+id,null);
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

}
