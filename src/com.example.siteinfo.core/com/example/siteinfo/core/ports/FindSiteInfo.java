package com.example.siteinfo.core.ports;


import com.example.siteinfo.core.entities.SiteInfo;
import java.util.Optional;


public interface FindSiteInfo {

    Optional<SiteInfo> findSiteInfo();

}
