package com.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：   "+Thread.currentThread().getName()+"    paymentInfo_OK,id:    "+id+"\t"+"O(∩_∩)O哈哈~";
    }
    //服务降级
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandle" ,commandProperties ={
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")  //如果未超过三秒或者程序无异常，则正常运行，否则执行paymentInfo_TimeoutHandle方法
    } )
    @Override
    public String paymentInfo_Timeout(Integer id) {
        int timeNum=2;
        int age=1/0;
        try{
            TimeUnit.SECONDS.sleep(timeNum);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池：  "+Thread.currentThread().getName()+"    paymentInfo_Timeout,id:   "+"\tO(∩_∩)O哈哈~"+"\t"+"耗时（s）：~"+timeNum;
    }



    public String paymentInfo_TimeoutHandle(Integer id) {
        return "线程池：  "+Thread.currentThread().getName()+"    系统繁忙或运行报错，请稍后再试  ,id:   "+"\t"+"o(╥﹏╥)o";
    }



    //==========服务熔断================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60") //失败率到达多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("*******id不能为负数");
        }
        String serialNumber= IdUtil.simpleUUID();//等价于UUID.randomUUID().toString();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_Fallback(@PathVariable("id") Integer id){
        return "id不能为负数，请稍后重试，o(╥﹏╥)o   ~~~id:  "+id;
    }
}
