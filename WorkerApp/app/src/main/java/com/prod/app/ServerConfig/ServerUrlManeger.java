package com.prod.app.ServerConfig;

public class ServerUrlManeger {

    private String BASE_URL = "https://workertestservershubham.herokuapp.com/";

    public String getServerUrl(UrlPathProvider.UrlPathEnum data) {
        return BASE_URL + UrlPathProvider.getPath(data);
    }

}
