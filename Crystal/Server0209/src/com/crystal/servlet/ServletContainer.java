package com.crystal.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.crystal.servlet.impl.ServletContextImpl;

public class ServletContainer {

	private Map<String, ServletContextImpl> contexts = new HashMap<String, ServletContextImpl>();
	
	private static ServletContainer instance = new ServletContainer();
	
	public static ServletContainer getInstance() {
		return instance;
	}
	
	private ServletContainer() {
		String[] appNames = new String[] { "HelloWorld" };

		for (int i = 0; i < appNames.length; i++) {
			ServletContextImpl context = new ServletContextImpl(appNames[i]);
			context.init();
			contexts.put(appNames[i], context);
		}
	}
	
	public void destroy() {
		for(Map.Entry<String, ServletContextImpl> e : contexts.entrySet()) {
			ServletContextImpl context = e.getValue();
			context.destroy();
		}
	}
	
	public boolean process(String url) throws ServletException, IOException {
		// get app's name from url
		int index1 = url.indexOf('/');
		if(index1 != 0)
			return false;
		
		int index2 = url.indexOf('/', index1 + 1);
		if(index2 == -1)
			return false;
		
		String appName = url.substring(index1 + 1, index2);
		String servletURL = url.substring(index2);
		
		ServletContextImpl context = contexts.get(appName);
		if(context == null)
			return false;
		
		ServletManager smgr = context.getServletManager();
		Servlet servlet = smgr.getServletByURL(servletURL);
		
		if(servlet == null)
			return false;
		
		servlet.service(null, null);
		return true;
	}
	

}
