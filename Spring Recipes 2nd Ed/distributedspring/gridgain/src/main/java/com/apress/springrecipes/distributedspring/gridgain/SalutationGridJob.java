package com.apress.springrecipes.distributedspring.gridgain;

import org.gridgain.grid.*;
import org.gridgain.grid.resources.GridSpringApplicationContextResource;

import org.springframework.context.ApplicationContext;

import java.io.Serializable;


/**
 * @author Josh Long
 */
public class SalutationGridJob implements GridJob {
    private static final long serialVersionUID = 1L;
    @GridSpringApplicationContextResource
    private ApplicationContext context;
    private String nameOfPersonToSalute;

    public SalutationGridJob(String name) {
        this.nameOfPersonToSalute = name;
    }

    public void cancel() {
    }

    public Serializable execute() throws GridException {
        GridNode gridNode = GridFactory.getGrid().getLocalNode();
        Serializable attribute = gridNode.getAttribute("");
        System.out.println(nameOfPersonToSalute + ":" + (null == context));

        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        return null;
    }
}
