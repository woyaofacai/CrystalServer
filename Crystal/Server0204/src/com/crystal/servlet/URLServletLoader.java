package com.crystal.servlet;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.Servlet;

public class URLServletLoader implements ServletLoader {

	private URLClassLoader urlClassLoader;
	
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
	public Servlet load(String name) {
		Servlet servlet = null;
		try {
			Class clazz = urlClassLoader.loadClass(name);
			Object obj = clazz.newInstance();
			if (obj instanceof Servlet) {
				servlet = (Servlet) obj;
			} else {
				System.out.println(name + " is not a servlet");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(name + " class not found");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return servlet;
	}

	
	
}
