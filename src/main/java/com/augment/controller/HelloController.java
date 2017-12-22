package com.augment.controller;

import com.augment.service.HelloService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class HelloController {
    @ManagedProperty("#{helloService}")
    private HelloService helloService;

    public String sayHello() {
        return helloService.sayHello();
    }

    public void setHelloService(final HelloService helloService) {
        this.helloService = helloService;
    }
}
