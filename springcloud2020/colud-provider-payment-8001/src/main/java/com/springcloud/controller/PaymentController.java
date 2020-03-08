package com.springcloud.controller;



import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    //@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        System.out.println("**********插入结果:"+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功,端口号："+serverPort,result);
        }
        return new CommonResult(444,"插入数据库失败",null);
    }
    @GetMapping("/payment/get/{id}")
    //通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：URL 中的 {xxx} 占位符可以通过@PathVariable(“xxx“) 绑定到操作方法的入参中。
    public CommonResult getPaymentById(@PathVariable("id") long id){
        Payment payment=paymentService.getPaymentById(id);
        System.out.println("**********插入结果:"+payment);
        if (payment!=null){
            return new CommonResult(200,"查询数据库成功端口号："+serverPort,payment);
        }
        return new CommonResult(444,"查询数据库失败,没有对应记录:"+id,null);
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
      List<String > services= discoveryClient.getServices();
        for (String element  : services) {
            System.out.println("****element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }


    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //暂停几秒的线程
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}
