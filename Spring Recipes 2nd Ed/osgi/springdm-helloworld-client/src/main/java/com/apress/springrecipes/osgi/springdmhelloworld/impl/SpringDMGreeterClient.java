package com.apress.springrecipes.osgi.springdmhelloworld.impl;

import com.apress.springrecipes.osgi.helloworld.service.GreeterService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Locale;


public class SpringDMGreeterClient implements InitializingBean {
    @Autowired
    private GreeterService greeterService;

    public void afterPropertiesSet() throws Exception {
        for (String name : Arrays.asList("Mario", "Fumiko", "Makani")) {
            System.out.println(greeterService.greet(Locale.FRENCH.toString(), name));
        }
    }
}
