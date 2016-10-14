package com.apress.springrecipes.weather;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;

import org.dom4j.xpath.DefaultXPath;

import org.springframework.ws.server.endpoint.AbstractDom4jPayloadEndpoint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;


public class TemperatureDom4jEndpoint extends AbstractDom4jPayloadEndpoint {
    private static final String namespaceUri = "http://springrecipes.apress.com/weather/schemas";
    private XPath cityPath;
    private XPath datePath;
    private DateFormat dateFormat;
    private WeatherService weatherService;

    public TemperatureDom4jEndpoint() {
        // Create the XPath objects, including the namespace
        Map<String, String> namespaceUris = new HashMap<String, String>();
        namespaceUris.put("weather", namespaceUri);
        cityPath = new DefaultXPath("/weather:GetTemperaturesRequest/weather:city");
        cityPath.setNamespaceURIs(namespaceUris);
        datePath = new DefaultXPath("/weather:GetTemperaturesRequest/weather:date");
        datePath.setNamespaceURIs(namespaceUris);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    protected Element invokeInternal(Element requestElement, Document responseDocument)
        throws Exception {
        // Extract the service parameters from the request message
        String city = cityPath.valueOf(requestElement);
        List<Date> dates = new ArrayList<Date>();

        for (Object node : datePath.selectNodes(requestElement)) {
            Element element = (Element) node;
            dates.add(dateFormat.parse(element.getText()));
        }

        // Invoke the backend service to handle the request
        List<TemperatureInfo> temperatures = weatherService.getTemperatures(city, dates);

        // Build the response message from the result of backend service
        Element responseElement = responseDocument.addElement("GetTemperaturesResponse", namespaceUri);

        for (TemperatureInfo temperature : temperatures) {
            Element temperatureElement = responseElement.addElement("TemperatureInfo");
            temperatureElement.addAttribute("city", temperature.getCity());
            temperatureElement.addAttribute("date", dateFormat.format(temperature.getDate()));
            temperatureElement.addElement("min").setText(Double.toString(temperature.getMin()));
            temperatureElement.addElement("max").setText(Double.toString(temperature.getMax()));
            temperatureElement.addElement("average").setText(Double.toString(temperature.getAverage()));
        }

        return responseElement;
    }
}
