package com.springcloud;

import com.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration= MySelfRule.class)//告诉80访问哪个，用什么访问方法
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
