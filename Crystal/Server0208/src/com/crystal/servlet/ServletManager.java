package com.crystal.servlet;

import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

public interface ServletManager {
	
	public void init();
	
	public Servlet getServletByName(String name) throws ServletException;
	
	public Servlet getServletByURL(String url) throws ServletException;
	
	public void addServletConfig(String name, String clazzName, 
			Map<String, String> initParams, int loadOnStartup);
	
	public void addServletMapping(String url, String name);
	
	public void destroy();

}
