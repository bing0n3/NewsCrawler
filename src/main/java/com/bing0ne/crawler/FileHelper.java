package com.bing0ne.crawler;

import java.io.File;

/**
 * Created by bing0ne on 04/08/2017.
 */
public class FileHelper {

    public static void mkdir(File file) {
        File fd = file;
        try {
            if (!fd.exists()) {
                fd.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fd = null;
        }
    }

    public static void mkdir(String path) {
        File fd = null;
        try {
            fd = new File(path);
            if (!fd.exists()) {
                fd.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fd = null;
        }
    }
}
