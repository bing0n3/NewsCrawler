package com.bing0ne.crawler;

import com.bing0ne.crawler.DB.MysqlDBServiceImpl;
import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by bing0ne on 01/08/2017.
 */
public class MySqlCrawlerFactory implements CrawlController.WebCrawlerFactory<Crawler> {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPw;
    private final String imgPath;

    public MySqlCrawlerFactory(String dbUrl, String dbUser, String dbPw, String imgSavePath) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPw = dbPw;
        this.imgPath =  imgSavePath;
    }

    public Crawler newInstance() throws Exception {
        return new Crawler(new MysqlDBServiceImpl(dbUrl,dbUser,dbPw,"org.postgresql.Driver"),imgPath);
    }
}
