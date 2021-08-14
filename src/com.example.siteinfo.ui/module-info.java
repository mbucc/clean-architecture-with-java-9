module com.example.siteinfo.ui {
	requires jdk.httpserver;
	requires com.samskivert.jmustache;
	requires com.example.siteinfo.core;
	requires com.example.ui;
	uses com.example.siteinfo.core.usecases.GetSiteInfo;
	provides com.example.ui.HttpHandlerWithContext
		with com.example.siteinfo.ui.SiteInfoHttpHandler;
}
