package com.bing0ne.crawler.Model;

import java.sql.Timestamp;

/**
 * Created by bing0ne on 03/08/2017.
 */
public class ExtractResult {
    private String html;
    private String json;
    private String imgPath;
    private String title;
    private Timestamp timestamp;

    public ExtractResult() {
    }

    public ExtractResult(String html, String json, String imgPath, String title) {
        this.html = html;
        this.json = json;
        this.imgPath = imgPath;
        this.title = title;
        this.timestamp = null;
    }

    public ExtractResult(String html, String json, String imgPath, String title, Timestamp timestamp) {
        this.html = html;
        this.json = json;
        this.imgPath = imgPath;
        this.title = title;
        this.timestamp = timestamp;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
