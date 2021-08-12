module com.example.siteinfo.ui {
	requires jdk.httpserver;
	requires com.samskivert.jmustache;
	requires com.example.siteinfo.core;
	uses com.example.siteinfo.core.usecases.GetSiteInfo;
	provides com.sun.net.httpserver.HttpHandler
		with com.example.siteinfo.ui.SiteInfoHttpHandler;
}
