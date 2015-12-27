package com.test;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.test.servlets.SimpleServlet;
import com.test.servlets.TimeServlet;

public class Test {
	public static void main(String[] args) throws ServletException, IOException {

		Scanner scanner = new Scanner(System.in);
		while (true) {

			Servlet servlet = null;
			String clazzName = scanner.next();
			if ("SimpleServlet".equals(clazzName)) {
				servlet = new SimpleServlet();
			} else if ("TimeServlet".equals(clazzName)) {
				servlet = new TimeServlet();
			} else if ("quit".equals(clazzName))
				break;

			if (servlet != null) {
				servlet.init(null);
				servlet.service(null, null);
				servlet.destroy();
			}
			else
			{
				System.out.println(clazzName + " not found!");
			}
		}
		
		scanner.close();
	}

}
