package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.FeedContent;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import org.springframework.xml.xpath.NodeMapper;

import org.w3c.dom.*;

import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;


@Controller
public class RestNewsController {
    @Autowired
    protected RestTemplate restTemplate;
    @Autowired
    protected org.springframework.xml.xpath.XPathExpression feeditemExpression;
    @Autowired
    protected org.springframework.xml.xpath.XPathExpression feedtitleExpression;
    @Autowired
    protected org.springframework.xml.xpath.XPathExpression feedlinkExpression;
    @Autowired
    protected org.springframework.xml.xpath.AbstractXPathTemplate feedXPathTemplate;

    @RequestMapping("/sportsnews")
    public String getYahooNews(Model model) {
        // Return view newstemplate via resolver. the view
        // will be mapped to /WEB-INF/jsp/newstemplate.jsp
        String result = restTemplate.getForObject("http://search.yahooapis.com/NewsSearchService/V1/newsSearch?appid={appid}&query={query}&results={results}&language={language}", String.class,
                "YahooDemo", "sports", "2", "en");
        model.addAttribute("newsfeed", result);

        return "newstemplate";
    }

    @RequestMapping("/nationalweather")
    public String getWeatherNews(Model model) {
        // Return view nationalweathertemplate. Via resolver the view
        // will be mapped to /WEB-INF/jsp/nationalweathertemplate.jsp
        try {
            StreamSource source = restTemplate.getForObject("http://rss.weather.com/rss/national/rss_nwf_rss.xml?cm_ven={cm_ven}&cm_cat={cm_cat}&par={par}", StreamSource.class, "NWF", "rss", "NWF_rss");

            // Define DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            dbf.setIgnoringComments(false);
            dbf.setIgnoringElementContentWhitespace(true);
            dbf.setNamespaceAware(true);

            // Define DocumentBuilder
            DocumentBuilder db = null;
            db = dbf.newDocumentBuilder();

            // Define InputSource
            InputSource is = new InputSource();
            is.setSystemId(source.getSystemId());
            is.setByteStream(source.getInputStream());
            is.setCharacterStream(source.getReader());
            is.setEncoding("ISO-8859-1");

            // Define W3C Document 
            Document doc = db.parse(is);

            // Get items
            NodeList itemElements = doc.getElementsByTagName("item");

            // Define lists for titles and links
            List<String> feedtitles = new ArrayList<String>();
            List<String> feedlinks = new ArrayList<String>();

            // Loop over all item elements
            int length = itemElements.getLength();

            for (int n = 0; n < length; ++n) {
                NodeList childElements = itemElements.item(n).getChildNodes();
                int lengthnested = childElements.getLength();

                for (int k = 0; k < lengthnested; ++k) {
                    if (childElements.item(k).getNodeName().equals("title")) {
                        feedtitles.add(childElements.item(k).getChildNodes().item(0).getNodeValue());
                    }

                    if (childElements.item(k).getNodeName().equals("link")) {
                        feedlinks.add(childElements.item(k).getChildNodes().item(0).getNodeValue());
                    }
                }
            }

            // List for content 
            List<FeedContent> feedcontent = new ArrayList<FeedContent>();
            int titlelength = feedtitles.size();

            // Loop over extracted titles and links 
            for (int x = 0; x < titlelength; ++x) {
                feedcontent.add(new FeedContent(feedtitles.get(x), feedlinks.get(x)));
            }

            // Place feed type, version and content in model object
            model.addAttribute("feedtype", doc.getDocumentElement().getNodeName());
            model.addAttribute("feedversion", doc.getDocumentElement().getAttribute("version"));
            model.addAttribute("feedcontent", feedcontent);
        } catch (Exception exc) {
            System.out.println(exc);
        }

        return "nationalweathertemplate";
    }

    @RequestMapping("/nationalweather2")
    public String getWeatherNews2(Model model) {
        // Return view nationalweathertemplate. Via resolver the view
        // will be mapped to /WEB-INF/jsp/nationalweathertemplate.jsp
        try {
            StreamSource source = restTemplate.getForObject("http://rss.weather.com/rss/national/rss_nwf_rss.xml?cm_ven={cm_ven}&cm_cat={cm_cat}&par={par}", StreamSource.class, "NWF", "rss", "NWF_rss");

            // Define DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            dbf.setIgnoringComments(false);
            dbf.setIgnoringElementContentWhitespace(true);
            dbf.setNamespaceAware(true);

            // Define DocumentBuilder
            DocumentBuilder db = null;
            db = dbf.newDocumentBuilder();

            // Define InputSource
            InputSource is = new InputSource();
            is.setSystemId(source.getSystemId());
            is.setByteStream(source.getInputStream());
            is.setCharacterStream(source.getReader());
            is.setEncoding("ISO-8859-1");

            // Define W3C Document 
            Document doc = db.parse(is);

            // Define lists for titles and links
            List feedtitles = new ArrayList();
            List feedlinks = new ArrayList();

            // Defin XPath constructs
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            // Define XPath expression to extract titles								\
            XPathExpression titleexpr = xpath.compile("//item/title");

            // Define XPath expression to extract links
            XPathExpression linkexpr = xpath.compile("//item/link");

            // Evaluate XPath expressions
            Object titleresult = titleexpr.evaluate(doc, XPathConstants.NODESET);
            Object linkresult = linkexpr.evaluate(doc, XPathConstants.NODESET);

            // Loop over extracted title elements using DOM
            NodeList titlenodes = (NodeList) titleresult;

            for (int i = 0; i < titlenodes.getLength(); i++) {
                feedtitles.add(titlenodes.item(i).getChildNodes().item(0).getNodeValue());
            }

            // Loop over extracted link elements using DOM	    
            NodeList linknodes = (NodeList) linkresult;

            for (int j = 0; j < linknodes.getLength(); j++) {
                feedlinks.add(linknodes.item(j).getChildNodes().item(0).getNodeValue());
            }

            // List for content 
            List feedcontent = new ArrayList();
            int titlelength = feedtitles.size();

            // Loop over extracted titles and links 
            for (int x = 0; x < titlelength; ++x) {
                feedcontent.add(new FeedContent((String) feedtitles.get(x), (String) feedlinks.get(x)));
            }

            // Place feed type, version and content in model object
            model.addAttribute("feedtype", doc.getDocumentElement().getNodeName());
            model.addAttribute("feedversion", doc.getDocumentElement().getAttribute("version"));
            model.addAttribute("feedcontent", feedcontent);
        } catch (Exception exc) {
            System.out.println(exc);
        }

        return "nationalweathertemplate";
    }

    @RequestMapping("/nationalweather3")
    public String getWeatherNews3(Model model) {
        // Return view nationalweathertemplate. Via resolver the view
        // will be mapped to /WEB-INF/jsp/nationalweathertemplate.jsp
        try {
            StreamSource source = restTemplate.getForObject("http://rss.weather.com/rss/national/rss_nwf_rss.xml?cm_ven={cm_ven}&cm_cat={cm_cat}&par={par}", StreamSource.class, "NWF", "rss", "NWF_rss");

            // Define DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            dbf.setIgnoringComments(false);
            dbf.setIgnoringElementContentWhitespace(true);
            dbf.setNamespaceAware(true);

            // Define DocumentBuilder
            DocumentBuilder db = null;
            db = dbf.newDocumentBuilder();

            // Define InputSource
            InputSource is = new InputSource();
            is.setSystemId(source.getSystemId());
            is.setByteStream(source.getInputStream());
            is.setCharacterStream(source.getReader());
            is.setEncoding("ISO-8859-1");

            // Define W3C Document 
            Document doc = db.parse(is);

            // Define lists for titles and links
            List feedtitles = new ArrayList();
            List feedlinks = new ArrayList();

            List<Node> titlenodes = feedtitleExpression.evaluateAsNodeList(doc.getDocumentElement());
            List<Node> linknodes = feedlinkExpression.evaluateAsNodeList(doc.getDocumentElement());

            for (Node node : titlenodes) {
                feedtitles.add(node.getChildNodes().item(0).getNodeValue());
            }

            for (Node node : linknodes) {
                feedlinks.add(node.getChildNodes().item(0).getNodeValue());
            }

            // List for content 
            List feedcontent = new ArrayList();
            int titlelength = feedtitles.size();

            // Loop over extracted titles and links 
            for (int x = 0; x < titlelength; ++x) {
                feedcontent.add(new FeedContent((String) feedtitles.get(x), (String) feedlinks.get(x)));
            }

            // Place feed type, version and content in model object
            model.addAttribute("feedtype", doc.getDocumentElement().getNodeName());
            model.addAttribute("feedversion", doc.getDocumentElement().getAttribute("version"));
            model.addAttribute("feedcontent", feedcontent);
        } catch (Exception exc) {
            System.out.println(exc);
        }

        return "nationalweathertemplate";
    }

    @RequestMapping("/nationalweather4")
    public String getWeatherNews4(Model model) {
        // Return view nationalweathertemplate. Via resolver the view
        // will be mapped to /WEB-INF/jsp/nationalweathertemplate.jsp
        try {
            StreamSource source = restTemplate.getForObject("http://rss.weather.com/rss/national/rss_nwf_rss.xml?cm_ven={cm_ven}&cm_cat={cm_cat}&par={par}", StreamSource.class, "NWF", "rss", "NWF_rss");

            // Define DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            dbf.setIgnoringComments(false);
            dbf.setIgnoringElementContentWhitespace(true);
            dbf.setNamespaceAware(true);

            // Define DocumentBuilder
            DocumentBuilder db = null;
            db = dbf.newDocumentBuilder();

            // Define InputSource
            InputSource is = new InputSource();
            is.setSystemId(source.getSystemId());
            is.setByteStream(source.getInputStream());
            is.setCharacterStream(source.getReader());
            is.setEncoding("ISO-8859-1");

            // Define W3C Document 
            Document doc = db.parse(is);

            // Define lists for titles and links
            List feedtitles = new ArrayList();
            List feedlinks = new ArrayList();

            List feedcontent = feeditemExpression.evaluate(doc,
                    new NodeMapper() {
                        public Object mapNode(Node node, int nodeNum)
                            throws DOMException {
                            Element itemElement = (Element) node;
                            Element titleElement = (Element) itemElement.getElementsByTagName("title").item(0);
                            Element linkElement = (Element) itemElement.getElementsByTagName("link").item(0);

                            return new FeedContent(titleElement.getTextContent(), linkElement.getTextContent());
                        }
                    });

            // Place feed type, version and content in model object
            model.addAttribute("feedtype", doc.getDocumentElement().getNodeName());
            model.addAttribute("feedversion", doc.getDocumentElement().getAttribute("version"));
            model.addAttribute("feedcontent", feedcontent);
        } catch (Exception exc) {
            System.out.println(exc);
        }

        return "nationalweathertemplate";
    }

    @RequestMapping("/nationalweather5")
    public String getWeatherNews5(Model model) {
        // Return view nationalweathertemplate. Via resolver the view
        // will be mapped to /WEB-INF/jsp/nationalweathertemplate.jsp
        try {
            Source source = restTemplate.getForObject("http://rss.weather.com/rss/national/rss_nwf_rss.xml?cm_ven={cm_ven}&cm_cat={cm_cat}&par={par}", Source.class, "NWF", "rss", "NWF_rss");

            // Define lists for titles and links
            List feedtitles = new ArrayList();
            List feedlinks = new ArrayList();

            List feedcontent = feedXPathTemplate.evaluate("//item", source,
                    new NodeMapper() {
                        public Object mapNode(Node node, int nodeNum)
                            throws DOMException {
                            Element itemElement = (Element) node;
                            Element titleElement = (Element) itemElement.getElementsByTagName("title").item(0);
                            Element linkElement = (Element) itemElement.getElementsByTagName("link").item(0);

                            return new FeedContent(titleElement.getTextContent(), linkElement.getTextContent());
                        }
                    });

            // No Document , just hard-code feed type and version
            model.addAttribute("feedtype", "rss");
            model.addAttribute("feedversion", "2.0");
            // Place feedcontent obtained using XPathTemplate
            model.addAttribute("feedcontent", feedcontent);
        } catch (Exception exc) {
            System.out.println(exc);
        }

        return "nationalweathertemplate";
    }

    @RequestMapping("/worldnews")
    public String getGoogleNews(Model model) {
        // Return view worldnewstemplate. Via resolver the view
        // will be mapped to /WEB-INF/jsp/worldnewstemplate.jsp
        // GOOGLE BLOCKS REQUESTS
        String result = restTemplate.getForObject("http://news.google.com/news?ned={ned}&topic={topic}&output={output}", String.class, "us", "w", "rss");
        model.addAttribute("worldnewsfeed", result);

        return "worldnewstemplate";
    }
}
