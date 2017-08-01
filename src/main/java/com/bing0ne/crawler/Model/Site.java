package com.bing0ne.crawler.Model;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by bing0ne on 31/07/2017.
 */
@JSONType(serialzeFeatures= SerializerFeature.BeanToArray, parseFeatures= Feature.SupportArrayToBean)
public class Site {
    private String SiteName;
    private SiteConfig config;

    public Site() {};
    public Site(String SiteName, SiteConfig config){
        this.SiteName = SiteName;
        this.config = config;
    }

    public Site(Site site){
        this.SiteName = site.getSiteName();
        this.config = new SiteConfig(site.getConfig());
    }

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String siteName) {
        this.SiteName = siteName;
    }

    public SiteConfig getConfig() {
        return config;
    }

    public void setConfig(SiteConfig config) {
        this.config = config;
    }
}
