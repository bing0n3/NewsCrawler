package com.bing0ne.crawler;

import com.alibaba.fastjson.JSON;
import com.bing0ne.crawler.Model.Config;
import org.apache.poi.openxml4j.opc.internal.FileHelper;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bing0ne on 31/07/2017.
 */


public class ConfigReader {

    public static Config readConfigFile(String filepath){

        File file=new File(filepath);
        Config config = null;

        // 如果配置文件不存在，创建默认的配置文件
        if(!file.exists()|| file.isDirectory()){
            copyDefaultConfig(filepath);
        }
        try {
            String config_string = readFile(filepath);
            /*
            过滤掉换行符，制表符等字符
            fastjson无法解析多行的json
            */
            Pattern Filters = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = Filters.matcher(config_string);
            String dest = m.replaceAll("");
            config = JSON.parseObject(dest,Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }


    // copy default config to ~/.NewsCrawler.json
    private static void copyDefaultConfig(String filepath){
        InputStream input = null;
        OutputStream output = null;
        try {
            input = FileHelper.class.getClassLoader().getResourceAsStream("config.json");
            output = new FileOutputStream(filepath);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("默认配置文件模版不存在");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input.close();
            output.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取文件到String
    private static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        ConfigReader.readToBuffer(sb, filePath);
        return sb.toString();
    }

    private static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }


    // 测试读取功能
    public static void main(String[] args) {
        Config config = readConfigFile("/Users/bing0ne/.NewsCrawler.json");
        System.out.println(config.getMysql().getAddress());
        System.out.println(config.getMysql().getUserName());
        System.out.println(config.getSites().get(0).getConfig().getSeed().get(0));
        System.out.println(config.getSites().get(0).getConfig().getExclude());
    }


}