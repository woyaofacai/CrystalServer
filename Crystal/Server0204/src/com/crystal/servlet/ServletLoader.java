package com.crystal.servlet;

import javax.servlet.Servlet;

public interface ServletLoader {
	
	public Servlet load(String name);
	
}
