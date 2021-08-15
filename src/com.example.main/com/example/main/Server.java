package com.example.main;


import com.example.ui.HttpHandlerWithContext;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ServiceLoader;


public class Server {

	public static final String START_INFO = "starting server on port %d";


	public static void main(String[] args) throws IOException {
		int port = 8000;
		System.out.println(String.format(START_INFO, port));
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

		ServiceLoader.load(HttpHandlerWithContext.class)
			.stream()
			.map(ServiceLoader.Provider::get)
			.forEach(o -> server.createContext(o.getContext(), o));

		server.setExecutor(null); // creates a default executor
		server.start();
	}

}
