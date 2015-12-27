package com.test;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.crystal.servlet.ServletLoader;
import com.crystal.servlet.SimpleServletLoader;
import com.crystal.servlet.URLServletLoader;

public class Test {
	public static void main(String[] args) throws ServletException, IOException {

		Scanner scanner = new Scanner(System.in);
		ServletLoader loader = new URLServletLoader("HelloWorld");
		while (true) {
			String clazzName = scanner.next();
			if("quit".equals(clazzName))
				break;
			
			Servlet servlet = loader.load(clazzName);
			
			if (servlet != null) {
				servlet.init(null);
				servlet.service(null, null);
				servlet.destroy();
			}
		}
		scanner.close();
	}

}
