package com.example.siteinfo.db;


import com.example.siteinfo.core.entities.SiteInfo;
import com.example.siteinfo.core.ports.FindSiteInfo;
import java.util.Optional;


public class StubbedSiteInfoFinder implements FindSiteInfo {

	@Override
	public Optional<SiteInfo> findSiteInfo() {
		return Optional.of(
			new SiteInfo("silicon-valley", "We want to make the world a better place."));
	}
}
