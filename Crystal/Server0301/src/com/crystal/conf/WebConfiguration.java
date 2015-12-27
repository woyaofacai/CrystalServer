package com.crystal.conf;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.crystal.servlet.ServletConfigImpl;
import com.crystal.servlet.ServletManager;


public class WebConfiguration {
	
	private String appName;
	private ServletManager servletManager;
	
	public WebConfiguration(String appName, ServletManager servletManager) {
		this.appName = appName;
		this.servletManager = servletManager;
	}

	public boolean init() {
		String path = getWebXmlPath(appName);
		SAXReader reader = new SAXReader();
        Document document;
		try {
			document = reader.read(path);
		} catch (DocumentException e1) {
			e1.printStackTrace();
			return false;
		}
        Element root = document.getRootElement();
        
		List<Element> servletNodes = root.elements("servlet");
        for(Element e : servletNodes) {
        	parseServletElement(e);
        }
        
        List<Element> servletMappingElements = root.elements("servlet-mapping");
        for(Element e : servletMappingElements) {
        	parseServletMappingElement(e);
        }
        return true;
	}
	
	private void parseServletMappingElement(Element e) {
		Element nameElement = e.element("servlet-name");
		Element urlElement = e.element("url-pattern");
		if(nameElement != null && urlElement != null) {
			String name = nameElement.getText();
			String url = urlElement.getText();
			servletManager.addServletMapping(url, name);
		}
	}

	private void parseServletElement(Element e) {
		Element nameElement = e.element("servlet-name");
		if(nameElement == null)
			return;
		Element clazzElement = e.element("servlet-class");
		if(clazzElement == null)
			return;
		
		String name = nameElement.getText();
		String clazzName = clazzElement.getText();
		int startup = -1;
		Element startupElement = e.element("load-on-startup");
		if(startupElement != null) {
			startup = Integer.parseInt(startupElement.getText());
		}
		
		Map<String, String> initParams = new HashMap<String, String>();
		List<Element> paramElements = e.elements("init-param");
		for(Element paramElement: paramElements) {
			Element paramNameElement = paramElement.element("param-name");
			Element paramValueElement = paramElement.element("param-value");
			if(paramNameElement != null && paramValueElement != null) {
				String paramName = paramNameElement.getText();
				String paramValue = paramValueElement.getText();
				initParams.put(paramName, paramValue);
			}
		}
		
		servletManager.addServletConfig(name, clazzName, initParams, startup);
	}
	
	
	
	public String getWebXmlPath(String appName) {
		return System.getProperty("user.dir") + File.separator + "webapps" + 
				File.separator + appName + File.separator + "WEB_INF" + File.separator + "web.xml";
	}
}
