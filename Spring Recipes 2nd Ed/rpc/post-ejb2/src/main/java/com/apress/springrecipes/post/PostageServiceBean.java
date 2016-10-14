package com.apress.springrecipes.post;

import org.springframework.ejb.support.AbstractStatelessSessionBean;

import javax.ejb.CreateException;


public class PostageServiceBean extends AbstractStatelessSessionBean implements PostageService {
    private PostageService postageService;

    protected void onEjbCreate() throws CreateException {
        postageService = (PostageService) getBeanFactory().getBean("postageService");
    }

    public double calculatePostage(String country, double weight) {
        return postageService.calculatePostage(country, weight);
    }
}
