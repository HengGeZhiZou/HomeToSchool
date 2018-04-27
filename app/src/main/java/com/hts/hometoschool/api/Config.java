package com.hts.hometoschool.api;


import java.net.URL;

public  class Config {
    public static final String host="14v974583d.iok.la";
    public static final String port="31122";
    public static final String news="/hts/news/";

    public static String getNews(){
        return "http://"+host+":"+port+news;
    }
}
