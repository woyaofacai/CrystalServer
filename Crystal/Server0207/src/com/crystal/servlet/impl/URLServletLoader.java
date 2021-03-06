package com.crystal.servlet.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import com.crystal.servlet.ServletLoader;

public class URLServletLoader implements ServletLoader {

	private URLClassLoader urlClassLoader;
	private Map<String, Class<Servlet>> map = new HashMap<String, Class<Servlet>>();
	
	public URLServletLoader(String contextName) throws MalformedURLException {
		
		String root = System.getProperty("user.dir") + File.separator + "webapps" + 
					  File.separator + contextName + File.separator + "WEB_INF" + File.separator;
		File file1 = new File(root + "classes");
		File file2 = new File(root + "lib");
		URL url1 = file1.toURI().toURL();
		URL url2 = file2.toURI().toURL();
		

		urlClassLoader = new URLClassLoader(new URL[] {url1, url2});
	}
	
	
	@Override
	public Class<Servlet> load(String name) {
		Class<Servlet> clazz = map.get(name);
		if(clazz != null) {
			return clazz;
		}
		
		Class cl = null;
		try {
			cl = urlClassLoader.loadClass(name);
			if(cl != null) {
				clazz = (Class<Servlet>)cl;
				map.put(name, clazz);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
}
