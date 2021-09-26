package com.hollysys.smartfactory.springbootkafkaproductor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sanelee
 */
@Controller
@RequestMapping("/api/kafka/")
public class ProducterController {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("Asend")
    @ResponseBody
    public boolean Asend(@RequestParam String message) {
        try {
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic1", message);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.err.println("topic1 - 生产者 发送消息失败：" + throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                    System.out.println("topic1 - 生产者 发送消息成功：" + stringObjectSendResult.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @GetMapping("Bsend")
    @ResponseBody
    public boolean Bsend(@RequestParam String message) {
        try {
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic3", message);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.err.println("topic3 - 生产者 发送消息失败：" + throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                    System.out.println("topic3 - 生产者 发送消息成功：" + stringObjectSendResult.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @GetMapping("test")
    @ResponseBody
    public String test(){
        System.out.println("hello world!");
        return "ok";
    }
}
