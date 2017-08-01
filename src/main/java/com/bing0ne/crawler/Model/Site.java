package com.bing0ne.crawler.Model;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by bing0ne on 31/07/2017.
 */
@JSONType(serialzeFeatures= SerializerFeature.BeanToArray, parseFeatures= Feature.SupportArrayToBean)
public class Site {
    private String url;
    private SiteConfig config;

    public Site() {};
    public Site(String url, SiteConfig config){
        this.url = url;
        this.config = config;
    }

    public Site(Site site){
        this.url = site.getUrl();
        this.config = new SiteConfig(site.getConfig());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SiteConfig getConfig() {
        return config;
    }

    public void setConfig(SiteConfig config) {
        this.config = config;
    }
}
