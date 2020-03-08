package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")  //如果有@HystrixCommand配置却没有指定跳转方法的都统一用这个
public class PaymentHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result =paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

//    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    public String paymentInfo_Timeout(@PathVariable("id")Integer id){
//        String result =paymentHystrixService.paymentInfo_Timeout(id);
//        return result;
//    }
    //服务降级
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod" ,commandProperties ={
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")  //如果未超过1.5秒或者程序无异常，则正常运行，否则执行paymentInfo_TimeoutHandle方法
//    } )
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id")Integer id){
        int age=1/0;
        String result =paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }
    public String paymentTimeoutFallbackMethod(@PathVariable("id")Integer id){

        return  "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    //下面是全局fallback
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }
}
