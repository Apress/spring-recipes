package com.apress.springrecipes.distributedspring.gridgain;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;


public class Main {
    public static void main(String[] args) throws Throwable {
        // you can setup $GRIDGAIN_HOME as an environmental variable 
        File whereImKeepingGridgain = new File(new File(SystemUtils.getUserHome(), "Desktop"), "gridgain-2.1.1");
        System.setProperty("GRIDGAIN_HOME", whereImKeepingGridgain.getAbsolutePath());

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("gridservice.xml");
        applicationContext.registerShutdownHook();
        applicationContext.start();

        SalutationService salutationServiceImpl = (SalutationService) applicationContext.getBean("salutationService");

        String[] names = ("Alan,Arin,Clark,Craig,Drew,Gary,Gordon,Fumiko," + "Hicham,Jordon,Kathy,Ken,Makani,Mario, " + "Mark,Mia,Mike,Nick,Richard,Richelle, " +
            "Rod,Ron,Scott,Shaun,Srinivas,Valerie,Venkatesh").split(",");

        System.out.println(StringUtils.repeat("=", 100));

        for (String name : names) {
            System.out.println("Result: " + salutationServiceImpl.saluteSomeoneInForeignLanguage(name));
        }

        System.out.println(StringUtils.repeat("=", 100));
        System.out.println("Results:" + StringUtils.join(salutationServiceImpl.saluteManyPeopleInRandomForeignLanguage(names), ","));
    }
}
