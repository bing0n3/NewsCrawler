package com.bing0ne.crawler.DB;

import com.bing0ne.crawler.Model.ExtractResult;
import edu.uci.ics.crawler4j.crawler.Page;

/**
 * Created by bing0ne on 01/08/2017.
 */
public interface MysqlDBService {
    void store(Page webPage, ExtractResult result);
    boolean isExist(String url);
    void close();
}
