package com.bing0ne.crawler;

import com.bing0ne.crawler.DB.MysqlDBService;
import com.bing0ne.crawler.Model.ExtractResult;
import com.bing0ne.crawler.Model.Site;
import com.bing0ne.crawler.Model.SiteConfig;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.frontier.DocIDServer;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Crawler extends WebCrawler {

    /**
     * 正则匹配指定的后缀文件
     */
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");
    /**
     *关于配置文件信息
     */
    private Site site;
    private SiteConfig siteConfig;

    private List<String> base;
    private final String imgPath;


    private final MysqlDBService mysqlDBService;

    public Crawler(MysqlDBService mysqlDBService,String imgPath){
        this.mysqlDBService = mysqlDBService;
        this.imgPath = imgPath;
    }


    // 重载onstart类, 获得custom data 也就是网站的配置信息
    @Override
    public void onStart(){
        site = (Site) myController.getCustomData();
        siteConfig = site.getConfig();
        this.base = new ArrayList<String>(siteConfig.getBase());
        // 检查储存图片的文件夹是否存在


    }





    /**
     * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
     * 第一个参数referringPage封装了当前爬取的页面信息
     * 第二个参数url封装了当前爬取的页面url信息
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {

        String href = url.getURL().toLowerCase();  // 得到小写的url
        return !FILTERS.matcher(href).matches()   // 正则匹配，过滤掉我们不需要的后缀文件
                && isStartWith(href) && !mysqlDBService.isExist(url.getURL()); // url必须是base规定中的开头，规定站点
    }

    /**
     * 当我们爬到我们需要的页面，这个方法会被调用，我们可以尽情的处理这个页面
     * page参数封装了所有页面信息
     */
    @Override
    public void visit(Page page) {

        String url = page.getWebURL().getURL();
        logger.info("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();


            /**
            * 判断网站类型，如果是文章则抓取，否则返回
             * 由于新闻网站大多数都存在og:type这一个meta属性,为article的时候,为文章.
             */
            Map<String,String> meta = htmlParseData.getMetaTags();
            if(!meta.containsKey("og:type") || !meta.get("og:type").toLowerCase().equals("article")){
                logger.info("The webiste type is not a article.");
                return;
            }

            /**
             * 判断是否启用了amp,如果启用了amp只抓取amp页面
             * 当amp有配置,但是网页不是以amp开头开头则不抓取该页面
             * 使用amp是因为,国外的网站大多数都适配了google的amp页面,该页面的权重较高,网站排名会提高
             */
            Document doc = Jsoup.parse(html);
            if ( siteConfig.getAmp().equals("no") || !doc.getElementsByAttribute("amp").is("html")) {
                logger.info("该界面不是amp标准,跳过");
                return;
            }



            ExtractResult extracted = Extractor.extract(html,site, imgPath);

            logger.info("Text length: " + text.length());
            logger.info("Html length: " + html.length());
            logger.info("Number of outgoing links: " + links.size());



            try {
                mysqlDBService.store(page, extracted);
            } catch (RuntimeException e) {
                logger.error("Storing failed", e);
            }
        }
    }


    /**
     * 判断将被抓取的网址是不是本站，
     * 当url为规定的开头时，
     * 返回true，否则返回false
     */
    private boolean isStartWith(String url){
        for(String theBase : this.base) {
            if(url.startsWith(theBase))
                return true;
        }
        logger.info("This url not belong to ");
        return false;
    }
}
