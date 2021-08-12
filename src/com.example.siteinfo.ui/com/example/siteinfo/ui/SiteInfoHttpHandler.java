package com.example.siteinfo.ui;


import com.example.siteinfo.core.usecases.GetSiteInfo;
import com.example.siteinfo.core.entities.SiteInfo;

import java.io.IOException;
import java.util.Optional;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ServiceLoader;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;


public class SiteInfoHttpHandler implements HttpHandler {

	private final GetSiteInfo service;

	private final String okHtml = "<html><head><title>The {{name}} homepage</title></head>\n" +
		"<body><h1>The {{name}} homepage</h1><p>{{intro}}</p></body></html>\n";

	private final String notFoundHtml = "<html><head><title>Not Found</title></head>\n" +
		"<body><h1>Not Found</h1></body></html>\n";

	private final String errHtml = "<html><head><title>Internal Server Error</title></head>\n" +
		"<body>\n" +
		"<h1>Internal Server Error</h1>\n" +
		"<pre>%s</pre>\n" +
		"</body></html>\n";

	private final Template okTemplate = Mustache.compiler().compile(okHtml);


	public SiteInfoHttpHandler() {
		ServiceLoader<GetSiteInfo> loader = ServiceLoader.load(GetSiteInfo.class);
		this.service = loader
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("no GetSiteInfo implementation found"));
	}


	// Not exported, and currently unused.  Would be used by tests to inject a mock.
	SiteInfoHttpHandler(GetSiteInfo service) {
		this.service = service;
	}


	@Override
	public void handle(HttpExchange x) throws IOException {

		String response;
		int status;
		try {
			Optional<SiteInfo> y = this.service.getSiteInfo();
			if (y.isPresent()) {
				status = 200;
				response = okTemplate.execute(y.get());
			} else {
				status = 404;
				response = notFoundHtml;
			}
	
		} catch (Exception e) {
			status = 500;
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			response = String.format(errHtml, sw.toString());
		}

		try {
			Headers ys = x.getResponseHeaders();
			ys.set("Content-Type", "text/html");
			x.sendResponseHeaders(status, response.length());
			OutputStream os = x.getResponseBody();
			os.write(response.getBytes());
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
