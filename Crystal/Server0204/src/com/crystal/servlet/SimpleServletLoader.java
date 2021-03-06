package com.crystal.servlet;

import javax.servlet.Servlet;

public class SimpleServletLoader implements ServletLoader {

	@Override
	public Servlet load(String name) {
		Servlet servlet = null;
		try {
			Class clazz = Class.forName(name);
			Object obj = clazz.newInstance();

			if (obj instanceof Servlet) {
				servlet = (Servlet) obj;
			} else {
				System.out.println(name + " is not a servlet");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return servlet;
	}

}
