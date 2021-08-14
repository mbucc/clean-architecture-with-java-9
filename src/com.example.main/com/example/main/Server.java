package com.example.main;


import com.example.ui.HttpHandlerWithContext;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ServiceLoader;


public class Server {

	public static final String START_INFO = "starting context prefix %s on port %d";


	public static void main(String[] args) throws IOException {
		int port = 8000;
		String prefix = "/myapp";
		System.out.println(String.format(START_INFO, prefix, port));
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

		// TODO: Add void setContextPrefix(String) method to the HttpHandlerWithContext interface.
		// So each handler can render HTML with correct URLs.
		ServiceLoader.load(HttpHandlerWithContext.class)
			.stream()
			.map(ServiceLoader.Provider::get)
			.forEach(o -> server.createContext(prefix + o.getContext(), o));

		server.setExecutor(null); // creates a default executor
		server.start();
	}

}
