package com.bing0ne.crawler.DB;

import com.bing0ne.crawler.Model.ExtractResult;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.slf4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by bing0ne on 01/08/2017.
 */
public class MysqlDBServiceImpl implements MysqlDBService {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MysqlDBServiceImpl.class);
    private ComboPooledDataSource comboPooledDataSource;
    private PreparedStatement insertKeyStatement;
    private PreparedStatement selecKeyStatement;

    public MysqlDBServiceImpl(String dbUrl, String dbUser, String dbPw, String driver) throws
            PropertyVetoException, SQLException {
        comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(dbUrl);
        comboPooledDataSource.setUser(dbUser);
        comboPooledDataSource.setPassword(dbPw);

        createDatabase();
    }


    // 创建数据库
    private void createDatabase() throws SQLException {

        comboPooledDataSource.getConnection().createStatement().executeUpdate(
                "CREATE TABLE IF NOT EXISTS news" +
                        " ( " +
                        "  id bigint unsigned NOT NULL AUTO_INCREMENT," +
                        "  url VARCHAR(1024) NOT NULL," +
                        "  title VARCHAR(2048) NOT NULL," +
                        "  html LONGTEXT," +
                        "  parsedHtml TEXT," +
                        "  articleJson TEXT," +
                        "  imagePath TEXT," +
                        "  seen timestamp," +
                        "  primary key (id)," +
                        "  UNIQUE KEY url (url)" +
                        ")");


        insertKeyStatement = comboPooledDataSource.getConnection().prepareStatement(
                "insert into news(url,title,html,parsedHtml,articleJson,imagePath,seen) values " +
                "(?,?,?,?,?,?,?)");

        selecKeyStatement = comboPooledDataSource.getConnection().prepareStatement(
                "SELECT 1 FROM news WHERE url=?; " );

    }


    public void store(Page page, ExtractResult extractResult) {

        if (page.getParseData() instanceof HtmlParseData) {
            try {
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

                insertKeyStatement.setString(1, page.getWebURL().getURL());
                insertKeyStatement.setString(2, extractResult.getTitle());
                insertKeyStatement.setString(3, htmlParseData.getHtml());
                insertKeyStatement.setString(4, extractResult.getHtml());
                insertKeyStatement.setString(5, extractResult.getJson());
                insertKeyStatement.setString(6, extractResult.getImgPath());
                insertKeyStatement.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
                insertKeyStatement.executeUpdate();
            } catch (SQLException e) {
                logger.error("SQL Exception while storing Article for url'{}'", page.getWebURL().getURL(), e);
                throw new RuntimeException(e);
            }
        }
    }


    //判断url是否已经存在在数据库中,方便多次抓取
    public boolean isExist(String url) {
        try {
            selecKeyStatement.setString(1, url);
            ResultSet rs = selecKeyStatement.executeQuery();
            if(!rs.next()) {
                logger.info("数据库中没有该数据");
                return false;
            } else {
                logger.info("数据库中已经存在该链接");
                return true;
            }

        } catch (SQLException e) {
            //logger.error("SQL Exception Url has existed");
            throw new RuntimeException(e);
        }

    }



    public void close() {
        if (comboPooledDataSource != null) {
            comboPooledDataSource.close();
        }
    }




}
