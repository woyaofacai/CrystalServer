package com.crystal.boostrap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	
	public void start() throws IOException {
		ServerSocket serverSocket = new ServerSocket(1234);
		while(true) {
			Socket socket = serverSocket.accept();
			InputStream input = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			readRequest(input);
			sendResponse(out);
			socket.close();
		}
	}
	
	public void sendResponse(OutputStream out) throws IOException {
		String statusLine = "HTTP/1.0 200\n";
		String headers = "Content-Type: text/html\n";
		String body = "HelloWorld";
		
		out.write(statusLine.getBytes());
		out.write(headers.getBytes());
		out.write("\n".getBytes());
		out.write(body.getBytes());
	}
	
	public void readRequest(InputStream input) throws IOException {
		byte[] buffer = new byte[2048];
		input.read(buffer);
	}
	
	public static void main(String[] args) {
		try {
			new HttpServer().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
