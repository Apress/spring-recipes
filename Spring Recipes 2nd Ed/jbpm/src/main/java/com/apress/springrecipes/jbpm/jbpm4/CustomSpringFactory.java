package com.apress.springrecipes.jbpm.jbpm4;

import org.jbpm.api.ProcessEngine;

import org.jbpm.pvm.internal.cfg.ConfigurationImpl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * A custom {@link org.springframework.beans.factory.BeanFactory} that we can use to setup the {@link
 * org.jbpm.api.ProcessEngine}. THis is based on jBPM's {@link org.jbpm.pvm.internal.processengine.SpringHelper}.
 */
public class CustomSpringFactory implements FactoryBean, InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private ProcessEngine processEngine;
    private String jbpmCfg;

    public void setJbpmCfg(final String jbpmCfg) {
        this.jbpmCfg = jbpmCfg;
    }

    @Override
    public Object getObject() throws Exception {
        return processEngine;
    }

    @Override
    public Class<?> getObjectType() {
        return ProcessEngine.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        processEngine = new ConfigurationImpl().springInitiated(applicationContext).setResource(jbpmCfg).buildProcessEngine();
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext)
        throws BeansException {
        this.applicationContext = applicationContext;
    }
}
