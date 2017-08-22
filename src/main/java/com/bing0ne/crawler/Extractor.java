package com.bing0ne.crawler;

import com.bing0ne.crawler.Model.ExtractResult;
import com.bing0ne.crawler.Model.Site;
import com.bing0ne.crawler.Model.SiteConfig;
import net.didion.jwnl.data.Exc;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * Created by bing0ne on 02/08/2017.
 */
public class Extractor {

    private static final Whitelist whitelist = Whitelist.basic().removeTags("a","br","b","em","strong","u","i")
            .addTags("h1","h2","h3","h4","h5","div");
    private static final Logger logger = LoggerFactory.getLogger(Extractor.class);

    public static ExtractResult extract(String html, Site site, String imgPath){


        SiteConfig config = site.getConfig();

        Document doc = Jsoup.parse(html);

        // 提取标题
        String title = Extractor.extractTitle(doc,config.getTitle());


        // /找到文章区域
        Elements elements = Extractor.include(doc.getAllElements(),config.getInclude());

        // 提取图片
        // 检查path是否已经创建,如果已经创建则直接进入下一步.
        FileHelper.mkdir(imgPath+"/"+site.getSiteName()+"/");
        imgPath = imgExtractor(doc,config.getImgs(),imgPath+"/"+site.getSiteName()+"/");


        //去除文章杂质，
        String ehtml = Extractor.exclude(elements,config.getExclude()).outerHtml();


        /**
         *利用jsoup的过滤器，对html文本再一次进行过滤，只保留
         *a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol, p, pre, q, small, strike, strong, sub, sup, u, ul
         *这些标签
         **/
        ehtml = Jsoup.clean(ehtml, whitelist);

        // 将净化后的html转为json格式的
        String json = Extractor.html2Json(ehtml);

        return new ExtractResult(ehtml, json, imgPath,title);

    }


    //提取文章的标题
    private static String extractTitle(Document doc, String titleRule) {
        if(doc.select(titleRule).isEmpty()){
            return new String("");
        }
        return doc.select(titleRule).get(0).text().toString();
    }

    // 排除不需要的标签
    private static Elements exclude(Elements elements, String excludeDiv) {


        Elements ele;
        if (excludeDiv.equals(""))
            return  elements;

        // 移除需要移除的标签和图片标签
         elements.select(excludeDiv).remove();

        return elements;
    }

    // 获得指定div内的内容,返回Elements
    private static Elements include(Elements elements, String includes){

        Elements ele;
        if (includes.equals("")) {
            return elements;
        }

        ele = elements.select(includes);

        return ele;

    }


    // 解析后的htlm转化为json
    private static String html2Json(String html){
        return Html2json.getJSON(html);
    }


    private static String imgExtractor(Document doc, String config, String path) {

        StringBuilder filenameBuilder = new StringBuilder();


        //如果图片规则为"none"则不抓取图片
        // url.getpath()
        if(config.toLowerCase().equals("none") || config.equals("") ){
            return new String("");
        }

        Elements ele = doc.select(config);



        //遍历urlList 下载图片
        for (Element el : ele) {

            String sUrl =  el.absUrl("src");

            // 处理没有协议的图片链接
            if(sUrl.equals("")) {
                String aUrl = el.attr("src");
                if(aUrl.startsWith("//")) {
                    aUrl = "http:" + aUrl;
                    sUrl = aUrl;
                }
            }
            if(sUrl.equals("") && !el.absUrl("content").equals("")) {
                sUrl = el.attr("content");
            }

            if(sUrl.equals(("")))
                continue;

            try {
                URL url = new URL(sUrl);

                // 去除url问号后面的内容
                String finalUrl  = url.getPath();

                // 文件保存地址
                String filename = path + FilenameUtils.getName(finalUrl);

                logger.debug("Download Iamge from url ");

                //根据url，下载文件到filename
                Extractor.download(url, filename);

                //将文件地址记录
                filenameBuilder.append("\""+filename+"\",");

            }catch (IOException e){
                logger.error("图片 " + sUrl + "下载失败");
                e.printStackTrace();
            }
        }

        // 删除多余的","
        if(filenameBuilder.length() != 0) {
            filenameBuilder.deleteCharAt(filenameBuilder.length() - 1);
        }

        // 返回地址集合
        return filenameBuilder.toString();
    }


    //下载图片到指定位置
    public static void download(URL url, String filename) throws IOException {
//        // 构造URL
//        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流


        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }


    public static Date extractDate(String date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date result = dateFormat.parse(date);
            return result;
        } catch (ParseException e) {
            logger.error("时间解析失败");
            e.printStackTrace();
        }

        return null;
    }

}
