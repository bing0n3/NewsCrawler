package com.bing0ne.crawler;

import com.bing0ne.crawler.DB.MysqlDBServiceImpl;
import com.bing0ne.crawler.Model.Config;
import com.bing0ne.crawler.Model.Mysql;
import com.bing0ne.crawler.Model.Site;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bing0ne on 31/07/2017.
 */
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    // 每次抓取前的延迟 单位 毫秒，防止IP被ban
    private static final int politenessDelay = 500;
    // 爬出的深度
    private static final int maxDepthOfCrawling = 1;


    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "./data";
        FileHelper.mkdir(crawlStorageFolder);
        //每个爬虫的线程数
        int numberOfCrawlers = 7;

        // 读取配置文件
        Config crawlerConfig =  ConfigReader.readConfigFile("./.NewsCrawler.json");
        //  数据库的配置
        Mysql mysqlConfig = crawlerConfig.getMysql();
        // 每个网站的配置
        ArrayList<Site> sites = crawlerConfig.getSites();
        // 存储图片的路径
        String imgPath = crawlerConfig.getImage_save_path();

        // 创建数据库连接池
        MysqlDBServiceImpl mysqlDBService = new MysqlDBServiceImpl(mysqlConfig.getAddress(),mysqlConfig.getUserName()
                ,mysqlConfig.getPassword(),"com.mysql.jdbc.Driver");

        //爬虫配置
        List<CrawlController> controllers = new ArrayList<CrawlController>();
        List<String> siteNames = new ArrayList<String>();
        for(Site site : sites){

            // 添加网站名字到siteName 中， 用于输出log
            siteNames.add(site.getSiteName());

            CrawlConfig crawlConfig = new CrawlConfig();


            crawlConfig.setResumableCrawling(true);

            crawlConfig.setCrawlStorageFolder(crawlStorageFolder + "/" + site.getSiteName());
            crawlConfig.setPolitenessDelay(politenessDelay);
            //设置爬出的深度
            crawlConfig.setMaxDepthOfCrawling(maxDepthOfCrawling);

            PageFetcher pageFetcher = new PageFetcher(crawlConfig);

            //设置robots.txt
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);


            CrawlController controller = new CrawlController(crawlConfig, pageFetcher,robotstxtServer);
            controller.setCustomData(site);

            //为爬虫设置爬取的起点
            for(String seed : site.getConfig().getSeed()){
                controller.addSeed(seed);
            }

            controller.startNonBlocking(new MySqlCrawlerFactory(
                    mysqlDBService ,imgPath
                    ), numberOfCrawlers);
            controllers.add(controller);
        }


        // log 信息 爬虫运行状况
        for(int i = 0; i < controllers.size(); i++){
            controllers.get(i).waitUntilFinish();
            logger.info("Crawler for " + siteNames.get(i) + " is finished!");
        }



    }
}

