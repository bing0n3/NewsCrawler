package com.bing0ne.crawler.Model;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing0ne on 31/07/2017.
 */
public class Config {
    private Mysql mysql;
    private String image_save_path;
    private ArrayList<Site> sites;

    public Config() {
        this.mysql = null;
        this.image_save_path = null;
        this.sites = new ArrayList<Site>();
    }

    public Mysql getMysql() {
        return mysql;
    }

    public void setMysql(Mysql mysql) {
        this.mysql = mysql;
    }

    public String getImage_save_path() {
        return image_save_path;
    }

    public void setImage_save_path(String image_save_path) {
        this.image_save_path = image_save_path;
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

   public void addSite(Site site){
        sites.add(new Site(site));
   }


}



