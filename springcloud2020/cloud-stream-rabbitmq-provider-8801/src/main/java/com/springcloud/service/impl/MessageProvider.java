package com.springcloud.service.impl;

import com.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)     //生产者发送消息的管道   source代表的是生产者的     发送消息到rabbitmq
public class MessageProvider implements IMessageProvider {
    @Resource
    private MessageChannel output;  //消息发送管道
    @Override
    public String send() {
        String serial= UUID.randomUUID().toString(); //流水号
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("***********serial:  "+serial);
        return serial;
    }
}
