package com.hollysys.smartfactory.projectdemo.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author lizhi186545
 */
public class TestEvent extends ApplicationEvent {

    private static final long serialVersionUID = 2831618830529722642L;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
