package com.hollysys.smartfactory.springbootkafkaconsumer.controller;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerController {
    @KafkaListener(topics = "mytopic04")
    public void onMessage(ConsumerRecord<?, ?> record){
        //insertIntoDb(buffer);//这里为插入数据库代码
        //System.out.println("message: " + message);
        System.out.println("简单消费，record："+record.topic()+"-"+record.partition()+"-"+record.value());
    }
}
