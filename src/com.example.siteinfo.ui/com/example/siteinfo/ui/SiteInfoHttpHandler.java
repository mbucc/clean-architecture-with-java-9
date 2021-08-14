package com.example.siteinfo.ui;


import com.example.siteinfo.core.usecases.GetSiteInfo;
import com.example.ui.AbstractHttpHandlerWithContext;
import com.example.ui.HandlerResponse;

import java.util.ServiceLoader;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import com.sun.net.httpserver.HttpExchange;


public class SiteInfoHttpHandler extends AbstractHttpHandlerWithContext {

	public final static String SERVICE_NOT_FOUND = "no GetSiteInfo implementation found";

	private final String okHtml = "<html><head><title>The {{name}} homepage</title></head>\n" +
		"<body><h1>The {{name}} homepage</h1><p>{{intro}}</p></body></html>\n";

	private final String notFoundHtml = "<html><head><title>Not Found</title></head>\n" +
		"<body><h1>Not Found</h1></body></html>\n";

	private final Template okTemplate = Mustache.compiler().compile(okHtml);


	private final GetSiteInfo service;

	public SiteInfoHttpHandler() {
		ServiceLoader<GetSiteInfo> loader = ServiceLoader.load(GetSiteInfo.class);
		this.service = loader
			.findFirst()
			.orElseThrow(() -> new IllegalStateException(SERVICE_NOT_FOUND));
	}

	@Override
	public String getContext() {
		return "/siteinfo";
	}

	@Override
	public HandlerResponse safeHandle(HttpExchange x) {

		return this.service.getSiteInfo()
			.map(o -> new HandlerResponse(200, okTemplate.execute(o)))
			.orElse(new HandlerResponse(404, notFoundHtml));

	}

}
