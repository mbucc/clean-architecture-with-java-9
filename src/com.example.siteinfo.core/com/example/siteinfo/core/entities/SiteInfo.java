package com.example.siteinfo.core.entities;


/**
 *	A SiteInfo contains basic information about a web site.
 */
public class SiteInfo {
	private final String name;
	private final String intro;

	public SiteInfo(String name, String intro) {
		this.name = require(name, "name");
		this.intro = require(intro, "intro");
	}

	private String require(String x, String label) {
		if (x == null || x.isBlank()) {
			throw new IllegalArgumentException(label + " is required");
		}
		return x;
	}

	public String getName() {
		return this.name;
	}

	public String getIntro() {
		return this.intro;
	}
}
