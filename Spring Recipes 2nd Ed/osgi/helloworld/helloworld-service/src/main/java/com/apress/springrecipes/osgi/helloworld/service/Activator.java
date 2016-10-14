package com.apress.springrecipes.osgi.helloworld.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Properties;


public class Activator implements BundleActivator {
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Start: ");
        bundleContext.registerService(GreeterService.class.getName(), new GreeterServiceImpl(), new Properties()); // the Properties could be used to provide
                                                                                                                   // key/value pairs against which service
                                                                                                                   // queries might be later predicated
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stop: ");

        // NOOP
    }
}
