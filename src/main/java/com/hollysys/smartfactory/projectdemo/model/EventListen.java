package com.hollysys.smartfactory.projectdemo.model;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lizhi186545
 */
@Component
public class EventListen {

    @EventListener
    public void event(TestEvent event){
        for (int i = 0;i<10;i++){
            System.out.println("event = ["+event.getMsg()+"]");
        }
    }
}
