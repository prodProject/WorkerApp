package com.prod.app.ServerConfig;

public class GetUrlMaker {

    public static String getUrlWIthQuery(String url, String value) {
        return url + "?query=" + value;
    }
}
