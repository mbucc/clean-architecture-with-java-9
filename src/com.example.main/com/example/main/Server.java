package com.example.main;


import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ServiceLoader;


public class Server {

	public static void main(String[] args) throws IOException {
		int port = 8000;
		String context = "/myapp";
		System.out.println(String.format("starting %s on port %d", context, port));
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

		ServiceLoader<HttpHandler> loader = ServiceLoader.load(HttpHandler.class);
		HttpHandler handler = loader
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("no HttpHandler implementation found"));
		server.createContext(context, handler);

		server.setExecutor(null); // creates a default executor
		server.start();
	}

}
