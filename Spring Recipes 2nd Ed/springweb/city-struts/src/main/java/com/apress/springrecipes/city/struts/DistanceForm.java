package com.apress.springrecipes.city.struts;

import org.apache.struts.action.ActionForm;


public class DistanceForm extends ActionForm {

    private String srcCity;
    private String destCity;

    public String getDestCity() {
        return destCity;
    }

    public String getSrcCity() {
        return srcCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public void setSrcCity(String srcCity) {
        this.srcCity = srcCity;
    }
}
