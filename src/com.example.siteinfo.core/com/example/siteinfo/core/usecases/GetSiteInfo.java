package com.example.siteinfo.core.usecases;


import com.example.siteinfo.core.entities.SiteInfo;
import java.util.Optional;


public interface GetSiteInfo {

    Optional<SiteInfo> getSiteInfo();

}
