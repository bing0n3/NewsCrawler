package com.bing0ne.crawler.Model;

/**
 * Created by bing0ne on 31/07/2017.
 */
public class Mysql {
    private String address;
    private String userName;
    private String password;

    public Mysql(){
    }

    public Mysql(String address, String userName, String password) {
        this.address = address;
        this.userName = userName;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
