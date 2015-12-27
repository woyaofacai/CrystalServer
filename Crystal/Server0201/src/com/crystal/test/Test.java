package com.crystal.test;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.crystal.test.servlets.SimpleServlet;

public class Test {
	public static void main(String[] args) throws ServletException, IOException {
		Servlet servlet = new SimpleServlet();
		servlet.init(null);
		servlet.service(null, null);
		servlet.destroy();
	}
	
}
