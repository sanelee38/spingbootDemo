package com.hollysys.smartfactory.springbootkafkaconsumer.controller;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
public class ConsumerController {

    @Autowired
    ConsumerFactory consumerFactory;

    /*
    消费过滤器
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        // 被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        // 消息过滤策略
        factory.setRecordFilterStrategy(consumerRecord -> {
            if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
                return false;
            }
            //返回true消息则被过滤
            return true;
        });
        return factory;
    }

//    @KafkaListener(topics = {"topic1"},containerFactory = "filterContainerFactory")
    @KafkaListener(topics="topic1")
    @SendTo("topic2")
    public String onMessage1(ConsumerRecord<?, ?> record){
        System.out.println("收到消息");
        System.out.println("执行逻辑");

        //insertIntoDb(buffer);//这里为插入数据库代码
        //System.out.println("message: " + message);
        System.out.println("简单消费，record："+record.topic()+"-"+record.partition()+"-"+record.value());
        if (Integer.parseInt(record.value().toString()) % 3 == 0){
            return record.value()+"";
        }else {
            return "";
        }
    }
    @KafkaListener(topics = {"topic3"},containerFactory = "filterContainerFactory")
    public void onMessage2(ConsumerRecord<?, ?> record){
        System.out.println("收到消息");
        System.out.println("执行逻辑");
        //insertIntoDb(buffer);//这里为插入数据库代码
        //System.out.println("message: " + message);
        System.out.println("简单消费，record："+record.topic()+"-"+record.partition()+"-"+record.value());
    }
}
