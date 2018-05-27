package com.hts.hometoschool.api;


import java.net.URL;

public  class Config {
    public static final String host="14v974583d.iok.la";
    public static final String port="27523";
    public static final String news="/hts/news/";
    public static final String sendProject="/hts/project/";
    public static final String userinfo="/hts/user/";
    public static String getNews(){
        return "http://"+host+":"+port+news;
    }

    public static String sendProject(){
        return "http://"+host+":"+port+sendProject;
    }

    public static String getHotPro(){
        return "http://"+host+":"+port+sendProject;
    }

    public static String proPass(){
        return "http://"+host+":"+port+sendProject;
    }

    public static String noProPass(){
        return "http://"+host+":"+port+sendProject;
    }

    public static String lastPro(){ return "http://"+host+":"+port+sendProject; }
    public static String checkingPro(){ return "http://"+host+":"+port+sendProject; }
    public static String personNoPassPro(){ return "http://"+host+":"+port+sendProject; }
    public static String personPassPro(){ return "http://"+host+":"+port+sendProject; }

    public static String stuLogin(){return "http://"+host+":"+port+userinfo;}
    public static String teacLogin(){return "http://"+host+":"+port+userinfo;}
}
