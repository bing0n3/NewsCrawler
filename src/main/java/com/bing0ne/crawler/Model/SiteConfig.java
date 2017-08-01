package com.bing0ne.crawler.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing0ne on 31/07/2017.
 */
public class SiteConfig{
    private ArrayList<String> seed;  // seed url, crawler start from this website
    private ArrayList<String> base;   // base url, only urls start with this url will be patch
    private String date;
    private String title;
    private ArrayList<String> exclude;
    private ArrayList<String> include;

    public SiteConfig() {
    }

    public SiteConfig(ArrayList<String> seed, ArrayList<String> base, String date, String title, ArrayList<String> exclude, ArrayList<String> include) {
        this.seed = seed;
        this.base = base;
        this.date = date;
        this.title = title;
        this.exclude = exclude;
        this.include = include;
    }

    public SiteConfig(SiteConfig config){
        this.seed = config.seed;
        this.base = config.base;
        this.date = config.date;
        this.title = config.title;
        this.exclude = new ArrayList<String>(config.getExclude());
        this.include = new ArrayList<String>(config.getInclude());
    }

    public ArrayList<String> getSeed() {
        return seed;
    }

    public void setSeed(ArrayList<String> seed) {
        this.seed = seed;
    }

    public ArrayList<String> getBase() {
        return base;
    }

    public void setBase(ArrayList<String> base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getExclude() {
        return exclude;
    }

    public void setExclude(ArrayList<String> exclude) {
        this.exclude = exclude;
    }

    public List<String> getInclude() {
        return include;
    }

    public void setInclude(ArrayList<String> include) {
        this.include = include;
    }
}
