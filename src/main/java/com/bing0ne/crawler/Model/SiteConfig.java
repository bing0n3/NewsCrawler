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
    private String exclude;
    private String include;
    private String imgs;
    private String amp;

    public SiteConfig() {
    }

    public SiteConfig(ArrayList<String> seed, ArrayList<String> base, String date, String title, String exclude, String include, String imgs, String amp) {
        this.seed = seed;
        this.base = base;
        this.date = date;
        this.title = title;
        this.exclude = exclude;
        this.include = include;
        this.imgs = imgs;
        this.amp = amp;
    }

    public SiteConfig(SiteConfig config){
        this.seed = config.seed;
        this.base = config.base;
        this.date = config.date;
        this.title = config.title;
        this.exclude = config.exclude;
        this.include = config.include;
        this.imgs = config.imgs;
        this.amp = config.amp;
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

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getAmp() {
        return amp;
    }

    public void setAmp(String amp) {
        this.amp = amp;
    }
}
