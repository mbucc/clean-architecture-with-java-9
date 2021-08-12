package com.example.siteinfo.core.services;


import java.util.Optional;
import java.util.ServiceLoader;
import com.example.siteinfo.core.usecases.GetSiteInfo;
import com.example.siteinfo.core.entities.SiteInfo;
import com.example.siteinfo.core.ports.FindSiteInfo;


/**
 * A GetSiteInfoService implements the get site info use case by
 * looking up the site info from the persistence layer.
 *
 * <p>If this application had any business logic, it would also go here.</p>
 */
public class GetSiteInfoService implements GetSiteInfo {

	private final FindSiteInfo db;


	public GetSiteInfoService() {
		this.db = ServiceLoader
			.load(FindSiteInfo.class)
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("no FindSiteInfo implementation found."));
	}

	// Not used, left as an example of how tests could inject a mock.
	GetSiteInfoService(FindSiteInfo db) {
		this.db = db;
	}


	@Override
	public Optional<SiteInfo> getSiteInfo() {
		return this.db.findSiteInfo();
	}

}
