package com.example.ui;


import com.sun.net.httpserver.HttpHandler;


public interface HttpHandlerWithContext extends HttpHandler {

	String getContext();

}
