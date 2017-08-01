package com.bing0ne.crawler.Model;

/**
 * Created by bing0ne on 31/07/2017.
 */
public class Mysql {
    private String address;
    private int port;
    private String password;

    public Mysql(){
        this.address = "127.0.0.1";
        this.port = 3366;
        this.password = "123456";
    }

    public Mysql(String address, int port, String password) {
        this.address = address;
        this.port = port;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
