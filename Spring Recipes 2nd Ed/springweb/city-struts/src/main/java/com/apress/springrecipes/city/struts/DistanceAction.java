package com.apress.springrecipes.city.struts;

import com.apress.springrecipes.city.CityService;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DistanceAction extends Action {
    private CityService cityService;

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().equals("POST")) {
            DistanceForm distanceForm = (DistanceForm) form;
            String srcCity = distanceForm.getSrcCity();
            String destCity = distanceForm.getDestCity();

            double distance = cityService.findDistance(srcCity, destCity);
            request.setAttribute("distance", distance);
        }

        return mapping.findForward("success");
    }
}
