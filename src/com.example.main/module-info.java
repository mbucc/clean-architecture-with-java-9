module com.example.main {
	requires com.example.siteinfo.ui;
	requires jdk.httpserver;
	uses com.sun.net.httpserver.HttpHandler;
}
