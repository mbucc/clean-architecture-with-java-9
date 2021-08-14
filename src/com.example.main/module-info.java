module com.example.main {
	requires com.example.ui;
	requires com.example.siteinfo.ui;
	requires jdk.httpserver;
	uses com.example.ui.HttpHandlerWithContext;
}
