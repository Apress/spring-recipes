package com.apress.springrecipes.osgi.helloworld.client;

import com.apress.springrecipes.osgi.helloworld.service.GreeterService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Arrays;
import java.util.Locale;


/**
 * this is a simple client that uses the service
 */
public class Activator implements BundleActivator {
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference[] refs = bundleContext.getServiceReferences(GreeterService.class.getName(), null);

        if ((null == refs) || (refs.length == 0)) {
            System.out.println("there is no service by this description!");

            return;
        }

        GreeterService greeterService = (GreeterService) bundleContext.getService(refs[0]);

        String[] names = { "Gary", "Steve", "Josh", "Mario", "Srinivas", "Tom", "James", "Manuel" };

        for (String language : Arrays.asList(Locale.ENGLISH.toString(), Locale.FRENCH.toString(), Locale.ITALIAN.toString())) {
            for (String name : names) {
                System.out.println(greeterService.greet(language, name));
            }
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {
        // NOOP
    }
}
