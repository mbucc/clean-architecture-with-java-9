module com.example.siteinfo.db {
	requires com.example.siteinfo.core;
	uses com.example.siteinfo.core.entities.SiteInfo;
	provides com.example.siteinfo.core.ports.FindSiteInfo
		with com.example.siteinfo.db.StubbedSiteInfoFinder;
}
