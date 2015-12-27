package com.crystal.servlet.impl;

import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.crystal.servlet.ServletLoader;
import com.crystal.servlet.ServletManager;

public class DefaultServletManager implements ServletManager {
	
	private Map<String, Servlet> map = new Hashtable<String, Servlet>();
	private ServletLoader loader;
	
	public DefaultServletManager(String appName) throws MalformedURLException {
		loader = new URLServletLoader(appName);
	}
	
	public Servlet getServlet(String name) throws ServletException {
		Servlet servlet = map.get(name);
		if (servlet == null) {
			Class<Servlet> clazz = loader.load(name);
			try {
				servlet = clazz.newInstance();
				servlet.init(null);
				map.put(name, servlet);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return servlet;
	}
	
	public void destroy() {
		for(Map.Entry<String, Servlet> entry : map.entrySet()) {
			Servlet servlet = entry.getValue();
			servlet.destroy();
		}
		map.clear();
	}
	
}
