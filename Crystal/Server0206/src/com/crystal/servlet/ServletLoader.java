package com.crystal.servlet;

import javax.servlet.Servlet;

public interface ServletLoader {
	
	public Class<Servlet> load(String name);
	
}
