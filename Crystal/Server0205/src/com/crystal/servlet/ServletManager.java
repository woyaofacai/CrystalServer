package com.crystal.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

public interface ServletManager {
	
	public Servlet getServlet(String name) throws ServletException;
	public void destroy();

}
